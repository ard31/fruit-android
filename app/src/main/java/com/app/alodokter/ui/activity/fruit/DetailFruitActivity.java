package com.app.alodokter.ui.activity.fruit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.alodokter.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailFruitActivity extends AppCompatActivity {

    @BindView(R.id.carouselView)
    CarouselView carouselView;

    int[] sampleImages = {R.drawable.fruit_mangga, R.drawable.fruit_naga, R.drawable.fruit_wortel};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fruit);

        ButterKnife.bind(this);

        ImageListener imageListener = (position, imageView) -> imageView.setImageResource(sampleImages[position]);
        carouselView = carouselView.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
    }

    @OnClick({R.id.ivBack})
    public void onBack(View view) {
        onBackPressed();
    }


}
