package com.example.android.gymplayer;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class NowPlayingActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    // UI Elements
    private SeekBar gSeekBar;
    private ImageButton gPrevSong;
    private ImageButton gNextSong;
    private ImageButton gPauseSong;
    private ImageButton gStopSong;
    private Button gBackButton;
    private TextView gSongName;
    private TextView gArtistName;
    private TextView gPlaylistName;
    private ImageView gAlbumArt;
    private MediaPlayer gMediaPlayer;
    private AudioManager gAudioManager;


    // Variables needed
    private String gIntentMessage;
    private String gSongPlaylist;
    private String gSong;
    private String gArtist;
    private int gAlbumArtResource;
    private Handler gSeekbarUpdateHandler = new Handler();

    /**
     * This abstract method identifies progress while media is playing and sets the position on the SeekBar
     */
    private Runnable gUpdateSeekbar = new Runnable() {
        @Override
        public void run() {
            if (gMediaPlayer != null && gMediaPlayer.getCurrentPosition() < gMediaPlayer.getDuration()) {
                gSeekBar.setProgress(gMediaPlayer.getCurrentPosition());
                gSeekbarUpdateHandler.postDelayed(gUpdateSeekbar, 1000);
            }
        }
    };

    /**
     * The OnAudioFocusChangeListener defines what happens when AudioFocus changes
     */
    AudioManager.OnAudioFocusChangeListener gOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                gMediaPlayer.pause();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                gMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] intentInfoArray;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        gAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        gIntentMessage = getIntent().getExtras().getString("songInfo");

        // Split extra intent info into song name, artist name, and current playlist
        intentInfoArray = gIntentMessage.split(",");
        gSong = intentInfoArray[0];
        gArtist = intentInfoArray[1];
        gSongPlaylist = "~  " + intentInfoArray[2] + "  ~";

        // Initialize UI Elements
        gSongName = findViewById(R.id.text_now_song);
        gArtistName = findViewById(R.id.text_now_artist);
        gPlaylistName = findViewById(R.id.text_now_playlist);
        gAlbumArt = findViewById(R.id.image_now_album);
        gStopSong = findViewById(R.id.imageButton_stop);
        gBackButton = findViewById(R.id.button_change_playlist);
        gPauseSong = findViewById(R.id.imageButton_pause);
        gPrevSong = findViewById(R.id.imageButton_previous);
        gNextSong = findViewById(R.id.imageButton_next);
        gSeekBar = findViewById(R.id.seekbar_now_playing);

        //Set OnClickListeners on clickable items
        gPauseSong.setOnClickListener(this);
        gBackButton.setOnClickListener(this);
        gPrevSong.setOnClickListener(this);
        gNextSong.setOnClickListener(this);
        gStopSong.setOnClickListener(this);
        gSeekBar.setOnSeekBarChangeListener(this);
        gSeekBar.setEnabled(true);

        //Display song details and album art
        displayMediaDetails();

        //Release media player if it currently exists
        releaseMediaPlayer();

        //Audio Focus is requested and playback starts if it is granted
        int result = gAudioManager.requestAudioFocus(gOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

            gMediaPlayer = MediaPlayer.create(NowPlayingActivity.this, getIntent().getExtras().getInt("songId"));
            gMediaPlayer.start();
            gSeekBar.setMax(gMediaPlayer.getDuration());
            gUpdateSeekbar.run();

            //Set up a listener on the media player to release it once playing stops
            gMediaPlayer.setOnCompletionListener(gCompletionListener);
        }
    }


    /**
     * This method displays media details: song title, artist name, album art
     */
    public void displayMediaDetails() {
        gSongName.setText(gSong);
        gArtistName.setText(gArtist);
        gPlaylistName.setText(gSongPlaylist);

        gAlbumArtResource = getIntent().getIntExtra("albumResId", 0);
        gAlbumArt.setImageResource(gAlbumArtResource);
    }

    /**
     * This method handles playing, pausing, and stopping a media file
     */
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imageButton_stop: {
                if (gMediaPlayer != null) {
                    gMediaPlayer.stop();
                    NowPlayingActivity.this.finish();
                    break;
                }

            }

            case R.id.button_change_playlist: {
                if (gMediaPlayer != null) {
                    gMediaPlayer.stop();
                    NowPlayingActivity.this.finish();
                    startActivity(new Intent(NowPlayingActivity.this, MainActivity.class));
                    break;
                }

            }

            case R.id.imageButton_pause: {
                if (gMediaPlayer.isPlaying()) {
                    gMediaPlayer.pause();
                    gPauseSong.setImageDrawable(ContextCompat.getDrawable(NowPlayingActivity.this, R.drawable.ic_play_button));
                    break;
                } else {
                    if (gMediaPlayer != null) {
                        gMediaPlayer.start();
                        gPauseSong.setImageDrawable(ContextCompat.getDrawable(NowPlayingActivity.this, R.drawable.ic_pause_button));
                        break;
                    } else {
                        int result = gAudioManager.requestAudioFocus(gOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                            gMediaPlayer = MediaPlayer.create(NowPlayingActivity.this, getIntent().getExtras().getInt("songId"));
                            gMediaPlayer.start();
                            break;

                        }
                    }
                }

            }

            //Toasts are generated for previous/next as they have not been built yet

            case R.id.imageButton_previous: {
                Toast.makeText(this, R.string.skip_to_prev, Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.imageButton_next: {
                Toast.makeText(this, R.string.skip_to_next, Toast.LENGTH_SHORT).show();
                break;
            }

        }
    }

    /**
     * This method allows the user to move the Seekbar and thus control playback
     */
    public void onProgressChanged(SeekBar seekBar, int progress, boolean userSeek) {

        if ((gMediaPlayer.isPlaying() || gMediaPlayer != null) && userSeek)
            gMediaPlayer.seekTo(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {

        // If the media player is not null, then it may be currently playing a sound.
        if (gMediaPlayer != null) {

            // Regardless of the current state of the media player, release its resources because they are not needed anymore
            gMediaPlayer.release();

            // Set the media player back to null. This way I can tell if the media player is configured to play a file or not
            gMediaPlayer = null;

            gAudioManager.abandonAudioFocus(gOnAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private MediaPlayer.OnCompletionListener gCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
            NowPlayingActivity.this.finish();
        }
    };
}
