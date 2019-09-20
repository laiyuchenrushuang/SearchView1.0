package com.ly.spinersearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> lists = new ArrayList<String>();
    private ArrayAdapter adapter1;
    private ArrayAdapter adapter2;
    private ArrayAdapter adapter3;
    Spinner sp1;
    Spinner sp2;
    Spinner sp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        sp3 = findViewById(R.id.sp3);
        lists.add("");
        lists.add("1");
        lists.add("2");
        lists.add("3");
        lists.add("4");
        lists.add("5");
        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.addAll(lists);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.addAll(lists);
        adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.addAll(lists);

        sp1.setAdapter(adapter1);
        sp2.setAdapter(adapter2);
        sp3.setAdapter(adapter3);

        bindEvent();
    }

    ArrayList<String> list = new ArrayList<>();

    private void bindEvent() {
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapter2.clear();
                adapter3.clear();
                adapter2.addAll(lists);
                adapter3.addAll(lists);
                adapter2.remove(sp1.getSelectedItem().toString());
                adapter2.notifyDataSetChanged();
                adapter3.remove(sp2.getSelectedItem().toString());
                adapter3.remove(sp1.getSelectedItem().toString());
                adapter3.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapter1.clear();
                adapter3.clear();
                adapter1.addAll(lists);
                adapter3.addAll(lists);
                adapter1.remove(sp2.getSelectedItem().toString());
                adapter1.notifyDataSetChanged();
                adapter3.remove(sp2.getSelectedItem().toString());
                adapter3.remove(sp1.getSelectedItem().toString());
                adapter3.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapter2.clear();
                adapter1.clear();
                adapter2.addAll(lists);
                adapter1.addAll(lists);
                adapter2.remove(sp3.getSelectedItem().toString());
                adapter2.notifyDataSetChanged();
                adapter1.remove(sp2.getSelectedItem().toString());
                adapter1.remove(sp3.getSelectedItem().toString());
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void showLog(String msg) {
        Log.d("lylog", " ->" + msg);
    }

}
