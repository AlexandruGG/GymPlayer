package com.example.android.gymplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class StretchingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        //Create a list of words
        final ArrayList<Song> songs = new ArrayList<Song>();

        //Add words to the ArrayList
        songs.add(new Song("Family Time", "Ziggy Marley", R.drawable.ziggy_marley_family_time, R.raw.ziggy_marley_01_family_time));
        songs.add(new Song("I Love You Too", "Ziggy Marley", R.drawable.ziggy_marley_family_time, R.raw.ziggy_marley_02_i_love_you_too));
        songs.add(new Song("Cry Cry Cry", "Ziggy Marley", R.drawable.ziggy_marley_family_time, R.raw.ziggy_marley_03_cry_cry_cry));
        songs.add(new Song("Take Me To Jamaica", "Ziggy Marley", R.drawable.ziggy_marley_family_time, R.raw.ziggy_marley_04_take_me_to_jamaica));
        songs.add(new Song("Ziggy Says", "Ziggy Marley", R.drawable.ziggy_marley_family_time, R.raw.ziggy_marley_05_ziggy_says));
        songs.add(new Song("Sun In My Face", "Aso", R.drawable.chillhop_essentials, R.raw.aso_06_sun_in_my_face));
        songs.add(new Song("Sleepyface", "Birocratic", R.drawable.chillhop_essentials, R.raw.birocratic_07_sleepyface));
        songs.add(new Song("Ecstacy", "Flamingosis", R.drawable.chillhop_essentials, R.raw.flamingosis_08_ecstacy));
        songs.add(new Song("Attila", "Monomassive", R.drawable.chillhop_essentials, R.raw.monomassive_09_attila));
        songs.add(new Song("Spring Waltz", "Saib", R.drawable.chillhop_essentials, R.raw.saib_10_spring_waltz));

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

                intentInfo = song + "," + artist + "," + "Stretching Playlist";
                Intent intent = new Intent(StretchingActivity.this, NowPlayingActivity.class);
                intent.putExtra("songInfo", intentInfo);
                intent.putExtra("albumResId", songObject.getImageResourceId());
                intent.putExtra("songId", songObject.getAudioResourceId());
                startActivity(intent);
            }
        });
    }
}

