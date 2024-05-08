package laPractica;

import java.util.Scanner;

public class Ronda {
    public int numRondas(){
        Scanner teclado=new Scanner(System.in);
        int totalRondas=0;
        System.out.println("Hay cuatro tipos de rondas,¿cuál quires jugar?" +
                '\n' + "Partida rápida (3 rondas)" +
                '\n' + "Partida corta (5 rondas)" +
                '\n' + "Partida normal (10 rondas)" +
                '\n' + "Partida larga (20 rondas)" + '\n' + "Escoge qué tipo quieres jugar (ejemplo: corta, larga,etc): ");
        String tipoRonda = teclado.next();
        if (tipoRonda.equalsIgnoreCase(Constante.partidaRapida)){
            totalRondas=3;
        } else if (tipoRonda.equalsIgnoreCase(Constante.partidaCorta)) {
            totalRondas=5;
        }else if (tipoRonda.equalsIgnoreCase(Constante.partidaNormal)) {
            totalRondas=10;
        }else if (tipoRonda.equalsIgnoreCase(Constante.partidaLarga)) {
            totalRondas=20;
        }else {
            System.out.println("El tipo de ronda que has indicado no es correcto, prueba de nuevo");
            Ronda ronda=new Ronda();
            totalRondas=ronda.numRondas();
        }
        return totalRondas;
    }

}
