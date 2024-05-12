package laPractica;

public class PreguntaMatematicas extends Pregunta{


    public PreguntaMatematicas() {
        super();
    }

    /**
     *
     * @return
     */
    //TODO: falta montarla entera
    @Override
    public String getEnunciadoPregunta() {
       String enunciadoPregunta=crearEnunciadoPregunta();
        return enunciadoPregunta;
    }
    public String crearEnunciadoPregunta(){
        String enunciado="2+2";
        return enunciado;
    }
    public String getRespuestaCorrecta(){
        String respuesta="4";
        return respuesta;
    }
}
