package com.example.ecommerceproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdaptor extends PagerAdapter {
    LayoutInflater layoutInflater;
    Context context;
    public SliderAdaptor(Context context)
    {
        this.context=context;
    }

    int imagesArray[] =
            {
                    R.drawable.onboardscreen1,
                    R.drawable.onboardscreen2,
                    R.drawable.onboardscreen1

            };
    int headingArray[] =
            {
                    R.string.firstslide,
                    R.string.secondslide,
                    R.string.thirdslide
            };
    int descriptionArray[] =
            {
                    R.string.description,
                    R.string.description,
                    R.string.description
            };


    @Override
    public int getCount() {
        return headingArray.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sliding_layout, container, false);

        ImageView imageView = view.findViewById(R.id.ivslider);
        TextView heading = view.findViewById(R.id.tvheading);
        TextView description = view.findViewById(R.id.tvDescription);

        imageView.setImageResource(imagesArray[position]);
        heading.setText(headingArray[position]);
        description.setText(descriptionArray[position]);

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object; // check this
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
