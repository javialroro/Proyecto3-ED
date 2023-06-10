/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author dmora
 */
public class Vertice {
    public Entidad dato;
    boolean visitado;
    ArrayList<Vertice> aristas;
    int peso;//peso

    // cosntructor
    // public Vertice(int dato)
    // {
    //     aristas = new ArrayList<Vertice>();
    //     this.dato = dato;
    //     this.visitado = false;
    // }

    // public Vertice(int dato, int peso)
    // {
    //     aristas = new ArrayList<Vertice>();
    //     this.dato = dato;
    //     this.visitado = false;
    //     this.peso = peso;
    // }

    public Vertice(Entidad entidad)
    {
        aristas = new ArrayList<Vertice>();
        this.dato = entidad;
        this.visitado = false;
        this.peso = entidad.ancho;
    }

    public void agregarArista (Vertice arista)
    {
        // si no está la arista para no repetir
        if (buscarArista(arista) == -1)
            aristas.add(new Vertice(arista.dato));
    }
    
    // public void agregarArista (Vertice arista, int peso)
    // {
    //     // si no está la arista para no repetir
    //     if (buscarArista(arista) == -1)
    //         aristas.add(new Vertice(arista.dato, peso));
    // }

    public int buscarArista(Vertice v)
    {
        for (int i = 0; i < aristas.size(); i++) {
            if (v.dato == aristas.get(i).dato)
                return i;
        }
        return -1;
    }
}
