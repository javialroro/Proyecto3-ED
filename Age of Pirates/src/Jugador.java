import javax.swing.*;
import java.util.ArrayList;

public class Jugador {
    Grafo grafo = new Grafo();
    int cantidadFuentes = 1;
    int cantidadMercados = 1;

    int cantidadConectores = 5;
    int cantidadBarcos = 0;

    int cantidadBombas = 0;
    int cantidadCanon = 0;
    int cantidadCanonBR = 0;
    int cantidadCanonM = 0;

    int cantidadMinas = 0;
    int cantidadTemplos = 4;
    int cantidadArmerias = 0;

    int dinero = 1000;

    int acero = 0;
    int numero = 0;


    Entidad[][] matriz = new Entidad[20][20];

    JButton[][] matrizBotones = new JButton[20][20];

    Jugador(int numero){
        this.numero = numero;
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                matriz[i][j] = new EntidadVacia();
            }
        }
    }

}
