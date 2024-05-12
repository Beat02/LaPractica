package laPractica;

public abstract class Pregunta {
    private String enunciadoPregunta;
    private String respuestaCorrecta;

    public Pregunta() {

    }

    public abstract String getEnunciadoPregunta();

    public void setEnunciadoPregunta(String enunciadoPregunta) {
        this.enunciadoPregunta = enunciadoPregunta;
    }
    public abstract String getRespuestaCorrecta();
}

