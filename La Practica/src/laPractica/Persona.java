package laPractica;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Persona extends Jugador implements Ficheros {
    public Persona(String nombre) {
        super(nombre);
    }

    public int menuJugador() throws IOException {
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
                    imprimirArchivo();
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

    /**
     * @return si es false es que NO está repetido, si es TRUE es que SI está repetido
     */
    public boolean jugadorRepetido(String nombre) throws IOException {
        Jugador nuevoJugador = new Jugador(nombre);
        ArrayList<Jugador> listaJugadores = importarArchivo();
        boolean mismoJugador = false;
        if (!listaJugadores.isEmpty()) {
            int i = 0;
            while (!mismoJugador && i < listaJugadores.size()) {
                mismoJugador = nuevoJugador.equals(listaJugadores.get(i));
                i++;
            }
            if (!mismoJugador) {
                System.out.println("El jugador no se encuentra en la base de datos, vamos a añadirlo");
            }
            if (mismoJugador) {
                System.out.println("Este jugador ya se encuentra en el registro");
            }
        }
        return mismoJugador;
    }

    public void anhadirJugador() throws IOException {
        String nombreNuevoJugador;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Dime el nombre del jugador que quieres añadir: " +
                "\n" + "¡RECUERDA! El nombre del jugador no puede tener espacios");
        nombreNuevoJugador = teclado.next(); //TODO: chequear que tenga espacios!
        boolean jugadorRepe = jugadorRepetido(nombreNuevoJugador);
        if (!jugadorRepe) {
            Jugador jugador = new Persona(nombreNuevoJugador);
            Path rutaRegistro = Paths.get(Constante.registrados);
            Files.write(rutaRegistro, jugador.toString().getBytes(), StandardOpenOption.APPEND);
            System.out.println("Jugador añadido");
        } else {
            System.out.println("No es posible añadir este jugador");
        }

    }

    public boolean eliminarJugador() {
        return true;
    }

    @Override
    public void imprimirArchivo() throws IOException {
        Path path = Paths.get(Constante.registrados);
        try {
            System.out.println(Files.readString(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Jugador> importarArchivo() throws IOException {
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        Path rutaFichero = Path.of(Constante.registrados);

        try {//TODO: revisar para entender mejor
            List<String> lineas = Files.readAllLines(rutaFichero, StandardCharsets.UTF_8);
            for (String linea : lineas) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                int puntuacion = Integer.parseInt(datos[1]);
                Jugador jugador = new Jugador(nombre);
                jugador.setPuntuacion(puntuacion);
                listaJugadores.add(jugador);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaJugadores;
    }

    @Override
    public void exportarArchivo(ArrayList<String> contenido, String nombreArchivo) throws IOException {

    }
}
