package ru.igorsharov.yand_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // получаем экземпляр элемента ListView
        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayList historyList =
                (ArrayList)getIntent().getSerializableExtra("HISTORY_LIST");


// используем адаптер данных
        ArrayAdapter<ArrayList> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, historyList);

        listView.setAdapter(adapter);

    }
}
