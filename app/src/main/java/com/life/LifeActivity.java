package com.life;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.GridLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LifeActivity extends AppCompatActivity {

    private static final int GRID_SPAN = 10;

    @BindView(R.id.grid_layout)
    GridLayout gridLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        ButterKnife.bind(this);

        gridLayout.setRowCount(GRID_SPAN);
        gridLayout.setColumnCount(GRID_SPAN);

        Point size = getDisplaySize();
        int cellSize = size.x / GRID_SPAN;

        for (int cellPosition = 0; cellPosition < GRID_SPAN; cellPosition++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundColor(ContextCompat.getColor(this,
                    android.R.color.holo_green_light));

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.height = cellSize;
            params.width = cellSize;
            params.leftMargin = 2;
            params.rightMargin = 2;
            imageView.setLayoutParams(params);

            gridLayout.addView(imageView);
        }
    }

    private Point getDisplaySize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }
}
