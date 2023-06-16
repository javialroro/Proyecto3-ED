import java.awt.*;
import java.util.Random;
public class Templo extends Fabrica{

    Thread threadTemplo;
    int comodin = 0;
    int uso = 0;
    boolean hecho;
    public Templo(){
        super();
        ancho = 1;
        alto = 1;
        color = Color.ORANGE;
        vida = 2;

        threadTemplo = new Thread(new Runnable() {
            @Override
            public void run() {
                int con = 0;

                while(true){
                    try {
                        con++;
                        Thread.sleep(1000);
                        System.out.println(con +" templo ");
                        if(con == 5){
                            con = 0;

                            Random random = new Random();
                            int numero = random.nextInt(2) + 1;
                            hecho = true;

                            if (numero == 1){
                                System.out.println("se generó un escudo");
                                comodin = 1;

                            }
                            else{
                                System.out.println("se generó un kraken");
                                comodin = 2;
                            }
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void iniciarThreadT() {
        threadTemplo.start();

    }
}
