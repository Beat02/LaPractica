package laPractica;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Partida {
    Map<String, Integer> mapaJugadores;
    ArrayList<Jugador> arrayJugadores;
    LocalDateTime fechaPartida;

    public Partida() {
        this.mapaJugadores = new HashMap<>();
        this.arrayJugadores = new ArrayList<>();
        this.fechaPartida = LocalDateTime.now();
    }

    public Map<String, Integer> getMapaJugadores() {
        return mapaJugadores;
    }

    public ArrayList<Jugador> getArrayJugadores() {
        return arrayJugadores;
    }

    public LocalDateTime getFechaPartida() {
        return fechaPartida;
    }

    public void setMapaJugadores(Map<String, Integer> mapaJugadores) {
        this.mapaJugadores = mapaJugadores;
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
        String jugador = mapaJugadores.toString();
        try {
            Files.write(rutaHistorico, jugador.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String salto = "" + '\n';
        try {
            Files.write(rutaHistorico, salto.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @apiNote nos imprime el jugador ganador de la partida
     */
    public void ganadorPartida() {
        int maximo = 0;
        String maximaClave = null;

        for (Map.Entry<String, Integer> datos : mapaJugadores.entrySet()) {
            int valor = datos.getValue();
            if (valor > maximo) {
                maximo = valor;
                maximaClave = datos.getKey();
            }
        }
        // Imprimir el máximo
        System.out.println("El ganador de la partida es: " + maximaClave + " con una puntuación de: " + maximo);
    }
    public void puntuacionFinalPartida(){
        System.out.println("El resultado final de esta partida es: "+ mapaJugadores.toString());
    }
    public void endGame(){
        puntuacionFinalPartida();
        ganadorPartida();
        actualizarHistorico();
    }

}
