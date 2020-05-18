package com.example.koresendura;

public class PrnFile {



    public String l_18x19(String productname, String companyname, String address, String tollfree, String mail,
                         String distributor, String sale, String website, String copy) {

        String prn = "";

        String inputproductname = productname;
        String inputcompanyname = companyname;
        String inputaddress = address;
        String inputtollfree = tollfree;
        String inputmailid = mail;
        String inputdistributor = distributor;
        String inputsaleon = sale;
        String inputwebsite = website;
        String inputcopy = copy;

        prn = prn + "<xpml><page quantity='0' pitch='19.1 mm'></xpml>SIZE 18 mm, 19.1 mm" + "\n";
        prn = prn + "SPEED 2" + "\n";
        prn = prn + "DENSITY 3" + "\n";
        prn = prn + "DIRECTION 0,0" + "\n";
        prn = prn + "REFERENCE 0,0" + "\n";
        prn = prn + "OFFSET 0 mm" + "\n";
        prn = prn + "SET PEEL OFF" + "\n";
        prn = prn + "SET CUTTER OFF" + "\n";
        prn = prn + "SET PARTIAL_CUTTER OFF" + "\n";
        prn = prn + "<xpml></page></xpml><xpml><page quantity='" + inputcopy + "' pitch='19.1 mm'></xpml>SET TEAR ON" + "\n";
        prn = prn + "CLS" + "\n";
        prn = prn + "QRCODE 198,195,L,3,A,180,M2,S7," + "\"" + "Product Name: " + inputproductname + "\n";
        prn = prn + "Company Name:" + inputcompanyname + "\n";
        prn = prn + "Address : " + inputaddress + "\n";
        prn = prn + "Toll Free no : " + inputtollfree + "\n";
        prn = prn + "Email : " + inputmailid + "\n";
        prn = prn + "Distributor : " + inputdistributor + "\n";
        prn = prn + "Sale On : " + inputsaleon + "\n";
        prn = prn + "For Product Details ,Please log on to : " + inputwebsite + "\n";
        prn = prn + "\"" + "\n";
        prn = prn + "PRINT 1," + inputcopy + "\n";
        prn = prn + "<xpml></page></xpml><xpml><end/></xpml>" + "\n";

        return prn;
    }


    public String l_100x20(String labelsize, String type, String core, String barcode, String copy) {

        String prn = "";

        String  inputlabelsize=labelsize;
        String  inputtype=type;
        String  inputcore=core;
        String  inputbarcode=barcode;
        String  inputcopy=copy;

        String datearr[]=barcode.split("/");
        String date=datearr[0];
        String month=datearr[1];
        String year=datearr[2];


        prn=prn+"<xpml><page quantity='0' pitch='20.0 mm'></xpml>^XA"+"\n";
        prn=prn+"^SZ2^JMA"+"\n";
        prn=prn+"^MCY^PMN"+"\n";
        prn=prn+"^PW799"+"\n";
        prn=prn+"^JZY"+"\n";
        prn=prn+"^LH0,0^LRN"+"\n";
        prn=prn+"^XZ"+"\n";
        prn=prn+"<xpml></page></xpml><xpml><page quantity='"+copy+"' pitch='20.0 mm'></xpml>^XA"+"\n";
        prn=prn+"^FT31,91"+"\n";
        prn=prn+"^CI0"+"\n";

        prn=prn+"^A0N,20,27^FD"+core+"^FS"+"\n";
        prn=prn+"^FT297,91"+"\n";
        prn=prn+"^A0N,20,27^FD"+core+"^FS"+"\n";
        prn=prn+"^FT564,91"+"\n";
        prn=prn+"^A0N,20,27^FD"+core+"^FS"+"\n";
        prn=prn+"^FT16,28"+"\n";

        prn=prn+"^A0N,17,23^FDSIZE :^FS"+"\n";
        prn=prn+"^FT282,28"+"\n";
        prn=prn+"^A0N,17,23^FDSIZE :^FS"+"\n";
        prn=prn+"^FT549,28"+"\n";
        prn=prn+"^A0N,17,23^FDSIZE :^FS"+"\n";
        prn=prn+"^FT16,59"+"\n";


        prn=prn+"^A0N,17,23^FDTYPE : ^FS"+"\n";
        prn=prn+"^FT282,59"+"\n";
        prn=prn+"^A0N,17,23^FDTYPE : ^FS"+"\n";;
        prn=prn+"^FT549,59"+"\n";
        prn=prn+"^A0N,17,23^FDTYPE : ^FS"+"\n";
        prn=prn+"^FT71,28"+"\n";

        prn=prn+"^A0N,17,23^FD"+inputlabelsize+"^FS"+"\n";
        prn=prn+"^FT337,28"+"\n";
        prn=prn+"^A0N,17,23^FD"+inputlabelsize+"^FS"+"\n";
        prn=prn+"^FT604,28"+"\n";
        prn=prn+"^A0N,17,23^FD"+inputlabelsize+"^FS"+"\n";
        prn=prn+"^FT80,59"+"\n";

        prn=prn+"^A0N,17,23^FD"+inputtype+"^FS"+"\n";
        prn=prn+"^FT346,59"+"\n";
        prn=prn+"^A0N,17,23^FD"+inputtype+"^FS"+"\n";
        prn=prn+"^FT613,59"+"\n";
        prn=prn+"^A0N,17,23^FD"+inputtype+"^FS"+"\n";
        prn=prn+"^FO40,102"+"\n";


        prn=prn+"^BY1,3.0^B3N,N,39,N,N^FD"+inputbarcode+"^FS"+"\n";
        prn=prn+"^FO306,102"+"\n";
        prn=prn+"^B3N,N,39,N,N^FD"+inputbarcode+"^FS"+"\n";
        prn=prn+"^FO573,102"+"\n";
        prn=prn+"^B3N,N,39,N,N^FD"+inputbarcode+"^FS"+"\n";
        prn=prn+"^PQ"+copy+",0,"+copy+",Y"+"\n";

        prn=prn+"^XZ"+"\n";
        prn=prn+"<xpml></page></xpml><xpml><end/></xpml>";


        return prn;
    }

