package com.example.ecommerceproject;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class informationactivity extends AppCompatActivity {
    ImageView iminfo;
    TextView tvnameinfo, tvpriceinfo, tvno;
    private FirebaseFirestore fc;
    FirebaseAuth auth;
    products p = null;
    allproducts s = null;
    Button cart, buy;
    ImageView imadd, imremove;
    int totalquantity = 1;
    int totalprice = 0;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informationactivity);

        iminfo = findViewById(R.id.iminfo);
        tvnameinfo = findViewById(R.id.tvnameinfo);
        tvpriceinfo = findViewById(R.id.tvpriceinfo);
        cart = findViewById(R.id.cart);
        buy = findViewById(R.id.buy);
        imadd = findViewById(R.id.imadd);
        imremove = findViewById(R.id.imminus);
        tvno = findViewById(R.id.tvno);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.detailed_toolbar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fc = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        final Object o = getIntent().getSerializableExtra("information");
        if (o instanceof products) {
            p = (products) o;

        } else if (o instanceof allproducts) {
            s = (allproducts) o;

        }

        if (p != null) {
            Glide.with(getApplicationContext()).load(p.getImage()).into(iminfo);
            tvnameinfo.setText(p.getName());
            tvpriceinfo.setText(String.valueOf(p.getPrice()));
            totalprice = p.getPrice() * totalquantity;
        }

        if (s != null) {
            Glide.with(getApplicationContext()).load(s.getI()).into(iminfo);
            tvnameinfo.setText(s.getN());
            tvpriceinfo.setText(String.valueOf(s.getP()));

            totalprice = s.getP() * totalquantity;
        }
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart();
                //Toast.makeText(informationactivity.this, ""+tvnameinfo.getText(), Toast.LENGTH_SHORT).show();
            }


        });
        imadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalquantity < 10) {
                    totalquantity++;
                    tvno.setText(String.valueOf(totalquantity));
                    if (s != null) {
                        totalprice = s.getP() * totalquantity;
                    }
                    if (p != null) {
                        totalprice = p.getPrice() * totalquantity;
                    }

                }
            }
        });
        imremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalquantity < 10) {
                    totalquantity--;
                    tvno.setText(String.valueOf(totalquantity));

                }
            }
        });


    }


    private void cart() {
        String ctime, cdate;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat cd = new SimpleDateFormat("MM dd,yyyy");
        cdate = cd.format(calendar.getTime());
        SimpleDateFormat ct = new SimpleDateFormat("HH:mm:ss a");
        ctime = ct.format(calendar.getTime());
        final HashMap<String, Object> cartmap = new HashMap<>();
        cartmap.put("productname", tvnameinfo.getText().toString());
        cartmap.put("productprice", tvpriceinfo.getText().toString());
        cartmap.put("currenttime", ctime);
        cartmap.put("currentdate", cdate);
        cartmap.put("totalquantity", tvno.getText().toString());
        cartmap.put("totalrpice", totalprice);
        fc.collection("addtocart").document(auth.getCurrentUser().getUid()).collection("users")
                .add(cartmap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(informationactivity.this, "it is added to cart", Toast.LENGTH_SHORT).show();
                finish();
            }
            // fc.collection("users")
            //.add(cartmap)
            //.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            //  @Override
            //public void onSuccess(DocumentReference documentReference) {

            //  Toast.makeText(informationactivity.this, "it is added to cart", Toast.LENGTH_SHORT).show();
            //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
            //}
            // })
            //.addOnFailureListener(new OnFailureListener() {
            //  @Override
            //public void onFailure(@NonNull Exception e) {
            //  Log.w(TAG, "Error adding document", e);

            //}
            //});


        });
    }
}






