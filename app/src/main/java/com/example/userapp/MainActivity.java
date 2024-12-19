package com.example.userapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tx=(TextView) findViewById(R.id.txtt);
        callApi();
    }

    private void callApi() {
        String ApiUrl="https://jsonplaceholder.typicode.com/users";
        JsonArrayRequest request=new JsonArrayRequest(
                Request.Method.GET,
                ApiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        StringBuilder result = new StringBuilder();
                        for (int a = 0; a < response.length(); a++) {
                            try {
                                JSONObject ob = response.getJSONObject(a);
                                String getName = ob.getString("name");
                                String getUname = ob.getString("username");
                                String phone = ob.getString("phone");
                                String web = ob.getString("website");
                                result.append("name: ").append(getName).append("\n");
                                result.append("username: ").append(getUname).append("\n");
                                result.append("phone: ").append(phone).append("\n");
                                result.append("web: ").append(phone).append("\n");
                                result.append("\n").append("\n");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        tx.setText(result.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}