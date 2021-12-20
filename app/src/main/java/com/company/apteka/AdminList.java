package com.company.apteka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminList extends AppCompatActivity {

    Connection connect;
    String connectionResult="";
    private RecAdapter adapter;
    private RecyclerView recyclerView;
    private Button add_product,add_category,add_manufacturer,add_prodiver;
    private List<ProductModel> list_product;
    private Spinner spinner_category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);

        init();

    }
    public void init(){
        recyclerView=findViewById(R.id.recyclerView_admin);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);//декорации
        recyclerView.addItemDecoration(dividerItemDecoration);
        add_product=findViewById(R.id.add_product_admin);
        add_category=findViewById(R.id.add_cat_admin);

        spinner_category=findViewById(R.id.spinner_category_admin);
        setSpinner();

        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminList.this,Add_Product.class);
                startActivity(intent);
            }
        });
        add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminList.this,Add_Cat.class);
                startActivity(intent);
            }
        });



    }

    public void setRecycler(String category){
        String categoryForRecycler=category;
        list_product =new ArrayList<>();
        if(categoryForRecycler.equals("Все записи")){
            try{
                ConnectionHelper connectionHelper =new ConnectionHelper();
                connect=connectionHelper.connection();
                if(connect!=null){
                    String querry="select * from products";
                    Statement st=connect.createStatement();
                    ResultSet resultSet = st.executeQuery(querry);

                    while(resultSet.next()){
                        ProductModel productModel =new ProductModel();
                        productModel.setId_product(resultSet.getString("id_product"));
                        productModel.setId_manufacturer(resultSet.getString("id_manufacturer"));
                        productModel.setName_product(resultSet.getString("name_product"));
                        productModel.setDecr(resultSet.getString("decr"));
                        productModel.setShelf_life(resultSet.getString("shelf_life"));
                        productModel.setBatch_number(resultSet.getString("batch_number"));
                        productModel.setId_provider(resultSet.getString("id_provider"));
                        productModel.setId_category(resultSet.getString("id_category"));
                        productModel.setPrice(resultSet.getString("price"));
                        list_product.add(productModel);
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

            adapter=new RecAdapter(this,list_product);
            if(list_product!=null){
                recyclerView.setAdapter(adapter);
                adapter.updateAdapter(list_product);
            }

        }
        else{
            try{
                ConnectionHelper connectionHelper =new ConnectionHelper();
                connect=connectionHelper.connection();
                if(connect!=null){
                    String querry="select * from products inner join category on category.id_category = products.id_category where category.name="+"'"+category+"'"+"";
                    Statement st=connect.createStatement();
                    ResultSet resultSet = st.executeQuery(querry);

                    while(resultSet.next()){
                        ProductModel productModel =new ProductModel();
                        productModel.setId_product(resultSet.getString("id_product"));
                        productModel.setId_manufacturer(resultSet.getString("id_manufacturer"));
                        productModel.setName_product(resultSet.getString("name_product"));
                        productModel.setDecr(resultSet.getString("decr"));
                        productModel.setShelf_life(resultSet.getString("shelf_life"));
                        productModel.setBatch_number(resultSet.getString("batch_number"));
                        productModel.setId_provider(resultSet.getString("id_provider"));
                        productModel.setId_category(resultSet.getString("id_category"));
                        productModel.setPrice(resultSet.getString("price"));
                        list_product.add(productModel);
                        System.out.println("По категории "+category+" внутри "+list_product.get(0));
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

            adapter=new RecAdapter(this,list_product);
            if(list_product!=null){
                recyclerView.setAdapter(adapter);
                adapter.updateAdapter(list_product);
            }

        }
    }



    public void setSpinner(){
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

        String[] name_category=new String[list_category.size()+1];
        name_category[0]="Все записи";
        System.out.println("Позиция1"+list_category.size());
        for(int i=1;i<list_category.size()+1;i++){

            name_category[i]=list_category.get(i-1).getName_category();
            System.out.println("Позиция в счетчике"+list_category.get(i-1));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,name_category);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener= new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = (String)parent.getItemAtPosition(position);
                setRecycler(category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner_category.setOnItemSelectedListener(itemSelectedListener);
    }
}