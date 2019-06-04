package com.example.mainactivity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import data.BmiModel;
import data.BmiModelAdapter;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    @Override
    protected  void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.list_layout);

        mListView = findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView() {
        /* Cursor data = mDatabaseHelper.getData();

        ArrayList<BmiModel> listData = new ArrayList<>();
        while(data.moveToNext()){
            BmiModel model = new BmiModel();
            model.setHeight(data.getColumnIndex());
            model.setWeight(1);
            model.setResult(1);
            model.setId(1);
            listData.add(model);
        }*/

        ArrayList<BmiModel> listData = mDatabaseHelper.getDataCorrectly();

        ListAdapter adapter = new BmiModelAdapter(this, listData);
        mListView.setAdapter(adapter);
    }
}
