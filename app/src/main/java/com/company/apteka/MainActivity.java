package com.company.apteka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//    Connection connect;
//    String connectionResult="";

    Button reg,sing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reg=findViewById(R.id.reg_main);
        sing=findViewById(R.id.sing_main);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Reg.class);
                startActivity(intent);
            }
        });
        sing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Sing.class);
                startActivity(intent);
            }
        });
    }




















//    public void getTextFromSql(View v){
//        TextView textView=findViewById(R.id.textView);
//        TextView textView2=findViewById(R.id.textView2);
//
//        try{
//            ConnectionHelper connectionHelper =new ConnectionHelper();
//            connect=connectionHelper.connection();
//            if(connect!=null){
//                String querry="Select * from clients";
//                Statement st=connect.createStatement();
//                ResultSet resultSet=st.executeQuery(querry);
//
//                while(resultSet.next()){
//                    textView.setText(resultSet.getString(3));
//                    textView2.setText(resultSet.getString(2));
//                }
//            }
//            else {
//                connectionResult="Check Connection";
//            }
//        }
//        catch (Exception e){
//            Toast.makeText(this, connectionResult, Toast.LENGTH_SHORT).show();
//            Log.e("errоооor here 3 : ", e.getMessage());
//        }
//    }
}