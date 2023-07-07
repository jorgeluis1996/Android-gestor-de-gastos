package com.jorge.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.jorge.examen.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler(Looper.getMainLooper()).postDelayed(()->{
            mostrarMain();
        },0);
    }

    private void mostrarMain() {
        SharedPreferences sharedPreferences = getSharedPreferences("datosSesion", Context.MODE_PRIVATE);
        String datos = sharedPreferences.getString("datosUsuario","nn");
        if(datos.equals("nn")){
            startActivity(new Intent(this, LoginActivity.class));
        }
        else{
            startActivity(new Intent(this, CajaActivity.class));
        }
        finish();
    }
}