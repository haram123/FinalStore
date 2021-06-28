package com.example.ecommerceproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<myCartModel> cartModelList;
    myCartAdaptor cartAdaptor;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    int overAllTotalAmount;
    TextView totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();

    }
    public void init()
    {
        totalAmount = findViewById(R.id.tvTotal);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        toolbar = (Toolbar) this.findViewById(R.id.my_cart_toolbar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(messageReciever, new IntentFilter("MyTotalAmount"));
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartModelList = new ArrayList<>();
        //cartModelList.add(new myCartModel("menshoes", "Men shoes", "shoes","maha","maham",123));

        cartAdaptor = new myCartAdaptor(this, cartModelList);
        recyclerView.setAdapter(cartAdaptor);


        firestore.collection("addtocart").document(firebaseAuth.getCurrentUser().getUid())
                .collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        myCartModel mycartmodel = document.toObject(myCartModel.class);
                        cartModelList.add(mycartmodel);
                        cartAdaptor.notifyDataSetChanged();
                    }
                }
                   else
                    {
                        Toast.makeText(getApplicationContext(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                    }
                }


        });


    }
   public BroadcastReceiver messageReciever = new BroadcastReceiver() {
       @Override
       public void onReceive(Context context, Intent intent) {
           int totalBill = intent.getIntExtra("totalAmount",0);
           totalAmount.setText("Total Amount: " + totalBill + "$");
       }
   };
}