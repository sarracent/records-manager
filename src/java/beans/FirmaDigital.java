/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Runtime.getRuntime;
import static java.lang.System.out;

/**
 *
 * @author yohana
 */
public class FirmaDigital {

    public void firmar(String direccion) throws IOException {
         String s = null;
        
            out.println("C:\\GET\\Firmas\\informaticos.p12");

            Process p = getRuntime().exec("java -jar C:\\GET\\jsignpdf\\JSignPdf.jar -kst PKCS12 -ksf C:\\GET\\Firmas\\informaticos.p12 -ksp informaticos " + direccion+" "+"-d C:\\GET\\");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(
                    p.getErrorStream()));

            while ((s = stdInput.readLine()) != null) {
                out.println(s);
            }
     

    }
 

}
