package laPractica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);

        // Bucle do-while para continuar solicitando entrada válida
        do {
            try {
                Partida partida = new Partida();
                Ranking ranking = new Ranking();
                Persona personaMenu = new Persona("menu");
                int opcionJugador = partida.menuIncial();

                while (opcionJugador > 0 && opcionJugador < 5) {
                    ArrayList<Jugador> arrayJugadores = partida.getArrayJugadores();
                    arrayJugadores.clear();
                    if (opcionJugador == 1) {
                        int jugadoresPartidaPersona = partida.jugadoresPartidaPersona();
                        if (jugadoresPartidaPersona >= 0 && jugadoresPartidaPersona < 4) {
                            partida.jugadorPartidaMaquina(jugadoresPartidaPersona);
                        }
                        partida.imprimirOrdenAleatorio(arrayJugadores);
                        //rondas
                        System.out.println("Perfecto, ya sabemos los jugadores");
                        Ronda ronda = new Ronda();
                        int numRondas = ronda.menuNumRondas();
                        for (int i = 0; i < numRondas; i++) {
                            ronda.jugarRonda(arrayJugadores);
                        }
                        partida.actualizarHistorico();
                        //todo: arreglar guardado ranking
                        ranking.guardarRankingPostPartida(arrayJugadores);
                        partida.endGame();
                    } else if (opcionJugador == 2) {
                        ranking.imprimirArchivo();
                    } else if (opcionJugador == 3) {
                        partida.imprimirArchivo();
                    } else {
                        personaMenu.menuJugador();
                    }
                    opcionJugador = partida.menuIncial();
                }
                System.out.println("¡Gracias por haber jugado a Conocer y Triunfar!");
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un valor entero válido.Press enter");
                // Limpiar el búfer del scanner después de una excepción para evitar bucles infinitos
                teclado.nextLine();
            }
        } while (true); // Continuar el bucle hasta que se proporcione una entrada válida
    }


}


