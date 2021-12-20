package com.company.apteka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Add_Cat extends AppCompatActivity {

    Connection connect;
    String connectionResult="";
    EditText name_cat,name_manufac,phone_provider,lico_provider,name_provider;
    Button add_cat,add_manufac,add_provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__cat);

        name_cat=findViewById(R.id.name_cat_add_cat);
        name_manufac=findViewById(R.id.name_manufacturer_add_cat);
        phone_provider=findViewById(R.id.phone_provider_add_cat);
        lico_provider=findViewById(R.id.lico_add_cat);
        name_provider=findViewById(R.id.name_provider_add_cat);
        add_cat=findViewById(R.id.add_cat_button);
        add_manufac=findViewById(R.id.add_manufacturer_button);
        add_provider=findViewById(R.id.add_provider_button);

        init();
    }

    public void init(){

        add_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=name_cat.getText().toString();

                try{
                    ConnectionHelper connectionHelper =new ConnectionHelper();
                    connect=connectionHelper.connection();
                    if(connect!=null){
                        String querry="INSERT INTO category (name) VALUES ('"+name+"')";
                        Statement st=connect.createStatement();
                        st.executeUpdate(querry);


                    }
                    else {
                        connectionResult="Check Connection";
                    }
                }
                catch (Exception e){
                    Toast.makeText(Add_Cat.this, connectionResult, Toast.LENGTH_SHORT).show();
                    Log.e("errоооor here 3 : ", e.getMessage());
                }
            }
        });





        add_manufac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=name_manufac.getText().toString();
            }
        });





        add_provider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=phone_provider.getText().toString();
                String lico=lico_provider.getText().toString();
                String name=name_provider.getText().toString();
            }
        });

    }
}