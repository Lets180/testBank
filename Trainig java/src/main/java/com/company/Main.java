package com.company;



import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import model.Client;
import repository.*;
import Exception.*;
import database.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
public class Main {
public static int amountClients = 0;
public static int nextNumber = 0;
    public static void main(String[] args) {
	// write your code here
        if (Files.exists(Path.of("e:\\Bases\\clientBase.csv")) != true) {
            Path path1 = Path.of("e:\\Bases");
            try {
                Files.createDirectory(path1);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        Scanner scDb = new Scanner(System.in);
        System.out.println("Enter password to get access to database");
        String passwordDb = scDb.nextLine();
        DbFunctions db = new DbFunctions();
        System.out.println("Enter name of database for connect");
        String nameDataBase = scDb.nextLine();
        Connection conn=db.connectDb(nameDataBase,"postgres","180180141");
        boolean inf = false;
        while (inf==false) {
            System.out.println("Press 1 if you want create table");
            System.out.println("Press 2 if you want insert row to exist table");
            System.out.println("Press 3 if you want output on console table");
            System.out.println("Press 4 if you want update data about client");
            System.out.println("Press 5 if you want search data about client");
            System.out.println("Press 6 if you want delete client");
            System.out.println("Press 7 if you want delete database");
            System.out.println("Press 8 if you want to exit");
            String keyDb = scDb.nextLine();
            switch (keyDb){
                case "1":System.out.println("Enter a name of table");
                    String nameTable = scDb.nextLine();
                    db.createTable(conn, nameTable);
                    break;
                case "2":System.out.println("Enter name of table");
                    nameTable = scDb.nextLine();
                    System.out.println("Enter data");
                    String data = scDb.nextLine();
                    db.insertRow(conn,data,nameTable);
                    break;
                case "3":System.out.println("Enter name of table");
                    nameTable = scDb.nextLine();
                    db.readData(conn,nameTable);
                    break;
                case "4":System.out.println("Enter name of table and field client to update");
                    String input = scDb.nextLine();
                    System.out.println("Enter id of client to update");
                    int id = scDb.nextInt();
                    String[] splitInput = input.split(",");
                    db.updateData(conn,splitInput[0],splitInput[1],id);
                    break;
                case "5":System.out.println("Enter name of table, field and keyword client to search");
                    input = scDb.nextLine();
                    splitInput = input.split(",");
                    db.searchData(conn,splitInput[0],splitInput[1],splitInput[2]);
                    break;
                case "6":System.out.println("Enter name of table and id client to delete");
                    input = scDb.nextLine();
                    splitInput = input.split(",");
                    db.deleteData(conn,splitInput[0],splitInput[1]);
                    break;
                case "7":System.out.println("Enter name of table to delete");
                    nameTable = scDb.nextLine();
                    db.deleteTable(conn,nameTable);
                    break;
                case "8":inf=true;
                    break;
                default:
                    System.out.println("Incorrect command");
                    break;
            }
            String answer = scDb.nextLine();
            if (answer.equals("Y") || answer.equals("y")) {
                System.out.println("Enter a name of table");
                String nameTable = scDb.nextLine();
                db.createTable(conn, nameTable);
            }
        }
            //File file1 = new File("e:\\Bases\\w123.csv");
            ArrayList<model.Client> baseOfClients = new ArrayList<>();
            ///int amountClients = 0;
            String alt2 = new String();
            boolean infinity = true;
            boolean existBase = false;

        System.out.println("Welcome to the Bank");
        String alt = new String();
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        do {
            System.out.println("If you want create a new databases, press Y");
            alt = sc1.nextLine();
            if (alt.equals("Y") || alt.equals("y")) {
                System.out.println("Input name of database");
                String nameBase = sc1.nextLine();
                CreateBase.create(nameBase);
                System.out.println("Do you want create more bases? Press Y");
                alt = sc1.nextLine();
            }
        } while (alt.equals("Y") || alt.equals("y"));
        String nameFile = CreateBase.choose();
        baseOfClients = CreateBase.getObjects(baseOfClients,nameFile);
        amountClients=CreateBase.getSize(nameFile)-1;
        nextNumber=CreateBase.getMaxID(nameFile);
        while (infinity!=false) {
            System.out.println("Press 1 if you want create a new client");
            System.out.println("Press 2 if you want delete client");
            System.out.println("Press 3 if you want update information about client");
            System.out.println("Press 4 if you want get information about client");
            System.out.println("Press 5 if you want provide deposit of client");
            System.out.println("Press 6 if you want provide withdraw of about client");
            System.out.println("Press 7 if you want to exit");
            String key=sc2.nextLine();
            switch (key){
                case "1" :try {
                        baseOfClients.add(Main.nextNumber, CreateClient.createClient(nameFile));
                        GetClientInfo.getInfoConsole(baseOfClients.get(Main.nextNumber));
                        Main.nextNumber++;}
                         catch (IsRightAge ex){
                    System.out.println(ex.getMessage());}
                        break;
                case "2" :System.out.println("Input client id to delete");
                        int id = sc1.nextInt();
                        DeleteBase.deleteClient(baseOfClients,nameFile,id);
                        break;
                case "3" :System.out.println("Input client id to update");
                        id = sc1.nextInt();
                        UpdateBase.updateInfoClient(baseOfClients,nameFile,id);
                        break;
                case "4" :System.out.println("Input client id to get info");
                        id = sc1.nextInt();
                        GetClientInfo.getInfoClient(baseOfClients,nameFile,id);
                        break;
                case "5" :System.out.println("Input client id to deposit");
                        id = sc1.nextInt();
                        System.out.println("Input amount of money to deposit");
                        int money = sc1.nextInt();
                        UpdateBase.updateBalanceClient(baseOfClients,nameFile,id,money);
                        break;                                                                                                 //Client test1 = CreateClient.consoleCreateAndWrite("e:\\Bases\\clientBase.txt");
                case "6" :System.out.println("Input client id to withdraw");
                        id = sc1.nextInt();
                        System.out.println("Input amount of money to withdraw");
                        money = sc1.nextInt();
                        UpdateBase.updateBalanceClient(baseOfClients,nameFile,id,money,infinity);
                        break;
                case "7" :infinity=false;
                        break;
                default:System.out.println("Incorrect command");
                        break;
            }

            //System.out.println("Welcome to the Bank");
            //System.out.println("Welcome to the Bank");
            //System.out.println();

        /*    Client test2 = CreateClient.consoleCreate();
            Client test3 = CreateClient.consoleCreate();



            String anyFileData = new String();
            try {
                FileReader fr = new FileReader("e:\\Bases\\clientBase.txt");

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            GetClientInfo.getInfoConsole(test2);*/
        }
    }
}





