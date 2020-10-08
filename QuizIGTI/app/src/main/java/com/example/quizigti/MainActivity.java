package com.example.quizigti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String CONTENT = "A linguagem oficial para desenvolvimento Android Nativo pela Google é a Kotlin; verdadeiro\n" +
            "O processo de publicação do aplicativo na Google Play é gratuito; false\n" +
            "O Brasil possui uma população de quase 210 milhões; verdadeiro\n" +
            "Flutter é uma dos frameworks de desenvolvimento mobile; verdadeiro\n" +
            "A linguagem de programação do Flutter é o Dart; verdadeiro\n" +
            "O Flutter possui interoperabilidade e pode ter projetos em Java e Dart; falso\n" +
            "React-Native é uma plataforma para desenvolvimento de aplicativos móveis; verdadeiro\n" +
            "O Kotlin possui interoperabilidade oque possibilita implementar projetos em Java e Kotlin; verdadeiro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        importQuestions(CONTENT);
    }
    private void importQuestions(String content) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput("questions", MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startActivity(new Intent());
    }
}