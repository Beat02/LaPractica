package laPractica;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Ranking {
    //TODO: revisar clase
    private ArrayList<Jugador> ranking;
    //Ver Jugadores: muestra la lista de jugadores registrados
    private List<Jugador> listaJugRegistrados;
    //un jugador eliminado se elimina del ranking pero no del histórico

    public Ranking() {
        this.ranking = new ArrayList<>();
        this.listaJugRegistrados=new ArrayList<>();
    }
    public void imprimirJugadoresRegistrados(int opcion){
        if (opcion==1){
            Path path=Paths.get(Constante.registrados);
            try {
                System.out.println(Files.readString(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }if (opcion==2){
            Path path=Paths.get(Constante.ranking);
            try {
                System.out.println(Files.readString(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static List<Jugador> cargarJugadoresDesdeArchivo(String rutaArchivo) {
        List<Jugador> listaJugadores = new ArrayList<>();
        Path rutaFichero = Path.of(rutaArchivo);

        try {
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


}
