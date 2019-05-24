package com.example.sonlazada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

import unity.Truyen;


public class truyen extends AppCompatActivity {
    ArrayList<String> arr,linktruyen;
    ArrayAdapter adapter;
    ListView lvtruyen;
    Toolbar toolbar;
    String urltruyen = "http://sontithaui.000webhostapp.com/gettruyenxxx.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen);
        anhxa();
        setuptoolbar();
        lvtruyen.setAdapter(adapter);
        lvtruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Intent intent = new Intent(truyen.this,truyenchitiet.class);
                  intent.putExtra("linktruyen",linktruyen.get(position));
                  startActivity(intent);
            }
        });
    }

    // ánh xạ
    private void anhxa()
    {
        linktruyen = new ArrayList<>();
        arr = new ArrayList<>();
        getdata();
        lvtruyen = (ListView) findViewById(R.id.listviewtruyen);
        adapter = new ArrayAdapter(truyen.this,android.R.layout.simple_list_item_1,arr);
        toolbar = (Toolbar) findViewById(R.id.toolbartruyen);
    }
    // setup toolbar
    private void setuptoolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
   // getdata into mảng
    private void getdata()
    {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urltruyen, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String ten = object.getString("tieude");
                        String link = object.getString("link");
                        linktruyen.add(link);
                        arr.add(ten);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                adapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(truyen.this, "Lỗi gì đấy.Chưa request dcc", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
