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
        return nombre + "," + puntuacion + ",";
    }
}
