package com.example.myapplication.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.EnityDB.DocHieu;
import com.example.myapplication.EnityDB.DocHieuHTCau;
import com.example.myapplication.EnityDB.NgheHieu;
import com.example.myapplication.EnityDB.NgheHieuMoTaTranh;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class NgheHieuMTTTestNhanhActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_name_user,tvArrowBack,tv_ThoiGian,tvSoCauHoanhThanh,tvCurrent,tvA,tvB,tvC,tvD,tvThoiGianPlay,tvThoiGianAudio;
    ImageView tvCauHoi;
    Button btnXacNhan;
    ImageButton btnPlay;
    MediaPlayer mp=null;
    SeekBar sbVoice;
    private long myDataTG;
    private long audioTime;

    private int count = 0;
    private int questionListSize;
    private int questionCounter;
    private CountDownTimer countDownTimer;

    private int score;
    private int sourceID;
    private Thread t;

    private boolean answered;
    private int currentQuestion = 0;
    private  ArrayList<NgheHieuMoTaTranh> arrayListDe;
    private NgheHieuMoTaTranh ngheHieuMoTaTranh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nghe_hieu_mtt_test_nhanh);
        tv_name_user = findViewById(R.id.tv_name_user);
        tvArrowBack = findViewById(R.id.tvArrowBack);
        tv_ThoiGian = findViewById(R.id.tv_ThoiGian);
        tvCauHoi = findViewById(R.id.tvCauHoi);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        tvSoCauHoanhThanh = findViewById(R.id.tvSoCauHoanhThanh);
        tvCurrent = findViewById(R.id.tvCurrent);
        sbVoice = findViewById(R.id.seekBarVoice);
        btnPlay = findViewById(R.id.btnPlay);
        sourceID = android.R.drawable.ic_media_play;
        btnPlay.setImageResource(sourceID);
        tvThoiGianAudio = findViewById(R.id.tvThoiGianAudio);
        tvThoiGianPlay = findViewById(R.id.tvThoiGianPlay);


        tvA = findViewById(R.id.tvA);
        tvB = findViewById(R.id.tvB);
        tvC = findViewById(R.id.tvC);
        tvD= findViewById(R.id.tvD);

        tvArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConForm();
            }
        });
        // Lấy ArrayList từ Intent
        arrayListDe = getIntent().getParcelableArrayListExtra("list_de");


        //LẤY THỜI GIAN
        myDataTG = getIntent().getLongExtra("myDataThoiGian", 0);
        //LẤY KÍCH CÕ DANH SÁCH BẰNG TỔNG SỐ CÂU HỎI
        questionListSize = arrayListDe.size();
        //ĐẢO VỊ VÍ CÂU HỎI
        Collections.shuffle(arrayListDe);

        for (int i = 0; i < arrayListDe.size(); i++) {
            Log.d("TAG", "Đề"+ ": " + arrayListDe.get(i));
        }

        setDataQuestion(arrayListDe.get(currentQuestion));

        startCountDown();

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConFormNopBai();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownTimer c = countDownAudio();
                if(sourceID == android.R.drawable.ic_media_play){
                    btnPlay.setImageResource(android.R.drawable.ic_media_pause);
                    mp.start();
                    c.start();
                    chaySeekBar();
                    sourceID = android.R.drawable.ic_media_pause;
                } else{
                    btnPlay.setImageResource(android.R.drawable.ic_media_play);
                    mp.pause();
                    c.cancel();
                    sourceID = android.R.drawable.ic_media_play;
                }
            }
        });
    }

    private void setDataQuestion(NgheHieuMoTaTranh ngheHieuMoTaTranh1) {
        if (ngheHieuMoTaTranh1 == null){
            return;
        }
        ngheHieuMoTaTranh = ngheHieuMoTaTranh1;

        tvA.setBackgroundResource(R.drawable.border_black);
        tvB.setBackgroundResource(R.drawable.border_black);
        tvC.setBackgroundResource(R.drawable.border_black);
        tvD.setBackgroundResource(R.drawable.border_black);

        String cauHoi = "Question "+ (currentQuestion+1);
        tvSoCauHoanhThanh.setText("Câu hỏi: "+currentQuestion+" / "+questionListSize);
        tvCurrent.setText(cauHoi);
        int resourceId = getResources().getIdentifier(arrayListDe.get(currentQuestion).getIdNH().getVoice(), "raw", getPackageName());
        mp = MediaPlayer.create(NgheHieuMTTTestNhanhActivity.this, resourceId);
        int minutes = (int) ((mp.getDuration()/1000)/60);
        int seconds = (int) ((mp.getDuration()/1000)%60);
        String timeTG = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);
        tvThoiGianAudio.setText(timeTG);
        tvCauHoi.setImageResource(getResources().getIdentifier(arrayListDe.get(currentQuestion).getDeHinhAnh(), "drawable", getPackageName()));
        tvA.setText("A");
        tvB.setText("B");
        tvC.setText("C");
        tvD.setText("D");

        tvA.setOnClickListener(this);
        tvB.setOnClickListener(this);
        tvC.setOnClickListener(this);
        tvD.setOnClickListener(this);
    }

    private void startCountDown() {

        countDownTimer = new CountDownTimer(myDataTG, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                myDataTG  = millisUntilFinished;
                //update time
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                //Hết giờ
                myDataTG = 0;
                updateCountDownText();

            }
        }.start();

    }
    private void showDA(){
        tvA.setText(arrayListDe.get(currentQuestion).getDapAnA());
        tvB.setText(arrayListDe.get(currentQuestion).getDapAnB());
        tvC.setText(arrayListDe.get(currentQuestion).getDapAnC());
        tvD.setText(arrayListDe.get(currentQuestion).getDapAnD());
    }

    private CountDownTimer countDownAudio() {
        audioTime = mp.getDuration();
        countDownTimer = new CountDownTimer(audioTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                audioTime  = millisUntilFinished;
                //update time
                updateCountDownTextAudio();
            }

            @Override
            public void onFinish() {
                //Hết giờ
                audioTime = 0;
                updateCountDownTextAudio();

            }
        };
        return countDownTimer;
    }

    private void updateCountDownText() {
        int minutes = (int) ((myDataTG/1000)/60);
        int seconds = (int) ((myDataTG/1000)%60);
        String timeTG = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);
        tv_ThoiGian.setText(timeTG);
        if (myDataTG < 10000) {
            tv_ThoiGian.setTextColor(Color.RED);
        }else{
            tv_ThoiGian.setTextColor(Color.WHITE);
        }
    }

    private void updateCountDownTextAudio() {
        int minutes = (int) (((mp.getDuration()-audioTime)/1000)/60);
        int seconds = (int) (((mp.getDuration()-audioTime)/1000)%60);
        String timeTG = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);
        tvThoiGianPlay.setText(timeTG);
    }

    @Override
    public void onBackPressed() {
        count++;
        if (count>=1){
            finishQuestion();
        }
        count = 0;
    }

    private void chaySeekBar() {
//        final SeekBar mSeelBar = new SeekBar(this);
        final int duration = mp.getDuration();
        final int amoungToupdate = duration / 1000;
        sbVoice.setMax(amoungToupdate);
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                for (int i = 0; i < duration / 1000; i++) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!(amoungToupdate * sbVoice.getProgress() >= duration)) {
                                int p = sbVoice.getProgress();
                                p += 1;
                                sbVoice.setProgress(p);
                            }
                        }
                    });
                    SystemClock.sleep(1000);
                }
            }

        });
        t.start();
    }

    //THOÁT QUA GIAO DIỆN CHÍNH
    private void finishQuestion() {
        //Chứa dữ liệu gửi activity
        Intent intent = new Intent();
        intent.putExtra("sorce", score);
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvA:
                tvA.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvA,arrayListDe.get(currentQuestion).getDapAnA(), ngheHieuMoTaTranh,arrayListDe.get(currentQuestion).getDapAnDung());
                break;
            case R.id.tvB:
                tvB.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvB,arrayListDe.get(currentQuestion).getDapAnB(), ngheHieuMoTaTranh,arrayListDe.get(currentQuestion).getDapAnDung());
                break;
            case R.id.tvC:
                tvC.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvC,arrayListDe.get(currentQuestion).getDapAnC(), ngheHieuMoTaTranh,arrayListDe.get(currentQuestion).getDapAnDung());
                break;
            case R.id.tvD:
                tvD.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvD,arrayListDe.get(currentQuestion).getDapAnD(), ngheHieuMoTaTranh,arrayListDe.get(currentQuestion).getDapAnDung());
                break;
        }
    }

    public void checkAnswer(TextView textView,String dapAnChon,NgheHieuMoTaTranh ngheHieuMoTaTranh, String danAn ){

        Log.d("TAG", "checkAnswer: "+textView.getText());
        Log.d("TAG", "checkAnswer: "+danAn);
        Log.d("TAG", "checkAnswer: "+ngheHieuMoTaTranh);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dapAnChon.equals(danAn)){
                    textView.setBackgroundResource(R.drawable.bg_answer);
                    showCorrect(ngheHieuMoTaTranh);
                    nextQuestion();
                }else{
                    textView.setBackgroundResource(R.drawable.bg_sai);
//                    showCorrect(ngheHieuMoTaTranh);
                    gameOver();
                }
            }
        },1000);
    }

    private void gameOver() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDiaLog("Đán án bạn vừa chọn là sai, vui lòng chọn lại!");
            }
        },500);
    }

    private void showCorrect(NgheHieuMoTaTranh ngheHieuMoTaTranh) {
        if ( ngheHieuMoTaTranh.getDapAnA().equals(arrayListDe.get(0).getDapAnDung()) ){
            tvA.setBackgroundResource(R.drawable.bg_answer);
        }else if( ngheHieuMoTaTranh.getDapAnB().equals(arrayListDe.get(0).getDapAnDung())){
            tvB.setBackgroundResource(R.drawable.bg_answer);
        }else if( ngheHieuMoTaTranh.getDapAnC().equals(arrayListDe.get(0).getDapAnDung())){
            tvC.setBackgroundResource(R.drawable.bg_answer);
        }else if( ngheHieuMoTaTranh.getDapAnD().equals( arrayListDe.get(0).getDapAnDung())){
            tvD.setBackgroundResource(R.drawable.bg_answer);
        }
    }

    private void nextQuestion() {
        if (currentQuestion == arrayListDe.size() -1){
            showDiaLog("Hoàn thành tất cả các câu");
            Intent intent = new Intent(NgheHieuMTTTestNhanhActivity.this, ResultNgheHieuActivity.class);
            startActivity(intent);
        }else{
            currentQuestion++;
            setDataQuestion(arrayListDe.get(currentQuestion));
        }

    }

    private void showDiaLog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                currentQuestion = 0;
//                showCorrect(mDocHieuHTCau);
                setDataQuestion(arrayListDe.get(currentQuestion));
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showConForm() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Thông báo");
        alertDialog.setIcon(R.drawable.question_mark);
        alertDialog.setMessage("Bạn có thật sự muốn thoát hay không?");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Bỏ qua", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }


    private void showConFormNopBai() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Thông báo");
        alertDialog.setIcon(R.drawable.question_mark);
        alertDialog.setMessage("Bạn có thể xem kết quả và đán án, sau khi đã nộp bài");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Nộp", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(NgheHieuMTTTestNhanhActivity.this, ResultNgheHieuActivity.class);
                startActivity(intent);

//                finish();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Hủy bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }
}