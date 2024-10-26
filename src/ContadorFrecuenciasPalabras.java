import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ContadorFrecuenciasPalabras{

    public static void main(String[] args) {
        // Ubicación del texto utilizado para este ejercicio.
        String archivoEntrada = "C:\\Users\\Adada\\Documents\\Poo Lab\\EjercicioCollections\\src\\entrada.txt";

        // Nombre del archivo donde serán guardados los resultados.
        String archivoSalida = "salida.csv";

        // Llama al método que es utilizado para contar la frecuencia de cada palabra en el archivo.
        Map<String, Integer> frecuenciaPalabras = contarFrecuenciaPalabras(archivoEntrada);

        // Muestra las palabras y sus frecuencias.
        System.out.println("Lista de las palabras y sus frecuencias:");
        for (Map.Entry<String, Integer> entrada : frecuenciaPalabras.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }

        // Guarda los resultados en un archivo CSV.
        guardarEnCSV(archivoSalida, frecuenciaPalabras);
    }


    /**
     * Método que cuenta la frecuencia de cada palabra del archivo de texto.
     * @param nombreArchivo En este caso será ¨entrada.txt¨.
     * @return Un mapa que contenga cada palabra como clave y su frecuencia como valor.
     */
    public static Map<String, Integer> contarFrecuenciaPalabras(String nombreArchivo) {
        // Mapa para almacenar cada palabra y su frecuencia.
        Map<String, Integer> mapaFrecuencia = new HashMap<>();

        // Abre el archivo de texto y analiza su contenido línea por línea.
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            while ((linea = br.readLine()) != null){
                // Para poder contar las palabras sin importar si es mayúscula o minúscula
                // Transforma todo a minúscula, dividimos las palabras, e ignoramos caracteres
                // no alfabéticos
                String[] palabras = linea.split("\\W+");

                // Iteramos sobre cada palabra obtenida de la línea actual.
                for(String palabra : palabras){
                    // Verificamos que la palabra no esté vacía.
                    if(!palabra.isEmpty()){
                        // Incrementamos el contador de la palabra en el mapa.
                        mapaFrecuencia.put(palabra, mapaFrecuencia.getOrDefault(palabra, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            // Capturamos cualquier error que ocurra al leer el archivo.
            System.out.println("Error al leer el archivo" + e.getMessage());
        }

        // Retornamos el mapa con la frecuencia de cada palabra.
        return mapaFrecuencia;
    }


    /**
     * Método que guarda el mapa de las palabras y las frecuencias en un archivo CSV.
     * @param nombreArchivo El archivo creado es ¨salida.csv¨.
     * @param frecuenciaPalabras El mapa que contiene las palabras y sus frecuencias.
     */
    public static void guardarEnCSV(String nombreArchivo, Map<String, Integer> frecuenciaPalabras) {
        // Crea o abre el archivo CSV para escribir los resultados.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo)) ){
            // Escribe la cabecera en el archivo CSV.
            bw.write("Palabra , Frecuencia");
            bw.newLine();

            // Escribe las palabras del texto y sus frecuencias en una nueva línea del archivo.
            for (Map.Entry<String, Integer> entrada : frecuenciaPalabras.entrySet()) {
                bw.write(entrada.getKey() + " , " + entrada.getValue());
                bw.newLine();
            }

            System.out.println("\n Resultados guardados en " + nombreArchivo);

        } catch (IOException e) {
            // En caso de haber algún error, mostrará el siguiente mensaje.
            System.err.println("Error al escribir el archivo CSV" + e.getMessage());
        }
    }
}