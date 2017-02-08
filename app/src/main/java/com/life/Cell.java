package com.life;

import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

class Cell {

    private ImageView imageView;
    boolean isAlive;

    Cell(ImageView imageView) {
        this.imageView = imageView;
    }

    void alive() {
        isAlive = true;
        imageView.setBackgroundColor(ContextCompat.getColor(imageView.getContext(),
                android.R.color.holo_green_light));
    }

    void dead() {
        isAlive = false;
        imageView.setBackgroundColor(ContextCompat.getColor(imageView.getContext(),
                android.R.color.white));
    }

    ImageView getImageView() {
        return imageView;
    }
}
