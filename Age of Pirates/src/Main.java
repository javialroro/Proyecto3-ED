import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Main {
    boolean hecho = false;

    Jugador jugador1 = new Jugador(1);
    Jugador jugador2 = new Jugador(2);
    Jugador jugador3 = new Jugador(3);
    Jugador jugador4 = new Jugador(4);


    JFrame frame = new JFrame("Age of Pirates");

    JFrame frameComponentes = new JFrame("Componentes");

    JFrame Mercado = new JFrame("Mercado");

    JPanel panelIzq = new JPanel();
    JPanel panelDer = new JPanel();

    JButton p1 = new JButton("P1");
    JButton p2 = new JButton("P2");
    JButton p3 = new JButton("P3");
    JButton p4 = new JButton("P4");
    JButton siguienteTurno = new JButton("Siguiente Turno");

    JButton Componentes = new JButton("Componentes");

    JButton botonEnviarMensaje = new JButton("Enviar mensaje");

    JButton botonDisparar = new JButton("Disparar");

    JTextArea chat = new JTextArea();
    JTextArea mensaje = new JTextArea();

    Entidad componenteSeleccionado = null;
    

    int cantidadComponenteSeleccionado = 5;

    Arma armaSeleccionada = null;

    int cantidadDisparos = 0;


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

        dibujarMatrizJ(jugador1.matriz, panelIzq, jugador1.matrizBotones, jugador1);
        dibujarMatrizE(jugador2.matriz, panelDer, jugador2.matrizBotones, jugador2);
        //dibujarMatrizJ(matriz2, panelDer, matrizBotones2, jugador2);

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

        p1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonesJugadores(jugador1);
            }
        });

        p2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonesJugadores(jugador2);
            }
        });

        p3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonesJugadores(jugador3);
            }
        });

        p4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonesJugadores(jugador4);
            }
        });

        siguienteTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarTurno();
            }
        });
        frame.add(Componentes, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        JButton botonIniciar = new JButton("Iniciar");
        frame.add(botonIniciar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;

        botonDisparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (turno == jugador1.numero) {
                    seleccionarArma(jugador1);
                }
                else if (turno == jugador2.numero) {
                    seleccionarArma(jugador2);
                }
                else if (turno == jugador3.numero) {
                    seleccionarArma(jugador3);
                }
                else if (turno == jugador4.numero) {
                    seleccionarArma(jugador4);
                }
            }
        });
        frame.add(botonDisparar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        
        frame.add(mensaje, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        botonEnviarMensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensajeEnviar = mensaje.getText();
                chat.setText(chat.getText() + "\n" + "Jugador " + turno + ": " +  mensajeEnviar);
                mensaje.setText("");
            }
        });
        frame.add(botonEnviarMensaje, gbc);

        frame.setVisible(true);
    }

    public void seleccionarArma(Jugador j){
        JFrame seleccionArma = new JFrame();
        seleccionArma.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        seleccionArma.setSize(300, 300);
        seleccionArma.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton cannonNormal = new JButton("Cañón");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        seleccionArma.add(cannonNormal, gbc);

        cannonNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                armaSeleccionada = new Canon();
                System.out.println("Cañon");
                seleccionArma.dispose();
                JOptionPane.showMessageDialog(null, "Seleccione la casille donde quiera disparar el cañon en el mapa");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JButton canonMultiple = new JButton("Cañón multiple");
        seleccionArma.add(canonMultiple, gbc);

        canonMultiple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                armaSeleccionada = new CanonM();
                System.out.println("Cañon multiple");
                seleccionArma.dispose();
                JOptionPane.showMessageDialog(null, "Seleccione la casille donde quiera disparar el cañon multiple en el mapa");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JButton canonBarbaRoja = new JButton("Cañón Barba Roja");
        seleccionArma.add(canonBarbaRoja, gbc);

        canonBarbaRoja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                armaSeleccionada = new CanonBR();
                System.out.println("Cañon Barba Roja");
                seleccionArma.dispose();
                JOptionPane.showMessageDialog(null, "Seleccione las 10 casille donde quiera disparar el cañon barba roja en el mapa");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        JButton bomba = new JButton("Bomba");
        seleccionArma.add(bomba, gbc);

        bomba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                armaSeleccionada = new Bomba();
                System.out.println("Bomba");
                seleccionArma.dispose();
                JOptionPane.showMessageDialog(null, "Coloque 3 cartuchos de dinamita en el mapa");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        JButton kraken = new JButton("Kraken");
        seleccionArma.add(kraken, gbc);

        kraken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                armaSeleccionada = new kraken();
                System.out.println("Kraken");
                seleccionArma.dispose();
                j.Ckraken--;
                JOptionPane.showMessageDialog(null, "Seleccione cualquier casilla para invocar al Kraken");
            }
        });

        if(j.Ckraken == 0){
            kraken.setEnabled(false);
        }

        if(j.cantidadCanon == 0){
            cannonNormal.setEnabled(false);
        }
        if(j.cantidadCanonM == 0){
            canonMultiple.setEnabled(false);
        }
        if(j.cantidadCanonBR == 0){
            canonBarbaRoja.setEnabled(false);
        }
        if(j.cantidadBombas == 0){
            bomba.setEnabled(false);
        }



        seleccionArma.setVisible(true);
    }

    public void cambiarTurno(){
        if(turno == 1){
            //panelIzq.removeAll();
            dibujarMatrizJ(jugador2.matriz, panelIzq, jugador2.matrizBotones, jugador2);
            turno = 2;
            dibujarMatrizE(jugador3.matriz, panelDer, jugador3.matrizBotones, jugador3);

        }
        else if(turno == 2){
            //panelIzq.removeAll();
            dibujarMatrizJ(jugador3.matriz, panelIzq, jugador3.matrizBotones, jugador3);
            turno = 3;
            dibujarMatrizE(jugador4.matriz, panelDer, jugador4.matrizBotones, jugador4);
        }
        else if(turno == 3){
            //panelIzq.removeAll();
            dibujarMatrizJ(jugador4.matriz, panelIzq, jugador4.matrizBotones, jugador4);
            turno = 4;
            dibujarMatrizE(jugador1.matriz, panelDer, jugador1.matrizBotones, jugador1);
        }
        else if(turno == 4){
            //panelIzq.removeAll();
            dibujarMatrizJ(jugador1.matriz, panelIzq, jugador1.matrizBotones, jugador1);
            turno = 1;
            dibujarMatrizE(jugador2.matriz, panelDer, jugador2.matrizBotones, jugador2);
        }
        System.out.println("Turno: " + turno);
    }
    public void botonesJugadores(Jugador jugador){
        if(turno==jugador.numero){
            JOptionPane.showMessageDialog(null, "Jugador invalido, es tu turno");
            return;
        }
        dibujarMatrizE(jugador.matriz, panelDer, jugador.matrizBotones, jugador);
    }

    public void dibujarMatrizJ(Entidad[][] matriz, JPanel panelMatriz, JButton[][] matrizBotones,Jugador jugador) {
        panelMatriz.removeAll();
        for (int r = 0; r < 20; r++) {
            for (int c = 0; c < 20; c++) {
                matrizBotones[r][c] = new JButton();
                if(matriz[r][c]instanceof EntidadVacia) {
                    matrizBotones[r][c].setBackground(Color.WHITE);
                }
                else {
                    matrizBotones[r][c].setBackground(matriz[r][c].color);
                }


                int finalR = r;
                int finalC = c;

                int finalC1 = c;
                int finalR1 = r;

                matrizBotones[r][c].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (componenteSeleccionado != null ) {
                            if(matriz[finalR1][finalC1]instanceof EntidadVacia){
                                if(cantidadComponenteSeleccionado>0) {

                                    if (componenteSeleccionado instanceof Armeria) {
                                        if(((Armeria) componenteSeleccionado).arma==1){
                                            componentSelect(new Armeria(1), finalR, finalC, matriz, matrizBotones,jugador);
                                        }
                                        else if(((Armeria) componenteSeleccionado).arma == 2){
                                            componentSelect(new Armeria(2), finalR, finalC, matriz, matrizBotones,jugador);
                                        }
                                        else if(((Armeria) componenteSeleccionado).arma == 3){
                                            componentSelect(new Armeria(3), finalR, finalC, matriz, matrizBotones,jugador);
                                        }

                                        else if(((Armeria) componenteSeleccionado).arma == 4){
                                            componentSelect(new Armeria(4), finalR, finalC, matriz, matrizBotones,jugador);

                                        }
                                    } else if (componenteSeleccionado instanceof Mina) {
                                        if(jugador.cantidadMinas > 0){
                                        Mina x = new Mina();
                                        x.y = jugador.cantidadMinas;
                                        componentSelect(x, finalR, finalC, matriz, matrizBotones,jugador);
                                        x.iniciarThread();
                                        }
                                    } else if (componenteSeleccionado instanceof Templo) {
                                        if(jugador.cantidadTemplos > 0){
                                            Templo x = new Templo();
                                            componentSelect(x, finalR, finalC, matriz, matrizBotones,jugador);
                                            x.iniciarThreadT();
                                        }
                                    } else if (componenteSeleccionado instanceof Canon) {
                                        componentSelect(new Canon(), finalR, finalC, matriz, matrizBotones,jugador);
                                    } else if (componenteSeleccionado instanceof CanonBR) {
                                        componentSelect(new CanonBR(), finalR, finalC, matriz, matrizBotones,jugador);
                                    } else if (componenteSeleccionado instanceof CanonM) {
                                        componentSelect(new CanonM(), finalR, finalC, matriz, matrizBotones,jugador);
                                    } else if (componenteSeleccionado instanceof Bomba) {
                                        componentSelect(new Bomba(), finalR, finalC, matriz, matrizBotones,jugador);
                                    } else if (componenteSeleccionado instanceof Conector) {
                                        componentSelect(new Conector(), finalR, finalC, matriz, matrizBotones,jugador);
                                    } else if (componenteSeleccionado instanceof Barco) {
                                        componentSelect(new Barco(), finalR, finalC, matriz, matrizBotones,jugador);
                                    } else if (componenteSeleccionado instanceof Mercado) {
                                        componentSelect(new Mercado(), finalR, finalC, matriz, matrizBotones,jugador);
                                    } else if (componenteSeleccionado instanceof FuenteDeEnergia) {
                                        componentSelect(new FuenteDeEnergia(), finalR, finalC, matriz, matrizBotones,jugador);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No hay componente seleccionado");
                                        System.out.println("PRIMERO");
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "No hay componentes disponibles");
                                }
                            }
                            else {
                                if (matrizBotones[finalR][finalC].getBackground() == Color.YELLOW) {
                                    JOptionPane.showMessageDialog(null, "Conector");

                                } else if (matrizBotones[finalR][finalC].getBackground() == Color.BLACK) {
                                    JOptionPane.showMessageDialog(null, "Fabrica");

                                } else if (matrizBotones[finalR][finalC].getBackground() == Color.BLUE) {
                                    JOptionPane.showMessageDialog(null, "Barco");
                                } else if (matrizBotones[finalR][finalC].getBackground() == Color.magenta) {
                                    JOptionPane.showMessageDialog(null, "Fuente de Energia");
                                } else if (matrizBotones[finalR][finalC].getBackground() == Color.green) {

                                    generarFrameMina((Mina) matriz[finalR][finalC],jugador);

                                } else if (matrizBotones[finalR][finalC].getBackground() == Color.ORANGE) {
                                    generarFrameTemplo((Templo) matriz[finalR][finalC],jugador);

                                } else if (matrizBotones[finalR][finalC].getBackground() == Color.cyan) {
                                    generarFrameMercado(jugador);
                                } else if (matrizBotones[finalR][finalC].getBackground() == Color.pink) {
                                    generarFrameArmeriaCañon(jugador);
                                }
                                else if(matrizBotones[finalR][finalC].getBackground() == Color.red){
                                    generarFrameArmeriaCañonMultiple(jugador);
                                }
                                else if(matrizBotones[finalR][finalC].getBackground() == Color.lightGray){
                                    generarFrameArmeriaCañonBR(jugador);
                                }
                                else if(matrizBotones[finalR][finalC].getBackground() == Color.darkGray){
                                    generarFrameArmeriaBomba(jugador);
                                }
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
        panelMatriz.repaint();
        panelMatriz.revalidate();
        //System.out.println("Turno: " + turno);
    }


    public void dibujarMatrizE(Entidad[][] matriz, JPanel panelMatriz, JButton[][] matrizBotones,Jugador jugador) {
        panelMatriz.removeAll();
        for (int r = 0; r < 20; r++) {
            for (int c = 0; c < 20; c++) {
                matrizBotones[r][c] = new JButton();

                // if(matriz[r][c]instanceof EntidadVacia) {
                //     if(!matriz[r][c].atacado) {
                //         matrizBotones[r][c].setBackground(Color.WHITE);
                //     }
                //     else {
                //         matrizBotones[r][c].setBackground(Color.RED);
                //     }
                // }
                //else {
                if (jugador.matrizAtaques[r][c] == 2 ) {
                    matrizBotones[r][c].setBackground(Color.GREEN);
                }
                else if(jugador.matrizAtaques[r][c] == 1){
                    matrizBotones[r][c].setBackground(Color.RED);
                }
                else{
                    matrizBotones[r][c].setBackground(Color.WHITE);
                }
                //}

                panelMatriz.add(matrizBotones[r][c]);
                int finalR = r;
                int finalC = c;
                matrizBotones[r][c].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (jugador.aguantesEscudo > 0) {
                            JOptionPane.showMessageDialog(null, "el ataque no impactó, el jugador posee escudo");
                            jugador.aguantesEscudo--;

                        } else {

                            if (armaSeleccionada == null) {
                                JOptionPane.showMessageDialog(null, "No hay arma seleccionada");
                                return;
                            }
                            if(armaSeleccionada instanceof kraken){

                                Random randomR = new Random();
                                Random randomC = new Random();
                                boolean xd = true;
                                while (true) {
                                    int enteroRandomR = randomR.nextInt(20);
                                    int enteroRandomC = randomC.nextInt(20);
                                    System.out.println("r: " + enteroRandomR + " c: " + enteroRandomC);
                                    if (!(matriz[enteroRandomR][enteroRandomC] instanceof EntidadVacia)) {
                                        matriz[enteroRandomR][enteroRandomC].destruido = true;
                                        borrarObjeto(matriz[enteroRandomR][enteroRandomC],enteroRandomR, enteroRandomC, matriz, matrizBotones ,jugador);
                                        JOptionPane.showMessageDialog(null, "El kraken destruyó un componente enemigo");
                                        String t = chat.getText()+ "\n" ;
                                        chat.setText(t + "El kraken destruyó un componente del jugador " + jugador.numero);
                                        armaSeleccionada = null;
                                        return;
                                    }
                                    cambiarTurno();


                                }
                            }
                            else if (matriz[finalR][finalC] instanceof EntidadVacia) {
                                matrizBotones[finalR][finalC].setBackground(Color.RED);
                                jugador.matrizAtaques[finalR][finalC] = 1;
                                chat.setText(chat.getText() + "\n" + "Ataque a " + jugador.numero + " fallido en la posición " + "(" + finalR + ", " + finalC + ")" );
                            } else {
                                matrizBotones[finalR][finalC].setBackground(Color.GREEN);
                                jugador.matrizAtaques[finalR][finalC] = 2;
                                chat.setText(chat.getText() + "\n" + "Ataque a " + jugador.numero + " exitoso en la posición " + "(" + finalR + ", " + finalC + ")" );

                                if (armaSeleccionada instanceof CanonM) {
                                    System.out.println("xd");
                                    Random randomR = new Random();
                                    Random randomC = new Random();
                                    int contador = 0;
                                    chat.setText(chat.getText() + "\n" + "Cañon multiple en: ");
                                    while (contador < 3) {
                                        int enteroRandomR = randomR.nextInt(20);
                                        int enteroRandomC = randomC.nextInt(20);
                                        if (matriz[enteroRandomR][enteroRandomC] instanceof EntidadVacia) {
                                            matrizBotones[enteroRandomR][enteroRandomC].setBackground(Color.RED);
                                            jugador.matrizAtaques[enteroRandomR][enteroRandomC] = 1;
                                            contador++;
                                            chat.setText(chat.getText() + "\n" + "Fallido en la posición " + "(" + enteroRandomR + ", " + enteroRandomC + ")" );

                                        } else {
                                            matrizBotones[enteroRandomR][enteroRandomC].setBackground(Color.GREEN);
                                            jugador.matrizAtaques[enteroRandomR][enteroRandomC] = 2;
                                            contador++;
                                            chat.setText(chat.getText() + "\n" + "Exitoso en la posición " + "(" + enteroRandomR + ", " + enteroRandomC + ")" );

                                        }

                                        matriz[enteroRandomR][enteroRandomC].atacado = true;
                                    }
                                    cantidadDisparos = 0;
                                    JOptionPane.showMessageDialog(null, "Se han completado los disparos");

                                    matriz[finalR][finalC].vida -= 1;
                                    if (matriz[finalR][finalC].vida == 0) {
                                        matriz[finalR][finalC].destruido = true;
                                        borrarObjeto(matriz[finalR][finalC], finalR, finalC, matriz, matrizBotones, jugador);
                                        for (int i = 0; i < 20; i++) {
                                            for (int j = 0; j < 20; j++) {
                                                if (jugador.matrizAtaques[i][j] == 2) {
                                                    matrizBotones[i][j].setBackground(Color.GREEN);
                                                } else if (jugador.matrizAtaques[i][j] == 1) {
                                                    matrizBotones[i][j].setBackground(Color.RED);
                                                } else {
                                                    matrizBotones[i][j].setBackground(Color.WHITE);
                                                }
                                            }
                                        }
                                        JOptionPane.showMessageDialog(null, "Se ha destruido el objeto");
                                        cambiarTurno();
                                        return;
                                    }
                                    cambiarTurno();
                                    return;
                                }
                                matriz[finalR][finalC].vida -= 1;
                                if (matriz[finalR][finalC].vida == 0) {
                                    matriz[finalR][finalC].destruido = true;
                                    borrarObjeto(matriz[finalR][finalC], finalR, finalC, matriz, matrizBotones, jugador);
                                    for (int i = 0; i < 20; i++) {
                                        for (int j = 0; j < 20; j++) {
                                            if (jugador.matrizAtaques[i][j] == 2) {
                                                matrizBotones[i][j].setBackground(Color.GREEN);
                                            } else if (jugador.matrizAtaques[i][j] == 1) {
                                                matrizBotones[i][j].setBackground(Color.RED);
                                            } else {
                                                matrizBotones[i][j].setBackground(Color.WHITE);
                                            }
                                        }
                                    }
                                    JOptionPane.showMessageDialog(null, "Se ha destruido el objeto");
                                    cambiarTurno();
                                    return;
                                }
                            }
                            matriz[finalR][finalC].atacado = true;

                            cantidadDisparos++;

                            if (cantidadDisparos == armaSeleccionada.disparos) {
                                JOptionPane.showMessageDialog(null, "Se han completado los disparos");
                                armaSeleccionada = null;
                                cambiarTurno();
                                cantidadDisparos = 0;
                                return;
                            }

                        }
                    }
                    });
                }

        }

        panelMatriz.repaint();
        panelMatriz.revalidate();
    }

    public void borrarObjeto(Entidad objeto, int x, int y, Entidad[][] matriz, JButton[][] matrizBotones,Jugador jugador){
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                if(matriz[i][j].destruido){
                    matriz[i][j] = new EntidadVacia();
                }
            }
        }
    }


    public void colocarObjeto(Entidad objeto, int x, int y, Entidad[][] matriz, JButton[][] matrizBotones,Jugador jugador) {
        int matrizAncho = matriz.length;
        int matrizAlto = matriz[0].length;

        // Verificar si el objeto se sale de los límites de la matriz
        if (x + objeto.ancho > matrizAncho || y + objeto.alto > matrizAlto) {
            // Calcular la nueva posición dentro de la matriz
            x = Math.min(x, matrizAncho - objeto.ancho);
            y = Math.min(y, matrizAlto - objeto.alto);
        }

        // Buscar la primera posición disponible para colocar el objeto
        boolean objetoColocado = false;
        for (int i = x; i <= matrizAncho - objeto.ancho && !objetoColocado; i++) {
            for (int j = y; j <= matrizAlto - objeto.alto && !objetoColocado; j++) {
                boolean hayColision = verificarColision(objeto, i, j, matriz);
                if (!hayColision && (i == x || j == y)) {
                    // Colocar el objeto en la posición encontrada
                    colocarObjetoEnPosicion(objeto, i, j, matriz, matrizBotones,jugador);
                    objetoColocado = true;
                }
            }
        }

        // Si no se pudo colocar el objeto, mostrar un mensaje de error
        if (!objetoColocado) {
            JOptionPane.showMessageDialog(null, "No hay espacio suficiente para colocar el objeto manteniendo una posición fija.");
        }
    }

    private boolean verificarColision(Entidad objeto, int x, int y, Entidad[][] matriz) {
        for (int i = x; i < x + objeto.ancho; i++) {
            for (int j = y; j < y + objeto.alto; j++) {
                if (matriz[i][j] instanceof EntidadVacia) {
                     // Hay un objeto en esta posición, hay colisión
                }
                else{
                    return true;
                }
            }
        }
        return false;
        //return false; // No hay colisión
    }

    private void colocarObjetoEnPosicion(Entidad objeto, int x, int y, Entidad[][] matriz, JButton[][] matrizBotones,Jugador jugador) {
        for (int i = x; i < x + objeto.ancho; i++) {
            for (int j = y; j < y + objeto.alto; j++) {
                matriz[i][j] = null;
                matriz[i][j] = objeto;
                jugador.grafo.agregarVertice(objeto);
                matrizBotones[i][j].setBackground(objeto.color);
                if(objeto instanceof Conector){
                    searchAround(matriz,i,j,jugador);
                }
            }
        }
    }

    public static void searchAround(Entidad[][] matrix, int targetRow, int targetCol,Jugador jugador) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        int radius = 5;
        ArrayList<Vertice> entidadesCerca = new ArrayList<>();

        for (int i = targetRow - radius; i <= targetRow + radius; i++) {
            for (int j = targetCol - radius; j <= targetCol + radius; j++) {
                if (isValidPosition(i, j, numRows, numCols)) {
                    if (matrix[i][j] instanceof EntidadVacia || matrix[i][j] instanceof Conector) {

                    }
                    else{
                        System.out.println("Objeto encontrado en la posición [" + i + ", " + j + "]");
                        Vertice vertice = new Vertice(matrix[i][j]);
                        entidadesCerca.add(vertice);
                    }

                }
            }
        }
        for (Vertice vertice:entidadesCerca) {
            for(Vertice verticeT:entidadesCerca){
                if(vertice!=verticeT && vertice.dato.getClass()!=verticeT.dato.getClass()){
                    jugador.grafo.agregarArista(jugador.grafo.buscarVertice(vertice.dato),jugador.grafo.buscarVertice(verticeT.dato));
                }
            }
        }
        jugador.grafo.imprimir();
    }

    public static boolean isValidPosition(int row, int col, int numRows, int numCols) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    public void generarFrameComponentes() {
        if (!hecho) {
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
            JButton botonFuente = new JButton("Armeria Cañon");
            ActionListenersComp(botonFuente, new Armeria(1));
            frameComponentes.add(botonFuente, gbc);

            gbc.gridy = 2;
            JButton botonMina = new JButton("Mina");
            ActionListenersComp(botonMina, new Mina());
            frameComponentes.add(botonMina, gbc);

            gbc.gridy = 3;
            JButton botonTemplo = new JButton("Templo");
            ActionListenersComp(botonTemplo, new Templo());
            frameComponentes.add(botonTemplo, gbc);

            gbc.gridy = 4;
            JButton botonArmeriaMultiple = new JButton("Armeria Cañon Multiple");
            ActionListenersComp(botonArmeriaMultiple, new Armeria(2));
            frameComponentes.add(botonArmeriaMultiple, gbc);

            gbc.gridy = 5;
            JButton botonArmeriaBarbaRoja = new JButton("Armeria Cañon Barba Roja");
            ActionListenersComp(botonArmeriaBarbaRoja, new Armeria(3));
            frameComponentes.add(botonArmeriaBarbaRoja, gbc);

            gbc.gridy = 6;
            JButton botonArmeriaBomba = new JButton("Armeria Bomba");
            ActionListenersComp(botonArmeriaBomba, new Armeria(4));
            frameComponentes.add(botonArmeriaBomba, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            JLabel LabelComponentes = new JLabel("Componentes");
            frameComponentes.add(LabelComponentes, gbc);

            gbc.gridy = 1;
            JButton botonConectores = new JButton("Conectores");
            ActionListenersComp(botonConectores, new Conector());
            frameComponentes.add(botonConectores, gbc);

            gbc.gridy = 2;
            JButton botonFuenteEnergia = new JButton("Fuente Energía");
            ActionListenersComp(botonFuenteEnergia, new FuenteDeEnergia());
            frameComponentes.add(botonFuenteEnergia, gbc);

            gbc.gridy = 3;
            JButton botonMercado = new JButton("Mercado");
            ActionListenersComp(botonMercado, new Mercado());
            frameComponentes.add(botonMercado, gbc);

            hecho = true;
        }
        frameComponentes.setVisible(true);
    }





    public void generarFrameMercado(Jugador j) {
        JFrame Mercado = new JFrame();
        Mercado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Mercado.setSize(300, 300);
        Mercado.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton boton1 = new JButton("vender cañon (recibes 100 de oro)");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        Mercado.add(boton1, gbc);
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(j.cantidadCanon>0){
                    j.dinero+=100;
                    j.cantidadCanon--;
                    JOptionPane.showMessageDialog(null, "Cañon Vendido");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No tienes cañones para vender");

                }

            }

        });


        JButton boton2 = new JButton("Vender cañon BR (recibes 200 de oro)");
        gbc.gridy = 1;
        Mercado.add(boton2, gbc);
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(j.cantidadCanonBR>0){
                    j.dinero+=200;
                    j.cantidadCanonBR--;
                    JOptionPane.showMessageDialog(null, "Cañon Barba Roja Vendido");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No tienes cañones para vender");

                }

            }

        });


        JButton boton3 = new JButton("Vender cañon M (recibe 150 de oro)");
        gbc.gridy = 2;
        Mercado.add(boton3, gbc);
        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(j.cantidadCanonM>0){
                    j.dinero+=150;
                    j.cantidadCanonM--;
                    JOptionPane.showMessageDialog(null, "Cañon Multiple Vendido");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No tienes cañones para vender");

                }
            }

        });






        JButton boton7 = new JButton("Vender tu acero (recibes la cantidad de acero en oro)");
        gbc.gridy = 3;
        Mercado.add(boton7, gbc);
        boton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(j.acero>0){
                    j.dinero+=j.acero;
                    j.acero=0;
                    JOptionPane.showMessageDialog(null, "Acero Vendido");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No tienes acero para vender");

                }

            }

        });

        JButton boton4 = new JButton("Comprar cañon (100 de oro)");
        gbc.gridx = 1;
        gbc.gridy = 0;
        Mercado.add(boton4, gbc);
        boton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(j.dinero>=100){
                    j.dinero-=100;
                    j.cantidadCanon++;
                    JOptionPane.showMessageDialog(null, "Cañon Comprado");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No tienes suficiente oro");
                }
            }

        });

        JButton boton5 = new JButton("Comprar cañon BR (200 de oro)");
        gbc.gridy = 1;
        Mercado.add(boton5, gbc);
        boton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(j.dinero>=200){
                    j.dinero-=200;
                    j.cantidadCanonBR++;
                    JOptionPane.showMessageDialog(null, "Cañon Barba Roja Comprado");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No tienes suficiente oro");
                }
            }

        });

        JButton boton6 = new JButton("Comprar cañon M (150 de oro)");
        gbc.gridy = 2;
        Mercado.add(boton6, gbc);
        boton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(j.dinero>=150){
                    j.dinero-=150;
                    j.cantidadCanon++;
                    JOptionPane.showMessageDialog(null, "Cañon Multiple Comprado");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No tienes suficiente oro");
                }
            }

        });

        JButton boton8 = new JButton("Comprar 10 de acero (10 de oro)");
        gbc.gridy = 3;
        Mercado.add(boton8, gbc);
        boton8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(j.dinero>=10){
                    j.dinero-=10;
                    j.acero+=10;
                    JOptionPane.showMessageDialog(null, "Acero Comprado");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No tienes suficiente oro");
                }
            }

        });

        Mercado.setVisible(true);
    }

    public void generarFrameArmeriaCañon(Jugador j){
        JFrame ArmeriaCañon = new JFrame();
        ArmeriaCañon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ArmeriaCañon.setSize(300, 300);
        ArmeriaCañon.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton boton1 = new JButton("Fabricar Cañon (500kg)");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        ArmeriaCañon.add(boton1, gbc);

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (j.acero >= 500) {
                    j.acero -= 500;
                    j.cantidadCanon++;
                    JOptionPane.showMessageDialog(null, "Cañon fabricado");
                } else {
                    JOptionPane.showMessageDialog(null, "No hay suficiente acero");
                }
            }

        });
        ArmeriaCañon.setVisible(true);

    }


    public void generarFrameArmeriaCañonMultiple(Jugador j){
        JFrame ArmeriaCañonM = new JFrame();
        ArmeriaCañonM.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ArmeriaCañonM.setSize(300, 300);
        ArmeriaCañonM.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton boton1 = new JButton("Fabricar Cañon Multiple(1000kg)");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        ArmeriaCañonM.add(boton1, gbc);

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (j.acero >= 1000) {
                    j.acero -= 1000;
                    j.cantidadCanonM++;
                    JOptionPane.showMessageDialog(null, "Cañon Multiple fabricado");
                } else {
                    JOptionPane.showMessageDialog(null, "No hay suficiente acero");
                }
            }

        });
        ArmeriaCañonM.setVisible(true);

    }


    public void generarFrameArmeriaCañonBR(Jugador j){
        JFrame ArmeriaCañonBR = new JFrame();
        ArmeriaCañonBR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ArmeriaCañonBR.setSize(300, 300);
        ArmeriaCañonBR.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton boton1 = new JButton("Fabricar Cañon Barba Roja(5000kg)");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        ArmeriaCañonBR.add(boton1, gbc);

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (j.acero >= 5000) {
                    j.acero -= 5000;
                    j.cantidadCanonBR++;
                    JOptionPane.showMessageDialog(null, "Cañon Barba Roja fabricado");
                } else {
                    JOptionPane.showMessageDialog(null, "No hay suficiente acero");
                }
            }

        });
        ArmeriaCañonBR.setVisible(true);

    }



    public void generarFrameArmeriaBomba(Jugador j){
        JFrame ArmeriaBomba = new JFrame();
        ArmeriaBomba.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ArmeriaBomba.setSize(300, 300);
        ArmeriaBomba.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton boton1 = new JButton("Fabricar Bomba(2000kg)");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        ArmeriaBomba.add(boton1, gbc);

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (j.acero >= 2000) {
                    j.acero -= 2000;
                    j.cantidadBombas+= 3;
                    JOptionPane.showMessageDialog(null, "Bombas fabricadas");
                } else {
                    JOptionPane.showMessageDialog(null, "No hay suficiente acero");
                }
            }

        });
        ArmeriaBomba.setVisible(true);

    }
    public void generarFrameTemplo(Templo t, Jugador j){

        JFrame templo = new JFrame("Mina");
        templo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        templo.setSize(300, 300);
        templo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton boton1 = new JButton("usar comodín");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        templo.add(boton1, gbc);

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t.comodin == 1 && t.hecho == true){
                    JOptionPane.showMessageDialog(null, "has usado el comodín escudo");
                    t.uso = 1;
                    t.hecho = false;
                    j.aguantesEscudo = 3;
                    String t = chat.getText()+ "\n" ;
                    chat.setText(t + " el jugador " + j.numero + " activó un escudo" + "\n");


                }
                else if(t.comodin == 2  && t.hecho == true){
                    JOptionPane.showMessageDialog(null, "has usado el comodín kraken");
                    t.uso = 2;
                    t.hecho = false;
                    j.Ckraken++;
                    String t = chat.getText()+ "\n" ;
                    chat.setText(t + " el jugador " + j.numero + " tiene un kraken" + "\n");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Este templo está en proceso de generar un comodín");


                }
            }

        });


        templo.setVisible(true);


    }
    public void generarFrameMina(Mina m, Jugador j){
        JFrame mina = new JFrame("Mina");
        mina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mina.setSize(300, 300);
        mina.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton boton1 = new JButton("recoger acero" + "(" + m.aceroGenerado + "kg)");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mina.add(boton1, gbc);
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(m.aceroGenerado> 0){
                   j.acero += m.aceroGenerado;
                   JOptionPane.showMessageDialog(null, "Acero recogido: +" +  + m.aceroGenerado + "kg");
                   m.aceroGenerado = 0;

               }
               else{
                   JOptionPane.showMessageDialog(null, "No hay acero por recoger");
               }
            }

        });
        mina.setVisible(true);

    }








    public void componentSelect(Entidad componente, int finalR, int finalC, Entidad[][] matriz, JButton[][] matrizBotones,Jugador jugador){
        if (componente instanceof Armeria) {
            if(jugador.cantidadArmerias > 0){
                colocarObjeto(componente, finalR, finalC, matriz, matrizBotones, jugador);
                jugador.cantidadArmerias--;
            }
            else {
                JOptionPane.showMessageDialog(null, "No hay componentes");
            }
        } else if (componente instanceof Mina) {
            if(jugador.cantidadMinas > 0){
                colocarObjeto(componente, finalR, finalC, matriz, matrizBotones, jugador);
                jugador.cantidadMinas--;
            }
            else {
                JOptionPane.showMessageDialog(null, "No hay componentes");
            }

        } else if (componente instanceof Templo) {
            if(jugador.cantidadTemplos > 0){
                colocarObjeto(componente, finalR, finalC, matriz, matrizBotones,jugador);
                jugador.cantidadTemplos--;
            }
            else {
                JOptionPane.showMessageDialog(null, "No hay componentes");
            }

        } else if (componente instanceof Canon) {
            if(jugador.cantidadCanon > 0){
                colocarObjeto(componente, finalR, finalC, matriz, matrizBotones,jugador);
                jugador.cantidadCanon--;
            }
            else {
                JOptionPane.showMessageDialog(null, "No hay componentes");
            }

        } else if (componente instanceof CanonBR) {
            if(jugador.cantidadCanonBR > 0){
                colocarObjeto(componente, finalR, finalC, matriz, matrizBotones,jugador);
                jugador.cantidadCanonBR--;
            }
            else {
                JOptionPane.showMessageDialog(null, "No hay componentes");
            }
        } else if (componente instanceof CanonM) {
            if(jugador.cantidadCanonM > 0){
                colocarObjeto(componente, finalR, finalC, matriz, matrizBotones,jugador);
                jugador.cantidadCanonM--;
            }
            else {
                JOptionPane.showMessageDialog(null, "No hay componentes");
            }
        } else if (componente instanceof Bomba) {
            if(jugador.cantidadBombas > 0){
                colocarObjeto(componente, finalR, finalC, matriz, matrizBotones,jugador);
                jugador.cantidadBombas--;
            }
            else {
                JOptionPane.showMessageDialog(null, "No hay componentes");
            }
        } else if (componente instanceof Conector) {
            if(jugador.cantidadConectores > 0){
                colocarObjeto(componente, finalR, finalC, matriz, matrizBotones,jugador);
                jugador.cantidadConectores--;
            }
            else {
                JOptionPane.showMessageDialog(null, "No hay componentes");
            }
        } else if (componente instanceof Barco) {
            if(jugador.cantidadBarcos > 0){
                colocarObjeto(componente, finalR, finalC, matriz, matrizBotones,jugador);
                jugador.cantidadBarcos--;
            }
            else {
                JOptionPane.showMessageDialog(null, "No hay componentes");
            }
        } else if (componente instanceof Mercado) {
            if(jugador.cantidadMercados > 0){
                colocarObjeto(componente, finalR, finalC, matriz, matrizBotones,jugador);
                jugador.cantidadMercados--;
            }
            else {
                JOptionPane.showMessageDialog(null, "No hay componentes");
            }
        } else if (componente instanceof FuenteDeEnergia) {
            if(jugador.cantidadFuentes > 0){
                colocarObjeto(componente, finalR, finalC, matriz, matrizBotones,jugador);
                jugador.cantidadFuentes--;
            }
            else {
                JOptionPane.showMessageDialog(null, "No hay componentes");
            }
        }

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

    public void borrarActionListeners(JButton [][] botones){
        for(int i = 0; i < botones.length; i++){
            for(int j = 0; j < botones[0].length; j++){
                if(botones[i][j].getBackground() == Color.WHITE){
                    for(ActionListener al : botones[i][j].getActionListeners()){
                        botones[i][j].removeActionListener(al);
                    }
                }
            }
        }
    }



}
