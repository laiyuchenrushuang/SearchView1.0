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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private ListPopupWindow mListPop;
    private List<String> lists = new ArrayList<String>();
    private ArrayAdapter adapter;
    List<String> newList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lists.add("one");
        lists.add("two");
        lists.add("three");
        lists.add("four");
        lists.add("five");
        lists.add("six");
        mEditText = (EditText) findViewById(R.id.editText1);
        mListPop = new ListPopupWindow(MainActivity.this);
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, lists);
        mListPop.setAdapter(adapter);
        mListPop.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        mListPop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        mListPop.setAnchorView(mEditText);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
        mListPop.setModal(true);//设置是否是模式

        mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mEditText.setText(lists.get(position));
                mListPop.dismiss();
            }
        });
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newList.clear();
                if (null != charSequence) {
                    //增加过滤的功能
                    newList.clear();
                    Pattern pattern = Pattern.compile(charSequence.toString());
                    showLog(charSequence.toString());
                    for (int inedx = 0;inedx<lists.size();inedx++){
                        Matcher matcher = pattern.matcher(lists.get(inedx));
                        if(matcher.find()){
                            showLog(lists.get(inedx));
                            newList.add(lists.get(inedx));
                        }
                    }
                    if(newList.size() != 0){
                        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, newList);
                        mListPop.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        mListPop.show();
                    }else {
                        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, lists);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mEditText.setEnabled(true);
                mEditText.setFocusable(true);
            }
        });
    }

    public void showLog(String msg) {
        Log.d("lylog", " ->" + msg);
    }

}
