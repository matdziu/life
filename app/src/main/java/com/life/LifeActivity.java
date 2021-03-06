package com.life;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LifeActivity extends AppCompatActivity {

    private static final int CELL_SIZE = 50;
    private static final long ANIMATION_PERIOD = 100;

    private int rowCount;
    private int columnCount;
    public static boolean isPlaying;

    private Handler handler;
    private Cell[][] cellMatrix;
    private TimerTask timerTask;

    @BindView(R.id.grid_layout)
    GridLayout gridLayout;

    @BindView(R.id.fab_play)
    FloatingActionButton fabPlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        ButterKnife.bind(this);
        handler = new Handler();

        Point size = getDisplaySize();
        rowCount = size.y / CELL_SIZE;
        columnCount = size.x / CELL_SIZE;

        cellMatrix = new Cell[rowCount][columnCount];

        gridLayout.setColumnCount(columnCount);
        gridLayout.setRowCount(rowCount);

        initState();
        refreshView();
    }

    @OnClick(R.id.fab_play)
    public void onPlayButtonClicked() {
        if (!isPlaying) {
            fabPlay.setImageResource(R.drawable.ic_stop);
            isPlaying = true;
            refreshView();
            calculate();
        } else {
            fabPlay.setImageResource(R.drawable.ic_play);
            isPlaying = false;
            deInit();
            refreshView();
        }
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

    private void initState() {
        for (int cellRowPosition = 0; cellRowPosition < rowCount; cellRowPosition++) {
            for (int cellColumnPosition = 0; cellColumnPosition < columnCount; cellColumnPosition++) {
                Cell cell = createCell();
                cellMatrix[cellRowPosition][cellColumnPosition] = cell;
                gridLayout.addView(cellMatrix[cellRowPosition][cellColumnPosition].getImageView());
            }
        }
    }

    private void deInit() {
        timerTask.cancel();
        for (int cellRowPosition = 0; cellRowPosition < rowCount; cellRowPosition++) {
            for (int cellColumnPosition = 0; cellColumnPosition < columnCount; cellColumnPosition++) {
                cellMatrix[cellRowPosition][cellColumnPosition].isAlive = false;
            }
        }
    }

    private void calculate() {
        Timer timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (int cellRowPosition = 0; cellRowPosition < rowCount; cellRowPosition++) {
                            for (int cellColumnPosition = 0; cellColumnPosition < columnCount; cellColumnPosition++) {
                                cellMatrix[cellRowPosition][cellColumnPosition].willBeAlive
                                        = decide(cellRowPosition, cellColumnPosition);
                            }
                        }
                        for (int cellRowPosition = 0; cellRowPosition < rowCount; cellRowPosition++) {
                            for (int cellColumnPosition = 0; cellColumnPosition < columnCount; cellColumnPosition++) {
                                cellMatrix[cellRowPosition][cellColumnPosition].isAlive
                                        = cellMatrix[cellRowPosition][cellColumnPosition].willBeAlive;
                            }
                        }
                        refreshView();
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, ANIMATION_PERIOD);
    }

    private void refreshView() {
        for (int cellRowPosition = 0; cellRowPosition < rowCount; cellRowPosition++) {
            for (int cellColumnPosition = 0; cellColumnPosition < columnCount; cellColumnPosition++) {
                if (cellMatrix[cellRowPosition][cellColumnPosition].isAlive) {
                    cellMatrix[cellRowPosition][cellColumnPosition].alive();
                } else {
                    cellMatrix[cellRowPosition][cellColumnPosition].dead();
                }
            }
        }
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

        return neighbours == 3 || cellMatrix[rowPosition][columnPosition].isAlive && neighbours == 2;
    }
}
