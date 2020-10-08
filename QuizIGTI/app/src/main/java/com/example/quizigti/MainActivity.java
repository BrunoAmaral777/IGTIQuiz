package com.example.quizigti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.telephony.mbms.StreamingServiceInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Questions> questionsList = new ArrayList<>();
    TextView txvPerguta;
    Button btnTrue;
    Button btnFalse;
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preenchendoListaDePerguntas();
        txvPerguta = findViewById(R.id.txvPergunta);
        btnTrue = findViewById(R.id.btnVerdadeiro);
        btnFalse = findViewById(R.id.btnFalso);
        txvPerguta.setText(questionsList.get(cont).getPergunta());
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cont == questionsList.size()){
                    cont = 0;
                    startActivity(new Intent(MainActivity.this, ResultadoFinalActivity.class));
                }
                if (questionsList.get(cont).getReposta().toLowerCase().trim().equals("verdadeiro")) {
                    Toast.makeText(MainActivity.this, "Acertou", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Errou", Toast.LENGTH_SHORT).show();
                }


                cont++;
                txvPerguta.setText(questionsList.get(cont).getPergunta());
            }
        });
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cont == questionsList.size()){
                    cont = 0;
                    startActivity(new Intent(MainActivity.this, ResultadoFinalActivity.class));
                }
                if (questionsList.get(cont).getReposta().toLowerCase().trim().equals("falso")) {
                    Toast.makeText(MainActivity.this, "Acertou", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Errou", Toast.LENGTH_SHORT).show();
                }
                cont++;
                txvPerguta.setText(questionsList.get(cont).getPergunta());
            }
        });
    }

    private void preenchendoListaDePerguntas() {
        List<String> bacon = importQuestions();
        for (String item :
                bacon) {
            String pergunta[] = item.split(";");
            Questions questions = new Questions(pergunta[0], pergunta[1]);
            questionsList.add(questions);
        }
    }

    private List<String> importQuestions() {
        AssetManager assetManager = getAssets();
        InputStream inputStream;
        List<String> linhas = new ArrayList<>();
        try {
            inputStream = assetManager.open("Perguntas-1.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = reader.readLine()) != null) {
                linhas.add(line);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;
    }
}