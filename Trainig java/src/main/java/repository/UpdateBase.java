package repository;

import model.Client;
import service.ClientService;
import service.IOCSV;
import Exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateBase {
    public static void updateInfoClient(ArrayList<Client> base, String file, int id){
        boolean exist = false;
        String newName = new String();
        int newAge=0;
        int newWeight=0;
        int newHeight=0;
        String answer = new String();
        Scanner sc = new Scanner(System.in);
        List<String[]> allClients = IOCSV.readCSV(file);
        int trueId = -1;
        for(String[] client:allClients){
            if (client[4].equals("id")==false) {
                trueId = Integer.parseInt(client[4]);
                if (trueId == id) {
                    exist = true;
                    System.out.println("Do you want change name of client? Press Y");
                    answer = sc.nextLine();
                    if ((answer.equals("Y")==true) || (answer.equals("y")==true)) {
                        System.out.println("Input a new name");
                        newName = sc.nextLine();
                        client[0] = newName;
                    } else newName = client[0];
                    System.out.println("Do you want change age of client? Press Y");
                    answer = sc.nextLine();
                    if ((answer.equals("Y")==true) || (answer.equals("y")==true)) {
                        System.out.println("Input a new age");
                        newAge = sc.nextInt();
                        client[1] = Integer.toString(newAge);
                    } else newAge = Integer.parseInt(client[1]);
                    System.out.println("Do you want change weight of client? Press Y");
                    answer = sc.nextLine();
                    if ((answer.equals("Y")==true) || (answer.equals("y")==true)) {
                        System.out.println("Input a new weight");
                        newWeight = sc.nextInt();
                        client[2] = Integer.toString(newWeight);
                    } else newWeight = Integer.parseInt(client[2]);
                    System.out.println("Do you want change height of client? Press Y");
                    answer = sc.nextLine();
                    if ((answer.equals("Y")==true) || (answer.equals("y")==true)) {
                        System.out.println("Input a new height");
                        newHeight = sc.nextInt();
                        client[3] = Integer.toString(newHeight);
                    } else newHeight = Integer.parseInt(client[3]);
                }
            }
        }
        if (exist==true) {
            for(model.Client client:base){
                trueId = client.getIdAccount();
                if (trueId==id) {
                    client.setName(newName);
                    client.setAge(newAge);
                    client.setWeight(newWeight);
                    client.setHeight(newHeight);
                }
            }
            IOCSV.writeCSV(allClients,file);
        } else System.out.println("Client does not exist");
    }
    public static void updateBalanceClient(ArrayList<Client> base, String file, int id, int deposit){
        boolean exist = false;
        int newBalance=0;
        String answer = new String();
        Scanner sc = new Scanner(System.in);
        List<String[]> allClients = IOCSV.readCSV(file);
        int trueId = -1;
        for(model.Client client:base){
            trueId = client.getIdAccount();
            if (trueId==id) {
                try {
                    ClientService.depositMoney(client, deposit);
                    newBalance = client.getBalanceAccount();
                    exist = true;
                }
                catch (IsRightDeposit ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        if (exist==true) {
        for(String[] client:allClients){
            if (client[4].equals("id")==false) {
                trueId = Integer.parseInt(client[4]);
                if (trueId == id) {
                    client[5] = Integer.toString(newBalance);
                }
            }
            }
            IOCSV.writeCSV(allClients,file);
        }
    }
    public static void updateBalanceClient(ArrayList<Client> base, String file, int id, int deposit,boolean b){
        boolean exist = false;
        int newBalance=0;
        String answer = new String();
        Scanner sc = new Scanner(System.in);
        List<String[]> allClients = IOCSV.readCSV(file);
        int trueId = -1;
        for(model.Client client:base){
            trueId = client.getIdAccount();
            if (trueId==id) {
                try {
                    ClientService.withdrawMoney(client, deposit);
                    newBalance = client.getBalanceAccount();
                    exist = true;
                }
                catch (IsRightWithdraw ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        if (exist==true) {
            for(String[] client:allClients){
                if (client[4].equals("id")==false){
                trueId = Integer.parseInt(client[4]);
                if (trueId==id) {
                    client[5]=Integer.toString(newBalance);
                }
                }
            }
            IOCSV.writeCSV(allClients,file);
        }
    }
}
