package laPractica;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        Partida partida = new Partida();
        Ranking ranking = new Ranking();
        Persona personaMenu=new Persona("menu");
        int opcionJugador = partida.menuIncial();
        while (opcionJugador > 0 && opcionJugador < 5) {
            ArrayList<Jugador> arrayJugadores = partida.getArrayJugadores();
            arrayJugadores.clear();
            if (opcionJugador == 1) {
                partida.jugadoresPartida();
                partida.imprimirOrdenAleatorio(arrayJugadores);
                //rondas
                System.out.println("Perfecto, ya sabemos los jugadores");
                Ronda ronda = new Ronda();
                int numRondas = ronda.menuNumRondas();
                for (int i = 0; i < numRondas; i++) {
                    ronda.jugarRonda(arrayJugadores);
                }
                partida.actualizarHistorico();
                partida.endGame();
                ranking.guardarRankingPostPartida(arrayJugadores);
                opcionJugador = partida.menuIncial();
            } else if (opcionJugador == 2) {
                ranking.imprimirArchivo();
                opcionJugador = partida.menuIncial();
            } else if (opcionJugador == 3) {
                partida.imprimirArchivo();
                opcionJugador = partida.menuIncial();
            } else{
                personaMenu.menuJugador();
                opcionJugador = partida.menuIncial();
            }
        }
        System.out.println("¡Gracias por haber jugado a Conocer y Triunfar!");


    }


}

