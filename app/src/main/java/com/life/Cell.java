package com.life;

import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

class Cell {

    private ImageView imageView;

    Cell(ImageView imageView) {
        this.imageView = imageView;
    }

    void create() {
        imageView.setBackgroundColor(ContextCompat.getColor(imageView.getContext(),
                android.R.color.holo_green_light));
    }

    void delete() {
        imageView.setBackgroundColor(ContextCompat.getColor(imageView.getContext(),
                android.R.color.white));
    }
}
