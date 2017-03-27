package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Formatter;
import java.util.Scanner;

public class TypeBook implements Type
{
    public PreparedStatement preparedStmt = null;
    public ResultSet resultSet = null;
    
    @Override
    public void add(Connection connect) throws Exception
    {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Автор: ");
        String author = in.nextLine();
                
        System.out.print("Наименование: ");
        String name = in.nextLine();
                
        System.out.print("Год издания: ");
        String date = in.nextLine();
                
        System.out.print("Количество страниц: ");
        String page_count = in.nextLine();
                
        System.out.print("Издательство: ");
        String publ_house = in.nextLine();
                
        System.out.print("Жанр: ");
        String genre = in.nextLine();
                
        System.out.print("Краткое содержание: ");
        String description = in.nextLine();
                                
        String insert = "insert into show_library_stock (type, author, name,"
                        + "date, page_count, publishing_house, genre, description)"
                        + "values (?, ?, ?, ?, ?, ?, ?, ?);";
                preparedStmt = connect.prepareStatement(insert);
                
                preparedStmt.setString(1, "книга");                                
                preparedStmt.setString(2, author);                                                
                preparedStmt.setString(3, name);                                              
                preparedStmt.setString(4, date);
                preparedStmt.setString(5, page_count);
                preparedStmt.setString(6, publ_house);  
                preparedStmt.setString(7, genre);
                preparedStmt.setString(8, description);
               
                preparedStmt.executeUpdate();
                preparedStmt.close();
    }
    
    @Override
    public void show(Connection connect, ResultSet resultSet) throws Exception
    {
        System.out.println(String.format("|%18s|%30s|%15s|%15s|%15s|%15s|%30s", "АВТОР", "НАИМЕНОВАНИЕ", 
                "ДАТА ИЗДАНИЯ", "КОЛ-ВО СТРАНИЦ", "ИЗДАТЕЛЬСТВО", "ЖАНР", "КРАТКОЕ СОДЕРЖАНИЕ"));
        
        while (resultSet.next()) 
        {
            Formatter fm = new Formatter();
            fm.format("|%18s|%30s|%15s|%15s|%15s|%15s|%20s|", resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                    resultSet.getString(8), resultSet.getString(9));
            
            System.out.println(fm);
            
        }
    }
}
