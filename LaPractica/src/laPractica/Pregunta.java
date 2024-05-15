package laPractica;

public abstract class Pregunta {
    EnunciadoRespuesta enunciadoRespuesta;
    TipoPregunta tipoPregunta;

    public Pregunta(TipoPregunta tipoPregunta){
        this.enunciadoRespuesta = null;
        this.tipoPregunta = tipoPregunta;
    }

    public EnunciadoRespuesta getEnunciadoRespuesta(){
        return enunciadoRespuesta;
    }
}

