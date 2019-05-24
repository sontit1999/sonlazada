package com.example.sonlazada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import unity.Sanpham;

public class inforsanpham extends AppCompatActivity {
    ImageView imgback;
    Toolbar toolbar;
    TextView txttensp,txtgiasp,txtgtsp;
    ImageView imgsanpham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inforsanpham);
        Intent intent = getIntent();
        Sanpham sanpham = (Sanpham) intent.getSerializableExtra("sanpham");
        Toast.makeText(this, sanpham.getTensp(), Toast.LENGTH_SHORT).show();
        anhxa();
        setup(sanpham);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    // set up data
    private void setup(Sanpham a) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgiasp.setText(decimalFormat.format(a.getGiasp())+"$");
        txttensp.setText(a.getTensp());
        txtgtsp.setText(a.getMota());
        Picasso.get().load(a.getLinkanhsp()).into(imgsanpham);
    }

    private void anhxa() {
        imgback = (ImageView) findViewById(R.id.imageviewback);
        txtgiasp = (TextView) findViewById(R.id.textviewgiasp);
        txttensp = (TextView) findViewById(R.id.textviewtensp);
        txtgtsp = (TextView) findViewById(R.id.textviewgioithieusp);
        imgsanpham = (ImageView) findViewById(R.id.imaviewsanpham);

    }
    private void setuptoolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Gái xinh");
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(inforsanpham.this, "haha", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
