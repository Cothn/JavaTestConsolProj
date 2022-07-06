package com.JavaTest.Patern.Structural;



import java.util.Scanner;

interface DataSource {

    boolean writeData(String data);
    String readData();
}

class FileDataSource implements DataSource {
    private String data;

    public FileDataSource(){
        data = "";
    }

    public FileDataSource(String startData){
        data = startData;
    }

    @Override
    public boolean writeData(String data) {
        //test
        System.out.println("PreWriting DataLog: "+ data);
        if (data != null){
            this.data += data;
            return true;
        }
        return false;
    }

    @Override
    public String readData() {
        return data;
    }
}


 class DataSourceDecorator implements DataSource{
     protected DataSource dataSource;

     public DataSourceDecorator(DataSource d){
         dataSource = d;
     }

     @Override
     public boolean writeData(String data) {

         return dataSource.writeData(data);
     }

     @Override
     public String readData() {
         return dataSource.readData();
     }
 }

class EncryptionDecorator extends DataSourceDecorator {

    public EncryptionDecorator(DataSource d) {
        super(d);
    }

    @Override
    public boolean writeData(String data) {
        //test
        System.out.println("PreEncryption DataLog: "+ data);
        return dataSource.writeData("Encrypted {"+data+"}");
    }

    @Override
    public String readData() {
        String tmp;
        tmp = dataSource.readData();
        return  tmp.substring(11,tmp.length()-1);
    }
}

class CompressionDecorator extends DataSourceDecorator {

    public CompressionDecorator(DataSource d) {
        super(d);
    }

    @Override
    public boolean writeData(String data) {
        //test
        System.out.println("PreCompression DataLog: "+ data);
        return dataSource.writeData("Compressed ["+data+"]");
    }

    @Override
    public String readData() {
        String tmp;
        tmp = dataSource.readData();
        return  tmp.substring(12,tmp.length()-1);
    }
}


public class Decorator {


    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        DataSource source = new FileDataSource();
        source = new EncryptionDecorator(source);
        source = new CompressionDecorator(source);

        System.out.println("Enter data:");
        source.writeData(s.next());
        System.out.println("Result: ");
        System.out.println(source.readData()+"\n");
    }

}
