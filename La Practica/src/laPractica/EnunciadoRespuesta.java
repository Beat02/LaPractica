package laPractica;

public class EnunciadoRespuesta {
    private String enunciado;
    private String respuesta;

    public EnunciadoRespuesta(String enunciado, String respuesta){
        this.enunciado = enunciado;
        this.respuesta = respuesta;
    }

    public String getEnunciado(){
        return this.enunciado;
    }

    public String getRespuesta(){
        return this.respuesta;
    }
}
