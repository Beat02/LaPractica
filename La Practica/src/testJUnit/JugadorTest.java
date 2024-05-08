package testJUnit;

import laPractica.Jugador;
import org.junit.jupiter.api.*;

public class JugadorTest {
    static Jugador nuevoJugador;

    @BeforeAll
    static void crearInstanciaSistemaVenta() {
        System.out.println("ANTES DE TODOS LOS TESTS");
        nuevoJugador = new Jugador("Yoasabi");
    }
    @Test
    @DisplayName("comprobar jugador repetido")
    void jugadorRepetido(){

    }
}
