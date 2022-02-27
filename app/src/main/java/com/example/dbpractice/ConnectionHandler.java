package com.example.dbpractice;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHandler {
    Connection con;
    String user, pass, ip, port, database;

    @SuppressLint("NewApi")
    public Connection connectionClass() {
        user = "admin";
        pass = "admin";
        ip = "192.168.0.51";
        port = "1433";
        database = "busesDB";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String ConnectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" +
                    ip + ":" + port + ";" +
                    "databasename=" + database +
                    ";user=" + user + ";password=" +
                    pass + ";";
            connection = DriverManager.getConnection(ConnectionURL);

        } catch (Exception e) {
            Log.i("SQLError: ", e.getMessage());
        }

        return connection;
    }

}
