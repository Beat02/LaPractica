package laPractica;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Ranking implements Ficheros {
    //TODO: revisar clase
    private ArrayList<Jugador> ranking;
    //Ver Jugadores: muestra la lista de jugadores registrados
    private List<Jugador> listaJugRegistrados;
    //un jugador eliminado se elimina del ranking pero no del histórico

    public Ranking() {
        this.ranking = new ArrayList<>();
        this.listaJugRegistrados = new ArrayList<>();
    }

    /**
     * @return ArrayList<Jugador> importado desde RankingJugadores.txt
     */
    @Override
    public ArrayList<Jugador> importarArchivo() throws IOException {
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        Path rutaFichero = Path.of(Constante.ranking);

        try {//TODO: revisar para entender mejor
            List<String> lineas = Files.readAllLines(rutaFichero, StandardCharsets.UTF_8);
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

    @Override
    public void exportarArchivo(ArrayList<String> contenido, String nombreArchivo) throws IOException {

    }

    @Override
    public void imprimirArchivo() throws IOException {
        Path path = Paths.get(Constante.ranking);
        try {
            System.out.println(Files.readString(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
