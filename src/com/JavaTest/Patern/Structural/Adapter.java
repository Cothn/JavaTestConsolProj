package com.JavaTest.Patern.Structural;



import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

interface ClientInterface extends DataSource {

    boolean writeData(String data);
    String readData();
}

class DataSourceService {
    private byte[] data;

    public DataSourceService(){    }

    public DataSourceService(byte[] startData){
        data = startData;
    }

    public boolean writeData(byte[] data) {
        if (data != null){
            this.data = data;
            return true;
        }
        return false;
    }

    public byte[] readData() {
        return data;
    }
}


 class AdapterForService implements ClientInterface{
     private final DataSourceService dss;

     public AdapterForService(DataSourceService dss){
         this.dss = dss;
     }

     @Override
     public boolean writeData(String data) {
         byte[] tmp;        //test
         System.out.println("PreAdapting DataLog: "+ data);
         if (data != null){
             tmp = (this.readData() +data).getBytes(StandardCharsets.UTF_8)  ;
             dss.writeData(tmp) ;
             return true;
         }
         return false;
     }

     @Override
     public String readData() {
         if(dss.readData() == null){
             return "";
         }
         return new String(dss.readData(), StandardCharsets.UTF_8);

     }
 }

public class Adapter {


    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        DataSource source = new AdapterForService(new DataSourceService());
        source = new EncryptionDecorator(source);
        source = new CompressionDecorator(source);

        System.out.println("Enter data:");
        source.writeData(s.next());
        System.out.println("Result: ");
        System.out.println(source.readData()+"\n");
    }

}
