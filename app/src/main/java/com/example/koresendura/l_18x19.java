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

public class l_18x19 extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_18x19);
        thisCon=this.getApplicationContext();
        pAct=new PublicAction(thisCon);


        mPermissionIntent = PendingIntent.getBroadcast(thisCon, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        thisCon.registerReceiver(mUsbReceiver, filter);

            hprtPrinterHelper = HPRTPrinterHelper.getHPRT(thisCon);
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
                                ;
                                Toast.makeText(getApplicationContext(), "Not Connected", Toast.LENGTH_LONG).show();
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
        AutoCompleteTextView productname=(AutoCompleteTextView)findViewById(R.id.productnamelbl);
        EditText companyname=(EditText)findViewById(R.id.companynamelbl);
        EditText address=(EditText)findViewById(R.id.addresslbl);
        EditText tollfree=(EditText)findViewById(R.id.tollfreelbl);
        EditText mailid=(EditText)findViewById(R.id.maillbl);
        EditText distributor=(EditText)findViewById(R.id.distributorlbl);
        EditText saleon=(EditText)findViewById(R.id.salelbl);
        EditText website=(EditText)findViewById(R.id.websitelbl);
        EditText copy=(EditText)findViewById(R.id.copylbl);

        String inputproductname =productname.getText().toString();
        String inputcompanyname = companyname.getText().toString();
        String inputaddress = address.getText().toString();
        String inputtollfree = tollfree.getText().toString();
        String inputmailid = mailid.getText().toString();
        String inputdistributor = distributor.getText().toString();
        String inputsaleon =saleon.getText().toString();
        String inputwebsite = website.getText().toString();
        String inputcopy = copy.getText().toString();


        try
        {

            if(inputproductname.length()==0||inputcompanyname.length()==0||inputaddress.length()==0||inputtollfree.length()==0||inputmailid.length()==0||
            inputdistributor.length()==0||inputsaleon.length()==0||inputwebsite.length()==0||inputcopy.length()==0)
            {
                Toast.makeText(thisCon, "no data", Toast.LENGTH_SHORT).show();
                return;
            }else{

                hprtPrinterHelper.start();

                PrnFile prnFile=new PrnFile();
                String prnfile=prnFile.l_18x19(inputproductname,inputcompanyname,inputaddress,inputtollfree,inputmailid,
                                               inputdistributor,inputsaleon,inputwebsite,inputcopy);

                hprtPrinterHelper.printData(prnfile);
                hprtPrinterHelper.end();

            }

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Please insert all the data to print sticker",Toast.LENGTH_LONG).show();
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
        Intent myIntent = new Intent(l_18x19.this, Scanner.class);
        l_18x19.this.startActivity(myIntent);

    }

    public void calibrate(View view) {
        PrnFile prnFile=new PrnFile();

        String prn=prnFile.calibrate();

        try{
            hprtPrinterHelper.start();

            hprtPrinterHelper.printData(prn);
            hprtPrinterHelper.end();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Cannot Print the prn please contact the developer",Toast.LENGTH_LONG).show();
        }

    }
}
