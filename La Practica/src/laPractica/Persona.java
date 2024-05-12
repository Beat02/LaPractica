package laPractica;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Persona extends Jugador {
    public Persona(String nombre) {
        super(nombre);
    }

    public int menuJugador() {
        Scanner teclado = new Scanner(System.in);
        int opcionMenu;
        int valorMinimo = 1;
        int valorMaximo = 4;
        System.out.println("Bienvenide al menú del jugador, ¿qué quieres hacer?" +
                "\n" + "1. Ver jugadores registrados" +
                "\n" + "2. Añadir jugador" +
                "\n" + "3. Eliminar jugador" +
                "\n" + "4. Volver al menú principal");

        opcionMenu = teclado.nextInt();
        while (opcionMenu < valorMinimo || opcionMenu > valorMaximo) {
            System.out.println("Valor introducido incorrecto, pruebe de nuevo con un valor del 1 al 4");
            opcionMenu = teclado.nextInt();
        }
        while (opcionMenu < 4 && opcionMenu > 0) {
            switch (opcionMenu) {
                case 1:
                    imprimirJugadores();
                    menuJugador();
                    break;
                case 2://añadir jugador
                    menuJugador();
                    break;
                case 3://eliminar jugador
                    menuJugador();
                    break;
            }
        }

        return opcionMenu;
    }

    public void imprimirJugadores() {
        Path path = Paths.get(Constante.registrados);
        try {
            System.out.println(Files.readString(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean anhadirJugador() {

        return true;
    }

    public boolean eliminarJugador() {
        return true;
    }

}
