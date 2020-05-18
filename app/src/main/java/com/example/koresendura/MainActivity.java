package com.example.koresendura;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;

import HPRTAndroidSDK.ZPL.HPRTPrinterHelper;

public class MainActivity extends AppCompatActivity {

    private Context thisCon=null;
    private PublicAction pAct;
    private HPRTPrinterHelper hprtPrinterHelper;
    private String ConnectType="";
    private PendingIntent mPermissionIntent=null;
    private static final String ACTION_USB_PERMISSION = "com.HPRTSDKSample";

    private ArrayAdapter arrPrinterList;
    private String PrinterName="";
    private String PortParam="";

    private UsbManager mUsbManager=null;
    private UsbDevice device=null;


    EditText ipaddress,portnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thisCon=this.getApplicationContext();
        pAct=new PublicAction(thisCon);

        mPermissionIntent = PendingIntent.getBroadcast(thisCon, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        thisCon.registerReceiver(mUsbReceiver, filter);

        String[] barcode=new String[PrinterProperty.Barcode.split(",").length-1];
        if (PrinterProperty.Barcode.contains("QRCODE")) {
            for (int i = 0; i < PrinterProperty.Barcode.split(",").length-1; i++) {
                barcode[i]=PrinterProperty.Barcode.split(",")[i];
            }
        }else {
            barcode=new String[PrinterProperty.Barcode.split(",").length];
            for (int i = 0; i < PrinterProperty.Barcode.split(",").length; i++) {
                barcode[i]=PrinterProperty.Barcode.split(",")[i];
            }

            hprtPrinterHelper = HPRTPrinterHelper.getHPRT(thisCon);
        }
    }

    private BroadcastReceiver mUsbReceiver = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {
            try
            {
                String action = intent.getAction();
                if (ACTION_USB_PERMISSION.equals(action))
                {
                    synchronized (this)
                    {
                        device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                        if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false))
                        {
                            if(hprtPrinterHelper.PortOpen(device)!=0)
                            {
//				        		HPRTPrinter=null;
                                ;Toast.makeText(getApplicationContext(),"Not Connected",Toast.LENGTH_LONG).show();
                                return;
                            }
                            else
                               Toast.makeText(getApplicationContext(),"Connected",Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            return;
                        }
                    }
                }
                if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action))
                {
                    device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (device != null)
                    {
                        hprtPrinterHelper.PortClose();
                    }
                }
            }
            catch (Exception e)
            {
                Log.e("HPRTSDKSample", (new StringBuilder("Activity_Main --> mUsbReceiver ")).append(e.getMessage()).toString());
            }
        }
    };



    public void onClickPrint(View view)
    {

        AutoCompleteTextView labelsize=(AutoCompleteTextView)findViewById(R.id.productname);
        EditText type=(EditText)findViewById(R.id.type1);
        EditText core=(EditText)findViewById(R.id.core1);
        EditText barcode=(EditText)findViewById(R.id.barcode1);
        EditText copy=(EditText)findViewById(R.id.copy1);

        String  inputlabelsize=labelsize.getText().toString();
        String  inputtype=type.getText().toString();
        String  inputcore=core.getText().toString();
        String  inputbarcode=barcode.getText().toString();
        String  inputcopy=copy.getText().toString();

        try
        {
            String inputdata="tomba";
            if(inputdata.length()==0)
            {
                Toast.makeText(thisCon, "no data", Toast.LENGTH_SHORT).show();
                return;
            }else{

                hprtPrinterHelper.start();

                PrnFile prnFile=new PrnFile();
                String prnfile=prnFile.l_100x20(inputlabelsize,inputtype,inputcore,inputbarcode,inputcopy);
                hprtPrinterHelper.printData(prnfile);
                hprtPrinterHelper.end();




            }

        }
        catch (Exception e)
        {
            Log.d("HPRTSDKSample", (new StringBuilder("Activity_1DBarcodes --> onClickPrint ")).append(e.getMessage()).toString());
        }
    }

    public void onClickConnect(View view) {
        ConnectType="USB";
//				HPRTPrinter=new ZPLPrinterHelper(thisCon,arrPrinterList.getItem(spnPrinterList.getSelectedItemPosition()).toString());
        //USB not need call "iniPort"
        mUsbManager = (UsbManager) thisCon.getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();

        boolean HavePrinter=false;
        while(deviceIterator.hasNext())
        {
            device = deviceIterator.next();
            int count = device.getInterfaceCount();
            for (int i = 0; i < count; i++)
            {
                UsbInterface intf = device.getInterface(i);
                if (intf.getInterfaceClass() == 7)
                {
                    HavePrinter=true;
                    mUsbManager.requestPermission(device, mPermissionIntent);
                }
            }
        }
        if(!HavePrinter){
            Toast.makeText(getApplicationContext(),"No printer available",Toast.LENGTH_LONG).show();

        }
    }

    public void onScan(View view){
        Intent myIntent = new Intent(MainActivity.this, Scanner.class);
        MainActivity.this.startActivity(myIntent);

    }

    public void  wifibtn(View view){
        ConnectType="WiFi";
        Intent serverIntent = new Intent(thisCon, Activity_Wifi.class);
        serverIntent.putExtra("PN", PrinterName);
        startActivityForResult(serverIntent, HPRTPrinterHelper.ACTIVITY_CONNECT_WIFI);
        return;
    }


}
