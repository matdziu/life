package com.life;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.GridLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LifeActivity extends AppCompatActivity {

    private static final int CELL_SIZE = 50;
    private static final long ANIMATION_DELAY = 1000;

    private int rowCount;
    private int columnCount;

    private Cell[][] cellMatrix;

    @BindView(R.id.grid_layout)
    GridLayout gridLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        ButterKnife.bind(this);

        Point size = getDisplaySize();
        rowCount = size.y / CELL_SIZE;
        columnCount = size.x / CELL_SIZE;

        cellMatrix = new Cell[rowCount][columnCount];

        gridLayout.setColumnCount(columnCount);
        gridLayout.setRowCount(rowCount);

        initCellMatrix();
        animate();
    }

    private Point getDisplaySize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    private Cell createCell() {
        ImageView imageView = new ImageView(this);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.height = CELL_SIZE;
        params.width = CELL_SIZE;
        params.leftMargin = 2;
        params.rightMargin = 2;
        params.topMargin = 2;
        params.bottomMargin = 2;
        imageView.setLayoutParams(params);
        return new Cell(imageView);
    }

    private void initCellMatrix() {
        int middleRow = rowCount / 2;
        int middleColumn = columnCount / 2;
        for (int cellRowPosition = 0; cellRowPosition < rowCount; cellRowPosition++) {
            for (int cellColumnPosition = 0; cellColumnPosition < columnCount; cellColumnPosition++) {
                Cell cell = createCell();
                cellMatrix[cellRowPosition][cellColumnPosition] = cell;


                // initial state - glider
                if (cellRowPosition == middleRow && cellColumnPosition == middleColumn) {
                    cell.alive();
                }
                if (cellRowPosition == middleRow + 1 && cellColumnPosition == middleColumn) {
                    cell.alive();
                }
                if (cellRowPosition == middleRow + 2 && cellColumnPosition == middleColumn) {
                    cell.alive();
                }
                if (cellRowPosition == middleRow + 2 && cellColumnPosition == middleColumn - 1) {
                    cell.alive();
                }
                if (cellRowPosition == middleRow + 1 && cellColumnPosition == middleColumn - 2) {
                    cell.alive();
                }
            }
        }
    }

    private void animate() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int cellRowPosition = 0; cellRowPosition < rowCount; cellRowPosition++) {
                    for (int cellColumnPosition = 0; cellColumnPosition < columnCount; cellColumnPosition++) {
                        if (decide(cellRowPosition, cellColumnPosition)) {
                            cellMatrix[cellRowPosition][cellColumnPosition].alive();
                        } else {
                            cellMatrix[cellRowPosition][cellColumnPosition].dead();
                        }
                        gridLayout.addView(cellMatrix[cellRowPosition][cellColumnPosition].getImageView());
                    }
                }
            }
        }, ANIMATION_DELAY);
    }

    private boolean decide(int rowPosition, int columnPosition) {
        int neighbours = 0;

        if (columnPosition > 0) {
            if (cellMatrix[rowPosition][columnPosition - 1].isAlive)
                neighbours++;

            if (rowPosition > 0 && cellMatrix[rowPosition - 1][columnPosition - 1].isAlive)
                neighbours++;

            if (rowPosition < rowCount - 1 && cellMatrix[rowPosition + 1][columnPosition - 1].isAlive)
                neighbours++;

        }

        if (columnPosition < columnCount - 1) {
            if (cellMatrix[rowPosition][columnPosition + 1].isAlive)
                neighbours++;
            if (rowPosition > 0 && cellMatrix[rowPosition - 1][columnPosition + 1].isAlive)
                neighbours++;
            if (rowPosition < rowCount - 1 && cellMatrix[rowPosition + 1][columnPosition + 1].isAlive)
                neighbours++;
        }
        if (rowPosition > 0 && cellMatrix[rowPosition - 1][columnPosition].isAlive)
            neighbours++;
        if (rowPosition < rowCount - 1 && cellMatrix[rowPosition + 1][columnPosition].isAlive)
            neighbours++;

        return neighbours == 3 || neighbours == 2;
    }
}
