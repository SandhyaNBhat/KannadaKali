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

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

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




    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Initialize AudioManager

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create ArrayList and Store the numbers in ArrayList
        final ArrayList<Word> colors = new ArrayList<Word>();

        colors.add(new Word("Black","Kappu",R.drawable.color_black,R.raw.black));
        colors.add(new Word("Blue", "Neeli",R.drawable.color_blue,R.raw.blue));
        colors.add(new Word("Brown","Kandu",R.drawable.color_brown,R.raw.brown));
        colors.add(new Word("Gray","Boodu",R.drawable.color_gray,R.raw.gray));
        colors.add(new Word("Green","Hasiru",R.drawable.color_green,R.raw.green));
        colors.add(new Word("Orange","Kittale",R.drawable.color_orange,R.raw.orange));
        colors.add(new Word("Pink","Gulabi",R.drawable.color_pink,R.raw.pink));
        colors.add(new Word("Purple","Nerale",R.drawable.color_purple,R.raw.purple));
        colors.add(new Word("Red","Kempu",R.drawable.color_red,R.raw.red));
        colors.add(new Word("Yellow","Haladi",R.drawable.color_yellow,R.raw.yellow));

        //Create a custom adapter to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this,colors,R.color.category_numbers);
        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Get the object at the clicked position

                Word word = colors.get(i);

                Log.v("ColorsActivity", "Current word: " + word);

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


                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());
                    mMediaPlayer.start();

                    //setup a listener on the media player, so that we can stop and release the media player once the sounds has
                    //finished playing.

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

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
