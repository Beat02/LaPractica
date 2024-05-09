package testJUnit;


import laPractica.Ranking;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;

public class RankingTest {
    static Ranking rankingPrueba;

    @BeforeAll
    static void crearInstanciaRanking() {
        System.out.println("ANTES DE TODOS LOS TESTS");
        rankingPrueba = new Ranking();
    }

    @AfterAll
    static void mensajeFinal() {
        System.out.println("TEST FINALIZADO");
    }

    @Test
    @DisplayName("comprobar que se imprime correctamente")
    void imprimirJugadoresRegistrados() {
        rankingPrueba.imprimirListaJugadores(2);
    }

}
