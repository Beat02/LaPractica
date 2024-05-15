package laPractica;

import java.io.IOException;
import java.util.ArrayList;

public interface Ficheros {
    // Método para imprimir el contenido de un archivo
    void imprimirFichero() throws IOException;

    // Método para importar un archivo a un ArrayList
    ArrayList<Jugador> importarArchivo() throws IOException;

    // Método para exportar un ArrayList a un archivo
    void exportarArchivo(ArrayList<Jugador> listaJugadores) throws IOException;

}
