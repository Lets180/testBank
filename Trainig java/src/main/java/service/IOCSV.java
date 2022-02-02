package service;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
//import org.apache.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import model.Client;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class IOCSV {
    public static void writeCSV(model.Client client, String file) {
        String infoClient = client.getName() + "," + client.getAge() + "," + client.getWeight() + "," + client.getHeight() + "," + client.getIdAccount() + "," + client.getBalanceAccount();
        try {
            CSVWriter wr = new CSVWriter(new FileWriter(file, true));
            String[] record = infoClient.split(",");
            wr.writeNext(record);
            wr.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void writeCSV(List<String[]> allClients, String file) {
        try {CSVWriter wr = new CSVWriter(new FileWriter(file, false));
            for (String[] client : allClients) {
                String infoClient = client[0];
                int i;
                for (i = 1; i < client.length; i++) {
                    infoClient+=",";
                    infoClient += client[i];

                }
                String[] record = infoClient.split(",");
                wr.writeNext(record);
            }
            wr.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

}
    public static List<String[]> readCSV(String file){
        List<String[]> allClients = null;
        try {
            CSVReader rd = new CSVReader(new FileReader(file));

            allClients = rd.readAll();
            rd.close();
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        catch (CsvException ex){
            System.out.println(ex.getMessage());
        }
        catch (IndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
        }

        return allClients;
    }
}
