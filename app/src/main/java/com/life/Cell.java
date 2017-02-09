package com.life;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import static com.life.LifeActivity.isPlaying;

class Cell {

    private ImageView imageView;
    boolean isAlive;
    boolean willBeAlive;

    Cell(ImageView imageView) {
        this.imageView = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPlaying) {
                    if (isAlive) {
                        dead();
                        isAlive = false;
                    } else {
                        alive();
                        isAlive = true;
                    }
                }
            }
        });
    }

    void alive() {
        imageView.setBackgroundColor(ContextCompat.getColor(imageView.getContext(),
                android.R.color.holo_green_light));
    }

    void dead() {
        imageView.setBackgroundColor(ContextCompat.getColor(imageView.getContext(),
                android.R.color.white));
    }

    ImageView getImageView() {
        return imageView;
    }
}
