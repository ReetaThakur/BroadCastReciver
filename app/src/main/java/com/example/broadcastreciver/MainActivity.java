package com.example.broadcastreciver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView nameuser;
    private TextView ageuser;
    private Button userclick;
    private LocalBroadcastManager localBroadcastManager;
    private AirplaneModeReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameuser=findViewById(R.id.name);
        ageuser=findViewById(R.id.age);
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        userclick=findViewById(R.id.btnclick);
        registerLocalReceiver();
        userclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.broadcastreceiver.broadcast");
                intent.putExtra("name","Reeta Thkaur");
                intent.putExtra("age",26);
                localBroadcastManager.sendBroadcast(intent);

            }
        });
    }
    private void registerLocalReceiver(){
        localReceiver=new AirplaneModeReceiver();
        IntentFilter intentFilter=new IntentFilter("com.broadcastreceiver.broadcast");
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    private class AirplaneModeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null){
                String name=intent.getStringExtra("name");
                int age=intent.getIntExtra("age",26);
                nameuser.setText(name);
                ageuser.setText(age+"");
            }

        }
    }
}