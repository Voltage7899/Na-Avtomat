package com.company.apteka;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

    private final Context context;
    List<ProductModel> ListProduct;


    public RecAdapter(Context context,List<ProductModel> listProduct){
        this.context=context;
        this.ListProduct=listProduct;
    }

    @NonNull
    @Override
    public RecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_element,parent,false);

        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdapter.ViewHolder holder, int position) {
        holder.name.setText(ListProduct.get(position).getName_product());
        holder.price.setText("Цена: "+ListProduct.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return ListProduct.size();
    }

    public void updateAdapter(List<ProductModel> newList){
        ListProduct=newList;
        notifyDataSetChanged();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name,price;
        private final Context context;
        private ProductModel productModel;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context=context;
            name=itemView.findViewById(R.id.name_element);
            price=itemView.findViewById(R.id.price_element);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Intent i =new Intent(context,EditProduct.class);
            i.putExtra("id",productModel.getId_product());
            context.startActivity(i);
        }
    }
}
