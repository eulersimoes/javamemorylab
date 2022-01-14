/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.esgsw.javaMemoryLab.mvc.util;

import br.com.esgsw.javaMemoryLab.mvc.controller.MemoryController;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Euler
 */
public class MemoryLabUtil {

    private static final Logger logger = LoggerFactory.getLogger(MemoryLabUtil.class);
    static Runtime rt = Runtime.getRuntime();

    public static void RegistrarMemoriaUtilizada() {
        long usedMB = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
        logger.info("memory usage: " + usedMB + " Mb");
    }
    
    public static void WriteObjectToFile(Object serObj) {
 
        String filepath = "C:\\Temp\\outfile" + new Random().toString() + ".tmp";
        try {
            logger.info("Escrevendo objeto para o disco !");
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("Objecto foi escrito com sucesso !");
         } catch (Exception ex) {
             System.out.println(ex.getMessage());
             ex.printStackTrace();
        }
    }

}
