package com.example.sonlazada;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        anhxa();
        Actionbar();
        ActionViewliper();
    }

    private void ActionViewliper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://2img.net/h/s25.postimg.cc/917s48lnj/25666103734_e051e6c80a_b.jpg");
        mangquangcao.add("http://media3.onbox.vn:8088/phimonbox/images/20190418/29049c0d62d642799101ae211bb2d1af.jpg");
        mangquangcao.add("https://anh.24h.com.vn/upload/3-2017/images/2017-08-17/1502986335-150296510441633-atita-lao13.jpg");
        mangquangcao.add("https://1.bp.blogspot.com/-UbvXuPlDBic/WOJF_TKOJlI/AAAAAAAAmAs/5-cg_-QP4k8M4j0IpOFk5TxlVnsQdTLWACLcB/s640/17264548_1004270759703007_3983698336473630247_n.jpg");
        for(int i=0;i<mangquangcao.size();i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        Animation animationslidein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animationslideout = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animationslidein);
        viewFlipper.setOutAnimation(animationslideout);
    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.icon2);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewliper);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewSanphamKm);
        navigationView = (NavigationView) findViewById(R.id.navigationviewmanhinhchinh);
        listView = (ListView) findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
    }
}
