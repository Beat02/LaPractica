package laPractica;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PreguntaLetras extends Pregunta{
    private final Path diccionario = Paths.get("src/LaPractica/diccionario.txt");
    private String palabra;

    public PreguntaLetras() {
        super(new TipoPregunta("Letras"));
        palabra = obtenerPalabraAleatoria();
        System.out.println(palabra);
        enunciadoRespuesta = new EnunciadoRespuesta("Adivina la palabra: "+ocultarLetrasDePalabraAleatorio(), palabra);
    }

    /**
     *
     * @return
     */
    private String obtenerPalabraAleatoria() {
        String palabra = "";
        try {
            long total = Files.lines(diccionario).count();
            int aleatorio = (int)(Math.random() * total);
            palabra = Files.lines(diccionario).skip(aleatorio).findFirst().get();
            while (palabra.length() < 4) {
                aleatorio = (int)(Math.random() * total);
                palabra = Files.lines(diccionario).skip(aleatorio).findFirst().get();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return palabra;
    }

    private String ocultarLetrasDePalabraAleatorio() {
        String palabraOculta = "";
        int[] ocultas = new int[3];
        for (int i = 0; i < 3; i++) {
            ocultas[i] = (int)(Math.random() * palabra.length());
        }
        for (int i = 0; i < palabra.length(); i++) {
            boolean oculta = false;
            for (int j = 0; j < 3; j++) {
                if (i == ocultas[j]) {
                    palabraOculta += "_";
                    oculta = true;
                    break;
                }
            }
            if (!oculta) {
                palabraOculta += palabra.charAt(i);
            }
        }
        return palabraOculta;
    }
}
