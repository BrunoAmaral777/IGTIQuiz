package com.example.quizigti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.telephony.mbms.StreamingServiceInfo;
import android.util.Log;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> bacon =importQuestions();
        List<Questions> questionsList  = new ArrayList<>();
        for (String item:
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