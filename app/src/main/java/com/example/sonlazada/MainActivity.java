package com.example.sonlazada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // sơn tít
    EditText edtUsername,edtPassword;
    Button btnDangnhap;
    TextView txtDangki;
    String urllogin = "http://192.168.0.100/login.php" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        txtDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,home.class));
            }
        });
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim() ;
                String password = edtPassword.getText().toString().trim() ;
                if(username.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Ko dc bỏ trống nhá!", Toast.LENGTH_SHORT).show();
                }else if(edtUsername.length() <6 || edtPassword.length() < 6)
                {
                    Toast.makeText(MainActivity.this, "Tài khoản và mật khẩu ít nhất 6 kí tự", Toast.LENGTH_SHORT).show();
                }
                else{
                    Dangnhap(username,password);
                }
            }
        });
    }

    private void anhxa() {
        edtUsername = (EditText) findViewById(R.id.edittextUsername);
        edtPassword = (EditText) findViewById(R.id.edittextPassword);
        btnDangnhap = (Button) findViewById(R.id.buttonDangnhap);
        txtDangki  = (TextView) findViewById(R.id.textviewDangki);
    }
    private void Dangnhap(final String username, final String password)
    {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urllogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().equals("success"))
                {
                    startActivity(new Intent(MainActivity.this,home.class));
                }else{
                    Toast.makeText(MainActivity.this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi gì đấy ?", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("username",username);
                param.put("password",password);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}
