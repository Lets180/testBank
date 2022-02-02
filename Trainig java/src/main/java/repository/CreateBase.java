package repository;

import com.opencsv.CSVWriter;
import model.Client;
import service.IOCSV;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class CreateBase {
    public static void create(String nameBase){
        String nameFile = "e:\\Bases\\"+nameBase+".csv";
        boolean exist=Files.exists(Path.of(nameFile));
        if (!exist) {
            try {
                File file1 = new File(nameFile);
                CSVWriter wr = new CSVWriter(new FileWriter(file1, true));
                String[] allClients = "Name,age,weight,height,id,balance".split(",");
                wr.writeNext(allClients);
                wr.close();
                //CreateClient.writeClientCSV(test2);
                //CreateClient.writeClientCSV(test3);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else System.out.println("Base with this name existing");
    }
    public static String choose(){
        boolean existBase=false;
        String nameFile = new String();
        Scanner sc = new Scanner(System.in);
        do {System.out.println("Choose base for work");
            String nameBase = sc.nextLine();
            nameFile = "e:\\Bases\\"+nameBase+".csv";
            if (Files.exists(Path.of(nameFile))){
                existBase=true;
            } else System.out.println("Base does not exist");
        } while (!existBase);
        return nameFile;
    }
    public static int getSize(String nameFile){
        int size=0;
        List<String[]> allClients = IOCSV.readCSV(nameFile);
        size=allClients.size()-1;
        return size;
    }
    public static ArrayList<model.Client> getObjects(ArrayList<model.Client> base, String nameFile){
        List<String[]> allClients = IOCSV.readCSV(nameFile);
        for(String[] client: allClients){
            if (client[0].equals("Name")==false){
          String name = client[0];
          int age = Integer.parseInt(client[1]);
          int weight = Integer.parseInt(client[2]);
          int height = Integer.parseInt(client[3]);
          int id = Integer.parseInt(client[4]);
          int balance = Integer.parseInt(client[5]);
          model.Client newClient = new Client(name,age,weight,height,id,balance);
          base.add(newClient);
        }
        }
        return base;
    }
    public static int getMaxID(String nameFile){
        int maxID=0;
        List<String[]> allClients = IOCSV.readCSV(nameFile);
        for(String[] client:allClients){
            if (!client[4].equals("id")){
            int idClient=Integer.parseInt(client[4]);
            if(idClient>maxID){
                maxID=idClient;
            }
            }
        }
        return maxID;
    }
}
