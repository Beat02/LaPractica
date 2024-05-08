package laPractica;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jugador {
    private final String nombre;
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

    public void actualizarContador(int resultadoPartida) {
        int puntuacionPreviaPartida = this.getPuntuacion();
        int puntuacionActualizada = puntuacionPreviaPartida + resultadoPartida;
        setPuntuacion(puntuacionActualizada);
    }

    /**
     * @param nombre
     * @return si es false es que NO está repetido, si es TRUE es que SI está repetido
     */
    public boolean jugadorRepetido(String nombre) {
        //TODO: falta importar la lista de jugadores y meterla en el arrayList
        Jugador nuevoJugador = new Jugador(nombre);

        Path rutaFichero = Paths.get("RankingJugadores.txt");
        List<String> listaJugadores = new ArrayList<>();

        try {
            listaJugadores = Files.readAllLines(Paths.get(rutaFichero.toUri()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean mismoJugador = false;
        if (!listaJugadores.isEmpty()) {
            int i = 0;
            while (!mismoJugador && i < listaJugadores.size()) {
                mismoJugador = nuevoJugador.getNombre().equals(listaJugadores.get(i));
                i++;
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
}
