package com.example.quizigti;

public class Questions {
    private String pergunta;
    private String reposta;

    public Questions(String pergunta, String reposta) {
        this.pergunta = pergunta;
        this.reposta = reposta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getReposta() {
        return reposta;
    }

    public void setReposta(String reposta) {
        this.reposta = reposta;
    }
}
