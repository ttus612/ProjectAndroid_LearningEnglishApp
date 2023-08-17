package com.example.myapplication.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.EnityDB.DocHieuHTCau;
import com.example.myapplication.EnityDB.DocHieuHTDoanVan;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class DocHieuTestNhanhHTCauActivity extends AppCompatActivity  implements View.OnClickListener {
    TextView tv_name_user,tvArrowBack,tv_ThoiGian,tvCauHoi,tvSoCauHoanhThanh,tvCurrent,tvA1,tvB1,tvC1,tvD1,tvA2,tvB2,tvC2,tvD2,tvA3,tvB3,tvC3,tvD3,tvA4,tvB4,tvC4,tvD4;
    Button btnXacNhan;

    private long myDataTG;

    private int count = 0;
    private int questionListSize;
    private int questionCounter;
    private CountDownTimer countDownTimer;
    private int score;

    private boolean answered;
    private int currentQuestion = 0;
    private ArrayList<DocHieuHTDoanVan> arrayListDe;
    private DocHieuHTDoanVan mDocHieuHTDoanVan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_hieu_test_nhanh_htcau);
        tv_name_user = findViewById(R.id.tv_name_user);
        tvArrowBack = findViewById(R.id.tvArrowBack);
        tv_ThoiGian = findViewById(R.id.tv_ThoiGian);
        tvCauHoi = findViewById(R.id.tvCauHoi);
        btnXacNhan = findViewById(R.id.btnXacNhan);
