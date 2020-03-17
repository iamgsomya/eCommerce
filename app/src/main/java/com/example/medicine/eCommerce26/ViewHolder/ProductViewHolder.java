package com.example.medicine.eCommerce26.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicine.eCommerce26.Interface.itemClickListner;
import com.example.medicine.eCommerce26.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtproductName,txtproductDescription;
    public ImageView imageView;
    public itemClickListner listner;


    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.product_image);
        txtproductName=itemView.findViewById(R.id.product_name);
        txtproductDescription=itemView.findViewById(R.id.product_description);
    }
    public  void setitemClickListner (itemClickListner listner)

    {
        this.listner=listner;

    }

    @Override
    public void onClick(View view)
    {listner.onClick(view,getAdapterPosition(),false);

    }
}
