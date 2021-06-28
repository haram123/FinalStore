package com.example.ecommerceproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;



import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Fragment fragmentmain;
    FirebaseAuth auth;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        fragmentmain=new Fragmentmain();
        Fragmentattach(fragmentmain);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.home_toolBar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);



    }

    private void Fragmentattach(Fragment fragmentmain) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_m,fragmentmain);
        transaction.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main_menu, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_logout)
        {
            auth.signOut();
            Intent intent = new Intent(MainActivity.this, RegisterationActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.menu_my_cart)
        {

            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        }
        return true;
    }
}