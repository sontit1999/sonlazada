package com.example.sonlazada;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import unity.Sanpham;

public class cagetorysanpham extends AppCompatActivity {
    sanphamAdapter adapter;
    String loaisp;
    RecyclerView recyclerViewcagetogy;
    Toolbar toolbar;
    ArrayList<Sanpham> sanphamArrayList;
    String urlloaisp = "http://sontithaui.000webhostapp.com/getdt.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cagetorysanpham);
        Intent intent = getIntent();
        loaisp = intent.getStringExtra("cagetory");
        if(loaisp.equals("LT"))
        {
            urlloaisp = "http://sontithaui.000webhostapp.com/getlt.php";
        }
        setuprecyclerview();
        setuptoolbar();
        adapter.setOnItemClickListener(new sanphamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(cagetorysanpham.this,inforsanpham.class);
                intent.putExtra("sanpham",sanphamArrayList.get(position));
                startActivity(intent);
            }
        });
    }


    // set up toolbar

    public void setuptoolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.iconback1);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   finish();
            }
        });
    }

    // get data
    private void getdata()
    {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlloaisp, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(cagetorysanpham.this, response.toString(), Toast.LENGTH_SHORT).show();
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String tensp = object.getString("tensp");
                        String masp = object.getString("masp");
                        int giasp = object.getInt("giasp");
                        String linkanh = object.getString("linkanhsp");
                        int sl = object.getInt("soluongsp");
                        String mota = object.getString("motasp");
                        sanphamArrayList.add(new Sanpham(masp,tensp,giasp,linkanh,sl,mota));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
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
    private void setuprecyclerview()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbarcagetory);
        recyclerViewcagetogy = (RecyclerView) findViewById(R.id.recycleviewcagetogy);
        sanphamArrayList = new ArrayList<>();
        adapter = new sanphamAdapter(cagetorysanpham.this,sanphamArrayList);
        getdata();
        recyclerViewcagetogy.setHasFixedSize(true);
        recyclerViewcagetogy.setLayoutManager(new GridLayoutManager(this,2));
        recyclerViewcagetogy.setAdapter(adapter);
        /*
        sanphamArrayList.add(new Sanpham("SP01","Tabelt",9000000,"https://cdn.tgdd.vn/Products/Images/522/163645/ipad-6th-wifi-32gb-1-400x460.png",15,"Đẹp"));
        sanphamArrayList.add(new Sanpham("SP01","Tabelt",9000000,"https://cdn.tgdd.vn/Products/Images/522/163645/ipad-6th-wifi-32gb-1-400x460.png",15,"Đẹp"));
        sanphamArrayList.add(new Sanpham("SP01","Tabelt",9000000,"https://cdn.tgdd.vn/Products/Images/522/163645/ipad-6th-wifi-32gb-1-400x460.png",15,"Đẹp"));
        sanphamArrayList.add(new Sanpham("SP01","Tabelt",9000000,"https://cdn.tgdd.vn/Products/Images/522/163645/ipad-6th-wifi-32gb-1-400x460.png",15,"Đẹp"));
        sanphamArrayList.add(new Sanpham("SP01","Tabelt",9000000,"https://cdn.tgdd.vn/Products/Images/522/163645/ipad-6th-wifi-32gb-1-400x460.png",15,"Đẹp"));
        sanphamArrayList.add(new Sanpham("SP01","Tabelt",9000000,"https://cdn.tgdd.vn/Products/Images/522/163645/ipad-6th-wifi-32gb-1-400x460.png",15,"Đẹp"));
        sanphamArrayList.add(new Sanpham("SP01","Tabelt",9000000,"https://cdn.tgdd.vn/Products/Images/522/163645/ipad-6th-wifi-32gb-1-400x460.png",15,"Đẹp"));
        sanphamArrayList.add(new Sanpham("SP01","Tabelt",9000000,"https://cdn.tgdd.vn/Products/Images/522/163645/ipad-6th-wifi-32gb-1-400x460.png",15,"Đẹp"));
        sanphamArrayList.add(new Sanpham("SP01","Tabelt",9000000,"https://cdn.tgdd.vn/Products/Images/522/163645/ipad-6th-wifi-32gb-1-400x460.png",15,"Đẹp"));
        Toast.makeText(this, sanphamArrayList.size() + "", Toast.LENGTH_SHORT).show();
        */

    }
}
