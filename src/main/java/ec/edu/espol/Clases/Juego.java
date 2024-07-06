package ec.edu.espol.Clases;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import ec.edu.espol.Clases.Carta.Color;

public class Juego {
    private Baraja baraja;
    private Jugador jugador;
    private ArrayList<Carta> manoMaquina;
    private ArrayList<Carta> lineaJuego;
    private static Color color;
    private static boolean turno = true;

    public Juego(Jugador jugador){
        baraja = new Baraja();
        this.jugador = jugador;
        manoMaquina = new ArrayList<>();
        lineaJuego = new ArrayList<>();
    }


    private Carta tomarCartaAzar(){
        Random random = new Random();
        Carta c;
        do{
            c = baraja.getCarta(random.nextInt(baraja.getCartas().size()));
        }while(c instanceof CartaComodin);
        return c;
    }

    private void imprimirManoMaquina(){
        String p = "Mano de maquina -> ";
        int contador = 1;
        for(Carta c : manoMaquina){
            p += c;
            if(contador < manoMaquina.size()){
                p+= " - ";
                contador++;
            }
        }
        System.out.println(p);
    }

    private void repartirCartas(){
        int cont = 0;
        Random r = new Random();
        while(cont < 7){
            Carta c = baraja.getCarta(r.nextInt(baraja.getCartas().size()));
            jugador.addCarta(c);
            baraja.eliminarCarta(c);
            
            Carta c2 = baraja.getCarta(r.nextInt(baraja.getCartas().size()));
            manoMaquina.add(c2);
            baraja.eliminarCarta(c2);

            cont++;
        }
        
    }

    private void mostrarLinea(){
        String p = "Linea de juego -> ";
        int contador = 1;
        for(Carta c : lineaJuego){
            p += c;
            if(contador < lineaJuego.size()){
                p += " - ";
                contador++;
            }
        }
        System.out.println(p);

    }

    private void agregarCartaMano(int i, ArrayList<Carta> cartas){
        for(int j = 0; j < i; j++){
            cartas.add(baraja.getCartas().get(j));
            baraja.getCartas().remove(j);
        }
    }

    public boolean agregarCartaLinea(Carta c, Scanner sc, Boolean maquina){
        if(c == null){
            return false;
        }
        if(maquina){
            System.out.println("La maquina escogió:" + c);
        }
        if(c instanceof CartaNumerica){
            if(c.getColor() == color || c.getValor().equals(lineaJuego.get(0).getValor())){
                lineaJuego.add(0,c);
                if(maquina){
                    manoMaquina.remove(c);
                }else{
                    jugador.getCartas().remove(c);
                }
                color = c.getColor();
                
                return true;
            }else{
                return false;
            }
        }else{

            if(maquina){
                if(c.getColor() == color || c.getColor() == Color.N){
                    Random random = new Random();
                    if(c.getColor() == Color.N){
                        do{
                            Color[] colores = Color.values();
                            Color col = colores[random.nextInt(colores.length-1)];
                            color = col;
                        }while(color == Color.N);
                        
                        System.out.println("La maquina escogio el color: " + color);
                        if(c.getValor().equals("+2")){
                            agregarCartaMano(2,jugador.getCartas());
                            System.out.println("Se te agregó dos cartas a la mano!");
                            
                        }else if(c.getValor().equals("+4")){
                            agregarCartaMano(4, jugador.getCartas());
                            System.out.println("Se te agregó cuatro cartas a la mano!");
                            
                        }
                    }else{
                        if(c.getValor().equals("+2")){
                            agregarCartaMano(2,jugador.getCartas());
                            System.out.println("Se te agregó dos cartas a la mano!");
                            
                        }else if(c.getValor().equals("+4")){
                            agregarCartaMano(4, jugador.getCartas());
                            System.out.println("Se te agregó cuatro cartas a la mano!");
                            
                        }else if(c.getValor().equals("^") || c.getValor().equals("&") ){
                            turno = false;
                            System.out.println("Perdiste el turno!");
                        }
                        color = c.getColor();
                    }
                }else{
                    return false;
                }
                lineaJuego.add(0,c);
                manoMaquina.remove(c);
            }else{
                if(c.getColor() == color || c.getColor() == Color.N){
                    if(c.getColor() == Color.N){
                        System.out.println("Ingrese el color que desea (R,A,V,Z): ");
                        String col = sc.nextLine();
                        color = Color.valueOf(col.toUpperCase());

                        System.out.println("El color ha cambiado a: " + color);
                        if(c.getValor().equals("+2")){
                            agregarCartaMano(2, manoMaquina);
                            System.out.println("Se ha agregado dos cartas al oponente");
                        }else if(c.getValor().equals("+4")){
                            agregarCartaMano(4, manoMaquina);
                            System.out.println("Se ha agregado cuatro cartas al oponente!");
                        }
                    }else{
                        if(c.getValor().equals("+2")){
                            agregarCartaMano(2, manoMaquina);
                            System.out.println("Se ha agregado dos cartas al oponente");
                        }else if(c.getValor().equals("+4")){
                            agregarCartaMano(4, manoMaquina);
                            System.out.println("Se ha agregado cuatro cartas al oponente!");
                        }else if(c.getValor().equals("^") || c.getValor().equals("&") ){
                            turno = false;
                            System.out.println("La maquina pierde el turno!");
                        }
                        color = c.getColor();
                    }
                }else{
                    return false;
                }
                lineaJuego.add(0,c);
                jugador.getCartas().remove(c);
            }
            return true;
        }
    }

