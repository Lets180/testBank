package repository;

import com.opencsv.CSVWriter;
import model.Client;
import service.IOCSV;
import Exception.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateClient {
    /*public static model.Client consoleCreateAndWrite(String file){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, input name of Client");
        String name = sc.nextLine();
        System.out.println("Please, input age of Client");
        int age = sc.nextInt();
        System.out.println("Please, input weight of Client");
        int weight = sc.nextInt();
        System.out.println("Please, input height of Client");
        int height = sc.nextInt();
        System.out.println("Please, input id of Client");
        int id = sc.nextInt();
        System.out.println("Please, input balance of Client");
        int balance = sc.nextInt();
        model.Client client = new model.Client(name,age,weight,height);
        try {
            FileWriter wr = new FileWriter(file, false);
            wr.write(name);
            wr.write(" ");
            wr.write(age);
            wr.write(" ");
            wr.write(weight);
            wr.write(" ");
            wr.write(height);
            wr.write(" ");
            wr.write(id);
            wr.write(" ");
            wr.write(balance);
            wr.write("/n");
            wr.close();
            }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        finally {

                   }

        return client;
    }*/
    public static model.Client consoleCreate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, input name of Client");
        String name = sc.nextLine();
        System.out.println("Please, input age of Client");
        int age = sc.nextInt();
        System.out.println("Please, input weight of Client");
        int weight = sc.nextInt();
        System.out.println("Please, input height of Client");
        int height = sc.nextInt();
        System.out.println("Please, input id of Client");
        int id = sc.nextInt();
        System.out.println("Please, input balance of Client");
        int balance = sc.nextInt();
        model.Client client = new model.Client(name,age,weight,height);
        return client;
    }
    public static model.Client createClient(String file) throws IsRightAge {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, input name,age,weight,height of Client");
        String clientData = sc.nextLine();
        String[] splitData = clientData.split(",");
        String name = splitData[0];
        int age = Integer.parseInt(splitData[1]);
        if (age<18) throw new IsRightAge("Age of client don't must below 18", age);
        int weight = Integer.parseInt(splitData[2]);
        int height = Integer.parseInt(splitData[3]);
     /*   System.out.println("Please, input name of Client");
        String name = sc.nextLine();
        System.out.println("Please, input age of Client");
        int age = sc.nextInt();
        System.out.println("Please, input weight of Client");
        int weight = sc.nextInt();
        System.out.println("Please, input height of Client");
        int height = sc.nextInt();*/
       // System.out.println("Please, input id of Client");
       // int id = sc.nextInt();
       // System.out.println("Please, input balance of Client");
       // int balance = sc.nextInt();
        model.Client client = new model.Client(name,age,weight,height);
        IOCSV.writeCSV(client, file);
        return client;
    }

  /*  public static void writeClientCSV(Client client) {
        try {
            CSVWriter wr = new CSVWriter(new FileWriter("e:\\Bases\\clientBase.csv",true));
            String name = client.getName();
            wr.writeNext();
            wr.writeNext(String.valueOf(client.getAge()));
            wr.writeNext(String.valueOf(client.getWeight()));
            wr.writeNext(String.valueOf(client.getHeight()));
            wr.writeNext(String.valueOf(client.getIdAccount()));
            wr.writeNext(String.valueOf(client.getBalanceAccount()));
                    //fieldsClient.split(",");
            wr.close();     }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }    
    }*/

    public static void writeClient(Client client) {
        try {
            FileWriter wr = new FileWriter("e:\\Bases\\clientBase.txt",true);
            String [] fieldsClient = new String[]{client.getName(),
                    String.valueOf(client.getAge()),
                    String.valueOf(client.getWeight()),
                    String.valueOf(client.getHeight()),
                    String.valueOf(client.getIdAccount()),
                    String.valueOf(client.getBalanceAccount())};
            //fieldsClient.split(",");
            wr.write(fieldsClient[0]);
            wr.write(fieldsClient[1]);
            wr.write(fieldsClient[2]);
            wr.write(fieldsClient[3]);
            wr.write(fieldsClient[4]);
            wr.write(fieldsClient[5]);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
        
    
}

