package library;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Library 
{

    public static void main(String[] args) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","root");
        
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", properties);
                
        Menu menu = new Menu();
        
        System.out.println("Библиотечный фонд\n");
        
        while(true)
        {
            System.out.println("1. Просмотр зарегистрированных изданий в фонде");
            System.out.println("2. Добавление нового издания в фонд");
            System.out.println("3. Просмотр информации выбранного издания");
            System.out.println("4. Удаление выбранного издания");
            System.out.println("5. Выход\n");
        
            System.out.println("Введите номер команды для продолжения:");
        
            Scanner in = new Scanner(System.in);
            int ch = in.nextInt();
        
            System.out.println();
        
            switch(ch)
            {
                case 1: 
                    menu.showPublications(connect); 
                    break;
                case 2:                  
                    menu.addPublication(connect);
                    break;
                case 3: 
                    System.out.print("Введите идентификатор издания: ");
                    int id = in.nextInt();
                    menu.showInformation(id, connect);
                    break;
                case 4:
                    System.out.print("Введите идентификатор издания: ");
                    id = in.nextInt();
                    menu.deletePublication(id, connect);
                    break;
                case 5: 
                    menu.exit();
                default: 
                    System.out.println("Ошибка выбора! Повторите ввод команды\n");
                    break;
            }
        }
    }
    
}