    public void reiniciarBaraja(){
        if(baraja.getCartas().size() <= 10){
            for(int i= 1; i < lineaJuego.size();){
                baraja.getCartas().add(lineaJuego.get(i));
                lineaJuego.remove(i);
            }
            baraja.mezclarCartas();
            Utilitaria.esperar(1);
            System.out.println("Se han tomado las cartas de la linea de juego ya que estaba por terminarse las cartas en la baraja!");
            Utilitaria.esperar(1);
            System.out.println("Se han mezclado las cartas de la baraja!");
        }
    }

    public void iniciarJuego(){
        repartirCartas();
        System.out.println("Se han repartido las cartas!");
        lineaJuego.add(tomarCartaAzar());
        color = lineaJuego.get(0).getColor();
        System.out.println("Se ha puesto una carta al azar en la linea de juego!");
        boolean bandera = true;
        boolean repetir = false;
        Scanner sc = new Scanner(System.in);


        while(bandera){
            if(turno){
                do{
                    Utilitaria.esperar(1);
                    jugador.imprimirMano();
                    mostrarLinea();
                    Utilitaria.esperar(1);
                    System.out.println("Indique el indice de la carta que desea jugar: ");
                    int i = sc.nextInt();
                    sc.nextLine();
                    Carta c = jugador.getCarta(i);
                    boolean continuar = agregarCartaLinea(c, sc, false);
                    Utilitaria.esperar(1);
                    if(continuar == false){
                        System.out.println("Error, el color o numero no coincide. Intentar de nuevo? (Si/No)");
                        String r = sc.nextLine();
                        if(r.equalsIgnoreCase("si")){
                            repetir = true;
                        }else{
                            jugador.getCartas().add(baraja.getCarta(0));
                            baraja.getCartas().remove(0);
                            System.out.println("Se ha agregado una carta a tu mano!");
                            repetir = false;
                        }
                    }else{
                        repetir = false;
                    }
                }while(repetir);
                if(jugador.getCartas().size() == 1){
                    System.out.println("UNO!");
                }else if(jugador.getCartas().size() == 0){
                    System.out.println("Ganaste!");
                    bandera = false;
                    turno = false;
                }
                System.out.println("");
            }else{
                turno = true;
            }
            reiniciarBaraja();
            if(turno){
                Utilitaria.esperar(1);
                imprimirManoMaquina();
                mostrarLinea();
                Carta c = manoMaquina.get(0);
                boolean continuar = agregarCartaLinea(c, sc, true);
                Utilitaria.esperar(1);
                if(continuar==false){
                    System.out.println("Error, el color o numero no coincide. Se ha agregado una carta al mazo de la maquina!");
                    manoMaquina.add(0,baraja.getCarta(0));
                    baraja.getCartas().remove(0);
                }
                if(manoMaquina.size()== 1){
                    System.out.println("UNO!");
                }else if(manoMaquina.size() == 0){
                    System.out.println("Gano la máquina!");
                    bandera = false;
                    turno = false;
                }
                System.out.println("");
            }else{
                turno = true;
            }
            Utilitaria.esperar(1);


        }
        sc.close();
    }

    
}
