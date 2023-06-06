import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    Entidad[][] matriz1 = new Entidad[20][20];
    Entidad[][] matriz2 = new Entidad[20][20];
    Entidad[][] matriz3 = new Entidad[20][20];
    Entidad[][] matriz4 = new Entidad[20][20];

    JButton[][] matrizBotones1 = new JButton[20][20];

    JButton[][] matrizBotones2 = new JButton[20][20];
    JButton[][] matrizBotones3 = new JButton[20][20];
    JButton[][] matrizBotones4 = new JButton[20][20];

    JFrame frame = new JFrame("Age of Pirates");

    JPanel panelIzq = new JPanel();
    JPanel panelDer = new JPanel();

    JButton p1 = new JButton("P1");
    JButton p2 = new JButton("P2");
    JButton p3 = new JButton("P3");
    JButton p4 = new JButton("P4");
    JButton siguienteTurno = new JButton("Siguiente Turno");

    JTextArea chat = new JTextArea();

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

        dibujarMatriz(matriz1, panelIzq, matrizBotones1);
        dibujarMatriz(matriz2, panelDer, matrizBotones2);

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

        //frame.pack();
        frame.setVisible(true);
    }

    public void dibujarMatriz(Entidad[][] matriz, JPanel panelMatriz, JButton[][] matrizBotones) {
        for (int r = 0; r < 20; r++) {
            for (int c = 0; c < 20; c++) {
                matrizBotones[r][c] = new JButton();
                int finalR = r;
                int finalC = c;
                matrizBotones[r][c].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //colocarObjeto(new Mercado(), finalR, finalC, matriz, matrizBotones);
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

    public static void main(String[] args) {
        Main main = new Main();
    }
}
