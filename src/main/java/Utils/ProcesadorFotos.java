
package Utils;

import java.io.File;
import java.io.IOException;

import java.util.Base64;
import org.apache.commons.io.FileUtils;


public class ProcesadorFotos {

    //Tuve que importar una libreria para que esto funcionase, se llama commons-io-2-7.jar
    
    public static File Convertir_Base64String_a_imagen(String base64String) {

        File archivo_salida;
        if (base64String == null || base64String.isEmpty()) {
            archivo_salida = new File(".", "null.png");
        } else {
            archivo_salida = new File(".", base64String.substring(40, 50) + ".png");
            try {

                byte[] decodedBytes = Base64.getDecoder().decode(base64String);
                FileUtils.writeByteArrayToFile(archivo_salida, decodedBytes);

            } catch (IOException e) {
                System.out.println("Todo mal: " + e.getMessage());
            }
        }

        return archivo_salida;

    }

    public static String Convertir_imagen_a_Base64String(File archivo) {
        String base64Image = "";
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(archivo);
            base64Image = Base64.getEncoder().encodeToString(fileContent);

        } catch (IOException e) {
            System.out.println("Todo mal de nuevo: " + e.getMessage());
        }
        return base64Image;

    }
}
