package laPractica;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.*;

public class Partida {
    ArrayList<Jugador> arrayJugadores;
    LocalDateTime fechaPartida;

    public Partida() {
        this.arrayJugadores = new ArrayList<>();
        this.fechaPartida = LocalDateTime.now();
    }

    public ArrayList<Jugador> getArrayJugadores() {
        return arrayJugadores;
    }

    public LocalDateTime getFechaPartida() {
        return fechaPartida;
    }

    /**
     * @param arrayJugadores
     * @apiNote metodo para crear el orden aleatorio al inicio de partida
     */
    public void imprimirOrdenAleatorio(ArrayList<Jugador> arrayJugadores) {
        Collections.shuffle(arrayJugadores);
        System.out.println("El orden de los jugadores después de mezclar aleatoriamente es:");
        for (Jugador objeto : arrayJugadores) {
            System.out.println(objeto);
        }
    }

    /**
     * @apiNote implementar al final de la partida
     */
    public void actualizarHistorico() {
        Path rutaHistorico = Paths.get(Constante.historico);
        if (!Files.exists(rutaHistorico)) {
            try {
                Files.createFile(rutaHistorico);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String historicoPartidas="";
        try {
            for (int i = 0; i < arrayJugadores.size(); i++) {
                Jugador jugador=arrayJugadores.get(i);
                historicoPartidas+= jugador.getNombre()+","+jugador.getPuntuacion()+ " - ";
            }
            historicoPartidas+=System.lineSeparator();
            Files.write(rutaHistorico, historicoPartidas.getBytes(), StandardOpenOption.CREATE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int menuIncial() {
        Scanner teclado = new Scanner(System.in);
        int opcionMenu;
        System.out.println("Bienvenide a Conocer y Triunfar, tu juego de preguntas online, ¿qué quieres hacer?" +
                "\n" + "1. Jugar nueva partida" +
                "\n" + "2. Ver ranking de jugadores" +
                "\n" + "3. Ver histórico de partidas" +
                "\n" + "4. Menú jugadores" +
                "\n" + "5. Salir");
        opcionMenu = teclado.nextInt();
        return opcionMenu;
    }


    /**
     * @apiNote nos imprime el jugador ganador de la partida
     */
    public void ganadorPartida() {
        int maximo = 0;
        String maximaClave = null;

        for (int i = 0; i < arrayJugadores.size(); i++) {
            int valor = arrayJugadores.get(i).getPuntuacion();
            if (valor > maximo) {
                maximo = valor;
                maximaClave = arrayJugadores.get(i).getNombre();
            }
        }
        // Imprimir el máximo
        System.out.println("El ganador de la partida es: " + maximaClave + " con una puntuación de: " + maximo);
    }

    public void puntuacionFinalPartida() {
        System.out.println("El resultado final de esta partida es: " + arrayJugadores.toString());
    }

    public void endGame() {
        puntuacionFinalPartida();
        ganadorPartida();
        actualizarHistorico();

    }

}
