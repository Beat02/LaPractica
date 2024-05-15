package laPractica;

import java.util.ArrayList;
import java.util.Scanner;

public class Ronda {

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
    public void jugarRonda(ArrayList<Jugador> arrayJugadores){
        Scanner teclado=new Scanner(System.in);
        for (int i = 0; i < arrayJugadores.size(); i++) {
            Jugador jugador=arrayJugadores.get(i);
            Pregunta pregunta=new PreguntaMatematicas();
            System.out.println("Es el turno de: "+jugador.getNombre());
            System.out.println(pregunta.getEnunciadoPregunta());
            String respuestaPorTeclado= teclado.next();
            boolean aciertoPregunta=pregunta.getRespuestaCorrecta().equalsIgnoreCase(respuestaPorTeclado);

            if (aciertoPregunta){
                System.out.println("¡Has acertado! :)");
                jugador.setPuntuacion(jugador.getPuntuacion()+1);
            } else if (!aciertoPregunta) {
                System.out.println("Has fallado :(");
                System.out.println("La respuesta correcta es: "+pregunta.getRespuestaCorrecta());
            }

        }for (int i = 0; i < arrayJugadores.size(); i++){
            Jugador jugador=arrayJugadores.get(i);
        }
    }

}
