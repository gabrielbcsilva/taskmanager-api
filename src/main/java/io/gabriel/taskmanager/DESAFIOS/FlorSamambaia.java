package io.gabriel.taskmanager.DESAFIOS;

import java.util.Scanner;

public class FlorSamambaia {
    public static String tentativaDesenhar(int r1, int x1, int y1, int r2, int x2, int y2) {
        double distanciaCentros = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        //Distância=(x2−x1)^2+(y2−y1)^2
        //R1≥Distância+R2

        //d=(1-0)^2+(1-0)^2
        //d=2
        //R1>=2+3(R2)
        //MORTO

        if (r1 >= distanciaCentros + r2) {
            return "RICO";
        } else {
            return "MORTO";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o raio do círculo do caçador (R1, 1 <= R1): ");
        int r1 = scanner.nextInt();

        if (r1 < 1) {
            System.out.println("Erro: O raio do círculo do caçador (R1) deve ser maior ou igual a 1.");
            return;
        }

        System.out.print("Informe a coordenada X do círculo do caçador (X1): ");
        int x1 = scanner.nextInt();

        System.out.print("Informe a coordenada Y do círculo do caçador (Y1): ");
        int y1 = scanner.nextInt();

        System.out.print("Informe o raio do círculo da flor (R2, R2 <= 1000): ");
        int r2 = scanner.nextInt();

        if (r2 > 1000) {
            System.out.println("Erro: O raio do círculo da flor (R2) deve ser menor ou igual a 1000.");
            return;
        }

        System.out.print("Informe a coordenada X do círculo da flor (X2): ");
        int x2 = scanner.nextInt();

        System.out.print("Informe a coordenada Y do círculo da flor (Y2): ");
        int y2 = scanner.nextInt();


        String resultado = tentativaDesenhar(r1, x1, y1, r2, x2, y2);

        System.out.println("Resultado: " + resultado);
    }
}

