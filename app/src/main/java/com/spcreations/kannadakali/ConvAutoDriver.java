package com.spcreations.kannadakali;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static android.view.View.GONE;

public class ConvAutoDriver extends AppCompatActivity {

    ImageView plyBtn;
    TextToSpeech mTTS;
    String text2convert;
    HashMap<Integer, String> autodriverhm = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

         plyBtn = findViewById(R.id.idPlayBtn);


        // Create ArrayList and Store the conversation with auto driver in ArrayList
        final ArrayList<Word> conversation1 = new ArrayList<Word>();

        conversation1.add(new Word("D1: Where do you want to go?","D1: Ellige hogabeku?",-1));
        conversation1.add(new Word("P1: Gandhi Nagar. Will you come?","P1: Gandhi Naragakke hogabeku. Barteera?",-1));
        conversation1.add(new Word("D1: Okay. Please sit.","D1: Aayithu banni kuthkolli",-1));
        conversation1.add(new Word("P1: Will you go as per meter charges?","P1: Meter haktheeralva?",-1));
        conversation1.add(new Word("D1: Yes madam. Please come.","D1: Haudu madam. Banni.",-1));
        conversation1.add(new Word("P2: Will you come to M.G.Road?","P2: M.G.Road ge bartheera?",-1));
        conversation1.add(new Word("D2: It will cost 200Rs.","D2: Innooru roopayi agatte",-1));
        conversation1.add(new Word("P2: Too much. I will give you Rs.150.","P2: Jaasthi aithu. 150 Rs. kodutthene.",-1));
        conversation1.add(new Word("D2: Too much traffic, will come for Rs.180.","D2: Tumba traffic iratte. 180 kodi bartheeni.",-1));
        conversation1.add(new Word("P2: That is also too much. You put meter.","P2: Illa adu jasthine.Neevu meter haaki.",-1));
        conversation1.add(new Word("D2: Okay Madam. Please give me 30Rs extra.","D2: Aithu. Meter mele 30 rupaayi kottubidi.",-1));

        autodriverhm.put(0,"ಎಲ್ಲಿಗೆ ಹೋಗ್ಬೇಕು?");
        autodriverhm.put(1,"ಗಾಂಧಿ ನಗರಕ್ಕೆ ಹೋಗ್ಬೇಕು. ಬರ್ತೀರಾ?");
        autodriverhm.put(2,"ಆಯಿತು, ಬನ್ನಿ, ಕುತ್ಕೋಳಿ.");
        autodriverhm.put(3,"ಮೀಟರ್ ಹಾಕ್ತೀರಲ್ವ?");
        autodriverhm.put(4,"ಹೌದು ಮೇಡಂ, ಬನ್ನಿ.");
        autodriverhm.put(5,"ಎಂಜಿ ರೋಡ್ ಗೆ ಬರ್ತೀರಾ?");
        autodriverhm.put(6,"ಇನ್ನೂರ್ ರೂಪಾಯಿ ಆಗತ್ತೆ.");
        autodriverhm.put(7,"ಜಾಸ್ತಿ ಆಯಿತು. ನೂರಾ ಐವತ್ತು ರೂಪಾಯಿ ಕೊಡ್ತಿನಿ.");
        autodriverhm.put(8,"ತುಂಬಾ ಟ್ರಾಫಿಕ್ ಇರತ್ತೆ. ನೂರಾ ಎಂಭತ್ತು ಕೊಡಿ ಬರ್ತೀನಿ.");
        autodriverhm.put(9,"ಇಲ್ಲ ಅದೂ ಜಾಸ್ತಿನೇ. ನೀವು ಮೀಟರ್ ಹಾಕಿ.");
        autodriverhm.put(10,"ಆಯಿತು. ಮೀಟರ್ ಮೇಲೆ ಮೂವತ್ತು ರೂಪಾಯಿ ಕೊಟ್ಬಿಡಿ ಮೇಡಂ.");
        //Create a custom adapter to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this,conversation1,R.color.category_conversations);


        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Get the object at the clicked position

                Word word = conversation1.get(i);

                 text2convert = word.getKannadaTranslation();

                 int a = conversation1.indexOf(word);

                 text2convert = autodriverhm.get(a);

                Log.v("LOGTAG","Text to convert "+text2convert);


                mTTS = new TextToSpeech(ConvAutoDriver.this, new TextToSpeech.OnInitListener() {
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