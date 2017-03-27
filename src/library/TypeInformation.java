
package library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class TypeInformation 
{
    public Statement statement = null;
    public ResultSet resultSet = null;
        
    public String getType(int id, Connection connect) throws Exception
    {
        statement = connect.createStatement();
        String select = "select type from show_library_stock where id = " + id +";";        
        resultSet = statement.executeQuery(select);
        
        String type = null;
        while (resultSet.next())
        {
            type = resultSet.getString(1);
        }
        
        System.out.println();
        
        return type;
    }
    
}
