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

public class ConvSchoolBus extends AppCompatActivity {

    TextToSpeech mTTS;
    String text2convert;
    HashMap<Integer, String> schoolbushm = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        // Create ArrayList and Store the conversation with auto driver in ArrayList
        final ArrayList<Word> conversation5 = new ArrayList<Word>();

        conversation5.add(new Word("P: Why is it so late today?","P:Ivattu ishtu thada yaake? ",-1));
        conversation5.add(new Word("P: Today my child is not going to school.","P: Namma magu ivattu school ge hogtha illa.",-1));
        conversation5.add(new Word("P: While returning my son won't come in the bus today.","P: Ivattu return bartha nanna maga bus alli barodilla. ",-1));
        conversation5.add(new Word("P: Why the Bus has not come yet? ","P: Bus yake innu bandilla?",-1));
        conversation5.add(new Word("P: We have been waiting here since long time.","P: Navu tumba hottinda illi kayatha idivi.",-1));
        conversation5.add(new Word("D: Bus broke down. Another bus will come for the pickup.","D: Bus haalagide. Bere bus baratte karkondu hogakke. ",-1));
        conversation5.add(new Word("D: Stuck in traffic, will reach in another 15 mins/30 mins","D: Traffic alli stuck agidivi, innondu kaalu/ardha ganteli allirtheevi.",-1));
        conversation5.add(new Word("P: I forgot to bring the pass.","P: Pass tarakke martu hoitu",-1));
        conversation5.add(new Word("P: Tomorrow onwards she will come to receive my child.","P: Naaleyinda magu-nna karkondu hogakke ivaru barthare. ",-1));
        conversation5.add(new Word("P: Her name is included in the pass","P: Ivala hesaru pass alli sersidivi.",-1));

        schoolbushm.put(0,"ಇವತ್ತು ಇಷ್ಟು ತಡ ಯಾಕೆ?");
        schoolbushm.put(1,"ನಮ್ಮ ಮಗು ಇವತ್ತು ಸ್ಕೂಲ್ ಗೆ ಹೋಗ್ತಾ ಇಲ್ಲ.");
        schoolbushm.put(2,"ಇವತ್ತು ರಿಟರ್ನ್ ಬರ್ತಾ ನನ್ನ ಮಗ ಬಸ್ ಅಲ್ಲಿ ಬರೋದಿಲ್ಲ.");
        schoolbushm.put(3,"ಬಸ್ ಯಾಕೆ ಇನ್ನೂ ಬಂದಿಲ್ಲ?");
        schoolbushm.put(4,"ನಾವು ತುಂಬಾ ಹೊತ್ತಿಂದ ಕಾಯ್ತ ಇದೀವಿ.");
        schoolbushm.put(5,"ಬಸ್ ಹಾಳಾಗಿದೆ. ಬೇರೆ ಬಸ್ ಬರತ್ತೆ ಕರ್ಕೊಂಡು ಹೋಗಕ್ಕೆ.");
        schoolbushm.put(6,"ಟ್ರಾಫಿಕ್ ಅಲ್ಲಿ ಸ್ಟಕ್ ಆಗಿದೀವಿ, ಇನ್ನೊಂದು ಕಾಲು / ಅರ್ಧ ಗಂಟೇಲಿ ಅಲ್ಲಿ ಇರ್ತಿವಿ.");
        schoolbushm.put(7,"ಪಾಸ್ ತರಕ್ಕೆ ಮರ್ತು ಹೋಯಿತು.");
        schoolbushm.put(8,"ನಾಳೆಯಿಂದ ಮಗುನ ಕರ್ಕೊಂಡು ಹೋಗಕ್ಕೆ ಇವರು ಬರತಾರೆ.");
        schoolbushm.put(9,"ಇವಳ ಹೆಸರು ಪಾಸ್ ಅಲ್ಲಿ ಸೇರ್ಸಿದೀವಿ.");
        schoolbushm.put(10,"ನಮ್ಮ ಮಗು ಇವತ್ತು ಸ್ಕೂಲ್ ಗೆ ಹೋಗ್ತಾ ಇಲ್ಲ.");

        //Create a custom adapter to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this,conversation5,R.color.category_conversations);


        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Get the object at the clicked position

                Word word = conversation5.get(i);

                text2convert = word.getKannadaTranslation();

                int a = conversation5.indexOf(word);
                text2convert = schoolbushm.get(a);


                Log.v("LOGTAG","Text to convert "+text2convert);


                mTTS = new TextToSpeech(ConvSchoolBus.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {

                        Log.e("LOGTAG","Status "+status);
                        if(status == TextToSpeech.SUCCESS){
                            Log.e("LOGTAG","Text to speech "+TextToSpeech.SUCCESS);


                           //  int result = mTTS.setLanguage(Locale.UK);
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