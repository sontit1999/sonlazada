package com.example.sonlazada;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sonlazada.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import unity.Sanpham;

public class home extends AppCompatActivity {
    ArrayList<Sanpham> sanphamArrayList;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    sanphamAdapter adapter;
    String urlspkm = "https://sontithaui.000webhostapp.com/getspkm.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        anhxa();
        getdata(urlspkm);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        Actionbar();
        ActionViewliper();
        sukiendrawer();
        adapter.setOnItemClickListener(new sanphamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(home.this,inforsanpham.class);
                intent.putExtra("sanpham",sanphamArrayList.get(position));
                startActivity(intent);
            }
        });
    }
   // get data san pham từ url vào mảng sản phẩm
    public void getdata(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(home.this, response.toString(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(home.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    // bắt sự kiện chọn item trong drawer
    private void sukiendrawer(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here
                switch (menuItem.getItemId())
                {
                        case R.id.menudienthoai:
                            Toast.makeText(home.this, "Bạn chọn điện thoại", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(home.this,cagetorysanpham.class);
                            intent.putExtra("cagetory","DT");
                            startActivity(intent);
                            break;
                        case R.id.menulaptop:
                            Toast.makeText(home.this, "Bạn chọn laptop", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(home.this,cagetorysanpham.class);
                            intent1.putExtra("cagetory","LT");
                            startActivity(intent1);
                            break;
                        case R.id.menutruyen :
                            Toast.makeText(home.this, "Bạn chọn truyện", Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(home.this,truyen.class);
                            startActivity(intent2);
                            break;
                        case R.id.menugaixinh:
                            Toast.makeText(home.this, "Bạn chọn gái xinh", Toast.LENGTH_SHORT).show();
                            Intent intent3 = new Intent(home.this,gaixinh.class);
                            startActivity(intent3);
                            break;

                }
                return true;
            }
        });
    }

    // set up viewlipper
    private void ActionViewliper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://2img.net/h/s25.postimg.cc/917s48lnj/25666103734_e051e6c80a_b.jpg");
        mangquangcao.add("http://media3.onbox.vn:8088/phimonbox/images/20190418/29049c0d62d642799101ae211bb2d1af.jpg");
        mangquangcao.add("https://anh.24h.com.vn/upload/3-2017/images/2017-08-17/1502986335-150296510441633-atita-lao13.jpg");
        mangquangcao.add("https://1.bp.blogspot.com/-UbvXuPlDBic/WOJF_TKOJlI/AAAAAAAAmAs/5-cg_-QP4k8M4j0IpOFk5TxlVnsQdTLWACLcB/s640/17264548_1004270759703007_3983698336473630247_n.jpg");
        mangquangcao.add("https://i.a4vn.com/2017/1/3/anh-girl-xinh-sexy-goi-cam-7efa8d.jpg");
        mangquangcao.add("https://i.a4vn.com/2017/1/3/anh-girl-xinh-sexy-goi-cam-c78c7a.jpg");
        mangquangcao.add("http://img.tophinh.com/img/photo-2018-12/5c1316c906b21/97bf3be651228a93fc39418ca03576bd-resize.jpg");
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
    // setup toolbar
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
        navigationView = (NavigationView) findViewById(R.id.navigationviewmanhinhchinh);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        sanphamArrayList = new ArrayList<>();
        adapter = new sanphamAdapter(this,sanphamArrayList);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuactionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menugiohang:
                Toast.makeText(this, "Bạn chọn giỏ hàng", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(home.this,giohang.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
