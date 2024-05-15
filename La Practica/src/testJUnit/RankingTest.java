package testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import laPractica.Jugador;
import laPractica.Persona;
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
        rankingPrueba.imprimirFichero();
    }

    @Test
    @DisplayName("comprobar correcta importacion")
    void importarRanking() throws IOException {
        Jugador jugador1 = new Persona("Yuji");
        Jugador jugador2 = new Persona("Yoasabi");
        ArrayList<Jugador> listaJugador = rankingPrueba.getRankingJugadores();
        assertEquals(jugador1, listaJugador.get(0));
        assertEquals(jugador2, listaJugador.get(1));

    }

    @Test
    @DisplayName("comprobar archivo existe")
    void rutaArchivo() throws IOException {
        assertTrue(Files.exists(rankingPrueba.getRutaRanking()));
    }

    @Test
    @DisplayName("comprobar eliminacion repes")
    void anhadirJugador() throws IOException {
        rankingPrueba.getRankingJugadores().removeFirst();
        Jugador jugador1 = new Persona("malena");
        Jugador jugador2 = new Persona("Luffy");
        jugador2.setPuntuacion(4);
        Jugador jugador3 = new Persona("Yuji");
        ArrayList<Jugador> listaJugadorPartida = new ArrayList<>();
        listaJugadorPartida.add(jugador3);
        listaJugadorPartida.add(jugador2);
        listaJugadorPartida.add(jugador1);
        Jugador jugador4 = new Persona("mar");
        Jugador jugador5 = new Persona("Luffy");
        jugador5.setPuntuacion(3);
        Jugador jugador6 = new Persona("Yuji");
        jugador6.setPuntuacion(20);
        rankingPrueba.getRankingJugadores().add(jugador4);
        rankingPrueba.getRankingJugadores().add(jugador5);
        rankingPrueba.getRankingJugadores().add(jugador6);
        System.out.println(rankingPrueba.getRankingJugadores());
        rankingPrueba.anhadirJugadores(listaJugadorPartida);
        System.out.println(rankingPrueba.getRankingJugadores());
        System.out.println(listaJugadorPartida);

    }
}
