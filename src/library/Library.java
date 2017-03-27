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
        
        System.out.println("������������ ����\n");
        
        while(true)
        {
            System.out.println("1. �������� ������������������ ������� � �����");
            System.out.println("2. ���������� ������ ������� � ����");
            System.out.println("3. �������� ���������� ���������� �������");
            System.out.println("4. �������� ���������� �������");
            System.out.println("5. �����\n");
        
            System.out.println("������� ����� ������� ��� �����������:");
        
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
                    System.out.print("������� ������������� �������: ");
                    int id = in.nextInt();
                    menu.showInformation(id, connect);
                    break;
                case 4:
                    System.out.print("������� ������������� �������: ");
                    id = in.nextInt();
                    menu.deletePublication(id, connect);
                    break;
                case 5: 
                    menu.exit();
                default: 
                    System.out.println("������ ������! ��������� ���� �������\n");
                    break;
            }
        }
    }
    
}
