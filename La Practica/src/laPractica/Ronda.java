package laPractica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ronda {
    private ArrayList<String> tipoPregunta = new ArrayList<>();

    public Ronda() {
        tipoPregunta = new ArrayList<>();
        tipoPregunta.add(Constante.mates);
        tipoPregunta.add(Constante.ingles);
        tipoPregunta.add(Constante.letras);
    }

    public ArrayList<String> getTipoPregunta() {
        return tipoPregunta;
    }

    public Pregunta tipoPreguntaRandom() {
        Collections.shuffle(tipoPregunta);
        Pregunta pregunta = null;
        if (tipoPregunta.getFirst().equalsIgnoreCase(Constante.mates)) {
            pregunta = new PreguntaMatematicas();
        }
        else if (tipoPregunta.getFirst().equalsIgnoreCase(Constante.ingles)) {
            pregunta = new PreguntaIngles();
        } else {
            pregunta = new PreguntaLetras();
        }
        return pregunta;

    }

    /**
     * @return int con número de rondas
     * @apiNote metodo con el menu sobre las rondas
     */
    public int menuNumRondas() {
        Scanner teclado = new Scanner(System.in);
        int totalRondas = 0;
        System.out.println("Hay cuatro tipos de rondas,¿cuál quires jugar?" +
                '\n' + "Partida rápida (3 rondas)" +
                '\n' + "Partida corta (5 rondas)" +
                '\n' + "Partida normal (10 rondas)" +
                '\n' + "Partida larga (20 rondas)" + '\n' + "Escoge qué tipo quieres jugar (ejemplo: corta, larga,etc): ");
        String tipoRonda = teclado.next();
        if (tipoRonda.equalsIgnoreCase(Constante.partidaRapida)) {
            totalRondas = 3;
        } else if (tipoRonda.equalsIgnoreCase(Constante.partidaCorta)) {
            totalRondas = 5;
        } else if (tipoRonda.equalsIgnoreCase(Constante.partidaNormal)) {
            totalRondas = 10;
        } else if (tipoRonda.equalsIgnoreCase(Constante.partidaLarga)) {
            totalRondas = 20;
        } else {
            System.out.println("El tipo de ronda que has indicado no es correcto, prueba de nuevo");
            Ronda ronda = new Ronda();
            totalRondas = ronda.menuNumRondas();
        }
        return totalRondas;
    }

    public void jugarRonda(ArrayList<Jugador> arrayJugadores) {
        for (int i = 0; i < arrayJugadores.size(); i++) {
            Jugador jugador = arrayJugadores.get(i);
            Pregunta pregunta = tipoPreguntaRandom();
            System.out.println("Es el turno de: " + jugador.getNombre());
            System.out.println(pregunta.enunciadoRespuesta.getEnunciado());
            jugador.elegirRespuesta(pregunta);
        }
    }

}
