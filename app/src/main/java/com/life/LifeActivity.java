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

    private static final int CELL_SIZE = 50;

    private ImageView[][] cellMatrix;

    @BindView(R.id.grid_layout)
    GridLayout gridLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        ButterKnife.bind(this);

        Point size = getDisplaySize();
        int columnCount = size.x / CELL_SIZE;
        int rowCount = size.y / CELL_SIZE;

        cellMatrix = new ImageView[columnCount][rowCount];

        gridLayout.setColumnCount(columnCount);
        gridLayout.setRowCount(rowCount);

        for (int cellColumnPosition = 0; cellColumnPosition < columnCount; cellColumnPosition++) {
            for (int cellRowPosition = 0; cellRowPosition < rowCount; cellRowPosition++) {
                ImageView imageView = new ImageView(this);
                imageView.setBackgroundColor(ContextCompat.getColor(this,
                        android.R.color.holo_green_light));

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.height = CELL_SIZE;
                params.width = CELL_SIZE;
                params.leftMargin = 2;
                params.rightMargin = 2;
                params.topMargin = 2;
                params.bottomMargin = 2;
                imageView.setLayoutParams(params);

                gridLayout.addView(imageView);
                cellMatrix[cellColumnPosition][cellRowPosition] = imageView;
            }
        }
    }

    private Point getDisplaySize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }
}
