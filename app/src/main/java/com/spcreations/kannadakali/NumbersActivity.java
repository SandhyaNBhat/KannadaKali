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

public class NumbersActivity extends AppCompatActivity {

    /**
     * To playback the sound files
     */
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
                            focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);

                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

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
        final ArrayList<Word> numbers = new ArrayList<Word>();

        numbers.add(new Word("One", "Ondu", R.drawable.number_one, R.raw.ondu));
        numbers.add(new Word("Two", "Eradu", R.drawable.number_two, R.raw.eradu));
        numbers.add(new Word("Three", "Mooru", R.drawable.number_three, R.raw.mooru));
        numbers.add(new Word("Four", "Nalku", R.drawable.number_four, R.raw.nalku));
        numbers.add(new Word("Five", "Aidu", R.drawable.number_five, R.raw.aidu));
        numbers.add(new Word("Six", "Aaru", R.drawable.number_six, R.raw.aaru));
        numbers.add(new Word("Seven", "Elu", R.drawable.number_seven, R.raw.eelu));
        numbers.add(new Word("Eight", "Entu", R.drawable.number_eight, R.raw.entu));
        numbers.add(new Word("Nine", "Ombhattu", R.drawable.number_nine, R.raw.ombattu));
        numbers.add(new Word("Ten", "Hattu", R.drawable.number_ten, R.raw.hattu));

        //Create a custom adapter to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, numbers, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Get the object at the clicked position

                Word word = numbers.get(i);

                Log.v("NumbersActivity", "Current word: " + word);

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

                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                    mMediaPlayer.start();

                }
            }
        });


    }

    @Override
    protected void onStop() {
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
