package salidaficheros;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class SalidaFicherosAlumnos {

    static final Alumno[] alumnos;
    
    static {
        alumnos  = new Alumno[]{
            new Alumno("Pepe", 5.23),
            new Alumno("Lucas", 6.60),
            new Alumno("Carmela", 8.80),
            new Alumno("Lucrecia", 4.43),
            new Alumno("Marco Aurelio Encarnación de todos los Santos", 2.23),
            new Alumno("Margarita María", 4.2)
        };
    }
    
    public void generarTexto() {
        PrintWriter fOut = null;
        try {
            fOut =  new PrintWriter("alumnos.txt");
            for (Alumno a : alumnos) {
                fOut.format(Locale.ROOT, "%s, %.2f%n", a.getNombre(), a.getNota());;
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
   
         public void generarBinarioRandom() {
        RandomAccessFile fOut = null;
        try {
            fOut =  new RandomAccessFile("alumnos.rnd.bin", "rw");
            fOut.setLength(0);
            for (Alumno a : alumnos) {
                // Representar el nombre con 20 bytes
                byte[] buff = Arrays.copyOfRange(a.getNombre().getBytes(), 0, 20);
                fOut.write(buff);
                fOut.writeDouble(a.getNota()); // 8 bytes
            }
        } catch (IOException ex) {
            System.err.println("No se pudo grabar el fichero");
            ex.printStackTrace();
        } finally {
           try {fOut.close();} catch (Exception ex) {};
        }
                
    }
  
    
     public void generarBinarioSecuencial() {
        DataOutputStream fOut = null;
        try {
            fOut =  new DataOutputStream(new FileOutputStream("alumnos.seq.bin"));
            for (Alumno a : alumnos) {
                fOut.writeUTF(a.getNombre());
                fOut.writeDouble(a.getNota()); // 8 bytes
            }
        } catch (IOException ex) {
            System.err.println("No se pudo grabar el fichero");
            ex.printStackTrace();
        } finally {
           try {fOut.close();} catch (Exception ex) {};
        }
                
    }
   
     public void leerUnRegistro() {
        Scanner in = new Scanner(System.in);
        RandomAccessFile fIn = null;
        try {
            fIn =  new RandomAccessFile("alumnos.bin", "rw");
            System.out.print("Numero de registro: ");
            int numRegistro = Integer.parseInt(in.nextLine());
            fIn.seek(numRegistro * 28);
            byte[] buff = new byte[20];
            fIn.read(buff);
            String nombre = new String(buff);
            double nota = fIn.readDouble();
            Alumno a = new Alumno(nombre, nota);
            System.out.format("Nombre: %s - Nota: %.2f%n", a.getNombre(), a.getNota());

        } catch (IOException ex) {
            System.err.println("No se pudo leer");
            ex.printStackTrace();
        } finally {
           try {fIn.close();} catch (Exception ex) {};
        }
                
    }
     
    public void run() {
        generarTexto();
        generarBinarioSecuencial();
        generarBinarioRandom();
        leerUnRegistro();
    }
    
    public static void main(String[] args) {
        new SalidaFicherosAlumnos().run();
    }
    
}
