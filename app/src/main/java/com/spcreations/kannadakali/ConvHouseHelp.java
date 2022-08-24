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

public class ConvHouseHelp extends AppCompatActivity {

    TextToSpeech mTTS;
    String text2convert;
    HashMap<Integer, String> househelphm = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> conversation2 = new ArrayList<Word>();

        conversation2.add(new Word("O: What all kinds of work you do?","O: Enenu kelsa madakke baratte?",-1));
        conversation2.add(new Word("M: I can do all kind of work.","M: Ella kelsanu maadthini.",-1));
        conversation2.add(new Word("O: I need someone for daily sweeping, mopping & utensils.","O: Nanage dinaalu gudisi, orasi mattu pathre tholeyakke yaradru beku.",-1));
        conversation2.add(new Word("M: I will do. How much will you pay?","M: Naanu madthini.Sambala eshtu kodtheera?",-1));
        conversation2.add(new Word("O: Tell me how much you are expecting?","O: Eshtagatte antha neeve heli",-1));
        conversation2.add(new Word("M: Give me 3000. I do good work.","M: 3000 kodi, chennagi kelsa madthini.",-1));
        conversation2.add(new Word("O: Society rate is 1500 for 2 bhk.","O: Ee society alli 2bhk ge 1500 kodthare.",-1));
        conversation2.add(new Word("M: See work and give,won't take more leaves.","M: Neeve kelsa nodi kodi, jasthi raja takollalla. ",-1));
        conversation2.add(new Word("O: Will give 2500. Can you come on Sunday's?","M: 2500 kodthini, Bhanuvara nu bartheera?",-1));
        conversation2.add(new Word("M: Okay,need 2 days leave monthly, will inform in advance.","M: Bartheeni adre thingalige 2 raja beku. Modale helirthini.",-1));
        conversation2.add(new Word("","General conversation samples ",-1));
        conversation2.add(new Word("O: Utensils are not washed properly. I expect proper cleaning","O: Pathre tholediddu sariyagi agtha illa. Innu clean aagbeku.",-1));
        conversation2.add(new Word("O: These days you are taking so many leaves.","O: Egeega tumba raja takoltha idira neevu.",-1));
        conversation2.add(new Word("O: Why didn't you come yesterday? Coming tomorrow?","O: Ninne yaake baralilla? Naale bartheeralva?",-1));
        conversation2.add(new Word("O: Should inform me before taking leaves.","O: Nange helbittu raja thakobeku.",-1));
        conversation2.add(new Word("O: Please call me early in the morning if you are not coming","O: Neevu baralla andre beliggene nange call maadi helbidi.",-1));
        conversation2.add(new Word("O: We are going out of station. Don't come for 1 week.","O: Naavu maneyalli iralla, neevu ondu vaara barbedi.",-1));
        conversation2.add(new Word("O: You look unwell, go home and take rest.","O: Nimage husharilva? Manege hogi vishranthi/rest thakoli.",-1));
        conversation2.add(new Word("O: Did you go to doctor? How are you feeling now?","O: Doctor hatra hogidra? Ega hegiddeera?",-1));
        conversation2.add(new Word("O: Did you eat breakfast? Want to have tea?","O: Thindi thindra? Tea enadru beka?",-1));
        conversation2.add(new Word("M: Need some advance money. Deduct from my salary.","M: Swalpa duddu bekittu. Nanna sambaladalli cut maadkoli.",-1));
        conversation2.add(new Word("O: How much money you need and for what? ","O: Eshtu duddu bekittu? Matte yaake duddu beku?",-1));
        conversation2.add(new Word("O: Ok, will give and deduct the amount from your salary. ","O: Aithu kodthini. Samabaladalli cut maadkothini. ",-1));

        
        //populate the hasmap with kannada translation

