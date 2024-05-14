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
        ArrayList<Jugador> datos=partidaPrueba.getArrayJugadores();
//        datos.put("PACO",5);
//        datos.put("KRIS",3);
//        datos.put("LUCIA",9);
        //partidaPrueba.setMapaJugadores();
        partidaPrueba.actualizarHistorico();
    }
    @Test
    @DisplayName("comprobar historico actualizado correctamente")
    void imprimirGanador(){
        ArrayList<Jugador> arrayJugadores=new ArrayList<>();
        arrayJugadores.add(new Jugador("Kris"));
        arrayJugadores.get(0).setPuntuacion(3);
        arrayJugadores.add(new Jugador("Mar"));
        arrayJugadores.get(1).setPuntuacion(9);
        arrayJugadores.add(new Jugador("Chenoa"));
        arrayJugadores.get(2).setPuntuacion(5);
        partidaPrueba.ganadorPartida();


    }

}
