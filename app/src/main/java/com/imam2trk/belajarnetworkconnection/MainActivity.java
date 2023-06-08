package com.imam2trk.belajarnetworkconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Views
    ImageView mConStatusIv;
    TextView mConStatusTv;
    Button mConStatusBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link views with xml
        mConStatusIv = findViewById(R.id.conStatusIv);
        mConStatusTv = findViewById(R.id.conStatusTv);
        mConStatusBtn = findViewById(R.id.conStatusBtn);

        //button click to check network status
        mConStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //function call to check network connection status
                checkNetworkConnectionStatus();
            }
        });
    }

    private void checkNetworkConnectionStatus(){
        boolean wifiConnected;
        boolean mobileConnected;
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        @SuppressLint("MissingPermission") NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()){ //connected with either mobile or wifi
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected) { //wifi connected
                mConStatusIv.setImageResource(R.drawable.wifi);
                mConStatusTv.setText("Connected with WiFi");
            } else if (mobileConnected) { //mobile data connected
                mConStatusIv.setImageResource(R.drawable.mobile);
                mConStatusTv.setText("Connected with Mobile Data Connection");
            }
        } else {
            mConStatusIv.setImageResource(R.drawable.none);
            mConStatusTv.setText("No internet connection");
        }
    }
}