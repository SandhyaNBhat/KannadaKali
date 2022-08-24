package com.spcreations.kannadakali;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConversationsActivity extends AppCompatActivity {
    /*
    * This activity is included in Version 2.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        // Create ArrayList and Store the numbers in ArrayList
        final ArrayList<Word> conversatons = new ArrayList<Word>();

        conversatons.add(new Word("AutoDriver", "Auto Chalaka",-1,-1));
        conversatons.add(new Word("House Help", "Mane Kelasadavalu", -1, -1));
        conversatons.add(new Word("Vendors", "Vyaparigalu", -1, -1));
        conversatons.add(new Word("Other service providers", "Ithare seve odagisuvavaru", -1, -1));
        conversatons.add(new Word("School Bus Driver", "School Bus Driver", -1, -1));
        //Create a custom adapter to create grid items for each item in the list.
        GridAdapter adapter = new GridAdapter(this, conversatons,R.color.category_conversations);

        GridView gridView = (GridView) findViewById(R.id.gridViewLayout);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = conversatons.get(i);
                int a = conversatons.indexOf(word);

                Log.v("LOGTAG", "Current word: " + word);
                Log.v("LOGTAG","Index is "+a);

                if (a==0){
                    Intent intent = new Intent(ConversationsActivity.this, ConvAutoDriver.class);
                    startActivity(intent);

                }

                if(a==1){
                    Intent intent = new Intent(ConversationsActivity.this, ConvHouseHelp.class);
                    startActivity(intent);
                }

                if(a==2){

                    Intent intent = new Intent(ConversationsActivity.this, ConvVendor.class);
                    startActivity(intent);

                }

                if(a==3){

                    Intent intent = new Intent(ConversationsActivity.this, ConvServiceProviders.class);
                    startActivity(intent);

                }

                if(a==4){

                    Intent intent = new Intent(ConversationsActivity.this, ConvSchoolBus.class);
                    startActivity(intent);

                }

            }
        });




    }
}