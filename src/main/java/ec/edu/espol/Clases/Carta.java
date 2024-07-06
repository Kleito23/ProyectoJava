package ec.edu.espol.Clases;

import java.util.ArrayList;

public abstract class Carta {
    enum Color {R,A,V,Z,N}
    private static ArrayList<String> comodines;
    protected Color color;
    protected String valor;

    public Carta(Color color, String valor) {
        this.color = color;
        this.valor = valor;
        comodines = new ArrayList<>();
        setComodines();
    }

    public Color getColor() {
        return color;
    }
    public String getValor() {
        return valor;
    }

    private void setComodines(){
        comodines.add("^");
        comodines.add("&");
        comodines.add("%");
        comodines.add("+4");
        comodines.add("+2");
    }

    public static ArrayList<String> getComodines() {
        return comodines;
    }

    @Override
    public String toString() {
        return "("+ color;
    }

}
