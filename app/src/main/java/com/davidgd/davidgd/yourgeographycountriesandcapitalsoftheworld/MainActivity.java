package com.davidgd.davidgd.yourgeographycountriesandcapitalsoftheworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {


    //codigo de anuncio
    private AdView mAdView;

    MediaPlayer mp;

    Button bjugar, btnInformacion;

    //Evita el doble clic
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Quita las notificaciones, no mover de lugar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //codigo del anuncio
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //Id de los botones
        bjugar = (Button) findViewById(R.id.bjugar);
        btnInformacion = (Button) findViewById(R.id.botonInformacion);

        //Sonido, nunca poner mayusculas en el nombre del sonido
        mp = MediaPlayer.create(this, R.raw.clic);

        //Eventos de click
        bjugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Evita el doble clic
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return; }
                mLastClickTime = SystemClock.elapsedRealtime();

                //Activa el sonido
                mp.start();
                // iniciamos el cambio de actividad
                Intent i = new Intent(MainActivity.this, juego.class);
                startActivity(i);

            }
        });


        btnInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Evita el doble clic
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return; }
                mLastClickTime = SystemClock.elapsedRealtime();

                //Activa el sonido
                mp.start();
                // iniciamos el cambio de actividad
                Intent i = new Intent(MainActivity.this, informacion.class);
                startActivity(i);
            }
        });


    }
}