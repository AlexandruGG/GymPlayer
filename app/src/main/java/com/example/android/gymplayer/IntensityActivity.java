package com.example.android.gymplayer;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class IntensityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        //Create a list of words
        final ArrayList<Song> songs = new ArrayList<Song>();

        //Add words to the ArrayList
        songs.add(new Song("Fight Fire With Fire", "Metalllica", R.drawable.metallica_ride_the_lightning, R.raw.metallica_01_fight_fire_with_fire));
        songs.add(new Song("Ride The Lightning", "Metallica", R.drawable.metallica_ride_the_lightning, R.raw.metallica_02_ride_the_lightning));
        songs.add(new Song("For Whom The Bell Tolls", "Metallica", R.drawable.metallica_ride_the_lightning, R.raw.metallica_03_for_whom_the_bell_tolls));
        songs.add(new Song("Fade To Black", "Metallica", R.drawable.metallica_ride_the_lightning, R.raw.metallica_04_fade_to_black));
        songs.add(new Song("Trapped Under Ice", "Metallica", R.drawable.metallica_ride_the_lightning, R.raw.metallica_05_trapped_under_ice));
        songs.add(new Song("Delusions of Saviour", "Slayer", R.drawable.slayer_repentless, R.raw.slayer_01_delusions_of_saviour));
        songs.add(new Song("Repentless", "Slayer", R.drawable.slayer_repentless, R.raw.slayer_02_repentless));
        songs.add(new Song("Take Control", "Slayer", R.drawable.slayer_repentless, R.raw.slayer_03_take_control));
        songs.add(new Song("Vices", "Slayer", R.drawable.slayer_repentless, R.raw.slayer_04_vices));
        songs.add(new Song("Cast The First Stone", "Slayer", R.drawable.slayer_repentless, R.raw.slayer_05_cast_the_first_stone));

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

                intentInfo = song + "," + artist + "," + "Intensity Playlist";
                Intent intent = new Intent(IntensityActivity.this, NowPlayingActivity.class);
                intent.putExtra("songInfo", intentInfo);
                intent.putExtra("albumResId", songObject.getImageResourceId());
                intent.putExtra("songId", songObject.getAudioResourceId());
                startActivity(intent);
            }
        });
    }
}

