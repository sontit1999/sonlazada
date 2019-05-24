package com.example.sonlazada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import unity.nguoi;

public class gaixinh extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<nguoi> arrayList;
    nguoiadapter adaptergaixinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaixinh);
        initview();
        setuptoolbar();
    }
    // set up toolbar
    private void setuptoolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Gái xinh");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    // set up recycleview
    private void initview() {
        toolbar = (Toolbar) findViewById(R.id.toolbargaixinh) ;
        recyclerView = (RecyclerView) findViewById(R.id.recycleviewgaixinh);
        arrayList = new ArrayList<>();
        arrayList.add(new nguoi("sơn tít","https://2img.net/h/s25.postimg.cc/917s48lnj/25666103734_e051e6c80a_b.jpg"));
        arrayList.add(new nguoi("sơn tít","http://media3.onbox.vn:8088/phimonbox/images/20190418/29049c0d62d642799101ae211bb2d1af.jpg"));
        arrayList.add(new nguoi("sơn tít","https://anh.24h.com.vn/upload/3-2017/images/2017-08-17/1502986335-150296510441633-atita-lao13.jpg"));
        arrayList.add(new nguoi("sơn tít","https://1.bp.blogspot.com/-UbvXuPlDBic/WOJF_TKOJlI/AAAAAAAAmAs/5-cg_-QP4k8M4j0IpOFk5TxlVnsQdTLWACLcB/s640/17264548_1004270759703007_3983698336473630247_n.jpg"));
        arrayList.add(new nguoi("sơn tít","https://i.a4vn.com/2017/1/3/anh-girl-xinh-sexy-goi-cam-7efa8d.jpg"));
        arrayList.add(new nguoi("sơn tít","https://i.a4vn.com/2017/1/3/anh-girl-xinh-sexy-goi-cam-c78c7a.jpg"));
        arrayList.add(new nguoi("sơn tít","http://img.tophinh.com/img/photo-2018-12/5c1316c906b21/97bf3be651228a93fc39418ca03576bd-resize.jpg"));
        adaptergaixinh = new nguoiadapter(arrayList,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptergaixinh);
    }
}
