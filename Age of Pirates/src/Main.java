import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    boolean hecho = false;
    Entidad[][] matriz1 = new Entidad[20][20];
    Entidad[][] matriz2 = new Entidad[20][20];
    Entidad[][] matriz3 = new Entidad[20][20];
    Entidad[][] matriz4 = new Entidad[20][20];

    JButton[][] matrizBotones1 = new JButton[20][20];

    JButton[][] matrizBotones2 = new JButton[20][20];
    JButton[][] matrizBotones3 = new JButton[20][20];
    JButton[][] matrizBotones4 = new JButton[20][20];

    JFrame frame = new JFrame("Age of Pirates");

    JFrame frameComponentes = new JFrame("Componentes");

    JPanel panelIzq = new JPanel();
    JPanel panelDer = new JPanel();

    JButton p1 = new JButton("P1");
    JButton p2 = new JButton("P2");
    JButton p3 = new JButton("P3");
    JButton p4 = new JButton("P4");
    JButton siguienteTurno = new JButton("Siguiente Turno");

    JButton Componentes = new JButton("Componentes");

    JTextArea chat = new JTextArea();

    Entidad componenteSeleccionado = null;

    int cantidadComponenteSeleccionado = 0;

    int cantidadFuentes = 1;
    int cantidadMercados = 1;

    int cantidadConectores = 0;
    int cantidadBarcos = 0;

    int cantidadBombas = 0;
    int cantidadCanon = 0;
    int cantidadCanonBR = 0;
    int cantidadCanonM = 0;

    int cantidadMinas = 0;
    int cantidadTemplos = 0;
    int cantidadArmerias = 0;



    int turno = 1;

    public Main() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 850);
        frame.setLayout(new GridBagLayout());

        Dimension minSize = new Dimension(100, 100);
        panelIzq.setLayout(new GridLayout(21, 21, 1, 1));
        panelIzq.setMinimumSize(minSize);
        panelIzq.setPreferredSize(minSize);
        panelDer.setLayout(new GridLayout(21, 21, 1, 1));
        panelDer.setMinimumSize(minSize);
        panelDer.setPreferredSize(minSize);

        dibujarMatrizJ(matriz1, panelIzq, matrizBotones1);
        dibujarMatrizJ(matriz2, panelDer, matrizBotones2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4; // Ajusta el peso para reducir el tamaño
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        frame.add(panelIzq, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.4; // Ajusta el peso para reducir el tamaño
        frame.add(panelDer, gbc);

        chat.setEditable(false);
        chat.setWrapStyleWord(true);
        chat.setText("Chat");
        gbc.gridx = 0;
        gbc.gridy = 1;
        //gbc.weightx = 0.4; // Ajusta el peso para reducir el tamaño
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(chat, gbc);

        JPanel botonesJugadores = new JPanel();
        botonesJugadores.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        botonesJugadores.add(p1, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        botonesJugadores.add(p2,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        botonesJugadores.add(p3, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        botonesJugadores.add(p4, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //gbc.weightx = 0.2;
        frame.add(botonesJugadores, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(siguienteTurno, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        Componentes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarFrameComponentes();
            }
        });
        frame.add(Componentes, gbc);

        //frame.pack();
        frame.setVisible(true);
    }

    public void dibujarMatrizJ(Entidad[][] matriz, JPanel panelMatriz, JButton[][] matrizBotones) {
        for (int r = 0; r < 20; r++) {
            for (int c = 0; c < 20; c++) {
                matrizBotones[r][c] = new JButton();
                int finalR = r;
                int finalC = c;

                matrizBotones[r][c].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (componenteSeleccionado != null && matriz[finalR][finalC] == null) {
                            if(cantidadComponenteSeleccionado > 0) {
                                if (componenteSeleccionado instanceof Armeria) {
                                    componentSelect(new Armeria(), finalR, finalC, matriz, matrizBotones, cantidadArmerias);
                                }
                                else if (componenteSeleccionado instanceof Mina){
                                    componentSelect(new Mina(), finalR, finalC, matriz, matrizBotones, cantidadMinas);
                                }
                                else if(componenteSeleccionado instanceof Templo){
                                    componentSelect(new Templo(), finalR, finalC, matriz, matrizBotones, cantidadTemplos);
                                }
                                else if(componenteSeleccionado instanceof Canon){
                                    componentSelect(new Canon(), finalR, finalC, matriz, matrizBotones, cantidadCanon);
                                }
                                else if(componenteSeleccionado instanceof CanonBR){
                                    componentSelect(new CanonBR(), finalR, finalC, matriz, matrizBotones, cantidadCanonBR);
                                }
                                else if(componenteSeleccionado instanceof CanonM){
                                    componentSelect(new CanonM(), finalR, finalC, matriz, matrizBotones, cantidadCanonM);
                                }
                                else if(componenteSeleccionado instanceof Bomba){
                                    componentSelect(new Bomba(), finalR, finalC, matriz, matrizBotones, cantidadBombas);
                                }
                                else if(componenteSeleccionado instanceof Conector){
                                    componentSelect(new Conector(), finalR, finalC, matriz, matrizBotones, cantidadConectores);
                                }
                                else if(componenteSeleccionado instanceof Barco){
                                    componentSelect(new Barco(), finalR, finalC, matriz, matrizBotones, cantidadBarcos);
                                }
                                else if(componenteSeleccionado instanceof Mercado){
                                    componentSelect(new Mercado(), finalR, finalC, matriz, matrizBotones, cantidadMercados);
                                }
                                else if(componenteSeleccionado instanceof FuenteDeEnergia){
                                    componentSelect(new FuenteDeEnergia(), finalR, finalC, matriz, matrizBotones, cantidadFuentes);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "No hay componente seleccionado");
                                }


                            }
                            else{
                                JOptionPane.showMessageDialog(null, "No hay mas componentes de este tipo");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "No hay componente seleccionado");
                        }
                    }
                });
                panelMatriz.add(matrizBotones[r][c]);
            }
        }
    }

    public void colocarObjeto(Entidad objeto, int x, int y,Entidad[][] matriz, JButton[][] matrizBotones) {
        for (int i = x; i < x + objeto.ancho; i++) {
            for (int j = y; j < y + objeto.alto; j++) {
                matriz[i][j] = objeto; // 'O' representa el objeto en el tablero
                matrizBotones[i][j].setBackground(objeto.color);
            }
        }
    }

    public void generarFrameComponentes(){
        if(!hecho){
        frameComponentes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameComponentes.setSize(500, 500);
        frameComponentes.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4; // Ajusta el peso para reducir el tamaño
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JLabel LabelFabricas = new JLabel("Fabricas");
        frameComponentes.add(LabelFabricas, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton botonFuente = new JButton("Armeria");
        ActionListenersComp(botonFuente, new Armeria());
        frameComponentes.add(botonFuente, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton botonMina = new JButton("Mina");
        ActionListenersComp(botonMina, new Mina());
        frameComponentes.add(botonMina, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        JButton botonTemplo = new JButton("Templo");
        ActionListenersComp(botonTemplo, new Templo());
        frameComponentes.add(botonTemplo, gbc);
        hecho = true;
        }
        frameComponentes.setVisible(true);


    }

    public void componentSelect(Entidad componente, int finalR, int finalC, Entidad[][] matriz, JButton[][] matrizBotones, int cantidad){
        colocarObjeto(componente, finalR, finalC, matriz, matrizBotones);
        cantidad--;
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    public void ActionListenersComp(JButton boton, Entidad componente){
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setComponente(componente);
            }
        });
    }

    public void setComponente(Entidad componente){
        this.componenteSeleccionado = componente;
    }
}
