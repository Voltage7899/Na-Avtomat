package com.company.apteka;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {


    Connection connection;
    String uname,pass,ip,port,database;
    @SuppressLint("NewApi")
    public Connection connection(){
        ip = "192.168.1.40";
        database="Volod";
        uname="volod";
        pass="123";
        port="1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        String ConnectionURL=null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            //ConnectionURL="jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename="+database+";user="+uname+";password="+pass+";";
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip +";databaseName="+ database + ";user=" + uname+ ";password=" + pass + ";";
            connection= DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException e){
            Log.e("Erroоооооr",e.getMessage());
        }
        catch (ClassNotFoundException e){
            Log.e("erroооооr here 2 : ", e.getMessage());
        }
        catch (Exception e){
            Log.e("errоооor here 3 : ", e.getMessage());
        }

        return connection;
    }
}