    public String l_75x50(String labelsize, String type, String core, String barcode, String copy) {

        String prn = "";

        String  inputlabelsize=labelsize;
        String  inputtype=type;
        String  inputcore=core;
        String  inputbarcode=barcode;
        String  inputcopy=copy;

        String datearr[]=barcode.split("/");
        String date=datearr[0];
        String month=datearr[1];
        String year=datearr[2];

        prn=prn+"<xpml><page quantity='0' pitch='50.0 mm'></xpml>^XA"+"\n";
        prn=prn+"^SZ2^JMA"+"\n";
        prn=prn+"^MCY^PMN"+"\n";
        prn=prn+"^PW600"+"\n";
        prn=prn+"^JZY"+"\n";
        prn=prn+"^LH0,0^LRN"+"\n";
        prn=prn+"^XZ"+"\n";
        prn=prn+"<xpml></page></xpml><xpml><page quantity='"+copy+"' pitch='50.0 mm'></xpml>^XA"+"\n";
        prn=prn+"^FT17,43"+"\n";
        prn=prn+"^CI0"+"\n";

        prn=prn+"^A0N,20,27^FDMFD. BY:^FS"+"\n";
        prn=prn+"^FT111,87"+"\n";
        prn=prn+"^A0N,25,34^FD177,UGF AC-2 WARD NO.2^FS"+"\n";
        prn=prn+"^FT230,113"+"\n";
        prn=prn+"^A0N,25,34^FDMEHRAULI^FS"+"\n";
        prn=prn+"^FT149,139"+"\n";

        prn=prn+"^A0N,25,34^FDNEW DELHI - 110030^FS"+"\n";
        prn=prn+"^FT17,182"+"\n";
        prn=prn+"^A0N,20,27^FDSIZE : ^FS"+"\n";
        prn=prn+"^FT362,179"+"\n";


        prn=prn+"^A0N,20,31^FD"+inputcore+"^FS"+"\n";
        prn=prn+"^FT17,302"+"\n";
        prn=prn+"^A0N,20,27^FDPHONE NO. 9718590631,9560802187,011-25340329^FS"+"\n";;
        prn=prn+"^FT20,335"+"\n";
        prn=prn+"^A0N,20,27^FDEMAIL: sales@ucbarinfotech.com^FS"+"\n";
        prn=prn+"^FT135,367"+"\n";

        prn=prn+"^A0N,20,27^FDCustomer care no.9891060187^FS"+"\n";
        prn=prn+"^FT17,261"+"\n";
        prn=prn+"^A0N,20,31^FDTYPE :^FS"+"\n";
        prn=prn+"^FO341,201"+"\n";

        prn=prn+"^BY3^BCN,41,N,N^FD>;"+inputbarcode+"^FS"+"\n";
        prn=prn+"^FT407,262"+"\n";
        prn=prn+"^A0N,20,27^FD"+inputbarcode+"^FS"+"\n";
        prn=prn+"^FT91,182"+"\n";

        prn=prn+"^A0N,20,27^FD"+inputlabelsize+"^FS"+"\n";
        prn=prn+"^FT100,261"+"\n";
        prn=prn+"^A0N,20,27^FD"+inputtype+"^FS"+"\n";
        prn=prn+"^FO149,16"+"\n";
        prn=prn+"^GB385,32,32^FS"+"\n";
        prn=prn+"^FT149,44"+"\n";
        prn=prn+"^A0N,31,32^FR^FDUC BAR INFTOTECH PVT. LTD.^FS"+"\n";
        prn=prn+"^PQ"+copy+",0,"+copy+",Y"+"\n";
        prn=prn+"^XZ"+"\n";
        prn=prn+"<xpml></page></xpml><xpml><end/></xpml>";


        return prn;
    }


