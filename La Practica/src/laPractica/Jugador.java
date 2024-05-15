package laPractica;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public abstract class Jugador {
    protected String nombre;
    private int puntuacion;
    static Logger logger = Logger.getLogger(Jugador.class.getName());

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntuacion = 0;
    }

    public Jugador() {

    }

    public String getNombre() {
        return nombre;
    }


    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntos) {
        this.puntuacion = puntos;
    }

    public abstract void elegirRespuesta(Pregunta pregunta);

    public void resultadoRespuesta(String respuesta, String respuestaDelEnunciado) {
        respuestaDelEnunciado = respuestaDelEnunciado.toLowerCase();
        if (respuesta.toLowerCase().equals(respuestaDelEnunciado)) {
            System.out.println("Correcto! " + this.getNombre() + " ha ganado 1 punto!");
            setPuntuacion(getPuntuacion() + 1);
            logger.info("[" + java.time.LocalDate.now() + "][" + java.time.LocalTime.now() + "]: " + this.getNombre() + " ha acertado la respuesta: " + respuesta);
        } else {
            System.out.println("Tu respuesta " + respuesta + " es incorrecta :(.");
            System.out.println("La respuesta correcta era: " + respuestaDelEnunciado);
            logger.info("[" + java.time.LocalDate.now() + "][" + java.time.LocalTime.now() + "]: " + this.getNombre() + " ha fallado la respuesta: " + respuesta);
        }
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
