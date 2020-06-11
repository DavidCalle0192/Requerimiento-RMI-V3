/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorAlertas.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JhonMZ
 */
public class ArchivoDAO {
    
    /**
     * @brief Se encarga de crear un archivo en la path especificado
     * @param pathName ruta y nombre del archivo
     * @return retorna true si el archivo se crea o false si el 
     * archivo ya existe o ocurre un error
     */
    public static boolean crearArchivo(String pathName){
         boolean res = false;

        File file = new File(pathName);
        try {
            if(file.createNewFile())res=true;
        } catch (IOException ex) {
            res = false;
            Logger.getLogger(HistorialAlertaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public static boolean existeArchivo(String pathName){
        File file = new File(pathName);
        //System.out.println(file.getAbsolutePath());
        return file.exists();
    }
    
    public static boolean  agregarLinea(String pathName, String linea){
        boolean res = false;
        
        if(!existeArchivo(pathName))crearArchivo(pathName);
        
        File file = new File(pathName);
        try {
            FileWriter fw = new FileWriter(file,true);
            fw.write(linea+"\n");
            fw.close();
            res = true;
        } catch (IOException ex) {
            Logger.getLogger(ArchivoDAO.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }
        
        return res;
    }
    
    public static ArrayList<String> obtenerLineas(String pathName){
        ArrayList<String> lineas = new ArrayList<>();
        File file = new File(pathName);
        
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
               lineas.add(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lineas;
    }
}
