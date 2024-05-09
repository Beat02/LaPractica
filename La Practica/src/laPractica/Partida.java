package laPractica;

import java.util.ArrayList;
import java.util.Collections;

public class Partida {
    ArrayList<Jugador> arrayJugadores;
    int numeroRondas;

    /**
     * @apiNote metodo para crear el orden aleatorio al inicio de partida
     * @param arrayJugadores
     */
    public void imprimirOrdenAleatorio(ArrayList<Jugador> arrayJugadores) {
        Collections.shuffle(arrayJugadores);
        System.out.println("El orden de los jugadores después de mezclar aleatoriamente es:");
        for (Jugador objeto : arrayJugadores) {
            System.out.println(objeto);
        }
    }
}
