package laPractica;

import java.util.ArrayList;
import java.util.Collections;

public class Maquina extends Jugador {

    public Maquina() {
        super(nombreAleatorio());
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @apiNote le da un nombre alearorio al jugador máquina
     * @return nombre de la máquina
     */
    public static String nombreAleatorio() {
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add(Constante.pcu1);
        nombres.add(Constante.pcu2);
        nombres.add(Constante.pcu3);
        nombres.add(Constante.pcu4);
        Collections.shuffle(nombres);
        String nombre = nombres.get(1);
        return nombre;
    }

    /**
     *
     * @param listaJugadores
     * @return si es true, el nombre ya está siendo usado en la partida
     */
    public boolean nombreRepetido(ArrayList<Jugador> listaJugadores) {
        boolean repetido = false;
        int i = 0;
        while (!repetido && i < listaJugadores.size()) {
            repetido = nombre.equals(listaJugadores.get(i).getNombre());
            i++;
        }
        return repetido;
    }

    /**
     *
     * @return nombre para la máquina si en algún momento se ha repetido
     */
    public String nuevoNombre() {
        Maquina maquina = new Maquina();
        boolean repetido = true;
        while (repetido) {
            repetido = nombre.equals(maquina.getNombre());
        }
        String nombreFinal = maquina.getNombre();
        return nombreFinal;
    }
}
