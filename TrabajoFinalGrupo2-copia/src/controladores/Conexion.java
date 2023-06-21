package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;



public class Conexion {
    Connection con;
    public Connection getConnection() throws SQLException{
        try{
            String myBD = "jdbc:mysql://localhost:3306/mydb?serverTimeone = UTC";
            con = DriverManager.getConnection(myBD, "root","");
            return con;
    }catch(SQLDataException e){
            System.out.println(e.toString());
            return null;
    }
    }
}
        
       
    
    
    

    

 