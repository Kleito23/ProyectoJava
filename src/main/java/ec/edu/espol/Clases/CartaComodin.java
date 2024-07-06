package ec.edu.espol.Clases;

public class CartaComodin extends Carta {

    public CartaComodin(Color color, String valor) {
        super(color,valor);
    }


    @Override
    public String toString() {
        return super.toString() + ","+ valor +")";
    }
    
    
}

