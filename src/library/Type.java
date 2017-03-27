package library;

import java.sql.Connection;
import java.sql.ResultSet;

public interface Type 
{    
    public void add(Connection connect) throws Exception;
    
    public void show(Connection connect, ResultSet resultSet) throws Exception;
}
