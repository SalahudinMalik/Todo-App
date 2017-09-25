package com.example.gallantgames.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String msgText;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , EditFieldClass.class);
                intent.putExtra(Intents_Constants.INTENT_MESSAGE_DATA,arrayList.get(position).toString());
                intent.putExtra((Intents_Constants.INTENT_ITEM_POSITION) , position);
                startActivityForResult(intent , Intents_Constants.INTENT_REQUEST_CODE_TWO);

            }
        });

    }
    public void onClick(View v)
    {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this , EditFieldClass.class);
        startActivityForResult(intent , Intents_Constants.INTENT_REQUEST_CODE);

    }
    public void onActivityResult(int requestCode , int resultCode , Intent data)
    {
        if (resultCode == Intents_Constants.INTENT_RESULT_CODE)
        {
            msgText = data.getStringExtra(Intents_Constants.INTENT_MESSAGE_FIELD);
            arrayList.add(msgText);
            arrayAdapter.notifyDataSetChanged();
        }
        else if (resultCode == Intents_Constants.INTENT_RESULT_CODE_TWO)
        {
            msgText = data.getStringExtra(Intents_Constants.INTENT_CHANGED_MESSAGE);
            position =  data.getIntExtra(Intents_Constants.INTENT_ITEM_POSITION, -1);
            arrayList.remove(position);
            arrayList.add(position , msgText);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
