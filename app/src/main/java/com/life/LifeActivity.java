package com.life;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LifeActivity extends AppCompatActivity {

    private static final int GRID_SPAN = 100;

    @BindView(R.id.grid_layout)
    GridLayout gridLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        ButterKnife.bind(this);

        gridLayout.setRowCount(GRID_SPAN);
        gridLayout.setColumnCount(GRID_SPAN);
    }
}
