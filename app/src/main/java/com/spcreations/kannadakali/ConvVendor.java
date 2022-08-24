package com.spcreations.kannadakali;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class ConvVendor extends AppCompatActivity {

    TextToSpeech mTTS;
    String text2convert;
    HashMap<Integer, String> vendorhm = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> conversation4 = new ArrayList<Word>();

        conversation4.add(new Word("C: Do you have that material?","C: Nimma hatra aa material ideya?",-1));
        conversation4.add(new Word("C: How much does it/that cost?","C: Idakke/Adakke esthagatte?",-1));
        conversation4.add(new Word("V: Material stock is not available.","V: Neevu kelida material stock illa.",-1));
        conversation4.add(new Word("V: Can get it if you want","V: Bekidre tharsi kodthini.",-1));
        conversation4.add(new Word("C: Yes, I need it. How long will you take?","C: Haudu nange beku. Eshtu dina agatte?",-1));
        conversation4.add(new Word("V: Place the order, we will get it in 2 days.","V: Order kottu hogi, 2 dinadalli tharsi kodthini.",-1));
        conversation4.add(new Word("C: Still not available? It is already 2 days.","C: Innu material bandilva? 2 dina agoitalla?",-1));
        conversation4.add(new Word("V: Still not come. Will call you once it arrives.","V: Innu bandilla, bandkudale call madtivi.",-1));
        conversation4.add(new Word("To converse with vegitable vendor","Tharakari maruvavana jote mathanadalu",-1));
        conversation4.add(new Word("1kg, 0.5 kg,0.25kg ","ondu kg, ardha kg,kaal kg",-1));
        conversation4.add(new Word("1 bundle methi leaves ,2 bundles palak ","ondu kattu menthe soppu,eradu kattu palak soppu",-1));
        conversation4.add(new Word("Are these vegitables fresh ?","Ee tharakaarigalella fresh/thaja ideya",-1));
        conversation4.add(new Word("That tomato is rotten, replace it.","Aa tomato kolethu hogide, beredu haaki/kodi.",-1));
        conversation4.add(new Word("Don't have change,take some chillies/gooseberries/coriander leaves.","Change illa, hasimenasinakaayi/nellikaayi/kottambari soppu takoli ",-1));


        vendorhm.put(0,"ನಿಮ್ಮ ಹತ್ರ ಆ ಮೆಟೀರಿಯಲ್ ಇದೆಯಾ?");
        vendorhm.put(1,"ಇದಕ್ಕೆ/ಅದಕ್ಕೆ ಎಷ್ಟಾಗತ್ತೆ?");
        vendorhm.put(2,"ನೀವು ಕೇಳಿದ ಮೆಟೀರಿಯಲ್ ಸ್ಟಾಕ್ ಇಲ್ಲ.");
        vendorhm.put(3,"ಬೇಕಿದ್ರೆ ತರ್ಸಿ ಕೊಡ್ತಿನಿ.");
        vendorhm.put(4,"ಹೌದು ನಂಗೆ ಬೇಕು. ಎಷ್ಟು ದಿನ ಆಗತ್ತೆ?");
        vendorhm.put(5,"ಆರ್ಡರ್ ಕೊಟ್ಟು ಹೋಗಿ. ೨ ದಿನದಲ್ಲಿ ತರ್ಸಿ ಕೊಡ್ತಿನಿ.");
        vendorhm.put(6,"ಇನ್ನೂ ಮೆಟೀರಿಯಲ್ ಬಂದಿಲ್ವ? ಎರಡು ದಿನ ಆಗೋಯಿತಲ್ಲ?");
        vendorhm.put(7,"ಇನ್ನು ಬಂದಿಲ್ಲ. ಬಂದಕೂಡಲೇ ಕಾಲ್ ಮಾಡ್ತಿವಿ.");
        vendorhm.put(8,"ತರಕಾರಿ ಮಾರುವವನ ಜೊತೆ ಮಾತನಾಡಲು");
        vendorhm.put(9,"ಒಂದು ಕೆಜಿ, ಅರ್ಧ ಕೆಜಿ, ಕಾಲು ಕೆಜಿ");
        vendorhm.put(10,"ಒಂದು ಕಟ್ಟು ಮೆಂತೆ ಸೊಪ್ಪು, ಎರಡು ಕಟ್ಟು ಪಾಲಕ್ ಸೊಪ್ಪು.");
        vendorhm.put(11,"ಈ ತರಕಾರಿಗಳೆಲ್ಲ ಫ್ರೆಶ್/ತಾಜಾ ಇದೆಯಾ?");
        vendorhm.put(12,"ಆ ಟೊಮೇಟೊ ಕೊಳ್ತು ಹೋಗಿದೆ, ಬೇರೆದು ಹಾಕಿ/ಕೊಡಿ.");
        vendorhm.put(13,"ಚೇಂಜ್ ಇಲ್ಲ. ಹಸಿಮೆಣಸಿನಕಾಯಿ/ನೆಲ್ಲಿಕಾಯಿ/ಕೊತ್ತಂಬರಿ ಸೊಪ್ಪು ತಕೋಳಿ.");

        //Create a custom adapter to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this,conversation4,R.color.category_conversations);


        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Get the object at the clicked position

                Word word = conversation4.get(i);

                text2convert = word.getKannadaTranslation();

                int a = conversation4.indexOf(word);
                text2convert = vendorhm.get(a);

                Log.v("LOGTAG","Text to convert "+text2convert);


                mTTS = new TextToSpeech(ConvVendor.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {

                        Log.e("LOGTAG","Status "+status);
                        if(status == TextToSpeech.SUCCESS){
                            Log.e("LOGTAG","Text to speech "+TextToSpeech.SUCCESS);

                            // int result = mTTS.setLanguage(Locale.UK);
                            int result = mTTS.setLanguage(new Locale("kn","IN"));

                            Log.e("LOGTAG","language is successfully set "+result);

                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                                Log.e("LOGTAG","Language not supported");
                                Intent m_intent = new Intent();
                                m_intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                                startActivityForResult(m_intent, 0);
                            }else{
                                Log.e("LOGTAG","Successfully initialized");
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    mTTS.speak(text2convert, TextToSpeech.QUEUE_FLUSH, null,null);
                                }
                            }
                        }else{
                            Log.e("LOGTAG","Issue with initialization "+TextToSpeech.SUCCESS);
                        }

                    }
                });

            }
        });



    }
}