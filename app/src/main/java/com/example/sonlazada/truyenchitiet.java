package com.example.sonlazada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class truyenchitiet extends AppCompatActivity {
    String url = "http://sontithaui.000webhostapp.com/getdetailtruyen.php";
    TextView txtdetail;
    String linktruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyenchitiet);
        Intent intent = getIntent();
        linktruyen = intent.getStringExtra("linktruyen");
        Toast.makeText(this, linktruyen, Toast.LENGTH_SHORT).show();
        anhxa();
        gettruyen();
    }

    private void anhxa() {
       txtdetail = (TextView) findViewById(R.id.textviewchitiet);
    }
    private void gettruyen(){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String chitiet = object.getString("content");
                        txtdetail.setText(chitiet);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(truyenchitiet.this, "Lỗi gì đấy.Chưa request dcc", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
