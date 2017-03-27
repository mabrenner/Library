package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.Formatter;
import java.util.Scanner;

public class TypeBrochure implements Type
{
    public PreparedStatement preparedStmt = null;
    public ResultSet resultSet = null;
    
    @Override
    public void add(Connection connect) throws Exception
    {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Наименование: ");
        String name = in.nextLine();
        
        System.out.print("Год и месяц издания: ");
        String date = in.nextLine();
        
        System.out.print("Издательство: ");
        String publ_house = in.nextLine();
        
        System.out.print("Краткое описание: ");
        String description = in.nextLine();
        
        String insert = "insert into show_library_stock (type, name,"
                        + "date, publishing_house, description)"
                        + "values (?, ?, ?, ?, ?);";
                preparedStmt = connect.prepareStatement(insert);
                
                preparedStmt.setString(1, "брошюра");                                                                              
                preparedStmt.setString(2, name);                                              
                preparedStmt.setString(3, date);
                preparedStmt.setString(4, publ_house);  
                preparedStmt.setString(5, description);
               
                preparedStmt.executeUpdate();
                preparedStmt.close();
    }
    
    @Override
    public void show(Connection connect, ResultSet resultSet) throws Exception
    {        
        System.out.println(String.format("|%18s|%20s|%15s|%30s", "НАИМЕНОВАНИЕ", 
                "ДАТА ИЗДАНИЯ",  "ИЗДАТЕЛЬСТВО", "КРАТКОЕ ОПИСАНИЕ"));

        
        while (resultSet.next()) 
        {
            Formatter fm = new Formatter();
            fm.format("|%18s|%20s|%15s|%30s|", resultSet.getString(4), resultSet.getString(5),
                     resultSet.getString(7), resultSet.getString(9));
            
            System.out.println(fm);
            
        }
    }
}
