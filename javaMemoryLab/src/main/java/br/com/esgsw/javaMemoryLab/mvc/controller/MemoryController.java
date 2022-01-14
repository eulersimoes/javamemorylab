/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.esgsw.javaMemoryLab.mvc.controller;

import static br.com.esgsw.javaMemoryLab.mvc.util.MemoryLabUtil.RegistrarMemoriaUtilizada;
import static br.com.esgsw.javaMemoryLab.mvc.util.MemoryLabUtil.WriteObjectToFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Euler
 */
@Controller
@RequestMapping("/")
public class MemoryController {

    @Value("${spring.application.name}")
    String appName;
    private static final Logger logger = LoggerFactory.getLogger(MemoryController.class);
    Runtime rt = Runtime.getRuntime();

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/criarlista")
    public void criarLista() {
        List<String> lista = new ArrayList<String>();
        logger.info("============================================");
        RegistrarMemoriaUtilizada();
        for (int i = 0; i < 90000; i++) {
            logger.info("Execucao: "+i);
            byte[] array = new byte[9000]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array, Charset.forName("UTF-8"));
            lista.add(generatedString);
            RegistrarMemoriaUtilizada();
            if(((rt.totalMemory() - rt.freeMemory()) / 1024 / 1024) >= 400 )
            {
                WriteObjectToFile(lista);
                lista.clear();
            }
        }
        logger.info("============================================");
        RegistrarMemoriaUtilizada();
    }
}
