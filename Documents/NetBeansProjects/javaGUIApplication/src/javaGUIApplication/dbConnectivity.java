package javaGUIApplication;
import java.sql.*;


public class dbConnectivity {
    
    //Database connection
    
    String host="localhost";
    String user="root";
    String password="";
    String database="pharmacy_db";
    
    public Connection con;
    
   public dbConnectivity(){
    try{
    String url="jdbc:mysql://"+host+":3306/"+database;
    con=DriverManager.getConnection(url,user,password);
    System.out.println("Database connected");
    
    }catch(SQLException e){
    System.out.println("Database connection failed");
    System.out.println(e.getMessage());
    
    }
    
    
    
    }
    public static void main(String[]args){
    new dbConnectivity();
    
    }
}



  