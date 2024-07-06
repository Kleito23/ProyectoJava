package ec.edu.espol.Clases;
public class CartaNumerica extends Carta {
    public CartaNumerica(Color color, String valor) {
        super(color,valor);

    }

    @Override
    public String toString(){
        return super.toString() + "," + valor +")";
    }
}