package testJUnit;

import laPractica.Jugador;
import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.APPEND;

public class JugadorTest {
    static Jugador nuevoJugador;

    @BeforeAll
    static void crearInstanciaJugador() {
        System.out.println("ANTES DE TODOS LOS TESTS");
        nuevoJugador = new Jugador("Yoasabi");
    }

    @AfterAll
    static void mensajeFinal() {
        System.out.println("TEST FINALIZADO");
    }

    @Test
    @DisplayName("comprobar jugador si está repetido")
    void jugadorRepetido() {
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        Path rutaFichero = Paths.get("RankingJugadores.txt");

        try {
            Files.deleteIfExists(rutaFichero);
            Files.createFile(rutaFichero);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Jugador prueba = new Jugador("Yuji");
        listaJugadores.add(prueba);
        listaJugadores.add(nuevoJugador);
        try {
            for (int i = 0; i < listaJugadores.size(); i++) {
                Files.write(rutaFichero,listaJugadores.get(i).toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertTrue(prueba.jugadorRepetido("Yoasabi"));
    }

    @Test
    @DisplayName("comprobar jugador NO está repetido, por lo que hay que hacer uno nuevo")
    void jugadorNoRepetido() {
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        Path rutaFichero = Paths.get("RankingJugadores.txt");

        try {
            Files.deleteIfExists(rutaFichero);
            Files.createFile(rutaFichero);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Jugador prueba = new Jugador("Yuji");
        listaJugadores.add(prueba);
        listaJugadores.add(nuevoJugador);
        try {
            for (int i = 0; i < listaJugadores.size(); i++) {
                Files.write(rutaFichero,listaJugadores.get(i).toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertFalse(prueba.jugadorRepetido("Kakashi"));

    }
}
