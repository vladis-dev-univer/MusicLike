package by.bsuir.musiclike.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import by.bsuir.musiclike.R;
import by.bsuir.musiclike.models.Song;

import static by.bsuir.musiclike.activities.SongsListActivity.songs;

public class SongActivity extends AppCompatActivity {
    public static boolean playing;
    TextView song_name,
            song_artist,
            duration_played,
            duration_total;
    ImageView cover_art,
            viewDesign;
    ImageButton btnPlay,
            btnNext,
            btnPrev;
    SeekBar seekBarTime,
            seekBarVolume;
    int position = -1;
    private ArrayList<Song> listSongs = songs;
    static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        initView();

        getIntentMethod();

        seekBarVolume.setProgress(50);
        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean isFromUser) {
                float volume = progress / 100f;
                mediaPlayer.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Buttons();

    }

    private void getIntentMethod() {
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        position = bundle.getInt("position");
        position = (position) % listSongs.size();
        initPlayer(position);

    }

    public void initPlayer(final int position) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }
        setData(position);
        Uri trackUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                listSongs.get(position).getId());
        mediaPlayer = MediaPlayer.create(getApplicationContext(), trackUri); // create and load media player with song resources
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                String totalTime = createTimeLabel(mediaPlayer.getDuration());
                duration_total.setText(totalTime);
                seekBarTime.setMax(mediaPlayer.getDuration() / 1000);
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.ic_pause_button);

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int curSongPosition = position;
                curSongPosition = (curSongPosition + 1) % (listSongs.size());
                initPlayer(curSongPosition);

            }
        });
        initiateSeekBar();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mediaPlayer != null) {
                    try {
                        if (mediaPlayer.isPlaying()) {
                            Message msg = new Message();
                            msg.what = mediaPlayer.getCurrentPosition();
                            msg.arg1 = mediaPlayer.getDuration();
                            handler.sendMessage(msg);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void initiateSeekBar() {
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress * 1000);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int current_position = msg.what;
            seekBarTime.setMax(mediaPlayer.getDuration() / 1000);
            seekBarTime.setProgress(current_position / 1000);
            System.out.println(seekBarTime.getProgress());
            String cTime = createTimeLabel(current_position);
            duration_played.setText(cTime);
            duration_total.setText(createTimeLabel(msg.arg1));
        }
    };

    public String createTimeLabel(int duration) {
        String timeLabel = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;

        timeLabel += min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;

    }

    public void setData(int position) {

        String name = listSongs.get(position).getName();
        String artist = listSongs.get(position).getArtist();
        song_name.setText(name);
        song_artist.setText(artist);
        try {

            Glide
                    .with(getApplicationContext())
                    .load(ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), listSongs.get(position).getAlbumID()).toString())
                    .thumbnail(0.2f)
                    .centerCrop()
                    .placeholder(R.drawable.ic_musical_note)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(cover_art);
        } catch (Exception e) {
            Glide.with(this).load(R.drawable.ic_musical_note).into(cover_art);
        }

    }

    private void initView() {
        viewDesign = findViewById(R.id.imageViewSong);
        song_name = findViewById(R.id.titleName);
        song_artist = findViewById(R.id.titleArtist);
        duration_played = findViewById(R.id.tvTime);
        duration_total = findViewById(R.id.tvDuration);
        seekBarTime = findViewById(R.id.seekBarSongLine);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        btnPlay = findViewById(R.id.buttonPlaySong);
        btnPrev = findViewById(R.id.buttonPrevSong);
        btnNext = findViewById(R.id.buttonNextSong);
        cover_art = findViewById(R.id.songPicture);
    }

    public void Buttons() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 0) {
                    position = listSongs.size() - 1;
                } else {
                    position--;
                }
                initPlayer(position);

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = (position + 1) % listSongs.size();
                initPlayer(position);
            }
        });
    }

    public void play() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            playing = false;
            mediaPlayer.pause();
            btnPlay.setImageResource(R.drawable.ic_play_button);
        } else {
            pause();
        }
    }

    public void pause() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            playing = true;
            mediaPlayer.start();
            btnPlay.setImageResource(R.drawable.ic_pause_button);
        }
    }


}