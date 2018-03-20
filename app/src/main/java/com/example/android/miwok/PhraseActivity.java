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

public class PhraseActivity extends AppCompatActivity {

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
        words.add(new Word("enni'nem", "come here",R.raw.phrase_come_here));
        words.add(new Word("hee'eenam", "yes,i'm coming",R.raw.phrase_im_coming));
        words.add(new Word("yoowutis", "lets go", R.raw.phrase_lets_go));
        words.add(new Word("tinne oyaase'ne", "What is your name?", R.raw.phrase_what_is_your_name));
        words.add(new Word("oyaaset", "My name is", R.raw.phrase_my_name_is));
        words.add(new Word("minto wuksus", "Where are you going?", R.raw.phrase_where_are_you_going));
        WordAdapter item = new WordAdapter(this, words, R.color.category_phrases);

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
                    m = MediaPlayer.create(PhraseActivity.this, word.getaudio());
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

