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
    private final int maxJugagores = Constante.maxJugagores;
    private final Path rutaHistorico = Paths.get(Constante.historico);
    private final Path rutaRegistrados = Paths.get(Constante.registrados);
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
        int maximo = Integer.MIN_VALUE;
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

// Imprime todos los jugadores con la puntuación máxima
        for (String nombre : maximaClave) {
            System.out.println("Jugador con la mayor puntuación: " + nombre + " con una puntuación de: " + maximo);
        }
    }

    public void puntuacionFinalPartida() {
        System.out.println("El resultado final de esta partida es: " + arrayJugadores);
    }

    public void endGame() {
        puntuacionFinalPartida();
        ganadorPartida();
    }

    @Override
    public void imprimirArchivo() throws IOException {
        System.out.println("---HISTORICO---");
        try {
            System.out.println(Files.readString(rutaHistorico));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Jugador> importarArchivo() throws IOException {
        return null;
    }

    @Override
    public void exportarArchivo(ArrayList<Jugador> listaJugadores) throws IOException {

    }

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
                arrayJugadores.add(new Persona(nombreJugador));
            }
        }
        System.out.println("Ya tenemos listos a los jugadores humanos");
    }

    /**
     *
     */
    public void jugadoresPartida() throws IOException {
        int maxJugagores = Constante.maxJugagores;
        int totalJugadoresPartidaActual = 0;
        int totalJugadorMaquina = 0;
        //pedir numero jugadores HUMANOS
        System.out.println("El total de jugadores posible es cuatro, y de esos cuatro, puedes escoger cuantos son humanos y cuantos son máquinas" + '\n'
                + "¿Cuántos jugadores humanos habrá en esta partida?");
        int totalJugadorHumano = teclado.nextInt();
        if (totalJugadorHumano > 1 && totalJugadorHumano < 4) {
            nombresJugadoresHumanos(totalJugadorHumano);
            totalJugadoresPartidaActual = maxJugagores - totalJugadorHumano;
            System.out.println("Ya hay " + totalJugadorHumano + " jugadores humanos, puedes incluir hasta " +
                    totalJugadoresPartidaActual + " jugadores máquina, escribe cuantos quieres:");
            totalJugadorMaquina = teclado.nextInt();
            totalJugadoresPartidaActual += totalJugadorMaquina;
            insertarJugadorMaquina(totalJugadoresPartidaActual, totalJugadorMaquina, totalJugadorHumano);
        } else if (totalJugadorHumano == 1) {
            nombresJugadoresHumanos(totalJugadorHumano);
            System.out.println("Solo hay un jugador humano, si quieres, puedes incluir hasta tres jugadores máquina, escribe cuantos quieres:");
            totalJugadorMaquina = teclado.nextInt();
            insertarJugadorMaquina(totalJugadoresPartidaActual, totalJugadorMaquina, totalJugadorHumano);
        } else if (totalJugadorHumano == 0) {
            System.out.println("Veo que en esta partida quiere ver jugar a la máquina, ¿cuántas quieres que jueguen?");
            totalJugadorMaquina = teclado.nextInt();
            insertarJugadorMaquina(totalJugadoresPartidaActual, totalJugadorMaquina, totalJugadorHumano);

        }

    }

    private void insertarJugadorMaquina(int totalJugadoresPartidaActual, int totalJugadorMaquina, int totalJugadorHumano) {
        while (maxJugagores < totalJugadoresPartidaActual) {
            System.out.println("El valor introducido es incorrecto" + "\n" +
                    "RECUERDA: EL VALOR MÍNIMO DE JUGADORES TOTALES ES 1 Y EL MÁXIMO ES 4" +
                    "\n" + "prueba otra vez:");
            totalJugadorMaquina = teclado.nextInt();
            totalJugadoresPartidaActual = totalJugadorHumano + totalJugadorMaquina;
        }
        for (int i = 0; i < totalJugadorMaquina; i++) {
            Maquina maquina = new Maquina();
            maquina.setNombre(i);
            arrayJugadores.add(maquina);
        }
    }
}
