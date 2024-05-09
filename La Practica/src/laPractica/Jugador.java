package laPractica;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jugador {
    protected String nombre;
    private int puntuacion;


    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntuacion = 0;
    }

    public String getNombre() {
        return nombre;
    }


    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }


    /**
     * @apiNote metodo para actualizar el contador de los jugadores tras una partida
     * @param resultadoPartida
     */
    public void actualizarContador(int resultadoPartida) {
        int puntuacionPreviaPartida = this.getPuntuacion();
        int puntuacionActualizada = puntuacionPreviaPartida + resultadoPartida;
        setPuntuacion(puntuacionActualizada);
    }

    /**
     *
     * @return si es false es que NO está repetido, si es TRUE es que SI está repetido
     */
    public boolean jugadorRepetido(String nombre) {
        Jugador nuevoJugador = new Jugador(nombre);
        Path rutaFichero = Paths.get("RankingJugadores.txt");
        List<Jugador> listaJugadores = Ranking.cargarJugadoresDesdeArchivo("RankingJugadores.txt");
        boolean mismoJugador = false;
        if (!listaJugadores.isEmpty()) {
            int i = 0;
            while (!mismoJugador && i < listaJugadores.size()) {
                mismoJugador = nuevoJugador.equals(listaJugadores.get(i));
                i++;
            }
            if (!mismoJugador) {
                System.out.println("El jugador no se encuentra en la base de datos, vamos a añadirlo");
            }
            if (mismoJugador) {
                System.out.println("Este jugador ya se encuentra en el registro");
            }
        }
        return mismoJugador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(nombre, jugador.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        return nombre + "," + puntuacion;
    }
}
