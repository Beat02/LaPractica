package laPractica;

public abstract class Pregunta {
    String enunciadoPregunta;
    String tipoPregunta;
    String respuestaCorrecta;

    public Pregunta() {
        this.enunciadoPregunta=NULL;
        this.tipoPregunta=;
        this.respuestaCorrecta=;
    }
    public abstract String getEnunciadoPregunta();
    public abstract String getTipoPregunta();
    public abstract String getRespuestaCorrecta();
}
