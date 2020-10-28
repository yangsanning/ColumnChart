package ysn.com.demo.columnchart;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

import ysn.com.demo.columnchart.bean.ProgressColumn;
import ysn.com.view.columnchart.progress.ProgressColumnView;

public class MainActivity extends AppCompatActivity {

    private ProgressColumnView progressColumnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressColumnView = findViewById(R.id.main_activity_progress_column_view);

        ArrayList<String> xCoordinateList = new ArrayList();
        xCoordinateList.add("0");
        xCoordinateList.add("10");
        xCoordinateList.add("20");
        xCoordinateList.add("30");
        xCoordinateList.add("40");
        xCoordinateList.add("50");
        xCoordinateList.add("60");
        progressColumnView.setXCoordinateList(xCoordinateList, 60);

        setDatas();

        findViewById(R.id.main_activity_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDatas();
            }
        });
    }

    private void setDatas() {
        ArrayList datas = new ArrayList();
        for (int i = 1; i < 13; i++) {
            int randomInt = getRandomInt(60);
            datas.add(new ProgressColumn(randomInt, getRandomInt(randomInt), i + "月"));
        }
        progressColumnView.setDatas(datas);
    }

    private int getRandomInt(int bound) {
        return Math.max(new Random().nextInt(bound), 1);
    }
}