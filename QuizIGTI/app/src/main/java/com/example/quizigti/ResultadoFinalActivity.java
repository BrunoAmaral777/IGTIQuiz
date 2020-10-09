package com.example.quizigti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ResultadoFinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btnReturn = findViewById(R.id.btnReturn);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_final);
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        TextView txvProgres = findViewById(R.id.text_view_progress);

        Intent intent = getIntent();
        int pontuacao = intent.getIntExtra(MainActivity.PONTUACAO, 0);

        int percent = (pontuacao * 100) / 7;
        if (percent>=100){
            percent= 100;
        }
        progressBar.setProgress(percent);
        txvProgres.setText(percent + "%");

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultadoFinalActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}