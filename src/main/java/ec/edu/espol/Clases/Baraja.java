package ec.edu.espol.Clases;

import java.util.ArrayList;
import java.util.Collections;

import ec.edu.espol.Clases.Carta.Color;

public class Baraja {
    private ArrayList<Carta> cartas;

    public Baraja(){
        cartas = new ArrayList<>();
        inicializarBaraja();
        mezclarCartas();
    }

    public void inicializarBaraja(){
        //Generar las cartas numericas 0-9 para cada color
        for(Color color : Carta.Color.values()){
            if(color != Color.N){
                for(int i = 0; i <= 9; i++){
                    Carta c = new CartaNumerica(color, i+"");
                    cartas.add(c);
                }
            }

            for(String v : Carta.getComodines()){
                if(color != Color.N){
                    if(v.equals("%")== false){
                        Carta c1 = new CartaComodin(color, v);
                        Carta c2 = new CartaComodin(color, v);
                        cartas.add(c1);
                        cartas.add(c2);
                    }
                }else{
                    if(v.equals("%") || v.equals("+4") || v.equals("+2") ){
                        Carta c1 = new CartaComodin(color, v);
                        Carta c2 = new CartaComodin(color, v);
                        cartas.add(c1);
                        cartas.add(c2);
                    }
                }
            }
        }

        
    }

    public ArrayList<Carta> getCartas(){
        return cartas;
    }

    public void mezclarCartas(){
        Collections.shuffle(cartas);
    }

    public Carta getCarta(int i){
        return cartas.get(i);
    }
    public void eliminarCarta(Carta c){
        cartas.remove(c);
    }

}