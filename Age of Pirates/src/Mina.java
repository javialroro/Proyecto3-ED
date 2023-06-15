import java.awt.*;

public class Mina extends Fabrica{
    Thread threadMinita;
    int aceroGenerado= 0;
    int y = 0;

    Mina(){

        super();
        color = Color.green;

        threadMinita = new Thread(new Runnable() {
            @Override
            public void run() {
                int con = 0;
                while(true){
                    try {
                        Thread.sleep(1000);
                        con++;
                        System.out.println(con +" mina " + y);
                        if(con == 2){
                            con = 0;
                            aceroGenerado += 50;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        vida = 2;

    }

    public void iniciarThread() {
        threadMinita.start();

    }

}

