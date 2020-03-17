package com.example.medicine.eCommerce26;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class AdminCategoryActivity extends AppCompatActivity
{
    private ImageView capsule,bottle,injection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);
        capsule= findViewById(R.id.capsule);
        injection= findViewById(R.id.injection);
        bottle=findViewById(R.id.bottle);


        capsule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(AdminCategoryActivity.this,Admin_Activity.class);
                intent.putExtra("category","capsule");
                startActivity(intent);
            }
        });
        bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(AdminCategoryActivity.this,Admin_Activity.class);
                intent.putExtra("category","bottle");
                startActivity(intent);
            }
        });
        injection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,Admin_Activity.class);
                intent.putExtra("category","injection");
                startActivity(intent);
            }
        });

    }
}
