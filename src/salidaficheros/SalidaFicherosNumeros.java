package salidaficheros;

import java.io.*;

public class SalidaFicherosNumeros {

    static final int[] numeros;
    
    static {
        numeros = new int[]{100, 2123, 55, 199, 22};
    }
    
    public void generarTexto() {
        PrintWriter fOut = null;
        try {
            fOut =  new PrintWriter("numeros.txt");
            for (int i: numeros) {
                fOut.println(i);;
            }
        } catch (IOException ex) {
            System.err.println("No se pudo grabar el fichero");
            ex.printStackTrace();
        } finally {
            if (fOut != null) {
                fOut.close();
            }
        }
                
    }
    
     public void generarBinario() {
        RandomAccessFile fOut = null;
        try {
            fOut =  new RandomAccessFile("numeros.bin", "rw");
            for (int i: numeros) {
                fOut.writeInt(i);
            }
        } catch (IOException ex) {
            System.err.println("No se pudo grabar el fichero");
            ex.printStackTrace();
        } finally {
           try {fOut.close();} catch (Exception ex) {};
        }
                
    }
       
    public void run() {
        generarTexto();
        generarBinario();
    }
    
    public static void main(String[] args) {
        new SalidaFicherosNumeros().run();
    }
    
}
