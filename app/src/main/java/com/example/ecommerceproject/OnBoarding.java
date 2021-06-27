package com.example.ecommerceproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnBoarding extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdaptor sliderAdaptor;
    Button btnStart;
    TextView[] dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //for hiding status bar


        setContentView(R.layout.activity_on_boarding);

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        btnStart = findViewById(R.id.btnStart);
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        sliderAdaptor = new SliderAdaptor(this);
        viewPager.setAdapter(sliderAdaptor);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoarding.this, RegisterationActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
    private void addDots(int position)
    {
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for(int i=0;i<dots.length;i++)
        {
            dots[i]= new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40);
            dotsLayout.addView(dots[i]);

        }
        if(dots.length>0)
        {
            dots[position].setTextColor(getResources().getColor(R.color.lightpink));
        }

    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            if(position==0|| position==1)
            {
                btnStart.setVisibility(View.INVISIBLE);
            }
            else
            {
                btnStart.setVisibility(View.VISIBLE);
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}