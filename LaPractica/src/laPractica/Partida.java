package laPractica;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.*;

public class Partida implements Ficheros {
    private ArrayList<Jugador> arrayJugadores;
    private LocalDateTime fechaPartida;
    private final int maxJugagores = Constante.MAX_JUGAGORES;
    private final Path rutaHistorico = Paths.get(Constante.HISTORICO);
    private final Path rutaRegistrados = Paths.get(Constante.REGISTRADOS);
    final Scanner teclado = new Scanner(System.in);

    public Partida() {
        this.arrayJugadores = new ArrayList<>();
        this.fechaPartida = LocalDateTime.now();
    }

    public ArrayList<Jugador> getArrayJugadores() {
        return arrayJugadores;
    }

    public LocalDateTime getFechaPartida() {
        return fechaPartida;
    }

    /**
     * @param arrayJugadores
     * @apiNote metodo para crear el orden aleatorio al inicio de partida
     */
    public void imprimirOrdenAleatorio(ArrayList<Jugador> arrayJugadores) {
        Collections.shuffle(arrayJugadores);
        System.out.println("El orden de los jugadores después de mezclar aleatoriamente es:");
        for (Jugador objeto : arrayJugadores) {
            System.out.println(objeto);
        }
    }

    /**
     * @apiNote implementar al final de la partida
     */
    public void actualizarHistorico() {

        if (!Files.exists(rutaHistorico)) {
            try {
                Files.createFile(rutaHistorico);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            String historicoPartidas = "";
            try {
                for (int i = 0; i < arrayJugadores.size(); i++) {
                    Jugador jugador = arrayJugadores.get(i);
                    historicoPartidas += jugador.getNombre() + "," + jugador.getPuntuacion() + " - ";
                }
                historicoPartidas += System.lineSeparator();
                Files.write(rutaHistorico, historicoPartidas.getBytes(), StandardOpenOption.APPEND);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * @return
     * @apiNote menu inicio juego
     */
    public int menuIncial() {
        int opcionMenu;
        System.out.println("Bienvenide a Conocer y Triunfar, tu juego de preguntas online, ¿qué quieres hacer?" +
                "\n" + "1. Jugar nueva partida" +
                "\n" + "2. Ver ranking de jugadores" +
                "\n" + "3. Ver histórico de partidas" +
                "\n" + "4. Menú jugadores" +
                "\n" + "5. Salir");
        opcionMenu = teclado.nextInt();
        return opcionMenu;
    }


    /**
     * @apiNote nos imprime el jugador ganador de la partida
     */
    public void ganadorPartida() {
        int maximo = 0;
        List<String> maximaClave = new ArrayList<>();
        for (int i = 0; i < arrayJugadores.size(); i++) {
            int valor = arrayJugadores.get(i).getPuntuacion();
            if (valor > maximo) {
                maximaClave.clear(); // Borra la lista anterior de jugadores con la puntuación máxima
                maximo = valor;
                maximaClave.add(arrayJugadores.get(i).getNombre());
            } else if (valor == maximo) {
                maximaClave.add(arrayJugadores.get(i).getNombre()); // Agrega a la lista de empates
            }
        }
        for (String nombre : maximaClave) {
            System.out.println("Jugador con la mayor puntuación: " + nombre + " con una puntuación de: " + maximo);
            MiLogger.log("Fin de partida con " + arrayJugadores.size() + " jugadores. Ganador ha sido " + nombre + " con " + maximo + " puntos.");
        }
    }

    /**
     * @apiNote mensaje final partida
     */
    public void endGame() {
        System.out.println("El resultado final de esta partida es: " + arrayJugadores);
        ganadorPartida();
    }

    /**
     * @throws IOException
     * @apiNote imprime historico partidas
     */
    @Override
    public void imprimirFichero() throws IOException {
        System.out.println("---HISTORICO---");
        try {
            System.out.println(Files.readString(rutaHistorico));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param numJugadoresHumanos
     * @throws IOException
     * @apiNote pedimos por pantalla nombres jugadores persona
     */
    public void nombresJugadoresHumanos(int numJugadoresHumanos) throws IOException {
        for (int i = 0; i < numJugadoresHumanos; i++) {
            System.out.println("Dime el nombre del jugador:");
            String nombreJugador = teclado.next();
            for (int j = 0; j < arrayJugadores.size(); j++) {
                Jugador jugador = arrayJugadores.get(j);
                boolean repeArray = nombreJugador.equalsIgnoreCase(jugador.getNombre());
                if (repeArray) {
                    while (repeArray) {
                        System.out.println("Ese jugador ya está en esta partida, dime otro: ");
                        nombreJugador = teclado.next();
                        repeArray = nombreJugador.equalsIgnoreCase(jugador.getNombre());
                    }
                }
            }
            Persona jugador = new Persona(nombreJugador);
            boolean jugadorEnRegistro = jugador.jugadorRepetido(nombreJugador);
            if (jugadorEnRegistro) {
                System.out.println("Jugador en el registro de jugadores, podemos añadirlo a la partida");
                arrayJugadores.add(new Persona(nombreJugador));
            } else {
                System.out.println("El jugador no se encuentra en el registro, procedemos a añadirlo");
                Files.write(rutaRegistrados, (jugador.getNombre() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
                MiLogger.log("Jugador añadido al registro de jugadores: " + nombreJugador);
                arrayJugadores.add(new Persona(nombreJugador));
            }
            MiLogger.log("Jugador añadido a la partida: " + nombreJugador);
        }
        System.out.println("Perfecto, ya casi estamos");
    }

    /**
     * @return total jugadores persona
     * @throws IOException
     */
    public int jugadoresPartidaPersona() throws IOException {
        int totalJugadorHumano = 0;
        do {
            try {
                System.out.println("El total de jugadores posible es cuatro. ¿Cuántos jugadores humanos habrá en esta partida?");
                totalJugadorHumano = teclado.nextInt();
                if (totalJugadorHumano < 0 || totalJugadorHumano > 4) {
                    throw new InputMismatchException("Error: Debes ingresar un valor entre 1 y 4.");
                }
                if (totalJugadorHumano == 0) {
                    break;
                } else {
                    nombresJugadoresHumanos(totalJugadorHumano);
                }
            } catch (InputMismatchException exc) {
                System.out.println("Error: Debes ingresar un valor entero válido entre 0 y 4.");
                teclado.nextLine(); // Limpiar el búfer del scanner para evitar bucles infinitos
                totalJugadorHumano = -1; // Asignar un valor inválido para que el bucle continúe
            } catch (NoSuchElementException exc) {
                System.out.println("Error: Debes ingresar un valor válido.");
                teclado.nextLine();
                totalJugadorHumano = -1;
            }
        } while (totalJugadorHumano == -1);
        return totalJugadorHumano;
    }

    /**
     * @param totalJugadorHumano
     * @apiNote adjudicamos total numero jugadores maquina
     */
    public void jugadorPartidaMaquina(int totalJugadorHumano) {
        int totalJugadoresPartidaActual = 0;
        int totalJugadorMaquina = 0;
        if (totalJugadorHumano > 0 || totalJugadorHumano < 4) {
            totalJugadoresPartidaActual = maxJugagores - totalJugadorHumano;
            System.out.println("Ya hay " + totalJugadorHumano + " jugadores humanos, puedes incluir hasta " +
                    totalJugadoresPartidaActual + " jugadores máquina, escribe cuantos quieres:");
            do {
                try {
                    totalJugadorMaquina = teclado.nextInt();
                } catch (NoSuchElementException exc) {
                    System.out.println("Error: Debes ingresar un valor válido.");
                    teclado.nextLine(); // Limpiar el búfer del scanner después de una excepción para evitar bucles infinitos
                    totalJugadorHumano = -1; // Asignar un valor inválido para que el bucle do-while continúe
                }
            } while (totalJugadorHumano == -1);
            totalJugadoresPartidaActual = totalJugadorHumano + totalJugadorMaquina;
            insertarJugadorMaquina(totalJugadoresPartidaActual, totalJugadorMaquina, totalJugadorHumano);
        }
    }

    /**
     * @param totalJugadoresPartidaActual
     * @param totalJugadorMaquina
     * @param totalJugadorHumano
     * @apiNote asigna los jugadores maquina
     */
    public void insertarJugadorMaquina(int totalJugadoresPartidaActual, int totalJugadorMaquina, int totalJugadorHumano) {
        while (maxJugagores < totalJugadoresPartidaActual) {
            System.out.println("El valor introducido es incorrecto" + "\n" +
                    "RECUERDA: EL VALOR MÍNIMO DE JUGADORES TOTALES ES 1 Y EL MÁXIMO ES 4" +
                    "\n" + "prueba otra vez:");
            totalJugadorMaquina = teclado.nextInt();
            totalJugadoresPartidaActual = totalJugadorHumano + totalJugadorMaquina;
        }
        for (int i = 0; i < totalJugadorMaquina; i++) {
            Maquina maquina = new Maquina(null);
            maquina.setNombre(i);
            arrayJugadores.add(maquina);
        }

    }

    @Override
    public ArrayList<Jugador> importarArchivo() throws IOException {
        return null;
    }

    @Override
    public void exportarArchivo(ArrayList<Jugador> listaJugadores) throws IOException {

    }
}
