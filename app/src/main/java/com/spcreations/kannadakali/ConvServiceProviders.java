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

public class ConvServiceProviders extends AppCompatActivity {
    TextToSpeech mTTS;
    String text2convert;
    HashMap<Integer, String> serviceproviderhm = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create ArrayList and Store the conversation with auto driver in ArrayList
        final ArrayList<Word> conversation3 = new ArrayList<Word>();

        conversation3.add(new Word("Converse with Plumber","Plumber jothe maathukate",-1));
        conversation3.add(new Word("P: What is the problem Madam?","P: Enaagide Madam?",-1));
        conversation3.add(new Word("O: Water leakage in bathroom. Can you check?","O: Bathroom alli neeru leak agtha ide. Swalpa nodthira?",-1));
        conversation3.add(new Word("O: Water not coming in the kitchen sink.","O: Aduge mane sink alli neeru bartha illa.",-1));
        conversation3.add(new Word("O: Dirty water is coming","O: Kolaku/Galeej neeru bartha ide.",-1));
        conversation3.add(new Word("P: Need to replace one of the parts.","P: Ondu part hogide, bere hakbeku.",-1));
        conversation3.add(new Word("O: How long does it take to repair it?","O: Sari madakke eshtu hottagatte?",-1));
        conversation3.add(new Word("P: If you get the item, I will fix it tomorrow","P: Neevu aa part tharsi kotre naalene sari maadi kodthini.",-1));
        conversation3.add(new Word("P: Will you get the new one or should I bring it?","P: Neeve tharsi kodtheera? illa naane thakondu barbeka?",-1));
        conversation3.add(new Word("O: How much does it cost? Can you get it?","O: Esthagatte? Neeve takondu bartheera?",-1));
        conversation3.add(new Word("O: Yesterday it worked, but today again leaking","O: Ninne sari agittu adre ivattu matte leake agtha ide.",-1));
        conversation3.add(new Word("P: Looks like pipe is broken, let me check.","P: Pipe odedu hogide anisatte nodthini.",-1));
        conversation3.add(new Word("Converse with Electrician","Electrician jothe maathukate",-1));
        conversation3.add(new Word("O: Our Geyser is not working. Can you check?","O:Namma Geyser work agtha illa, enagide antha nodthira?",-1));
        conversation3.add(new Word("O: Attempt to switch on mixer trips the electricity out.","O: Mixer hakidre trip agtha ide.",-1));
        conversation3.add(new Word("O: We don't have power since morning","O: Beligge inda current illa.",-1));
        conversation3.add(new Word("O: Getting electric shock from Iron box.","O: Isthri pettigeyinda shock hoditha ide.",-1));

        serviceproviderhm.put(0,"plumber ಜೊತೆ ಮಾತುಕತೆ");
        serviceproviderhm.put(1,"ಏನಾಗಿದೆ ಮೇಡಂ?");
        serviceproviderhm.put(2,"ಬಾತ್ರೂಮ್ ಅಲ್ಲಿ ನೀರು ಲೀಕ್ ಆಗ್ತಾ ಇದೆ. ಸ್ವಲ್ಪ ನೋಡತೀರಾ?");
        serviceproviderhm.put(3,"ಅಡುಗೆ ಮನೆ ಸಿಂಕ್ ಅಲ್ಲಿ ನೀರು ಬರ್ತಾ ಇಲ್ಲ.");
        serviceproviderhm.put(4,"ಕೊಳಕು/ಗಲೀಜ್ ನೀರು ಬರ್ತಾ ಇದೆ.");
        serviceproviderhm.put(5,"ಒಂದು ಪಾರ್ಟ್ ಹೋಗಿದೆ ಬೇರೆ ಹಾಕ್ಬೇಕು.");
        serviceproviderhm.put(6,"ಸರಿ ಮಾಡಕ್ಕೆ ಎಷ್ಟು ಹೊತ್ತು ಆಗತ್ತೆ?");
        serviceproviderhm.put(7,"ನೀವು ಆ ಪಾರ್ಟ್ ತರ್ಸಿ ಕೊಟ್ರೆ ನಾಳೇನೇ ಸರಿ ಮಾಡಿ ಕೊಡ್ತಿನಿ.");
        serviceproviderhm.put(8,"ನೀವೇ ತರ್ಸಿ ಕೊಡ್ತಿರಾ ಇಲ್ಲ ನಾನೇ ತಕೊಂಡು ಬರ್ಬೇಕಾ?");
        serviceproviderhm.put(9,"ಎಷ್ಟಾಗತ್ತೆ? ನೀವೇ ತಕೊಂಡು ಬರ್ತೀರಾ?");
        serviceproviderhm.put(10,"ನಿನ್ನೆ ಸರಿ ಆಗಿತ್ತು. ಇವತ್ತು ಮತ್ತೆ ಲೀಕ್ ಆಗ್ತಾ ಇದೆ.");
        serviceproviderhm.put(11,"ಪೈಪ್ ಒಡೆದು ಹೋಗಿದೆ ಅನಿಸತ್ತೆ. ನೋಡತೀನಿ.");
        serviceproviderhm.put(12,"ಎಲೆಕ್ಟ್ರಿಷಿಯನ್ ಜೊತೆ ಮಾತುಕತೆ");
        serviceproviderhm.put(13,"ನಮ್ಮ ಗೀಸರ್ ವರ್ಕ್ ಆಗ್ತಾ ಇಲ್ಲ. ಏನಾಗಿದೆ ನೋಡತೀರಾ?");
        serviceproviderhm.put(14,"ಮಿಕ್ಸರ್ ಹಾಕಿದ್ರೆ ಟ್ರಿಪ್ ಆಗ್ತಾ ಇದೆ.");
        serviceproviderhm.put(15,"ಬೆಳಿಗ್ಗೆ ಇಂದ ಕರೆಂಟ್ ಇಲ್ಲ.");
        serviceproviderhm.put(16,"ಇಸ್ತ್ರಿ ಪೆಟ್ಟಿಗೆಯಿಂದ ಶಾಕ್ ಹೊಡಿತಾ ಇದೆ.");


        //Create a custom adapter to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this,conversation3,R.color.category_conversations);


        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Get the object at the clicked position

                Word word = conversation3.get(i);

                text2convert = word.getKannadaTranslation();

                int a = conversation3.indexOf(word);

                text2convert = serviceproviderhm.get(a);

                Log.v("LOGTAG","Text to convert "+text2convert);


                mTTS = new TextToSpeech(ConvServiceProviders.this, new TextToSpeech.OnInitListener() {
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