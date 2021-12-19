package com.company.apteka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sing extends AppCompatActivity {
    Connection connect;
    String connectionResult="";
    EditText phone_field,pass_field;
    Button sing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing);
        phone_field=findViewById(R.id.phone_sing);
        pass_field=findViewById(R.id.pass_sing);
        sing=findViewById(R.id.sing_sing);

        sing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sing_sql();
            }
        });
    }

    public void sing_sql(){
        String phone=phone_field.getText().toString();
        String pass =pass_field.getText().toString();

        try{

            ConnectionHelper connectionHelper =new ConnectionHelper();
            connect=connectionHelper.connection();

            if(connect!=null){

                String querry="select phone from clients where phone="+phone+"";
                Statement st=connect.createStatement();
                ResultSet resultSet = st.executeQuery(querry);
                System.out.println(resultSet);
                while(resultSet.next()){
                    if(resultSet.getString(1).equals(phone)){
                        System.out.println("Запись " + resultSet.getString(1));
                        String querry2="select fio,phone,pass from clients where phone="+phone+"";
                        Statement st2=connect.createStatement();
                        ResultSet resultSet2 = st2.executeQuery(querry2);
                        while (resultSet2.next()){
                            System.out.println("Запись " + resultSet2.getString(1) + " "+resultSet2.getString(2));
                            if (resultSet2.getString(2).equals(phone) && resultSet2.getString(3).equals(pass) && resultSet2.getString(1).equals("BigBoss")){
                                Toast.makeText(this, "Добро пожаловать BigBoss", Toast.LENGTH_SHORT).show();
                                Intent intent2 = new Intent(Sing.this,AdminList.class);
                                startActivity(intent2);

                            }
                            else if(resultSet2.getString(2).equals(phone) && resultSet2.getString(3).equals(pass)){
                                System.out.println();
                                Intent intent1 = new Intent(Sing.this,UserList.class);
                                startActivity(intent1);
                            }
                            else{
                                Toast.makeText(this, "Неправильный пароль телефон", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                    else{
                        Toast.makeText(this, "Пользователя с таким телефоном не существует", Toast.LENGTH_SHORT).show();
                    }
                }




            }
            else {
                connectionResult="Check Connection";
            }
        }
        catch (Exception e){
            Toast.makeText(this, connectionResult, Toast.LENGTH_SHORT).show();
            Log.e("errоооor here 3 : ", e.getMessage());
        }
    }
}