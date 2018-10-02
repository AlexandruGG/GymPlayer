package com.example.android.gymplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class CardioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        //Create a list of words
        final ArrayList<Song> songs = new ArrayList<Song>();

        //Add words to the ArrayList
        songs.add(new Song("The Humpty Dance", "Digital Underground", R.drawable.default_album, R.raw.digital_underground_01_the_humpty_dance));
        songs.add(new Song("The Creator", "Pete Rock", R.drawable.default_album, R.raw.pete_rock_02_the_creator));
        songs.add(new Song("Gangsta's Paradise", "Coolio", R.drawable.default_album, R.raw.coolio_03_gangsta_paradise));
        songs.add(new Song("Hypnotize", "The Notorious B.I.G.", R.drawable.default_album, R.raw.the_notorious_big_04_hypnotize));
        songs.add(new Song("Real Hip-Hop", "Das EFX", R.drawable.default_album, R.raw.das_efx_05_real_hip_hop));
        songs.add(new Song("Syntax Era", "Leaders Of The New School", R.drawable.default_album, R.raw.leaders_of_the_new_school_06_syntax_era));
        songs.add(new Song("Back In The Day", "Ahmad Lewis", R.drawable.default_album, R.raw.ahmad_lewis_07_back_in_the_day));
        songs.add(new Song("What You Want", "Mase", R.drawable.default_album, R.raw.mase_08_what_you_want));
        songs.add(new Song("Problems", "Raw Breed", R.drawable.default_album, R.raw.raw_breed_09_problems));
        songs.add(new Song("Cha Cha Cha", "Flipmode Squad", R.drawable.default_album, R.raw.flipmode_squad_10_cha_cha_cha));

        SongAdapter adapter = new SongAdapter(this, songs);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Song songObject = songs.get(position);


                /*With the intent to start the NowPlayingActivity I pass along other data
                such as the name of the song, name of the artist, and resource IDs for them as well as the album art*/
                String intentInfo = "";
                String song = songObject.getSongName();
                String artist = songObject.getArtistName();

                intentInfo = song + "," + artist + "," + "Cardio Playlist";
                Intent intent = new Intent(CardioActivity.this, NowPlayingActivity.class);
                intent.putExtra("songInfo", intentInfo);
                intent.putExtra("albumResId", songObject.getImageResourceId());
                intent.putExtra("songId", songObject.getAudioResourceId());
                startActivity(intent);
            }
        });
    }
}

