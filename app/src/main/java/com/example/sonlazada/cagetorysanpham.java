package com.example.sonlazada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import unity.Sanpham;

public class cagetorysanpham extends AppCompatActivity {
    RecyclerView recyclerView;
    sanphamAdapter adapter;
    ArrayList<Sanpham> arrsanpham;
    String urlloaisp = "http://192.168.0.100/getloaisanpham.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cagetorysanpham);
        anhxa();
        Intent intent = getIntent();
        String category = intent.getStringExtra("cagetory");



    }



    // get data
    public void getdata(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String masp = object.getString("masp");
                        String tensp = object.getString("tensp");
                        int giasp = object.getInt("giasp");
                        String linkanh = object.getString("linkanhsp");
                        int sl = object.getInt("soluongsp");
                        String mota = object.getString("motasp");
                        arrsanpham.add(new Sanpham(masp,tensp,giasp,linkanh,sl,mota));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(cagetorysanpham.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void anhxa() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleviewcagetory);
        arrsanpham = new ArrayList<>();
    }
}
