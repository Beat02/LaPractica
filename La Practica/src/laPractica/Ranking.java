package laPractica;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Ranking {
    private ArrayList<Jugador> ranking;

    public Ranking() {
        this.ranking = ;
    }
    public ArrayList<Jugador> ficheroArrayJugadores(){
        Path rutaFichero = Paths.get("RankingJugadores.txt");

        try {
            long tamanyoFichero= Files.size(rutaFichero);
            if (tamanyoFichero>0){
                for (int x=0;x<tamanyoFichero;x++){
                    Files.readAllLines(rutaFichero);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
