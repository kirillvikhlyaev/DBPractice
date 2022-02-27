package com.example.dbpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvListLenght;
    private ProgressBar progressBar;
    private Button btnSearch;
    private ListView lvBuses;

    List<Bus> busesList;
    ArrayList<String> busesArrayList;

    Connection connect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetInfoFromSQL getInfoFromSQL = new GetInfoFromSQL();
                getInfoFromSQL.execute();
            }
        });
    }

    private void init() {
        tvListLenght = findViewById(R.id.tvListLenght);
        btnSearch = findViewById(R.id.btnSearch);
        lvBuses = findViewById(R.id.lvBuses);
        progressBar = findViewById(R.id.progressBar);
        busesList = new ArrayList<>();
        busesArrayList = new ArrayList<>();
    }

    public class GetInfoFromSQL extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            lvBuses.setVisibility(View.GONE);

            busesList.clear();
            busesArrayList.clear();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            progressBar.setVisibility(View.GONE);
            lvBuses.setVisibility(View.VISIBLE);

            tvListLenght.setText("Элементов: " + busesList.size());

            for (Bus bus : busesList) {
                busesArrayList.add(String.valueOf(bus.getBusID()) + ". " + bus.getBusRoute() + " - "
                        + String.valueOf(bus.getBusLatitude()) + "/" + String.valueOf(bus.getBusLongitude()));
            }
            ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, busesArrayList);
            lvBuses.setAdapter(adapter);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                ConnectionHandler connectionHandler = new ConnectionHandler();
                connect = connectionHandler.connectionClass();
                if (connect!=null) {
                    String query = "Select * from busesTB";
                    Statement st = connect.createStatement();
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {
                        Bus bus = new Bus();
                        bus.setBusID(rs.getInt(1));
                        bus.setBusRoute(rs.getString(2));
                        bus.setBusLatitude(rs.getDouble(3));
                        bus.setBusLongitude(rs.getDouble(4));
                        busesList.add(bus);
                    }
                }

            } catch (Exception e) {
                Log.i("Main_SQLError: ", e.getMessage());
            }
            return null;
        }
    }

}
