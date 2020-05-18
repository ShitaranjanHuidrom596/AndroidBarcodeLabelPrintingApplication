package com.example.koresendura;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;

import HPRTAndroidSDK.ZPL.HPRTPrinterHelper;

public class Box100_150 extends AppCompatActivity {

    private Context thisCon = null;
    private PublicAction pAct;
    private HPRTPrinterHelper hprtPrinterHelper;
    private String ConnectType = "";
    private PendingIntent mPermissionIntent = null;
    private static final String ACTION_USB_PERMISSION = "com.HPRTSDKSample";
    public int countforback = 0;

    TextView labeltype;
    Button backbtn;


    private ArrayAdapter arrPrinterList;
    private String PrinterName = "";
    private String PortParam = "";
    EditText barcodeinput;
    private UsbManager mUsbManager = null;
    private UsbDevice device = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box100_150);
        Intent intent = getIntent();
        labeltype = (TextView) findViewById(R.id.labelsizetxt2);
        labeltype.setText(intent.getStringExtra("labeltype"));
        thisCon = this.getApplicationContext();
        pAct = new PublicAction(thisCon);

        backbtn = (Button) findViewById(R.id.backbtn);
        barcodeinput = (EditText) findViewById(R.id.barcode1);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), selectsize.class);
                Box100_150.this.startActivity(myIntent);
            }
        });


        mPermissionIntent = PendingIntent.getBroadcast(thisCon, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        thisCon.registerReceiver(mUsbReceiver, filter);

        String[] barcode = new String[PrinterProperty.Barcode.split(",").length - 1];
        if (PrinterProperty.Barcode.contains("QRCODE")) {
            for (int i = 0; i < PrinterProperty.Barcode.split(",").length - 1; i++) {
                barcode[i] = PrinterProperty.Barcode.split(",")[i];
            }
        } else {
            barcode = new String[PrinterProperty.Barcode.split(",").length];
            for (int i = 0; i < PrinterProperty.Barcode.split(",").length; i++) {
                barcode[i] = PrinterProperty.Barcode.split(",")[i];
            }

            hprtPrinterHelper = HPRTPrinterHelper.getHPRT(thisCon);
        }
    }

    private BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if (ACTION_USB_PERMISSION.equals(action)) {
                    synchronized (this) {
                        device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                        if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                            if (hprtPrinterHelper.PortOpen(device) != 0) {
//				        		HPRTPrinter=null;
                                ;
                                Toast.makeText(getApplicationContext(), "Not Connected", Toast.LENGTH_LONG).show();
                                return;
                            } else
                                Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();

                        } else {
                            return;
                        }
                    }
                }
                if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                    device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (device != null) {
                        hprtPrinterHelper.PortClose();
                    }
                }
            } catch (Exception e) {
                Log.e("HPRTSDKSample", (new StringBuilder("Activity_Main --> mUsbReceiver ")).append(e.getMessage()).toString());
            }
        }
    };


    public void print(View view) {


        AutoCompleteTextView to = (AutoCompleteTextView) findViewById(R.id.totxt);
        EditText invoice = (EditText) findViewById(R.id.invoicetxt);
        EditText labelsize = (EditText) findViewById(R.id.labelsizetxt);
        EditText quantity = (EditText) findViewById(R.id.quantitytxt);
        EditText address = (EditText) findViewById(R.id.addresstxt);
        EditText copy=(EditText) findViewById(R.id.copytxt);

        String inputlabelsize = labelsize.getText().toString();
        String inputto = to.getText().toString();
        String inputinvoice = invoice.getText().toString();
        String inputquantity = quantity.getText().toString();
        String inputaddress = address.getText().toString();
        String inputcopy = copy.getText().toString();


        try {

            if (inputlabelsize.length() == 0 || inputto.length() == 0 || inputinvoice.length() == 0 || inputquantity.length() == 0 || inputcopy.length() == 0) {
                Toast.makeText(thisCon, "no data", Toast.LENGTH_SHORT).show();
                return;
            } else {

                hprtPrinterHelper.start();

                PrnFile prnFile = new PrnFile();
                if (labeltype.getText().toString().equals("100x150box")) {
                    String prnfile4 = prnFile.boxl100_150(inputto,inputinvoice,inputlabelsize,inputquantity,inputaddress,inputcopy);
                    Toast.makeText(getApplicationContext(), "100x150box", Toast.LENGTH_LONG).show();
                    // Toast.makeText(getApplicationContext(),prnfile,Toast.LENGTH_LONG).show();
                    hprtPrinterHelper.printData(prnfile4);
                    prnfile4 = "";
                    hprtPrinterHelper.end();
                }


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cannot print sticker pleas contact developer", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickConnect(View view) {
        ConnectType = "USB";
//				HPRTPrinter=new ZPLPrinterHelper(thisCon,arrPrinterList.getItem(spnPrinterList.getSelectedItemPosition()).toString());
        //USB not need call "iniPort"
        mUsbManager = (UsbManager) thisCon.getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();

        boolean HavePrinter = false;
        while (deviceIterator.hasNext()) {
            device = deviceIterator.next();
            int count = device.getInterfaceCount();
            for (int i = 0; i < count; i++) {
                UsbInterface intf = device.getInterface(i);
                if (intf.getInterfaceClass() == 7) {
                    HavePrinter = true;
                    mUsbManager.requestPermission(device, mPermissionIntent);
                }
            }
        }
        if (!HavePrinter) {
            Toast.makeText(getApplicationContext(), "No printer available", Toast.LENGTH_LONG).show();

        }
    }

    public void onScan(View view) {
        Intent myIntent = new Intent(getApplicationContext(), Scanner.class);
        Box100_150.this.startActivity(myIntent);

    }

    public void calibrate(View view) {
        PrnFile prnFile = new PrnFile();

        String prn = prnFile.calibrate();

        try {
            hprtPrinterHelper.start();

            hprtPrinterHelper.printData(prn);
            hprtPrinterHelper.end();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cannot calibrate please contact the developer", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        onPause();
        countforback++;
        if (countforback == 1) {
            Toast.makeText(getApplicationContext(), "Press back again to exit application", Toast.LENGTH_LONG).show();
        } else if (countforback >= 2) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    @Override
    protected void onPause() {

        super.onPause();
    }

    public void calibratekores(View view) {
        PrnFile prnFile = new PrnFile();

        String prn = prnFile.calibrate();

        try {
            hprtPrinterHelper.start();

            hprtPrinterHelper.printData(prn);
            hprtPrinterHelper.end();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cannot Print the prn please contact the developer", Toast.LENGTH_LONG).show();
        }

    }
}
