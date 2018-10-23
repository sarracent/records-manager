/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import static beans.util.JsfUtil.addErrorMessage;
import static beans.util.JsfUtil.addSuccessMessage;
import java.io.File;
import java.io.IOException;
import static java.util.ResourceBundle.getBundle;
import static org.apache.commons.io.FileUtils.forceDelete;
import static org.apache.commons.io.FileUtils.moveFile;

/**
 *
 * @author harold
 */
public class Firmar {
    public void firmarPDF(Computadora c) {

        try {
           
            FirmaDigital firma = new FirmaDigital();
            firma.firmar("C:\\GET\\expedientes\\" + c.getNombreDireccion().getNombre() + "\\" + c.getNombrePc() + "\\" + c.getNombrePc() + ".pdf");
            File f1 = new File("C:\\GET\\expedientes\\" + c.getNombreDireccion().getNombre() + "\\" + c.getNombrePc() + "\\" + c.getNombrePc() + ".pdf");
            File f2 = new File("C:\\GET\\" + c.getNombrePc() + "_signed.pdf");
            File f3 = new File("C:\\GET\\expedientes\\" + c.getNombreDireccion().getNombre() + "\\" + c.getNombrePc() + "\\" + c.getNombrePc() + ".pdf");
            System.out.println("C:\\GET\\expedientes\\" + c.getNombreDireccion().getNombre() + "\\" + c.getNombrePc() + "\\" + c.getNombrePc() + ".pdf");

            if (f2.exists()) {
                forceDelete(f3);
                moveFile(f2, f1);
                addSuccessMessage(getBundle("/Bundle").getString("Computadorafirmada"));
            } else {
                System.out.println("C:\\GET\\expedientes\\" + c.getNombreDireccion().getNombre() + "\\" + c.getNombrePc() + "\\" + c.getNombrePc() + ".pdf");
                //forceDelete(f2);
                addErrorMessage(getBundle("/Bundle").getString("Computadoranofirmada"));
            }

        } catch (IOException ex) {
            addErrorMessage(getBundle("/Bundle").getString("Computadoranofirmada"));
        }

    }
}
