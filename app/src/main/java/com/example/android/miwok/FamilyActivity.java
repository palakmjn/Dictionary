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

public class FamilyActivity extends AppCompatActivity {

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
        words.add(new Word("tune", "daughter", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("ape", "father", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("eta", "mother", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("angsi", "son", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("tete", "older sister", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("taachi", "older brother", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("kolliti", "younger sister", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("paapa", "grandfather", R.drawable.family_grandfather, R.raw.family_grandfather));
        words.add(new Word("ama", "grandmother", R.drawable.family_grandmother, R.raw.family_grandmother));
        WordAdapter item = new WordAdapter(this, words, R.color.category_family);

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
                    m = MediaPlayer.create(FamilyActivity.this, word.getaudio());
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

