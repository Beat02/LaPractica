package testJUnit;
import laPractica.Jugador;
import laPractica.Partida;
import laPractica.Persona;
import laPractica.Ranking;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;

public class PersonaTest {
    static Persona persona;

    @BeforeAll
    static void crearInstanciaPersona() throws IOException {
        System.out.println("ANTES DE TODOS LOS TESTS");
        persona = new Persona("Jimmy");
    }
    @BeforeEach
     void crearPartida(){
        System.out.println("ANTES DE CADA TEST");
        Partida partida=new Partida();
       ArrayList<Jugador> arrayList= partida.getArrayJugadores();
       arrayList.add(new Persona("Jimmy"));
        arrayList.add(new Persona("Kim"));


    }

    @AfterAll
    static void mensajeFinal() {
        System.out.println("TEST FINALIZADO");
    }
    @Test
    @DisplayName("comprobar eliminar persona")
    void eliminarPersRegistro() throws IOException {
        persona.eliminarJugador();


    }
}
