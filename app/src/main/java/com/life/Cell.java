package com.life;

import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

class Cell {

    private ImageView imageView;
    boolean isAlive;

    Cell(ImageView imageView) {
        this.imageView = imageView;
    }

    void create() {
        isAlive = true;
        imageView.setBackgroundColor(ContextCompat.getColor(imageView.getContext(),
                android.R.color.holo_green_light));
    }

    void delete() {
        isAlive = false;
        imageView.setBackgroundColor(ContextCompat.getColor(imageView.getContext(),
                android.R.color.white));
    }
}
