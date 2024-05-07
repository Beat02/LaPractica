package laPractica;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        //Instanciar fichero ranking jugadores
        Path rutaFichero = Paths.get("RankingJugadores.txt");

        //pedir numero jugadores HUMANOS
        System.out.println("Bienvenide al Trivial, tu juego de preguntas" + '\n' +
                "El total de jugadores posible es cuatro, y de esos cuatro, puedes escoger cuantos son humanos y cuantos son máquinas" + '\n'
                + "¿Cuántos jugadores humanos habrá en esta partida?");

        int maxJugagores = 4;//TODO: para la clase Constante
        int totalJugadoresPartidaActual = 0;
        int totalJugadorMaquina = 0;
        ArrayList<Jugador> arrayJugadores = new ArrayList<>();
        int totalJugadorHumano = teclado.nextInt();
        if (totalJugadorHumano > 1 && totalJugadorHumano < 4) {
            totalJugadoresPartidaActual = maxJugagores - totalJugadorHumano;
            System.out.println("Ya hay " + totalJugadorHumano + " jugadores humanos, puedes incluir hasta " +
                    totalJugadoresPartidaActual + " jugadores máquina, escribe cuantos quieres:");
            totalJugadorMaquina = teclado.nextInt();
        } else if (totalJugadorHumano == 1) {
            System.out.println("Solo hay un jugador humano, si quieres, puedes incluir hasta tres jugadores máquina, escribe cuantos quieres:");
            totalJugadorMaquina = teclado.nextInt();
        } else if (totalJugadorHumano == 0) {
            System.out.println("Veo que en esta partida quiere ver jugar a la máquina, ¿cuántas quieres que jueguen?");
            totalJugadorMaquina = teclado.nextInt();//TODO: aquí hay que meter unas cuantas excepciones
        }
        //TODO: comprobar cantidad jugadores humanos
        System.out.println("Perfecto, ya tenemos el número de jugadores");
        if (totalJugadorHumano > totalJugadorMaquina) {
            int i = 0;
            while (i < totalJugadorHumano) {
                System.out.println("Ahora necesito que me des el nombre de los jugadores humanos:" + '\n' +
                        "NOMBRE JUGADOR 1:");
                Jugador jugador1 = new Jugador(teclado.next());
                //TODO: implementar metodo Ranking jugadorRepetido()




            }


        }
        System.out.println("");


    }
}
