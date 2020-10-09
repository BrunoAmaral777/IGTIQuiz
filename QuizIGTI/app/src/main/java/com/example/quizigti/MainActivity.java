package com.example.quizigti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.mbms.StreamingServiceInfo;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
    public static final String PONTUACAO = "PONTUACAO";
    public static final int SUCCESS = 1;
    public static final int ERROR = 0;
    List<Questions> questionsList = new ArrayList<>();
    TextView txvPerguta;
    Button btnTrue;
    Button btnFalse;
    int cont = 0;
    int pontuacao = 0;

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
                if (questionsList.get(cont).getReposta().toLowerCase().trim().equals("verdadeiro")) {
                    showToast(SUCCESS, "Acertou!!");
                    pontuacao++;
                } else {
                    showToast(ERROR, "Errou!");
                }


                if (cont == questionsList.size() - 1) {
                    cont = 0;
                    Intent intent = new Intent(MainActivity.this, ResultadoFinalActivity.class);
                    intent.putExtra(PONTUACAO, Math.max(pontuacao, 0));
                    startActivity(intent);
                    finish();
                } else {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cont++;
                            txvPerguta.setText(questionsList.get(cont).getPergunta());
                        }
                    }, 2000);

                }
            }
        });
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (questionsList.get(cont).getReposta().toLowerCase().trim().equals("falso")) {
                    showToast(SUCCESS, "Acertou!!");
                    pontuacao++;
                } else {
                    showToast(ERROR, "Errou!");
                }
                if (cont == questionsList.size() - 1) {
                    cont = 0;
                    Intent intent = new Intent(MainActivity.this, ResultadoFinalActivity.class);
                    intent.putExtra(PONTUACAO, Math.max(pontuacao, 0));
                    startActivity(intent);
                    finish();
                } else {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cont++;
                            txvPerguta.setText(questionsList.get(cont).getPergunta());
                        }
                    }, 2000);

                }
            }
        });
    }

    private void showToast(int type, String text) {
        Toast toast = new Toast(this);
        ViewGroup container = findViewById(R.id.container_toast);
        View view = getLayoutInflater().inflate(R.layout.custom_toast, container);
        ImageView image = view.findViewById(R.id.imageView);
        switch (type) {
            case SUCCESS:
                view.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_success));
                image.setImageResource(R.drawable.ic_baseline_check_24);
                break;
            case ERROR:
                view.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_error));
                image.setImageResource(R.drawable.ic_baseline_cancel_24);
                break;
        }

        TextView txtMessage = view.findViewById(R.id.txv_message);
        txtMessage.setText(text);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
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