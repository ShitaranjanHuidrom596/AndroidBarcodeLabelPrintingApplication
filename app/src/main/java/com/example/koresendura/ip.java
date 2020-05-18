package com.example.koresendura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import HPRTAndroidSDK.ZPL.HPRTPrinterHelper;

public class ip extends AppCompatActivity {
    private Context thisCon=null;
    private HPRTPrinterHelper hPRTPrinter;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        thisCon=this.getApplicationContext();
        hPRTPrinter = HPRTPrinterHelper.getHPRT(thisCon);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Intent intent = new Intent();
                intent.putExtra("is_connected", "OK");
                setResult(HPRTPrinterHelper.ACTIVITY_CONNECT_WIFI, intent);
                finish();
            }
        };
    }

    public void onClickConnect(View view)
    {
        if (!checkClick.isClickEvent()) return;

        try
        {
            if(hPRTPrinter!=null)
            {
                hPRTPrinter.PortClose();
            }

            final String strIP="192.168.0.35";
            final String strPort="9100";
            if(strIP.length()==0)
            {
                Toast.makeText(thisCon, "please insert ip", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        if(hPRTPrinter.PortOpen("WiFi,"+strIP+","+strPort)!=0)
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        else
                        {
                            Message message = new Message();
                            handler.sendMessage(message);
                        }
                    } catch (Exception e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(getApplicationContext(),"Error2",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }.start();

        }
        catch (Exception e)
        {
            Log.d("HPRTSDKSample", (new StringBuilder("Activity_Wifi --> onClickConnect ")).append(e.getMessage()).toString());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),"Error3",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
