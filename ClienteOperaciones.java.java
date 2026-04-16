import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteChat {

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5050;

        try (Socket socket = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            Scanner teclado = new Scanner(System.in);

            System.out.println("Servidor: " + entrada.readLine());

            String mensaje;

            while (true) {
                System.out.print("Ingresar comando: ");
                mensaje = teclado.nextLine();

                salida.println(mensaje);

                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Desconectado");
                    break;
                }

                System.out.println("Servidor: " + entrada.readLine());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}