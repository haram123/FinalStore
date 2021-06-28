package com.example.ecommerceproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myCartAdaptor extends RecyclerView.Adapter<myCartAdaptor.ViewHolder> {
    Context context;
    List<myCartModel> list;
    int totalAmount;

    public myCartAdaptor(Context context, List<myCartModel> list)
    {
        this.context=context;
        this.list=list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View w=(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_items,parent,false));
        return new ViewHolder(w);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(list.get(position).getCurrentdate());
        holder.time.setText(list.get(position).getCurrenttime());
        holder.price.setText(list.get(position).getProductprice());
        holder.name.setText(list.get(position).getProductname());
        holder.totalquantity.setText(list.get(position).getTotalquantity());
        holder.totalprice.setText(String.valueOf(list.get(position).getTotalrpice()));

        totalAmount = totalAmount + list.get(position).getTotalrpice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalAmount);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, price, date, time,totalquantity, totalprice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            totalprice = itemView.findViewById(R.id.total_price);
            totalquantity = itemView.findViewById(R.id.total_quantity);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
