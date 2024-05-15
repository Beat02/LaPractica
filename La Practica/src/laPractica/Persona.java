package laPractica;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class Persona extends Jugador implements Ficheros {
    private final Path rutaRegistrados = Paths.get(Constante.registrados);

    public Persona(String nombre) {
        super(nombre);
    }

    final Scanner teclado = new Scanner(System.in);

    /**
     * @return int con la opcion del menu seleccionado
     * @throws IOException al haber un Input mismatch
     * @apiNote menu jugador
     */
    public int menuJugador() throws IOException {
        if (!Files.exists(rutaRegistrados)) {
            Files.createFile(rutaRegistrados);
        }
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
                    imprimirFichero();
                    opcionMenu = menuJugador();
                    break;
                case 2:
                    anhadirJugador();
                    opcionMenu = menuJugador();
                    break;
                case 3:
                    eliminarJugador();
                    opcionMenu = menuJugador();
                    break;
            }
        }
        return opcionMenu;
    }

    /**
     * @return si es false es que NO está repetido, si es TRUE es que SI está repetido
     */
    public boolean jugadorRepetido(String nombre) throws IOException {
        Jugador nuevoJugador = new Persona(nombre);
        ArrayList<Jugador> listaJugadores = importarArchivo();
        boolean mismoJugador = false;
        if (!listaJugadores.isEmpty()) {
            int i = 0;
            while (!mismoJugador && i < listaJugadores.size()) {
                mismoJugador = nuevoJugador.getNombre().equalsIgnoreCase(listaJugadores.get(i).getNombre());
                i++;
            }
            if (mismoJugador) {
                System.out.println("Este jugador se encuentra en el registro");
            }
        }
        return mismoJugador;
    }

    /**
     * @throws IOException al escribir en el fichero JugadoresRegistrados.txt si hay error
     * @apiNote añade jugador al fichero de registro
     */
    public void anhadirJugador() throws IOException {
        String nombreNuevoJugador;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Dime el nombre del jugador que quieres añadir: " +
                "\n" + "¡RECUERDA! El nombre del jugador no puede tener espacios");
        nombreNuevoJugador = teclado.next(); //TODO: chequear que tenga espacios!
        boolean jugadorRepe = jugadorRepetido(nombreNuevoJugador);
        if (!jugadorRepe) {
            Jugador jugador = new Persona(nombreNuevoJugador);
            Files.write(rutaRegistrados, (jugador.getNombre() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            System.out.println("Jugador añadido");
        } else {
            System.out.println("No es posible añadir este jugador");
        }
    }

    /**
     * @throws IOException en los metodos internos
     * @apiNote elimina jugador del registro
     */
    public void eliminarJugador() throws IOException {
        String nombreEliminarJugador;
        Scanner teclado = new Scanner(System.in);
        Ranking ranking = new Ranking();
        System.out.println("Dime el nombre del jugador que quieres eliminar: " +
                "\n" + "¡RECUERDA! El nombre del jugador no puede tener espacios");
        nombreEliminarJugador = teclado.next(); //TODO: chequear que tenga espacios!
        boolean jugadorRepe = jugadorRepetido(nombreEliminarJugador);
        if (jugadorRepe) {
            ArrayList<Jugador> listaJugadores = importarArchivo();
            int i = 0;
            while (jugadorRepe && i < listaJugadores.size()) {
                jugadorRepe = (listaJugadores.get(i).getNombre().equalsIgnoreCase(nombreEliminarJugador));
                if (!jugadorRepe) {
                    i++;
                    jugadorRepe = true;
                } else {
                    ranking.eliminarJugadorRanking(listaJugadores.get(i));
                    listaJugadores.remove(i);
                    exportarArchivo(listaJugadores);
                    System.out.println("Jugador eliminado del registro y del ranking");
                    return;
                }
            }
        } else {
            System.out.println("No es posible eliminar un jugador que no se encuentra en el registro");
        }
    }

    /**
     * @throws IOException si hay problemas al leer las lineas
     * @apiNote imprime historico de partidas
     */
    @Override
    public void imprimirFichero() throws IOException {
        System.out.println("---REGISTRO JUGADORES---");
        try {
            // Lee todas las líneas del archivo y guárdalas en una lista
            List<String> lineas = Files.readAllLines(rutaRegistrados, StandardCharsets.UTF_8);
            // Ordena la lista alfabéticamente
            Collections.sort(lineas);
            // Imprime cada línea ordenada
            for (String linea : lineas) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return devuelve un ArrayList con los jugadores que están el el fichero JugadoresRegistrados.txt
     * @throws IOException si hay errores con la creacion del fichero o al pasar de la linea al ArrayList
     * @apiNote
     */
    @Override
    public ArrayList<Jugador> importarArchivo() throws IOException {
        ArrayList<Jugador> listaJugadores = new ArrayList<>();

        if (!Files.exists(rutaRegistrados)) {
            try {
                Files.createFile(rutaRegistrados);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                List<String> lineas = Files.readAllLines(rutaRegistrados, StandardCharsets.UTF_8);
                listaJugadores = lineas.stream()
                        .map(Persona::new)
                        .sorted(Comparator.comparing(Jugador::getNombre))
                        .collect(Collectors.toCollection(ArrayList::new));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listaJugadores;

    }

    /**
     * @param listaJugadores array con los jugadores
     * @throws IOException si hay un error al escribir en el fichero JugadoresRegistrados.txt
     * @apiNote escribimos el arrayList en el fichero JugadoresRegistrados.txt
     */
    @Override
    public void exportarArchivo(ArrayList<Jugador> listaJugadores) throws IOException {
        Files.deleteIfExists(rutaRegistrados);
        try {
            String resgistroJugadores = "";
            for (Jugador jugador : listaJugadores) {
                resgistroJugadores += jugador.getNombre() + System.lineSeparator();
            }
            Files.write(rutaRegistrados, resgistroJugadores.getBytes(), StandardOpenOption.CREATE);

            System.out.println("Archivo creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    /**
     * @param pregunta objeto de pregunta con el tipo ya escogido
     * @apiNote respuesta del jugador persona a las preguntas
     */
    @Override
    public void elegirRespuesta(Pregunta pregunta) {
        String respuesta = teclado.nextLine();
        logger.info("[" + java.time.LocalDate.now() + "][" + java.time.LocalTime.now() + "]: " + this.getNombre() + " ha elegido la respuesta: " + respuesta);
        resultadoRespuesta(respuesta, pregunta.getEnunciadoRespuesta().getRespuesta());
    }
}
