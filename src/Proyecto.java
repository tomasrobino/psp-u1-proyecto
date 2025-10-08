import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Ejecuta los argumentos como si fuesen comandos de powershell
 * Para agregar argumentos, encerrar el comando y los argumentos en comillas
 * Ejemplo:
 * "cd src" pwd
 * Esto ejecutará el commando:
 * cd src
 * seguido por:
 * pwd
 *
 */
public class Proyecto {
    public static void main(String[] args) {
        String so = System.getProperty("os.name");
        System.out.println("Este programa se esta ejecutando en "+so);

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("powershell", String.join(";", args));
        try {
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            int retorno = process.waitFor();
            if (retorno == 0) {
                String line;
                while ((line=br.readLine()) != null) {
                    System.out.println(line);
                }
            } else {
                String line;
                System.out.println("El proceso finalizo de forma incorrecta");
                while ((line=br2.readLine()) != null) {
                    System.out.println(line);
                }
            }
            br.close();
            br2.close();
        } catch (IOException e) {
            System.out.println("Error de I/O en el proceso");
        } catch (InterruptedException e) {
            System.out.println("El proceso fue interrumpido antes de que termine");
        }
    }
}
