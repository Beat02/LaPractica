package laPractica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Maquina extends Jugador {
    private final ArrayList<String> listaNombres;

    public Maquina(String nombre) {
        nombre=null;
        super(nombre);
        this.listaNombres = new ArrayList<>();
        listaNombres.add(Constante.pcu1);
        listaNombres.add(Constante.pcu2);
        listaNombres.add(Constante.pcu3);
        listaNombres.add(Constante.pcu4);
    }

    /**
     * @apiNote nos da unos de los nombres asignados a las CPU en honor a personas queer y mujeres en la ciencia
     * @param indice
     */
    public void setNombre(int indice) {
        this.nombre = getlistaNombres().get(indice);
    }

    private ArrayList<String> getlistaNombres() {
        return listaNombres;
    }
    /**
     * @apiNote respuesta automatizada de la maquina segun tipo pregunta
     * @param pregunta
     */
    public void elegirRespuesta(Pregunta pregunta) {
        String respuesta = "";
        switch (pregunta.tipoPregunta.getTipo()) {
            case "Mates":
                respuesta = pregunta.getEnunciadoRespuesta().getRespuesta();
                break;
            case "Letras":
                respuesta = Util.RandomString();
                break;
            case "Ingles":
                respuesta = Util.RandomBetweenAandD();
                break;
        }
        resultadoRespuesta(respuesta, pregunta.getEnunciadoRespuesta().getRespuesta());
    }


    @Override
    public String getNombre() {
        return super.getNombre();
    }


}


