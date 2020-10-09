package com.example.quizigti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ResultadoFinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_final);
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        TextView txvProgres = findViewById(R.id.text_view_progress);

        progressBar.setProgress(60);
        txvProgres.setText(60 +"%");
    }
}