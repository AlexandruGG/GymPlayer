package com.example.android.gymplayer;

import android.app.Activity;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(Activity context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        // Get the Song object located at this position in the list
        Song currentSong = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID text_song_name
        TextView songTextView = (TextView) listItemView.findViewById(R.id.text_song_name);
        // Set this text (song name) on the song TextView
        songTextView.setText(currentSong.getSongName());

        // Find the TextView in the list_item.xml layout with the ID text_artist_name
        TextView artistTextView = (TextView) listItemView.findViewById(R.id.text_artist_name);
        // Set this text (artist name) on the artist TextView
        artistTextView.setText(currentSong.getArtistName());

        // Find the ImageView in the list_item.xml layout with the ID image_album
        ImageView songImage = (ImageView) listItemView.findViewById(R.id.image_album);
        // Set the ImageView (album art) to the image resource specified in the current song
        songImage.setImageResource(currentSong.getImageResourceId());

        /* Return the whole list item layout (containing 2 TextViews and an ImageView)
        so that it can be shown in the ListView */
        return listItemView;
    }
}