        househelphm.put(0,"ಏನೇನು ಕೆಲಸ ಮಾಡಕ್ಕೆ ಬರತ್ತೆ?");
        househelphm.put(1,"ಎಲ್ಲ ಕೆಲಸಾನೂ ಮಾಡ್ತಿನಿ.");
        househelphm.put(2,"ನಂಗೆ ದಿನಾಲೂ ಗುಡಿಸಿ,ಒರೆಸಿ ಮತ್ತು ಪಾತ್ರೆ ತೊಳಿಯಕ್ಕೆ ಯಾರಾದ್ರೂ ಬೇಕು.");
        househelphm.put(3,"ನಾನು ಮಾಡ್ತಿನಿ. ಸಂಬಳ ಎಷ್ಟು ಕೊಡ್ತೀರಾ?");
        househelphm.put(4,"ಎಷ್ಟಾಗತ್ತೆ ಅಂತ ನೀವೇ ಹೇಳಿ");
        househelphm.put(5, "ಮೂರು ಸಾವಿರ ಕೊಡಿ. ಚೆನ್ನಾಗಿ ಕೆಲಸ ಮಾಡ್ತಿನಿ.");
        househelphm.put(6,"ಈ ಸೊಸೈಟಿ ಲಿ ಎರಡು ಬೆಡ್ರೂಮ್ ಮನೆಗೆ ಸಾವಿರದ ಐನೂರು ಕೊಡ್ತಾರೆ.");
        househelphm.put(7,"ನೀವೇ ಕೆಲಸ ನೋಡಿ ಕೊಡಿ, ಜಾಸ್ತಿ ರಜ ತಕೊಳಲ್ಲ.");
        househelphm.put(8,"ಎರಡು ಸಾವಿರದ ಐನೂರು ಕೊಡ್ತಿನಿ. ಭಾನುವಾರನೂ ಬರ್ತೀರಾ?");
        househelphm.put(9,"ಬರ್ತೀನಿ ಆದ್ರೆ  ತಿಂಗಳಿಗೆ  ಎರಡು  ರಜಾ  ಬೇಕು. ಮೊದಲೇ  ಹೇಳಿರ್ತೀನಿ.");
        househelphm.put(10,"General conversation samples");
        househelphm.put(11,"ಪಾತ್ರೆ ತೊಳೆದಿದ್ದು ಸರಿಯಾಗಿ ಆಗ್ತಾ ಇಲ್ಲ. ಇನ್ನೂ ಕ್ಲೀನ್ ಆಗ್ಬೇಕು.");
        househelphm.put(12,"ಈಗೀಗ ತುಂಬಾ ರಜ ತಕೊಳ್ತಾ ಇದ್ದೀರಾ ನೀವು.");
        househelphm.put(13,"ನಿನ್ನೆ ಯಾಕ್ ಬರ್ಲಿಲ್ಲ? ನಾಳೆ ಬರ್ತೀರಲ್ವಾ?");
        househelphm.put(14,"ನಂಗೆ ಹೇಳ್ಬಿಟ್ಟು ರಜ ತಕೋಬೇಕು.");
        househelphm.put(15,"ನೀವು ಬರಲ್ಲ ಅಂದ್ರೆ ಬೆಳಿಗ್ಗೆನೇ ನಂಗೆ ಕಾಲ್ ಮಾಡಿ ಹೇಳ್ಬಿಡಿ.");
        househelphm.put(16,"ನಾವು ಮನೆಯಲ್ಲಿ ಇರಲ್ಲ ನೀವು ಒಂದು ವಾರ ಬರ್ಬೇಡಿ.");
        househelphm.put(17,"ನಿಮಗೆ ಹುಷಾರಿಲ್ವಾ? ಮನೆಗೆ ಹೋಗಿ ರೆಸ್ಟ್ ಮಾಡಿ.");
        househelphm.put(18,"ಡಾಕ್ಟರ್ ಹತ್ರ ಹೋಗಿದ್ರಾ? ಈಗ ಹೇಗಿದೀರಾ?");
        househelphm.put(19,"ತಿಂಡಿ ತಿಂದ್ರ? ಟೀ ಏನಾದ್ರು ಬೇಕಾ ?");
        househelphm.put(20,"ಸ್ವಲ್ಪ ದುಡ್ಡು ಬೇಕಿತ್ತು. ನನ್ನ ಸಂಬಳದಲ್ಲಿ ಕಟ್ ಮಾಡ್ಕೊಳಿ.");
        househelphm.put(21,"ಎಷ್ಟು ದುಡ್ಡು ಬೇಕಿತ್ತು? ಮತ್ತೆ ಯಾಕೆ ದುಡ್ಡು  ಬೇಕು?");
        househelphm.put(22,"ಆಯಿತು ಕೊಡ್ತಿನಿ. ಸಂಬಳದಲ್ಲಿ ಕಟ್ ಮಾಡ್ಕೋತೀನಿ.");
        //Create a custom adapter to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this,conversation2,R.color.category_conversations);





        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Get the object at the clicked position

                Word word = conversation2.get(i);

                text2convert = word.getKannadaTranslation();

                int a = conversation2.indexOf(word);

                text2convert = househelphm.get(a);

              Log.v("LOGTAG","Text to convert "+text2convert);


                mTTS = new TextToSpeech(ConvHouseHelp.this, new TextToSpeech.OnInitListener() {
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