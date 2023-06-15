import java.awt.*;

public class Armeria extends Fabrica{

    int arma = 0;
    public Armeria(int x){
        super();
        arma = x;
        if(x == 1){

            color = Color.pink;
        }
        else if(x == 2){
            color = Color.red;
        }
        else if(x == 3){

            color = Color.lightGray;
        }
        else {
        color = Color.darkGray;
        }
    
    }
}
