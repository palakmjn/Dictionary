package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer m;
    private AudioManager am;
    private void releaseRes(){
        if(m!=null){
            m.release();
            m=null;
        }
    }
    AudioManager.OnAudioFocusChangeListener l=new AudioManager.OnAudioFocusChangeListener(){
        @Override
        public void onAudioFocusChange(int f){
            if(f==AudioManager.AUDIOFOCUS_LOSS){
                m.stop();
            }
            else if(f==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || f==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                m.pause();
            }
            else if(f==AudioManager.AUDIOFOCUS_GAIN){
                m.start();
                m.seekTo(0);
            }
        }};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // getActionBar().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("lutti", "one", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("otiiko", "two", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("tolookosu", "three", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("oyyisa", "four", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("massokka", "five", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("temmokka", "six", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("kenekaku", "seven", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("kawinta", "eight", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("wo'e", "nine", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("na'aacha", "ten", R.drawable.number_ten, R.raw.number_ten));
        WordAdapter item = new WordAdapter(this, words, R.color.category_numbers);

        //  MediaPlayer.create(NumbersActivity.this,R.raw.number_eight);
        ListView list = (ListView) findViewById(R.id.activity_numbers);
        list.setAdapter(item);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {


                releaseRes();
                Word word = words.get(pos);
                int result = am.requestAudioFocus(l, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    m = MediaPlayer.create(NumbersActivity.this, word.getaudio());
                    m.start();
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseRes();
                        }
                    });
                }
            }
        });}


    @Override
   protected void onStop(){
        super.onStop();
        releaseRes();
    }}

