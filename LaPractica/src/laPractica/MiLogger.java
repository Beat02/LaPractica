package laPractica;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MiLogger {

    public static void log(String textoLog) {
        try {
            File archivoLog = new File(Constante.RUTA_ARCHIVO_LOG);
            if (!archivoLog.exists()) {
                archivoLog.getParentFile().mkdirs();
                archivoLog.createNewFile();
            }
            // Verificar si la última línea del archivo tiene la fecha actual
            boolean necesitaNuevoArchivo = false;
            if (archivoLog.length() != 0) {
                List<String> lineas = Files.readAllLines(archivoLog.toPath());
                String ultimaLinea = lineas.get(lineas.size() - 1);
                String ultimaFecha = ultimaLinea.substring(1, 11);

                String fechaActual = new SimpleDateFormat(Constante.FORMATO_FECHA).format(new Date());
                if (!ultimaFecha.equals(fechaActual)) {
                    necesitaNuevoArchivo = true;
                }
            }
            // Renombrar el archivo si la última fecha no es la actual
            if (necesitaNuevoArchivo) {
                String fechaParaNombre = new SimpleDateFormat(Constante.FORMATO_FECHA_RESPALDO).format(new Date());
                String nombreArchivoRespaldo = "salida.log." + fechaParaNombre;
                Path rutaArchivoRespaldo = archivoLog.toPath().resolveSibling(nombreArchivoRespaldo);
                Files.move(archivoLog.toPath(), rutaArchivoRespaldo);
                archivoLog = new File(Constante.RUTA_ARCHIVO_LOG);
                archivoLog.createNewFile();
            }

            String fechaHora = new SimpleDateFormat("[" + Constante.FORMATO_FECHA + "][" + Constante.FORMATO_HORA + "]").format(new Date());
            textoLog = fechaHora + ": " + textoLog;
            Files.write(archivoLog.toPath(), (textoLog + System.lineSeparator()).getBytes(), java.nio.file.StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }
}