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
    private final Path rutaRanking = Paths.get(Constante.ranking);

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
        if (!Files.exists(rutaRanking)) {
            try {
                Files.createFile(rutaRanking);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {//TODO: revisar para entender mejor
                List<String> lineas = Files.readAllLines(rutaRanking, StandardCharsets.UTF_8);
                // if (!lineas.isEmpty()) {
                for (String linea : lineas) {
                    if (linea.trim().isEmpty()) {
                        continue; // Saltar líneas vacías
                    }

                    String[] datos = linea.split(",");
                    if (datos.length < 2) {
                        continue; // Saltar líneas que no tienen el formato esperado
                    }
                    datos = linea.split(",");
                    String nombre = datos[0];
                    int puntuacion = Integer.parseInt(datos[1]);
                    Jugador jugador = new Jugador(nombre);
                    jugador.setPuntuacion(puntuacion);
                    listaJugadores.add(jugador);
                }
                //}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listaJugadores;
    }

    public void anhadirJugadores(ArrayList<Jugador> arrayJugadoresActuales) {
        arrayJugadoresActuales.removeIf(jugador -> jugador instanceof Maquina);
        if (!rankingJugadores.isEmpty()) {
            for (Jugador jugadorActual : arrayJugadoresActuales) {
                boolean nombreEncontrado = false;
                for (Jugador jugadorRanking : rankingJugadores) {
                    if (jugadorActual.getNombre().equals(jugadorRanking.getNombre())) {
                        // Actualizar la puntuación sumando las puntuaciones
                        jugadorRanking.setPuntuacion(jugadorRanking.getPuntuacion() + jugadorActual.getPuntuacion());
                        nombreEncontrado = true;
                        break;
                    }
                }
                if (!nombreEncontrado) {
                    // Añadir el jugadorActual a listaGrande
                    rankingJugadores.add(jugadorActual);
                }
            }
        }
    }

    public ArrayList<Jugador> organizarRanking() throws IOException {

        rankingJugadores = rankingJugadores.stream()
                .sorted(Comparator.comparingInt(Jugador::getPuntuacion).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
        return rankingJugadores;
    }

    @Override
    public void exportarArchivo(ArrayList<Jugador> listaJugadores) throws IOException {
        Files.deleteIfExists(rutaRanking);
        try {
            String rankingJugadores = "";
            for (int i = 0; i < listaJugadores.size(); i++) {
                Jugador jugador = listaJugadores.get(i);
                rankingJugadores += jugador.getNombre() + "," + jugador.getPuntuacion() + System.lineSeparator();
            }
            Files.write(rutaRanking, rankingJugadores.getBytes(), StandardOpenOption.CREATE);
            System.out.println("Archivo creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }


    public void guardarRankingPostPartida(ArrayList<Jugador> arrayFinalPartida) throws IOException {
        anhadirJugadores(arrayFinalPartida);
        exportarArchivo(rankingJugadores);
        organizarRanking();
    }

    public void guardarRankingAddDelete() throws IOException {
        exportarArchivo(rankingJugadores);
        importarArchivo();
        organizarRanking();
        exportarArchivo(rankingJugadores);
    }

    @Override
    public void imprimirArchivo() throws IOException {
        System.out.println("---RANKING---");
        try {
            System.out.println(Files.readString(rutaRanking));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarJugadorRanking(Jugador jugador) throws IOException {
        int i = 0;
        boolean jugadorEnLista = false;
        while (!jugadorEnLista && i < rankingJugadores.size()) {
            Jugador jugadorAEliminar = jugador;
            jugadorEnLista = jugadorAEliminar.getNombre().equalsIgnoreCase(rankingJugadores.get(i).getNombre());
            if (!jugadorEnLista) {
                i++;
            } else if (jugadorEnLista) {
                rankingJugadores.remove(i);
            }
        }
        guardarRankingAddDelete();

    }

    @Override
    public String toString() {
        String listaranking = super.toString();
        return listaranking;
    }
}