//        tvSoCauHoanhThanh = findViewById(R.id.tvSoCauHoanhThanh);
        tvCurrent = findViewById(R.id.tvCurrent);

        tvA1 = findViewById(R.id.tvA1);
        tvB1 = findViewById(R.id.tvB1);
        tvC1 = findViewById(R.id.tvC1);
        tvD1= findViewById(R.id.tvD1);

        tvA2 = findViewById(R.id.tvA2);
        tvB2 = findViewById(R.id.tvB2);
        tvC2 = findViewById(R.id.tvC2);
        tvD2= findViewById(R.id.tvD2);

        tvA3 = findViewById(R.id.tvA3);
        tvB3 = findViewById(R.id.tvB3);
        tvC3 = findViewById(R.id.tvC3);
        tvD3= findViewById(R.id.tvD3);

        tvA4 = findViewById(R.id.tvA4);
        tvB4 = findViewById(R.id.tvB4);
        tvC4 = findViewById(R.id.tvC4);
        tvD4= findViewById(R.id.tvD4);

        tvArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConForm();
            }
        });
        // Lấy ArrayList từ Intent
        arrayListDe = getIntent().getParcelableArrayListExtra("list_de_ht_cau");

        Log.d("TAG", "onCreate-----------: " + arrayListDe.size());

        //LẤY THỜI GIAN
        myDataTG = getIntent().getLongExtra("myDataThoiGian", 0);
        //LẤY KÍCH CÕ DANH SÁCH BẰNG TỔNG SỐ CÂU HỎI
        questionListSize = arrayListDe.size();
        //ĐẢO VỊ VÍ CÂU HỎI
        Collections.shuffle(arrayListDe);

        setDataQuestion(arrayListDe.get(currentQuestion));

        startCountDown();

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConFormNopBai();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvA1:
                tvA1.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvA1, arrayListDe.get(currentQuestion),arrayListDe.get(currentQuestion).getDapAnDung());
                break;
            case R.id.tvB1:
                tvB1.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvB1, arrayListDe.get(currentQuestion),arrayListDe.get(currentQuestion).getDapAnDung());
                break;
            case R.id.tvC1:
                tvC1.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvC1, arrayListDe.get(currentQuestion),arrayListDe.get(currentQuestion).getDapAnDung());
                break;
            case R.id.tvD1:
                tvD1.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvD1, arrayListDe.get(currentQuestion),arrayListDe.get(currentQuestion).getDapAnDung());
                break;

            case R.id.tvA2:
                tvA2.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvA2, arrayListDe.get(currentQuestion+1),arrayListDe.get(currentQuestion+1).getDapAnDung());
                break;
            case R.id.tvB2:
                tvB2.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvB2, arrayListDe.get(currentQuestion+1),arrayListDe.get(currentQuestion+1).getDapAnDung());
                break;
            case R.id.tvC2:
                tvC2.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvC2, arrayListDe.get(currentQuestion+1),arrayListDe.get(currentQuestion+1).getDapAnDung());
                break;
            case R.id.tvD2:
                tvD2.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvD2, arrayListDe.get(currentQuestion+1),arrayListDe.get(currentQuestion+1).getDapAnDung());
                break;

            case R.id.tvA3:
                tvA3.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvA3, arrayListDe.get(currentQuestion+2),arrayListDe.get(currentQuestion+2).getDapAnDung());
                break;
            case R.id.tvB3:
                tvB3.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvB3, arrayListDe.get(currentQuestion+2),arrayListDe.get(currentQuestion+2).getDapAnDung());
                break;
            case R.id.tvC3:
                tvC3.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvC3, arrayListDe.get(currentQuestion+2),arrayListDe.get(currentQuestion+2).getDapAnDung());
                break;
            case R.id.tvD3:
                tvD3.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvD3, arrayListDe.get(currentQuestion+2),arrayListDe.get(currentQuestion+2).getDapAnDung());
                break;

            case R.id.tvA4:
                tvA4.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvA4, arrayListDe.get(currentQuestion+3),arrayListDe.get(currentQuestion+3).getDapAnDung());
                break;
            case R.id.tvB4:
                tvB4.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvB4, arrayListDe.get(currentQuestion+3),arrayListDe.get(currentQuestion+3).getDapAnDung());
                break;
            case R.id.tvC4:
                tvC4.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvC4, arrayListDe.get(currentQuestion+3),arrayListDe.get(currentQuestion+3).getDapAnDung());
                break;
            case R.id.tvD4:
                tvD4.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvD4, arrayListDe.get(currentQuestion+3),arrayListDe.get(currentQuestion+3).getDapAnDung());
                break;
        }
    }

    private void setDataQuestion(DocHieuHTDoanVan docHieuHTDoanVan) {
        if (docHieuHTDoanVan == null){
            return;
        }
        mDocHieuHTDoanVan = docHieuHTDoanVan;
//        tvCurrent.setText("Question: " + arrayListDe.get(currentQuestion).getIdCau());

//        tvSoCauHoanhThanh.setText(currentQuestion+"/"+arrayListDe.size());
        tvA1.setBackgroundResource(R.drawable.border_black);
        tvB1.setBackgroundResource(R.drawable.border_black);
        tvC1.setBackgroundResource(R.drawable.border_black);
        tvD1.setBackgroundResource(R.drawable.border_black);

        tvA2.setBackgroundResource(R.drawable.border_black);
        tvB2.setBackgroundResource(R.drawable.border_black);
        tvC2.setBackgroundResource(R.drawable.border_black);
        tvD2.setBackgroundResource(R.drawable.border_black);

        tvA3.setBackgroundResource(R.drawable.border_black);
        tvB3.setBackgroundResource(R.drawable.border_black);
        tvC3.setBackgroundResource(R.drawable.border_black);
        tvD3.setBackgroundResource(R.drawable.border_black);

        tvA4.setBackgroundResource(R.drawable.border_black);
        tvB4.setBackgroundResource(R.drawable.border_black);
        tvC4.setBackgroundResource(R.drawable.border_black);
        tvD4.setBackgroundResource(R.drawable.border_black);

        String cauHoi = "Question "+ currentQuestion + 1;
//        tvSoCauHoanhThanh.setText("Câu hỏi: "+currentQuestion+" / "+questionListSize);
        tvCurrent.setText(cauHoi);
        tvCauHoi.setText(arrayListDe.get(currentQuestion).getIdDH().getDe());
        tvA1.setText(arrayListDe.get(currentQuestion).getDapAnA());
        tvB1.setText(arrayListDe.get(currentQuestion).getDapAnB());
        tvC1.setText(arrayListDe.get(currentQuestion).getDapAnC());
        tvD1.setText(arrayListDe.get(currentQuestion).getDapAnD());

        tvA2.setText(arrayListDe.get(currentQuestion+1).getDapAnA());
        tvB2.setText(arrayListDe.get(currentQuestion+1).getDapAnB());
        tvC2.setText(arrayListDe.get(currentQuestion+1).getDapAnC());
        tvD2.setText(arrayListDe.get(currentQuestion+1).getDapAnD());

        tvA3.setText(arrayListDe.get(currentQuestion+2).getDapAnA());
        tvB3.setText(arrayListDe.get(currentQuestion+2).getDapAnB());
        tvC3.setText(arrayListDe.get(currentQuestion+2).getDapAnC());
        tvD3.setText(arrayListDe.get(currentQuestion+2).getDapAnD());

        tvA4.setText(arrayListDe.get(currentQuestion+3).getDapAnA());
        tvB4.setText(arrayListDe.get(currentQuestion+3).getDapAnB());
        tvC4.setText(arrayListDe.get(currentQuestion+3).getDapAnC());
        tvD4.setText(arrayListDe.get(currentQuestion+3).getDapAnD());

        tvA1.setOnClickListener(this);
        tvB1.setOnClickListener(this);
        tvC1.setOnClickListener(this);
        tvD1.setOnClickListener(this);

        tvA2.setOnClickListener(this);
        tvB2.setOnClickListener(this);
        tvC2.setOnClickListener(this);
        tvD2.setOnClickListener(this);

        tvA3.setOnClickListener(this);
        tvB4.setOnClickListener(this);
        tvC3.setOnClickListener(this);
        tvD3.setOnClickListener(this);

        tvA4.setOnClickListener(this);
        tvB4.setOnClickListener(this);
        tvC4.setOnClickListener(this);
        tvD4.setOnClickListener(this);
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
                Intent intent = new Intent(DocHieuTestNhanhHTCauActivity.this, ResultDocHieuActivity.class);
                startActivity(intent);
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
    @Override
    public void onBackPressed() {
        count++;
        if (count>=1){
            finishQuestion();
        }
        count = 0;
    }
    //THOÁT QUA GIAO DIỆN CHÍNH
    private void finishQuestion() {
        //Chứa dữ liệu gửi activity
        Intent intent = new Intent();
        intent.putExtra("sorce", score);
        setResult(RESULT_OK, intent);
        finish();

    }



    public void checkAnswer(TextView textView,DocHieuHTDoanVan docHieuHTDoanVan, String danAn ){

        Log.d("TAG", "checkAnswer: "+textView.getText());
        Log.d("TAG", "checkAnswer: "+danAn);
        Log.d("TAG", "checkAnswer: "+docHieuHTDoanVan);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (textView.getText().equals(danAn)){
                    textView.setBackgroundResource(R.drawable.bg_answer);
                    showCorrect(docHieuHTDoanVan);
//                    nextQuestion();
                }else{
                    textView.setBackgroundResource(R.drawable.bg_sai);
                    showCorrect(docHieuHTDoanVan);
                    gameOver();
                }
            }
        },1000);
    }
    private void gameOver() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDiaLog("Rất tiếp bạn vừa chọn đán án sai !");
            }
        },500);
    }
    private void showCorrect(DocHieuHTDoanVan mDocHieuHTDoanVan) {
        Log.d("TAG", "showCorrect: "+mDocHieuHTDoanVan);
        if ( mDocHieuHTDoanVan.getDapAnA().equals(arrayListDe.get(0).getDapAnDung()) ){
            tvA1.setBackgroundResource(R.drawable.bg_answer);
        }else if( mDocHieuHTDoanVan.getDapAnB().equals(arrayListDe.get(0).getDapAnDung())){
            tvB1.setBackgroundResource(R.drawable.bg_answer);
        }else if( mDocHieuHTDoanVan.getDapAnC().equals(arrayListDe.get(0).getDapAnDung())){
            tvC1.setBackgroundResource(R.drawable.bg_answer);
        }else if( mDocHieuHTDoanVan.getDapAnD().equals( arrayListDe.get(0).getDapAnDung())){
            tvD1.setBackgroundResource(R.drawable.bg_answer);
        }else if ( mDocHieuHTDoanVan.getDapAnA().equals(arrayListDe.get(1).getDapAnDung()) ){
            tvA2.setBackgroundResource(R.drawable.bg_answer);
        }else if( mDocHieuHTDoanVan.getDapAnB().equals(arrayListDe.get(1).getDapAnDung())){
            tvB2.setBackgroundResource(R.drawable.bg_answer);
        }else if( mDocHieuHTDoanVan.getDapAnC().equals(arrayListDe.get(1).getDapAnDung())){
            tvC2.setBackgroundResource(R.drawable.bg_answer);
        }else if( mDocHieuHTDoanVan.getDapAnD().equals( arrayListDe.get(1).getDapAnDung())){
            tvD2.setBackgroundResource(R.drawable.bg_answer);
        }if ( mDocHieuHTDoanVan.getDapAnA().equals(arrayListDe.get(2).getDapAnDung()) ){
            tvA3.setBackgroundResource(R.drawable.bg_answer);
        }else if( mDocHieuHTDoanVan.getDapAnB().equals(arrayListDe.get(2).getDapAnDung())){
            tvB3.setBackgroundResource(R.drawable.bg_answer);
        }else if( mDocHieuHTDoanVan.getDapAnC().equals(arrayListDe.get(2).getDapAnDung())){
            tvC3.setBackgroundResource(R.drawable.bg_answer);
        }else if( mDocHieuHTDoanVan.getDapAnD().equals( arrayListDe.get(2).getDapAnDung())){
            tvD3.setBackgroundResource(R.drawable.bg_answer);
        }if ( mDocHieuHTDoanVan.getDapAnA().equals(arrayListDe.get(3).getDapAnDung()) ){
            tvA4.setBackgroundResource(R.drawable.bg_answer);
        }else if( mDocHieuHTDoanVan.getDapAnB().equals(arrayListDe.get(3).getDapAnDung())){
            tvB4.setBackgroundResource(R.drawable.bg_answer);
        }else if( mDocHieuHTDoanVan.getDapAnC().equals(arrayListDe.get(3).getDapAnDung())){
            tvC4.setBackgroundResource(R.drawable.bg_answer);
        }else if( mDocHieuHTDoanVan.getDapAnD().equals( arrayListDe.get(3).getDapAnDung())){
            tvD4.setBackgroundResource(R.drawable.bg_answer);
        }


        switch (tvCauHoi.getId()){
            case R.id.tvA1:
                tvA1.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvA1, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion).getDapAnDung());
                break;
            case R.id.tvB1:
                tvB1.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvB1, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion).getDapAnDung());
                break;
            case R.id.tvC1:
                tvC1.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvC1, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion).getDapAnDung());
                break;
            case R.id.tvD1:
                tvD1.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvD1, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion).getDapAnDung());
                break;

            case R.id.tvA2:
                tvA2.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvA2, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion+1).getDapAnDung());
                break;
            case R.id.tvB2:
                tvB2.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvB2, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion+1).getDapAnDung());
                break;
            case R.id.tvC2:
                tvC2.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvC2, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion+1).getDapAnDung());
                break;
            case R.id.tvD2:
                tvD2.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvD2, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion+1).getDapAnDung());
                break;


            case R.id.tvA3:
                tvA3.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvA3, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion+2).getDapAnDung());
                break;
            case R.id.tvB3:
                tvB3.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvB3, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion+2).getDapAnDung());
                break;
            case R.id.tvC3:
                tvC3.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvC3, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion+2).getDapAnDung());
                break;
            case R.id.tvD3:
                tvD3.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvD3, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion+2).getDapAnDung());
                break;

            case R.id.tvA4:
                tvA4.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvA4, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion+3).getDapAnDung());
                break;
            case R.id.tvB4:
                tvB4.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvB4, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion+3).getDapAnDung());
                break;
            case R.id.tvC4:
                tvC4.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvC4, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion+3).getDapAnDung());
                break;
            case R.id.tvD4:
                tvD4.setBackgroundResource(R.drawable.bg_select);
                checkAnswer(tvD4, mDocHieuHTDoanVan,arrayListDe.get(currentQuestion+3).getDapAnDung());
                break;
        }
    }
    private void nextQuestion() {
        if (currentQuestion == arrayListDe.size() -1){
            showDiaLog("Hoàn thành tất cả các câu");
            Intent intent = new Intent(DocHieuTestNhanhHTCauActivity.this, ResultDocHieuActivity.class);
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
//                setDataQuestion(arrayListDe.get(currentQuestion));
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}