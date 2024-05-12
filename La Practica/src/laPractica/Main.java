package laPractica;

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

        int maxJugagores = Constante.maxJugagores;
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
                        "NOMBRE JUGADOR:");
                Jugador jugador1 = new Jugador(teclado.next());
                //TODO: implementar metodo Ranking jugadorRepetido()
                boolean jugadorRepetido = jugador1.jugadorRepetido(jugador1.getNombre());
                if (!jugadorRepetido) {
                    //añadir jugador al array de ranking
                }


            }


        }
        if (totalJugadorMaquina > 0) {
            for (int i = 0; i < totalJugadorMaquina; i++) {
                Maquina pcu = new Maquina();
                boolean repetido = pcu.nombreRepetido(arrayJugadores);
                while (repetido) {
                    String nuevoName = pcu.nuevoNombre();
                    pcu.setNombre(nuevoName);
                    repetido = pcu.nombreRepetido(arrayJugadores);
                }
                arrayJugadores.add(pcu);
            }
        }

        //imprimir orden aleatorio
        Partida partida=new Partida();
        partida.imprimirOrdenAleatorio(arrayJugadores);
        //rondas
        System.out.println("Perfecto, ya sabemos los jugadores");
        Ronda ronda=new Ronda();
        int numRondas=ronda.menuNumRondas();
        for (int i = 0; i < numRondas; i++) {


        }




    }


}

