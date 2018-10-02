package com.example.android.gymplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class VolumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        //Create a list of words
        final ArrayList<Song> songs = new ArrayList<Song>();

        //Add words to the ArrayList
        songs.add(new Song("The Life N Death Of My Hood", "Betrayl", R.drawable.betrayl_the_life_and_death_of_my_hood, R.raw.betrayl_01_the_life_and_death_of_my_hood));
        songs.add(new Song("We Can Make It", "Betrayl", R.drawable.betrayl_the_life_and_death_of_my_hood, R.raw.betrayl_02_we_can_make_it));
        songs.add(new Song("The Corner", "Betrayl", R.drawable.betrayl_the_life_and_death_of_my_hood, R.raw.betrayl_03_the_corner));
        songs.add(new Song("Where Were U", "Betrayl", R.drawable.betrayl_the_life_and_death_of_my_hood, R.raw.betrayl_04_where_were_u));
        songs.add(new Song("On The Streets", "Betrayl", R.drawable.betrayl_the_life_and_death_of_my_hood, R.raw.betrayl_05_on_the_streets));
        songs.add(new Song("Supermagic", "Mos Def", R.drawable.mos_def_the_ecstatic, R.raw.mos_def_01_supermagic));
        songs.add(new Song("Twilite Speedball", "Mos Def", R.drawable.mos_def_the_ecstatic, R.raw.mos_def_02_twilite_speedball));
        songs.add(new Song("Auditorium", "Mos Def", R.drawable.mos_def_the_ecstatic, R.raw.mos_def_03_auditorium));
        songs.add(new Song("Wahid", "Mos Def", R.drawable.mos_def_the_ecstatic, R.raw.mos_def_04_wahid));
        songs.add(new Song("Priority", "Mos Def", R.drawable.mos_def_the_ecstatic, R.raw.mos_def_05_priority));

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
                Intent intent = new Intent(VolumeActivity.this, NowPlayingActivity.class);
                intent.putExtra("songInfo", intentInfo);
                intent.putExtra("albumResId", songObject.getImageResourceId());
                intent.putExtra("songId", songObject.getAudioResourceId());
                startActivity(intent);
            }
        });
    }
}

