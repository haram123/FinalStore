package com.example.ecommerceproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    EditText aname,ano,acode,acity,aadress;

    Button btnadd;
    FirebaseFirestore f;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        aname=findViewById(R.id.aname);
        acity=findViewById(R.id.acity);
        ano=findViewById(R.id.ano);
        acode=findViewById(R.id.acode);
        aadress=findViewById(R.id.aaddress);
        btnadd=findViewById(R.id.btnadd);
        f=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data1=aname.getText().toString();
                String data2=ano.getText().toString();
                String data3=acity.getText().toString();
                String data4=aadress.getText().toString();
                String data5=acode.getText().toString();
                String adress="";
                if (!data1.isEmpty())
                {
                    adress+=data1;
                }
                if (!data2.isEmpty())
                {
                    adress+=data2;
                }

                if (!data3.isEmpty())
                {
                    adress+=data3;
                }
                if (!data4.isEmpty())
                {
                    adress+=data4;
                }
                if (!data1.isEmpty() && !data2.isEmpty() && !data3.isEmpty() && !data4.isEmpty())
                {
                    Map<String,String> m=new HashMap<>();
                    m.put("address",adress);
                    f.collection("currentuser").document(auth.getCurrentUser().getUid())
                            .collection("address").add(m).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity2.this, "address is added", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity2.this, "enter all data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }

            }
        });

    }
}
