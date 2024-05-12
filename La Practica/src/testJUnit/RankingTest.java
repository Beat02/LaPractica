package testJUnit;

import static org.junit.jupiter.api.Assertions.*;
import laPractica.Jugador;
import laPractica.Ranking;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RankingTest {
    static Ranking rankingPrueba;

    @BeforeAll
    static void crearInstanciaRanking() throws IOException {
        System.out.println("ANTES DE TODOS LOS TESTS");
        rankingPrueba = new Ranking();
    }

    @AfterAll
    static void mensajeFinal() {
        System.out.println("TEST FINALIZADO");
    }

    @Test
    @DisplayName("comprobar que se imprime correctamente")
    void imprimirJugadoresRegistrados() throws IOException {
        rankingPrueba.imprimirArchivo();
    }
    @Test
    @DisplayName("comprobar correcta importacion")
    void importarRanking() throws IOException {
        Jugador jugador1=new Jugador("Yuji");
        Jugador jugador2=new Jugador("Yoasabi");
        ArrayList<Jugador> listaJugador=rankingPrueba.getRankingJugadores();
        assertEquals(jugador1,listaJugador.get(0));
        assertEquals(jugador2,listaJugador.get(1));

    }
    @Test
    @DisplayName("comprobar archivo existe")
    void rutaArchivo() throws IOException {
        assertTrue(Files.exists(rankingPrueba.getRutaRanking()));
    }


}
