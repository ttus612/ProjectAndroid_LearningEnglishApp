package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DB.Database;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class TestNhanh_MainActivity extends AppCompatActivity {


    TextView tvNameTestNhanh,tvArrowBack, tvDs, tvPercent;
    ProgressBar processBar;
    SeekBar seekBar;
    Button btnStart, btnPause, btnStop,btnView;

    MediaPlayer mediaPlayer;
    Database db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_nhanh_main);
        tvNameTestNhanh = findViewById(R.id.tvNameTestNhanh);
        tvArrowBack = findViewById(R.id.tvArrowBackTestNhanh);
        btnStart = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        processBar = findViewById(R.id.progressBar);
        seekBar = findViewById(R.id.seekBar);
        tvPercent = findViewById(R.id.tvPercent);
        btnView = findViewById(R.id.btnView);
        tvDs = findViewById(R.id.tvDs);
        db = new Database(TestNhanh_MainActivity.this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                processBar.setProgress(progress);
                tvPercent.setText(""+progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(TestNhanh_MainActivity.this, "onStartTrackingTouch", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(TestNhanh_MainActivity.this, "onStopTrackingTouch", Toast.LENGTH_SHORT).show();
            }
        });

        tvArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(v);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause(v);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop(v);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cursor cursor = db.query_hasresult("select * from LuyenThi");
                    String ds = "";
                    if (cursor.getCount() != 0){
                        while (cursor.moveToNext()){
                            int id = cursor.getInt(0);
                            String ten = cursor.getString(1);
                            ds += id + " - " + ten + "\n";
                        }
                    }else{
                        ds = "Không có dữ liệu";
                    }
                    tvDs.setText(ds);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void play(View view) {
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, R.raw.music3);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        mediaPlayer.start();
    }

    public void pause(View view) {
        if (mediaPlayer != null){
            mediaPlayer.pause();
        }
    }

    public void stop(View view) {
        stopPlayer();
    }

    private void stopPlayer(){
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
    
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}