    public String boxl_75x50(String labelsize, String type, String core, String quantity, String copy) {

        String prn = "";

        String  inputlabelsize=labelsize;
        String  inputtype=type;
        String  inputcore=core;
        String  inputquantity=quantity;
        String  inputcopy=copy;



        prn=prn+"<xpml><page quantity='0' pitch='50.0 mm'></xpml>^XA"+"\n";
        prn=prn+"^SZ2^JMA"+"\n";
        prn=prn+"^MCY^PMN"+"\n";
        prn=prn+"^PW600"+"\n";
        prn=prn+"^JZY"+"\n";
        prn=prn+"^LH0,0^LRN"+"\n";
        prn=prn+"^XZ"+"\n";
        prn=prn+"<xpml></page></xpml><xpml><page quantity='"+inputcopy+"' pitch='50.0 mm'></xpml>^XA"+"\n";
        prn=prn+"^FT72,62"+"\n";
        prn=prn+"^CI0"+"\n";

        prn=prn+"^A0N,28,38^FDSIZE:^FS"+"\n";
        prn=prn+"^FT72,150"+"\n";
        prn=prn+"^A0N,28,38^FDTYPE:^FS"+"\n";
        prn=prn+"^FT72,238"+"\n";
        prn=prn+"^A0N,28,38^FDCORE:^FS"+"\n";
        prn=prn+"^FT72,329"+"\n";

        prn=prn+"^A0N,28,38^FDROLL QTY:^FS"+"\n";
        prn=prn+"^FT192,60"+"\n";
        prn=prn+"^A0N,28,38^FD"+inputlabelsize+"^FS"+"\n";
        prn=prn+"^FT192,143"+"\n";


        prn=prn+"^A0N,28,38^FD"+inputtype+"^FS"+"\n";
        prn=prn+"^FT192,236"+"\n";
        prn=prn+"^A0N,28,38^FD"+inputcore+"^FS"+"\n";;
        prn=prn+"^FT248,329"+"\n";
        prn=prn+"^A0N,28,38^FD"+inputquantity+"^FS"+"\n";

        prn=prn+"^PQ"+inputcopy+",0,"+inputcopy+",Y"+"\n";
        prn=prn+"^XZ"+"\n";
        prn=prn+"<xpml></page></xpml><xpml><end/></xpml>";


        return prn;
    }

    public String boxl100_150(String to, String invoice, String labelsizes, String quantity, String address,String copy) {

        String prn = "";

        String  inputto=to;
        String  inputinvoice=invoice;
        String  inputlabelsize=labelsizes;
        String  inputquantity=quantity;
        String  inputaddress=address;
        String  inputcopy=copy;



        prn=prn+"<xpml><page quantity='0' pitch='150.0 mm'></xpml>^XA"+"\n";
        prn=prn+"^SZ2^JMA"+"\n";
        prn=prn+"^MCY^PMN"+"\n";
        prn=prn+"^PW799"+"\n";
        prn=prn+"^JZY"+"\n";
        prn=prn+"^LH0,0^LRN"+"\n";
        prn=prn+"^XZ"+"\n";
        prn=prn+"<xpml></page></xpml><xpml><page quantity='"+inputcopy+"' pitch='50.0 mm'></xpml>^XA"+"\n";
        prn=prn+"^FT32,88"+"\n";
        prn=prn+"^CI0"+"\n";

        prn=prn+"^A0N,56,76^FD"+"TO,"+"^FS"+"\n";
        prn=prn+"^FT151,500"+"\n";
        prn=prn+"^A0N,70,95^FDINVOICE NO:^FS"+"\n";
        prn=prn+"^FT88,726"+"\n";
        prn=prn+"^A0N,70,95^FD"+inputlabelsize+"^FS"+"\n";
        prn=prn+"^FT195,885"+"\n";

        prn=prn+"^A0N,70,95^FD"+inputquantity+"^FS"+"\n";
        prn=prn+"^FT439,885"+"\n";
        prn=prn+"^A0N,70,95^"+"FDBOX"+"^FS"+"\n";
        prn=prn+"^FT105,603"+"\n";


        prn=prn+"^A0N,70,95^FD"+inputinvoice+"^FS"+"\n";
        prn=prn+"^FT177,1008"+"\n";
        prn=prn+"^A0N,85,115^FD"+inputaddress+"^FS"+"\n";
        prn=prn+"^FT293,153"+"\n";
        prn=prn+"^A0N,85,115^FD"+inputto+"^FS"+"\n";

        prn=prn+"^PQ"+inputcopy+",0,"+inputcopy+",Y"+"\n";
        prn=prn+"^XZ"+"\n";
        prn=prn+"<xpml></page></xpml><xpml><end/></xpml>";


        return prn;
    }

