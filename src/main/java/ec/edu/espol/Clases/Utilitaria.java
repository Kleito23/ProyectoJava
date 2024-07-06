package ec.edu.espol.Clases;

public class Utilitaria {
    public static void esperar(int s){
        try{
            Thread.sleep(s*1000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

