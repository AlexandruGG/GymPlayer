package com.example.android.gymplayer;

/**
 * {@link Song} represents a playlist song that the user wants to listen to.
 * It contains the title of the song and the name of the artist.
 */

public class Song {

    //The name of the song
    private String gSongName;

    //The name of the artist
    private String gArtistName;

    //Resource ID for the audio
    private int gAudioResourceId;

    //Resource ID for the album art
    private int gImageResourceId;

    /**
     * Create a new Song object.
     *
     * @param songName        is the name of the song
     * @param artistName      is the name of the artist who produced the song
     * @param imageResourceId is the drawable resource ID for the album art image associated with the song
     * @param audioResourceId is the resource ID for the audio file
     */
    public Song(String songName, String artistName, int imageResourceId, int audioResourceId) {
        gSongName = songName;
        gArtistName = artistName;
        gAudioResourceId = audioResourceId;
        gImageResourceId = imageResourceId;
    }

    /**
     * Get the name of the song
     */
    public String getSongName() {
        return gSongName;
    }

    /**
     * Get the name of the artist
     */
    public String getArtistName() {
        return gArtistName;
    }

    /**
     * Get the image resource ID associated with the song
     */
    public int getImageResourceId() {
        return gImageResourceId;
    }

    /**
     * Get the audio resource ID associated with the song
     */
    public int getAudioResourceId() {
        return gAudioResourceId;
    }
}