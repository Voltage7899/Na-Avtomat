package com.company.apteka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
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
    private String current_category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);
        add_test_prod();
        init();

    }

    public void add_test_prod() {
        try{
            ConnectionHelper connectionHelper =new ConnectionHelper();
            connect=connectionHelper.connection();
            if(connect!=null){
                String querry="exec products_default";
                Statement st=connect.createStatement();
                st.executeUpdate(querry);



            }
            else {
                connectionResult="Check Connection";
            }
        }
        catch (Exception e){
            Toast.makeText(AdminList.this, connectionResult, Toast.LENGTH_SHORT).show();
            Log.e("errоооor here 3 : ", e.getMessage());
        }
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
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position=viewHolder.getAdapterPosition();
                int id=Integer.parseInt(((RecAdapter)recyclerView.getAdapter()).ListProduct.get(position).getId_product());
                try{
                    ConnectionHelper connectionHelper =new ConnectionHelper();
                    connect=connectionHelper.connection();
                    if(connect!=null){
                        String querry="DELETE FROM products WHERE id_product="+id+"";
                        Statement st=connect.createStatement();
                        st.executeUpdate(querry);
                        init();


                    }
                    else {
                        connectionResult="Check Connection";
                    }
                }
                catch (Exception e){
                    Toast.makeText(AdminList.this, connectionResult, Toast.LENGTH_SHORT).show();
                    Log.e("errоооor here 3 : ", e.getMessage());
                }
            }
        }).attachToRecyclerView(recyclerView);

    }

    public void setRecycler(){
        String categoryForRecycler=current_category;
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
               // adapter.updateAdapter(list_product);
            }
            System.out.println("Весь список до рестарта " + list_product);
        }
        else{
            try{
                ConnectionHelper connectionHelper =new ConnectionHelper();
                connect=connectionHelper.connection();
                if(connect!=null){
                    String querry="select * from products inner join category on category.id_category = products.id_category where category.name="+"'"+categoryForRecycler+"'"+"";
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
                        System.out.println("По категории "+categoryForRecycler+" внутри "+list_product.get(0));
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
                //adapter.updateAdapter(list_product);
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
                current_category = (String)parent.getItemAtPosition(position);
                setRecycler();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner_category.setOnItemSelectedListener(itemSelectedListener);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        init();
//    }

    @Override
    protected void onRestart() {
        super.onRestart();
        init();

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        System.out.println("Список во время резюма "+list_product);
//
//    }
}