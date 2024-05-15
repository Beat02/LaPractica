package laPractica;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PreguntaIngles extends Pregunta{

    private final Path ingles = Paths.get("src/LaPractica/ingles.txt");
    private String pregunta;
    private String[] respuestas;
    private final String[] ABCD = {"A", "B", "C", "D"};
    private int respuestaCorrecta;


    public PreguntaIngles() {
        super(new TipoPregunta("Ingles"));
        obtenerPreguntaAleatoria();
        String respuestasEnLinea = reordenarRespuestas();
        enunciadoRespuesta = new EnunciadoRespuesta(pregunta+"\n"+respuestasEnLinea, ABCD[respuestaCorrecta]);
    }

    /**
     *@apiNote con esto obtenemos una pregunta aleatoria
     */
    private void obtenerPreguntaAleatoria() {
        try {
            long total = Files.lines(ingles).count();
            int aleatorio = (int)(Math.random() * (total / 5));
            pregunta = Files.lines(ingles).skip(aleatorio * 5L).findFirst().get();
            respuestas = new String[4];
            for (int i = 0; i < 4; i++) {
                respuestas[i] = Files.lines(ingles).skip(aleatorio * 5L + i + 1).findFirst().get();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return string con las opciones de respuestas reordenadas
     */
    private String reordenarRespuestas() {
        List<String> listaRespuestas = new ArrayList<>(Arrays.asList(respuestas));
        Collections.shuffle(listaRespuestas);

        for (int i = 0; i < listaRespuestas.size(); i++) {
            if (listaRespuestas.get(i).equals(respuestas[0])) {
                respuestaCorrecta = i;
                break;
            }
        }

        StringBuilder respuestasEnLinea = new StringBuilder();
        for (int i = 0; i < respuestas.length; i++) {
            respuestasEnLinea.append(ABCD[i]).append(") ").append(listaRespuestas.get(i)).append("\n");
        }

        return respuestasEnLinea.toString();
    }
}
