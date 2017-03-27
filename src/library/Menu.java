package library;

import java.sql.*;
import java.util.*;

public class Menu extends TypeInformation
{
    //Statement statement = null;
    //ResultSet resultSet = null;
    PreparedStatement preparedStmt = null;
    
    public void showPublications(Connection connect) throws Exception
    {
        statement = connect.createStatement();
        
        String select = "select * from show_library_stock;";
        resultSet = statement.executeQuery(select);
        
        System.out.println("Просмотр зарегистрированных изданий в фонде\n"); 
        
        System.out.println(String.format("|%4s |%10s|%30s|%18s|%20s|%18s|", "ID", "ТИП", "НАИМЕНОВАНИЕ", 
                "ДАТА ИЗДАНИЯ", "КОЛИЧЕСТВО СТРАНИЦ", "ИЗДАТЕЛЬСТВО"));
        
        for (int i = 0; i < 108; i++)
        {
            System.out.print("-");
        }
        System.out.println();
        
        while (resultSet.next()) 
        {
            Formatter fm = new Formatter();
            fm.format("|%4s |%10s|%30s|%18s|%20s|%18s|", resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
            
            System.out.println(fm);
            
        }
        System.out.println();                
    }
    
    public void addPublication(Connection connect) throws Exception
    {        
        statement = connect.createStatement();
        
        System.out.println("Добавление нового издания в фонд\n");
        Scanner in = new Scanner(System.in);
        
        String insert;
        
        System.out.print("Тип (book, brochure, journal): ");
        String type = in.nextLine();
        
        
        if(type.equals("book"))
        {
            TypeBook add = new TypeBook();
            add.add(connect);
        }
        
        else if(type.equals("journal"))
        {
            TypeJournal add = new TypeJournal();
            add.add(connect);
        }
        
        else if(type.equals("brochure"))
        {
            TypeBrochure add = new TypeBrochure();
            add.add(connect);
        }
        
        else { System.out.println("Неверно введен тип"); }  
        
        System.out.println();
    }
    
    public void showInformation(int id, Connection connect) throws Exception
    {
        statement = connect.createStatement();
        
        TypeInformation typeInf = new TypeInformation();
 
        String select = "select * from show_library_stock where id = " + id + ";";
        resultSet = statement.executeQuery(select);
        
        if(typeInf.getType(id, connect).equals("книга"))
        { 
            TypeBook show = new TypeBook();
            show.show(connect, resultSet);
        }
       
        else if(typeInf.getType(id, connect).equals("брошюра")) 
        {
            TypeBrochure show = new TypeBrochure();
            show.show(connect, resultSet);
        }
        
        else if(typeInf.getType(id, connect).equals("журнал")) 
        {
            TypeJournal show = new TypeJournal();
            show.show(connect, resultSet);
        }
        
        else { System.out.println("Ошибка"); } 
                   
        
        System.out.println();        
    }
    
    public void deletePublication(int id, Connection connect) throws Exception
    {
        statement = connect.createStatement();
        
        String delete = "delete from show_library_stock where id = " + id + ";";
        preparedStmt = connect.prepareStatement(delete);
        preparedStmt.execute();
        
        System.out.println();
        
        //connect.close();       
    }
    
    public void exit()
    {
        System.exit(0);
    }
    
}
