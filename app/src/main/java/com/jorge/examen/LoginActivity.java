package com.jorge.examen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jorge.examen.databinding.ActivityLoginBinding;

import com.jorge.examen.datos.ServicioWeb;


import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding binding;

int numeroIntentos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {



        EditText etUser = findViewById(R.id.etUser);
        EditText etPassword = findViewById(R.id.etPassword);
        String user = etUser.getText().toString();
        String password = etPassword.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = ServicioWeb.rutaServicio + "iniciarsesion.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,


                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DATOS",response);
                        verficarLogin(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("DATOSERROR",error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("usuario", user);
                map.put("clave", password);
                numeroIntentos+=1;
                return map;
            }
        };
        queue.add(stringRequest);

        if (numeroIntentos ==2){
            this.finish();
            Toast.makeText(this, "Ha superado el limite de intentos", Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(this, HomeeActivity.class));
        //finish();

    }
    private void verficarLogin(String response) {
        switch (response){
            case "-1":
                Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                break;
            case "-2":
                Toast.makeText(this, "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, CajaActivity.class));
                verificarGuardarSesion(response);
                finish();

        }
    }

    private void verificarGuardarSesion(String response) {
        if(binding.chkGuardarSesion.isChecked()){
            SharedPreferences sharedPreferences = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("datosUsuario", response);
            editor.apply();
        }
    }



}