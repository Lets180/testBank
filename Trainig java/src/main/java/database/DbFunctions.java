package database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Scanner;

public class DbFunctions {
    public Connection connectDb(String dbName, String user, String password){
        Connection conn=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbName,user,password);
            if (conn!=null){
                System.out.println("Connection established");
            } else System.out.println("Connection failed");
        } catch (Exception ex){
            System.out.println(ex);
        }
        return conn;
    }
    public void createTable(Connection conn, String nameTable){
        Statement statement;
        try {
            boolean flag;
            Scanner sc = new Scanner(System.in);
            int i=0;
            ArrayList<String> field = new ArrayList<String>();
            String query = "create table "+nameTable+"(";
            do {
                flag=false;
                System.out.println("Enter name and type next field");
                field.add(sc.nextLine());
                System.out.println("Do you want enter more fields? Press Y");
                String add = sc.nextLine();
                if(add.equals("Y") || add.equals("y")) {
                    flag = true;
                }
            } while (flag==true);
            for (String fd:field){
                query+=fd;
                query+=",";
            }
            String query2 = query.substring(0, query.lastIndexOf(","));

            int numberField=1;
            System.out.println("List of fields:");
            for (String fd:field){
                System.out.println(numberField+" "+fd);
                numberField++;
            }
            System.out.println("If you want set any field as a primary key, enter number of this field, else press Enter");
            String pKey= sc.nextLine();
            if (!pKey.equals("")){
                query2+=",primary key (";
                int number = Integer.parseInt(pKey);
                int count=0;
                for (String fd:field){
                    if (count==(number-1)){
                        String[] nameType = fd.split(" ");
                        query2+=nameType[0];
                    }
                    count++;
                }
                query2+=")";
            }
            query2 += ");";
            System.out.println("Your query: "+query2);
            statement = conn.createStatement();
            statement.executeUpdate(query2);
            System.out.println("Table created");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    public void insertRow(Connection conn, String data, String nameTable){
        Statement statement;
        try{
            String[] field = data.split(",");
            String query = String.format("insert into %s (name,age,weight,height,balance) values ('%s','%s','%s','%s','%s');",nameTable, field[0],field[1],field[2],field[3],field[4]);
            System.out.println("Your query: "+query);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void readData(Connection conn, String nameTable){
        Statement statement;
        ResultSet rs=null;
        try{
            String query = String.format("select * from %s",nameTable);
            statement = conn.createStatement();
            rs=statement.executeQuery(query);
            while(rs.next()){
                System.out.print(rs.getString("name")+" ");
                System.out.print(rs.getString("age")+" ");
                System.out.print(rs.getString("weight")+" ");
                System.out.print(rs.getString("height")+" ");
                System.out.print(rs.getString("id")+" ");
                System.out.println(rs.getString("balance")+" ");
            }
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void updateData(Connection conn, String nameTable,String field,int id){
        Statement statement;
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter new data");
            String newData = sc.nextLine();
            String query=String.format("update %s set %s='%s' where id=%d;",nameTable,field,newData,id);
            System.out.println("Your query: "+query);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void searchData(Connection conn, String nameTable, String searchfield, String keyword){
        Statement statement;
        ResultSet rs=null;
        try{
            String query = String.format("select * from %s where %s='%s'",nameTable,searchfield,keyword);
            statement = conn.createStatement();
            rs=statement.executeQuery(query);
            while(rs.next()) {
                System.out.print(rs.getString("name") + " ");
                System.out.print(rs.getString("age") + " ");
                System.out.print(rs.getString("weight") + " ");
                System.out.print(rs.getString("height") + " ");
                System.out.print(rs.getString("id") + " ");
                System.out.println(rs.getString("balance") + " ");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void deleteData(Connection conn, String nameTable, String id){
        Statement statement;
        try{
            String query = String.format("delete from %s where id='%s'",nameTable,id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void deleteTable(Connection conn, String nameTable){
        Statement statement;
        try{
            String query = String.format("drop table %s",nameTable);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table deleted");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void insertAnyRow(Connection conn, String data, String nameTable){
        Statement statement;
        ResultSet rs=null;
        try{
            String prequery = String.format("select * from information_schema.columns where table_catalog='%s' and table_schema='public';", nameTable);
            statement = conn.createStatement();
            rs=statement.executeQuery(prequery);
            String[] columnName = null;
            int i=0;
            while (rs.next()){
                columnName[i]=rs.getString(i);
                i++;
            }
            String[] field = data.split(",");
            String query1 = String.format("insert into %s (",nameTable);
            for (int j=0;j<i;j++){
                query1+=columnName[j];
                if (j<(i-1)) {
                    query1+=",";
                } else query1+=") values (";
            }
            for (int j=0;j<i;j++){
                query1+=field[j];
                if (j<(i-1)) {
                    query1+=",";
                } else query1+=");";
            }
            //String query = String.format("insert into %s (name,age,weight,height,balance) values ('%s','%s','%s','%s','%s');",nameTable, field[0],field[1],field[2],field[3],field[4]);
            System.out.println("Your query: "+query1);
            statement = conn.createStatement();
            statement.executeUpdate(query1);
            System.out.println("Row inserted");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
