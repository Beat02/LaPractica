package testJUnit;

import laPractica.Jugador;
import laPractica.Maquina;
import laPractica.Partida;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

public class PartidaTest {
    static Partida partidaPrueba;

    @BeforeAll
    static void crearInstanciaRanking() {
        System.out.println("ANTES DE TODOS LOS TESTS");
        partidaPrueba = new Partida();
    }

    @AfterAll
    static void mensajeFinal() {
        System.out.println("TEST FINALIZADO");
    }
    @Test
    @DisplayName("comprobar que se imprime bien el orden de jugadores")
    void imprimirOrdenAleatorio(){
        ArrayList<Jugador> arrayJugador=new ArrayList<>();
        Maquina pcu=new Maquina();
        Jugador jug2=new Jugador("Gojo");
        Jugador jug3=new Jugador("Yuji");
        arrayJugador.add(pcu);
        arrayJugador.add(jug2);
        arrayJugador.add(jug3);
        partidaPrueba.imprimirOrdenAleatorio(arrayJugador);
    }
    @Test
    @DisplayName("comprobar historico actualizado correctamente")
    void actualizarHistorico(){
        Map<String, Integer> datos=partidaPrueba.getMapaJugadores();
        datos.put("PACO",5);
        datos.put("KRIS",3);
        datos.put("LUCIA",9);
        partidaPrueba.setMapaJugadores(datos);
        partidaPrueba.actualizarHistorico();
    }
    @Test
    @DisplayName("comprobar historico actualizado correctamente")
    void imprimirGanador(){
        Map<String, Integer> datos=partidaPrueba.getMapaJugadores();
        datos.put("PACO",5);
        datos.put("KRIS",3);
        datos.put("LUCIA",9);
        partidaPrueba.setMapaJugadores(datos);
        partidaPrueba.ganadorPartida();

    }

}
