package ec.edu.espol;

import ec.edu.espol.Clases.Juego;
import ec.edu.espol.Clases.Jugador;

public class Main {
    public static void main(String[] args) {
        Jugador jugador = new Jugador("Kleber");
        Juego juego = new Juego(jugador);

        juego.iniciarJuego();
    }
}