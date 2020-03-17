package com.example.medicine.eCommerce26;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class register_Activity extends AppCompatActivity {
    public Button CreateAccountButton;
    private EditText InputName,InputPhoneNumber,InputPassword;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        CreateAccountButton=findViewById(R.id.reg_btn);
        InputName=findViewById(R.id.reg_username_input);
        InputPhoneNumber=findViewById(R.id.reg_phone_number_input);
        InputPassword=findViewById(R.id.reg_password_input);
        loadingBar=new ProgressDialog(this);


        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();

            }
        });
    }

    private void CreateAccount() {
        String name=InputName.getText().toString();
        String phone=InputPhoneNumber.getText().toString();
        String password=InputPassword.getText().toString();
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText( this,"please write your name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone))
        {
            Toast.makeText( this,"please write your phone",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText( this,"please write your password",Toast.LENGTH_SHORT).show();
        }
        else
        {
         loadingBar.setTitle("CreateAccount");
         loadingBar.setMessage("Please,wait while is checking the credentials");
         loadingBar.setCanceledOnTouchOutside(false);
         loadingBar.show();
         VaildatephoneNumber(name,phone,password);
        }
    }

    private void VaildatephoneNumber(final String name, final String phone, final String password) {
        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("users").child(phone).exists())){
                    HashMap<String,Object> userdataMap=new HashMap<>();
                    userdataMap.put("phone",phone);
                    userdataMap.put("password",password);
                    userdataMap.put("name",name);


                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(register_Activity.this,"congratulation,your account is created",Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent=new Intent(register_Activity.this,loginActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                        loadingBar.dismiss();
                                        Toast.makeText(register_Activity.this,"Network Error",Toast.LENGTH_SHORT).show();

                                }
                            });


                }
                else
                {
                    Toast.makeText(register_Activity.this,"This"+phone+"already exit",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(register_Activity.this,"please try again using another phone number",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(register_Activity.this,MainActivity.class);
                    startActivity(intent);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
