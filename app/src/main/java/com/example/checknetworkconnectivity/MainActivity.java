package com.example.checknetworkconnectivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.AvailableNetworkInfo;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnNetwork,btnSetting,btnWifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNetwork = (Button) findViewById(R.id.btnnetwork);
        btnSetting = (Button) findViewById(R.id.btnsetting);
        btnWifi = (Button) findViewById(R.id.btnwifi);

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo connectedNetwork = cm.getActiveNetworkInfo();

        if(connectedNetwork != null){
            if(connectedNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                Toast.makeText(getApplicationContext(),connectedNetwork.getTypeName(),Toast.LENGTH_SHORT).show();
            }
            else if(connectedNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(getApplicationContext(),connectedNetwork.getTypeName(),Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getApplicationContext(),"Not Connected",Toast.LENGTH_SHORT).show();
        }

        btnNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = getIntent();
                finish();
                startActivity(it);
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        });

        btnWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                boolean wifiEnabled = wifi.isWifiEnabled();
                wifi.setWifiEnabled(!wifiEnabled);
            }
        });

    }
}
