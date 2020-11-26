package by.bsuir.musiclike.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import by.bsuir.musiclike.R;

public class SongActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvTime, tvDuration;
    SeekBar seekBarTime, seekBarVolume;
    ImageButton btnPlay, btnBack;

    MediaPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        tvTime = findViewById(R.id.tvTime);
        tvDuration = findViewById(R.id.tvDuration);
        seekBarTime = findViewById(R.id.seekBarSongLine);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        btnPlay = findViewById(R.id.buttonPlaySong);
        btnBack = findViewById(R.id.imageButtonArrowBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".MainActivity");
                startActivity(intent);
            }
        });

        musicPlayer = MediaPlayer.create(this, R.raw.jeremy_camp_i_still_believe);
        musicPlayer.setLooping(true);
        musicPlayer.seekTo(0);
        musicPlayer.setVolume(0.5f, 0.5f);

        String duration = millisecondsToString(musicPlayer.getDuration());
        tvDuration.setText(duration);


        btnPlay.setOnClickListener(this);

        //----------> SeekBarVolume <----------
        seekBarVolume.setProgress(50);
        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean isFromUser) {
                float volume = progress / 100f;
                musicPlayer.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //----------> SeekBarTime <----------
        seekBarTime.setMax(musicPlayer.getDuration());
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean isFromUser) {
                if (isFromUser) {
                    musicPlayer.seekTo(progress);
                    seekBar.setProgress(progress);

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //----------> Thread <----------
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (musicPlayer != null) {
                    if (musicPlayer.isPlaying()) {
                        try {
                            final double current = musicPlayer.getCurrentPosition();
                            final String elapsedTime = millisecondsToString((int)current);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvTime.setText(elapsedTime);
                                    seekBarTime.setProgress((int)current);
                                }
                            });

                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                    }
                }
            }
        }).start();
    }

    public String millisecondsToString(int time) {
        String elapsedTime = "";
        int minute = time / 1000 / 60;
        int seconds = time / 1000 % 60;
        elapsedTime = minute + ":";
        if (seconds < 10) {
            elapsedTime += "0";
        }

        elapsedTime += seconds;
        return elapsedTime;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonPlaySong) {
            if (musicPlayer.isPlaying()) {
                // is playing
                musicPlayer.pause();
                btnPlay.setImageResource(R.drawable.ic_play_button);
            } else {
                //on pause
                musicPlayer.start();
                btnPlay.setImageResource(R.drawable.ic_pause_button);
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(musicPlayer.isPlaying()){
            musicPlayer.stop();
        }
    }


}