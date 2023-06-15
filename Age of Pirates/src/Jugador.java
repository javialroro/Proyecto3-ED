import javax.swing.*;
import java.util.ArrayList;

public class Jugador {
    Grafo grafo = new Grafo();
    int cantidadFuentes = 1;
    int cantidadMercados = 1;

    int cantidadConectores = 5;
    int cantidadBarcos = 0;

    int cantidadBombas = 0;
    int cantidadCanon = 1;
    int cantidadCanonBR = 1;
    int cantidadCanonM = 1;

    int cantidadMinas = 2;
    int cantidadTemplos = 4;
    int cantidadArmerias = 5;

    int dinero = 100000;

    int acero = 550;
    int numero = 0;

    ArrayList<Mina> listaMinas = new ArrayList<Mina>();
    Entidad[][] matriz = new Entidad[20][20];

    int[][] matrizAtaques = new int[20][20];

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
