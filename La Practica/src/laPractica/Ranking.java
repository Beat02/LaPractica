package laPractica;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Ranking implements Ficheros {
    //TODO: revisar clase
    private ArrayList<Jugador> rankingJugadores;
    private final Path rutaRanking=Paths.get(Constante.ranking);

    public Ranking() throws IOException {
        this.rankingJugadores = importarArchivo();
    }

    public ArrayList<Jugador> getRankingJugadores() {
        return rankingJugadores;
    }

    public Path getRutaRanking() {
        return rutaRanking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ranking ranking = (Ranking) o;
        return Objects.equals(rankingJugadores, ranking.rankingJugadores) && Objects.equals(rutaRanking, ranking.rutaRanking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankingJugadores, rutaRanking);
    }

    /**
     * @return ArrayList<Jugador> importado desde RankingJugadores.txt
     */
    @Override
    public ArrayList<Jugador> importarArchivo() throws IOException {
        ArrayList<Jugador> listaJugadores = new ArrayList<>();

        try {//TODO: revisar para entender mejor
            List<String> lineas = Files.readAllLines(rutaRanking, StandardCharsets.UTF_8);
            for (String linea : lineas) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                int puntuacion = Integer.parseInt(datos[1]);
                Jugador jugador = new Jugador(nombre);
                jugador.setPuntuacion(puntuacion);
                listaJugadores.add(jugador);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaJugadores;
    }


    public ArrayList<Jugador> organizarRanking(ArrayList<Jugador> listaJugadores) throws IOException {

        listaJugadores = listaJugadores.stream()
                .sorted(Comparator.comparingInt(Jugador::getPuntuacion).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
        return listaJugadores;
    }

    @Override
    public void exportarArchivo(ArrayList<Jugador> listaJugadores) throws IOException {
        String listaLimpia="";
        try {
            Files.write(rutaRanking,listaLimpia.getBytes());
            Files.write(rutaRanking, listaJugadores.toString().getBytes(), StandardOpenOption.APPEND);
            System.out.println("Archivo creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }


    public void guardarRankingActualizado() throws IOException {
        ArrayList<Jugador> listaJugadores = importarArchivo();
        ArrayList<Jugador> listaOrganizada = organizarRanking(listaJugadores);
        exportarArchivo(listaOrganizada);
    }

    @Override
    public void imprimirArchivo() throws IOException {
        try {
            System.out.println(Files.readString(rutaRanking));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void eliminarJugadorRanking(Jugador jugador) throws IOException {
       ArrayList<Jugador> listaJugadores=importarArchivo();
        int i=0;
        boolean jugadorEnLista=false;
        while (!jugadorEnLista && i < listaJugadores.size()) {
            Jugador jugadorAEliminar = jugador;
            jugadorEnLista = jugadorAEliminar.equals(listaJugadores.get(i));
            i++;
        }
        listaJugadores.remove(i);
        exportarArchivo(listaJugadores);
        guardarRankingActualizado();
    }


}
