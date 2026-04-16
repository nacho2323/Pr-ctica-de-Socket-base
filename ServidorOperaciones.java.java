import java.io.*;
import java.net.*;
import java.time.LocalTime;

public class ServidorChat {

    public static void main(String[] args) {
        int puerto = 5050;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor activo en puerto " + puerto);

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

            salida.println("Bienvenido a NachoChat - Operaciones disponibles: SUMA, RESTA, MULT, DIV");

            String mensaje;

            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensaje);

                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Cliente desconectado");
                    break;
                }

                String[] partes = mensaje.split(" ");

                if (partes.length == 3) {
                    String comando = partes[0];
                    double a = Double.parseDouble(partes[1]);
                    double b = Double.parseDouble(partes[2]);

                    double resultado;

                    switch (comando.toUpperCase()) {
                        case "SUMA":
                            resultado = suma(a, b);
                            salida.println("Resultado: " + resultado);
                            break;

                        case "RESTA":
                            resultado = resta(a, b);
                            salida.println("Resultado: " + resultado);
                            break;

                        case "MULT":
                            resultado = multiplicacion(a, b);
                            salida.println("Resultado: " + resultado);
                            break;

                        case "DIV":
                            if (b == 0) {
                                salida.println("Error: división por cero");
                            } else {
                                resultado = division(a, b);
                                salida.println("Resultado: " + resultado);
                            }
                            break;

                        default:
                            salida.println("Comando no válido");
                    }

                } else if (mensaje.equalsIgnoreCase("HORA")) {
                    salida.println("Hora actual: " + LocalTime.now());

                } else {
                    salida.println("Formato incorrecto. Ej: SUMA 5 3");
                }
            }

            cliente.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // MÉTODOS MATEMÁTICOS REALES
    public static double suma(double a, double b) {
        return a + b;
    }

    public static double resta(double a, double b) {
        return a - b;
    }

    public static double multiplicacion(double a, double b) {
        return a * b;
    }

    public static double division(double a, double b) {
        return a / b;
    }
}