    public String gaurav(String copy) {

        String prn = "";

        String  inputcopy=copy;



        prn=prn+"<xpml><page quantity='0' pitch='50.0 mm'></xpml>^XA"+"\n";
        prn=prn+"^SZ2^JMA"+"\n";
        prn=prn+"^MCY^PMN"+"\n";
        prn=prn+"^PW799"+"\n";
        prn=prn+"^JZY"+"\n";
        prn=prn+"^LH0,0^LRN"+"\n";
        prn=prn+"^XZ"+"\n";
        prn=prn+"<xpml></page></xpml><xpml><page quantity='"+inputcopy+"' pitch='50.0 mm'></xpml>^XA"+"\n";
        prn=prn+"^FT256,1182"+"\n";
        prn=prn+"^CI0"+"\n";

        prn=prn+"^A0B,56,76^FD      GAURAV ENTERPRISES^FS"+"\n";
        prn=prn+"^FT342,1080"+"\n";
        prn=prn+"^A0B,56,76^FDHOUSE NO.46,NEAR SURVEY CITY^FS"+"\n";
        prn=prn+"^FT427,1080"+"\n";
        prn=prn+"^A0B,56,76^FDBUS STOP, BELTOLA, GUWAHATI^FS"+"\n";
        prn=prn+"^FT513,1080"+"\n";

        prn=prn+"^A0B,56,76^FD781028, ASSAM ,INDIA^FS"+"\n";
        prn=prn+"^FT120,1150"+"\n";
        prn=prn+"^A0B,56,76^FDTO,^FS"+"\n";
        prn=prn+"^FT656,1070"+"\n";
        prn=prn+"^A0B,56,76^FDPHONE NO:7002955161^FS"+"\n";

        prn=prn+"^PQ"+inputcopy+",0,"+inputcopy+",Y"+"\n";
        prn=prn+"^XZ"+"\n";
        prn=prn+"<xpml></page></xpml><xpml><end/></xpml>";


        return prn;
    }

    public String fragile(String inputcopy){
        String prn="";
        prn=prn+"<xpml><page quantity='0' pitch='50.0 mm'></xpml>^XA"+"\n";
        prn=prn+"^SZ2^JMA"+"\n";
        prn=prn+"^MCY^PMN"+"\n";
        prn=prn+"^PW799"+"\n";
        prn=prn+"^JZY"+"\n";
        prn=prn+"^LH0,0^LRN"+"\n";
        prn=prn+"^XZ"+"\n";
        prn=prn+"<xpml></page></xpml><xpml><page quantity='"+inputcopy+"' pitch='50.0 mm'></xpml>^XA"+"\n";
        prn=prn+"^FT481,1136"+"\n";
        prn=prn+"^CI0"+"\n";
        prn=prn+"^A0B,226,305^FDFRAGILE^FS"+"\n";
        prn=prn+"^PQ"+inputcopy+",0,"+inputcopy+",Y"+"\n";
        prn=prn+"<xpml></page></xpml><xpml><end/></xpml>";
        return  prn;
    }



    public String calibrate(){
        String prn="";
        prn = prn + "<xpml><page></xpml>" +"\n";
        prn = prn + "~JC" +"\n";
        prn = prn + "<xpml></page></xpml><xpml><end/></xpml>" + "\n";
        return  prn;
    }

}
