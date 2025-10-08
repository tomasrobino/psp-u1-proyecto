import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ej2_3 {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("powershell", "cd src ; javac Ej2_3_Sumador.java ; java Ej2_3_Sumador 1 8");
        try {
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line=br.readLine()) != null) {
                System.out.println(line+"\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
