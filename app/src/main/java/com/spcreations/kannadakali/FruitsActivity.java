package com.spcreations.kannadakali;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class FruitsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {

                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);

                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
                    {
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){

                        releaseMediaPlayer();

                    }

                }


            };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create ArrayList and Store the numbers in ArrayList
        final ArrayList<Word> fruits = new ArrayList<Word>();

        fruits.add(new Word("Banana","Baale Hannu",R.drawable.fruit_banana,R.raw.banana));
        fruits.add(new Word("Watermelon", "Kallangadi Hannu",R.drawable.fruit_watermelon,R.raw.watermelon));
        fruits.add(new Word("Orange","Kitthale Hannu",R.drawable.fruit_orange,R.raw.orange));
        fruits.add(new Word("Mango","Maavina Hannu",R.drawable.fruit_mango,R.raw.mango));
        fruits.add(new Word("Custard Apple","Seethaphala",R.drawable.fruit_custardapple,R.raw.custard_apple));
        fruits.add(new Word("Grapes","Drakshi",R.drawable.fruit_grapes,R.raw.grapes));
        fruits.add(new Word("Guava","Seebe Hannu",R.drawable.fruit_guava,R.raw.guava));
        fruits.add(new Word("Pomogranate","Daalimbe Hannu",R.drawable.fruit_pomogranate,R.raw.pomogranate));
        fruits.add(new Word("Papaya","Parangi Hannu",R.drawable.fruit_papaya,R.raw.papaya));
        fruits.add(new Word("Jackfruit","Halasina Hannu",R.drawable.fruit_jackfruit,R.raw.jackfruit));

        //Create a custom adapter to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this,fruits,R.color.category_numbers);

        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Get the object at the clicked position

                Word word = fruits.get(i);

                Log.v("FruitsActivity", "Current word: " + word);

                //release media player if it currently exists because we are about to play different sound file

                releaseMediaPlayer();

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(
                        mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                );

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // AudioFocus granted

                    //   mAudioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);

                    mMediaPlayer = MediaPlayer.create(FruitsActivity.this, word.getAudioResourceId());
                    mMediaPlayer.start();

                }
            }
        });
    }


    @Override
    protected void onStop(){
        super.onStop();

        //When the activity is stopped, release the media player resources

        releaseMediaPlayer();
    }


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            //Abandon audio focus when playback is complete
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
