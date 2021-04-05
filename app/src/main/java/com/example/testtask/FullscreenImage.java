package com.example.testtask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FullscreenImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image);

        ImageView imageView = findViewById(R.id.imageViewFull);

        Bundle bundle = getIntent().getExtras();

        Picasso.get()
                .load(bundle.getString("url"))
                .error(R.drawable.image_error)
                .into(imageView);
    }
}