package repository;

import model.Client;
import service.IOCSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetClientInfo {
    public static void getInfoConsole(model.Client client) {
        String name = client.getName();
        int age = client.getAge();
        int weight = client.getWeight();
        int height = client.getHeight();
        int id = client.getIdAccount();
        int balance = client.getBalanceAccount();
        System.out.println(name + " " + age + " " + weight + " " + height + " " + id + " " + balance);
    }

    public static void getInfoClient(ArrayList<model.Client> base, String file, int id) {
        boolean exist = false;
        List<String[]> allClients = IOCSV.readCSV(file);
        String infoClient = new String();
        int trueId = -1;
        for (String[] client : allClients) {
            if (client[4].equals("id")==false){
            trueId = Integer.parseInt(client[4]);
            if (trueId == id) {
                for (int i = 0; i <= 5; i++) {
                    infoClient += client[i] + " ";
                    if (i==5) {
                    System.out.println(infoClient + "\n");
                    infoClient="";
                    }
                }
                exist = true;
                }
            }
        }
        if (exist==false){
            System.out.println("Client does not exist");
        }
    }
}
   /* public static model.Client getToObject(String file) {
        BufferedReader fr = null;

        try {
            String[] fields = new String[100];
            fr = new BufferedReader(new FileReader(file));
            int c = 0;
            while ((c=fr.read())!=-1) {
                while (((char)c=fr.read()!=' '))
            }

        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        model.Client client = new Client();
        return client;
    }*/
