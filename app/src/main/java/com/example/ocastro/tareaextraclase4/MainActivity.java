package com.example.ocastro.tareaextraclase4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int contador = 0;
    int matriz[][]=new int [8][8];
    int matriz2[][]=new int[8][8];

    backtracking imprimir= new backtracking();


    class backtracking {
        int matriz[][] = new int[8][8];
        int x = 0, y = 0;
        int cont = 0;
        int visitados[] = new int[64];

        boolean comprobar = false;

        backtracking() {
            this.matriz = matriz;

        }

        void imprimir_matriz(int matriz[][]) {

            System.out.println("\n" + "-------------------------------------------------------");
            for (int x = 0; x < matriz.length; x++) {
                System.out.println("\n");
                for (int y = 0; y < matriz[x].length; y++) {
                    System.out.print(matriz[x][y] + ",");
                }
            }
        }


        Object colocar_obstaculo(int matriz[][], int matriz2[][]) {

            int random, random2;

            Random r = new Random();

            random = r.nextInt(8);
            random2 = r.nextInt(8);
            if ((random == 0 & random2 == 0) || (random == 7 & random2 == 7)) {
                return colocar_obstaculo(matriz, matriz2);
            }
            if (matriz[random][random2] == 1 & comprobar == false) {

                return colocar_obstaculo(matriz, matriz2);
            } else if (comprobar == false) {

                matriz[random][random2] = 1;
                matriz2[random][random2] = 1;
            }
            random = r.nextInt(8);
            random2 = r.nextInt(8);
            if ((random == 0 & random2 == 0) || (random == 7 & random2 == 7)) {
                return colocar_obstaculo(matriz, matriz2);
            }
            if (matriz[random][random2] == 1) {
                comprobar = true;
                return colocar_obstaculo(matriz, matriz2);

            } else if (matriz[random][random2] != 1) {

                matriz[random][random2] = 1;
                matriz2[random][random2] = 1;
            }
            comprobar = false;
            return 0;


        }

        Object mover(int matriz[][]) {
            visitados[0] = 00;

            if (matriz[7][7] == 6) {
                System.out.println("META FINAL");
                cont = 0;
                x = 0;
                y = 0;
                return 0;
            } else if (x != 7 & y != 7) {

                if (matriz[x + 1][y + 1] == 0) {
                    System.out.println("Diagonal Derecha y Abajo");
                    matriz[x + 1][y + 1] = 6;
                    cont++;
                    visitados[cont] = (x + 1) * 10 + (y + 1);

                    imprimir_matriz(matriz);
                    y++;
                    x++;
                    return mover(matriz);
                } else if (matriz[x + 1][y] == 0) {
                    System.out.println("ABAJO");
                    matriz[x + 1][y] = 6;

                    cont++;
                    visitados[cont] = (x + 1) * 10 + (y);

                    imprimir_matriz(matriz);
                    x++;
                    return mover(matriz);
                } else if (matriz[x][y + 1] == 0) {
                    System.out.println("DERECHA");
                    matriz[x][y + 1] = 6;

                    cont++;
                    visitados[cont] = (x) * 10 + (y + 1);
                    imprimir_matriz(matriz);
                    y++;
                    return mover(matriz);
                } else if (y != 0 && matriz[x][y - 1] == 0) {

                    matriz[x][y - 1] = 6;
                    System.out.println("Izquierda");
                    cont++;
                    visitados[cont] = (x) * 10 + (y - 1);

                    imprimir_matriz(matriz);
                    y--;
                    return mover(matriz);
                } else if (y != 0 && matriz[x + 1][y - 1] == 0) {

                    matriz[x + 1][y - 1] = 6;
                    System.out.println("Diagonal Izquierda y Abajo");
                    cont++;
                    visitados[cont] = (x + 1) * 10 + (y - 1);
                    imprimir_matriz(matriz);
                    x++;
                    y--;
                    return mover(matriz);

                } else if (x != 0 && matriz[x - 1][y] == 0) {
                    matriz[x - 1][y] = 6;
                    System.out.println("Arriba");
                    cont++;
                    visitados[cont] = (x - 1) * 10 + (y);
                    imprimir_matriz(matriz);
                    x--;
                    return mover(matriz);
                } else if (x != 0 && matriz[x - 1][y + 1] == 0) {

                    matriz[x - 1][y + 1] = 6;
                    System.out.println("Diagonal Derecha y Arriba");
                    cont++;
                    visitados[cont] = (x - 1) * 10 + (y + 1);
                    imprimir_matriz(matriz);
                    x--;
                    y++;
                    return mover(matriz);

                } else if (y != 0 & x != 0 && matriz[x - 1][y - 1] == 0) {

                    matriz[x - 1][y - 1] = 6;
                    System.out.println("Diagonal Izquierda y Arriba");
                    cont++;
                    visitados[cont] = (x - 1) * 10 + (y - 1);
                    imprimir_matriz(matriz);
                    x--;
                    y--;
                    return mover(matriz);

                } else {

                    return goToThePast(matriz);
                    //throw new IllegalArgumentException("NO HAY CAMINO");
                }

            } else if (x == 7) {
                return moverXsiete(matriz);
            } else if (y == 7) {
                return moverYsiete(matriz);
            } else {
                throw new IllegalArgumentException("NO HAY CAMINO");
            }


        }

        Object moverXsiete(int matriz[][]) {
            if (matriz[x][y + 1] == 0) {
                matriz[x][y + 1] = 6;
                System.out.println("Diagonal Derecha");
                cont++;
                visitados[cont] = (x) * 10 + (y + 1);

                imprimir_matriz(matriz);
                y++;
                return mover(matriz);
            } else if (matriz[x - 1][y + 1] == 0) {
                matriz[x - 1][y] = 6;
                System.out.println("Diagonal Derecha y Arriba");
                cont++;
                visitados[cont] = (x - 1) * 10 + (y + 1);

                imprimir_matriz(matriz);
                x--;
                y++;
                return mover(matriz);
            } else if (matriz[x - 1][y] == 0) {

                System.out.println("Diagonal Arriba");
                cont++;
                visitados[cont] = (x - 1) * 10 + (y);

                matriz[x - 1][y] = 6;
                imprimir_matriz(matriz);
                x--;
                return mover(matriz);
            } else if (y != 0 && matriz[x - 1][y - 1] == 0) {
                matriz[x - 1][y - 1] = 6;
                System.out.println("Diagonal Izquierda y Arriba");
                cont++;
                visitados[cont] = (x - 1) * 10 + (y - 1);

                imprimir_matriz(matriz);
                x--;
                y--;
                return mover(matriz);
            } else if (y != 0 && matriz[x][y - 1] == 0) {
                matriz[x][y - 1] = 6;
                System.out.println("Diagonal Izquierda");
                cont++;
                visitados[cont] = (x) * 10 + (y - 1);

                imprimir_matriz(matriz);
                y--;
                return mover(matriz);
            } else {

                return goToThePast(matriz);
                //throw new IllegalArgumentException("NO HAY CAMINO");
            }

        }

        Object moverYsiete(int matriz[][]) {
            if (matriz[x + 1][y] == 0) {

                matriz[x + 1][y] = 6;
                System.out.println("Diagonal Abajo");
                cont++;
                visitados[cont] = (x + 1) * 10 + (y);

                imprimir_matriz(matriz);
                x++;
                return mover(matriz);

            } else if (matriz[x + 1][y - 1] == 0) {
                matriz[x + 1][y - 1] = 6;
                System.out.println("Diagonal Izquierda y Abajo");
                cont++;
                visitados[cont] = (x + 1) * 10 + (y - 1);

                imprimir_matriz(matriz);
                x++;
                y--;
                return mover(matriz);
            } else if (matriz[x][y - 1] == 0) {
                matriz[x][y - 1] = 6;
                System.out.println("Diagonal Izquierda");
                cont++;
                visitados[cont] = (x) * 10 + (y - 1);

                imprimir_matriz(matriz);
                y--;
                return mover(matriz);
            } else if (x != 0 && matriz[x - 1][y - 1] == 0) {
                matriz[x - 1][y - 1] = 6;
                System.out.println("Diagonal Izquierda y Arriba");
                cont++;
                visitados[cont] = (x - 1) * 10 + (y - 1);

                imprimir_matriz(matriz);
                x--;
                y--;
                return mover(matriz);
            } else if (x != 0 && matriz[x - 1][y] == 0) {
                matriz[x - 1][y] = 6;
                System.out.println("Diagonal Abajo");
                cont++;
                visitados[cont] = (x - 1) * 10 + (y);

                imprimir_matriz(matriz);
                x--;

                return mover(matriz);
            } else {

                return goToThePast(matriz);
                //throw new IllegalArgumentException("NO HAY CAMINO");
            }


        }

        Object goToThePast(int matriz[][]) {
            if (cont == 0) {
                System.out.println("No hay camino");
            } else {

                x = visitados[cont] / 10;
                y = visitados[cont] % 10;
                matriz[x][y] = 3;
                cont--;
                imprimir_matriz(matriz);
                return mover(matriz);
            }


            return matriz;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0; i<8;i++) {
            for(int j=0; j<8;j++) {
                matriz[i][j]=0;
                matriz2[i][j]=0;
            }
        }
        matriz[0][0]=6;
        matriz2[0][0]=6;

        Button boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++NUEVA BUSQUEDA++++++++++++++++++++++++++");
                for(int k=0;k<8;k++) {
                    for(int l=0;l<8;l++) {
                        matriz[k][l]=matriz2[k][l];
                    }
                }

                imprimir.colocar_obstaculo(matriz,matriz2);
                imprimir.imprimir_matriz(matriz);

            }
        });
        Button boton2 = (Button) findViewById(R.id.button2);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imprimir.mover(matriz);
            }
        });






    }
}



