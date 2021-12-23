package com.company.apteka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Add_Product extends AppCompatActivity {

    private Connection connect;
    private String connectionResult="";
    private EditText name_product_field;
    private EditText decr_product_field;
    private EditText shelf_life_field;
    private EditText batch_number_field;
    private EditText price_product_field;
    private Button add_product;
    private int chosen_category,chosen_manufac,chosen_provider;

    private Spinner spinner_cat,spinner_manufac,spinner_provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product);

        name_product_field=findViewById(R.id.name_product_add);
        decr_product_field=findViewById(R.id.decr_add);
        shelf_life_field=findViewById(R.id.shelf_life_add);
        batch_number_field=findViewById(R.id.batch_number);
        price_product_field=findViewById(R.id.price_add);

        spinner_cat=findViewById(R.id.spinner_category_add);
        spinner_manufac=findViewById(R.id.spinner_manufac_add);
        spinner_provider=findViewById(R.id.spinner_provider_add);

        add_product=findViewById(R.id.add_product_add);

        setSpinner_cat();
        setSpinner_manufac();
        setSpinner_provider();

        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_product=name_product_field.getText().toString();
                String decr_product=decr_product_field.getText().toString();
                String shelf_life=shelf_life_field.getText().toString();
                String batch_product=batch_number_field.getText().toString();
                int price_product=Integer.parseInt(price_product_field.getText().toString());

                try{
                    ConnectionHelper connectionHelper =new ConnectionHelper();
                    connect=connectionHelper.connection();
                    if(connect!=null){
                        String querry="INSERT INTO products (id_manufacturer,name_product,decr,shelf_life,batch_number,id_provider,id_category,price) VALUES ("+chosen_manufac+",'"+name_product+"','"+decr_product+"','"+shelf_life+"',"+batch_product+","+chosen_provider+","+chosen_category+","+price_product+")";
                        Statement st=connect.createStatement();
                        st.executeUpdate(querry);

                        finish();
                    }
                    else {
                        connectionResult="Check Connection";
                    }
                }
                catch (Exception e){
                    Toast.makeText(Add_Product.this, connectionResult, Toast.LENGTH_SHORT).show();
                    Log.e("errоооor here 3 : ", e.getMessage());
                }
            }
        });
    }



    public void setSpinner_cat(){
        List<Category_Model> list_category=new ArrayList<>();
        try{
            ConnectionHelper connectionHelper =new ConnectionHelper();
            connect=connectionHelper.connection();
            if(connect!=null){
                String querry="select * from category";
                Statement st=connect.createStatement();
                ResultSet resultSet = st.executeQuery(querry);

                while(resultSet.next()){
                    Category_Model category_model=new Category_Model();
                    category_model.setId_category(resultSet.getString("id_category"));
                    category_model.setName_category(resultSet.getString("name"));

                    list_category.add(category_model);
                }
                System.out.println(list_category.get(0).getName_category());
            }
            else {
                connectionResult="Check Connection";
            }
        }
        catch (Exception e){
            Toast.makeText(this, connectionResult, Toast.LENGTH_SHORT).show();
            Log.e("errоооor here 3 : ", e.getMessage());
        }

        String[] name_category=new String[list_category.size()];
        System.out.println("Позиция1"+list_category.size());
        for(int i=0;i<list_category.size();i++){

            name_category[i]=list_category.get(i).getName_category();
            System.out.println("Позиция в счетчике"+list_category.get(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,name_category);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cat.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener= new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosen_category = position+1;
                System.out.println("Выбрана позиция категории "+chosen_category);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner_cat.setOnItemSelectedListener(itemSelectedListener);
    }
    public void setSpinner_manufac(){
        List<Manufac_Model> list_category=new ArrayList<>();
        try{
            ConnectionHelper connectionHelper =new ConnectionHelper();
            connect=connectionHelper.connection();
            if(connect!=null){
                String querry="select * from manufacturer";
                Statement st=connect.createStatement();
                ResultSet resultSet = st.executeQuery(querry);

                while(resultSet.next()){
                    Manufac_Model manufac_model=new Manufac_Model();
                    manufac_model.setId(resultSet.getString("id_manufacturer"));
                    manufac_model.setName_manufac(resultSet.getString("name"));

                    list_category.add(manufac_model);
                }
                System.out.println(list_category.get(0).getName_manufac());
            }
            else {
                connectionResult="Check Connection";
            }
        }
        catch (Exception e){
            Toast.makeText(this, connectionResult, Toast.LENGTH_SHORT).show();
            Log.e("errоооor here 3 : ", e.getMessage());
        }

        String[] name_category=new String[list_category.size()];
        System.out.println("Позиция1"+list_category.size());
        for(int i=0;i<list_category.size();i++){

            name_category[i]=list_category.get(i).getName_manufac();
            System.out.println("Позиция в счетчике"+list_category.get(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,name_category);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_manufac.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener= new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosen_manufac=position+1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner_manufac.setOnItemSelectedListener(itemSelectedListener);
    }
    public void setSpinner_provider(){
        List<Provider_Model> list_category=new ArrayList<>();
        try{
            ConnectionHelper connectionHelper =new ConnectionHelper();
            connect=connectionHelper.connection();
            if(connect!=null){
                String querry="select * from provider";
                Statement st=connect.createStatement();
                ResultSet resultSet = st.executeQuery(querry);

                while(resultSet.next()){
                    Provider_Model provider_model=new Provider_Model();
                    provider_model.setId(resultSet.getString("id_provider"));
                    provider_model.setPhone(resultSet.getString("phone"));
                    provider_model.setLico(resultSet.getString("legal_entity"));
                    provider_model.setName(resultSet.getString("name"));

                    list_category.add(provider_model);
                }
                System.out.println(list_category.get(0).getName());
            }
            else {
                connectionResult="Check Connection";
            }
        }
        catch (Exception e){
            Toast.makeText(this, connectionResult, Toast.LENGTH_SHORT).show();
            Log.e("errоооor here 3 : ", e.getMessage());
        }

        String[] name_category=new String[list_category.size()];
        System.out.println("Позиция1"+list_category.size());
        for(int i=0;i<list_category.size();i++){

            name_category[i]=list_category.get(i).getName();
            System.out.println("Позиция в счетчике"+list_category.get(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,name_category);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_provider.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener= new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosen_provider=position+1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner_provider.setOnItemSelectedListener(itemSelectedListener);
    }
}