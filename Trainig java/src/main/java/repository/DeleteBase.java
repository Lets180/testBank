package repository;

import model.Client;
import service.IOCSV;

import java.util.ArrayList;
import java.util.List;

public class DeleteBase {
    public static void deleteClient(ArrayList<model.Client> base, String file, int id){
        model.Client delClient = new model.Client();
        boolean exist = false;
        List<String[]> allClients = IOCSV.readCSV(file);
        int trueId = -1;
        for(int i=0; i<allClients.size();i++) {
            if (i==id){
            String[] client = allClients.get(i);
                allClients.remove(client);
                exist = true;
            }
        }
        if (exist==true) {
            for(int i=0; i<base.size();i++){
                delClient = base.get(i);
                if (delClient.getIdAccount()==id) {
                    base.remove(delClient);
                }
            }
            IOCSV.writeCSV(allClients,file);
    } else  System.out.println("Client does not exist");
}
}

