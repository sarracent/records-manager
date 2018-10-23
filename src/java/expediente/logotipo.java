/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import static com.itextpdf.text.Image.getInstance;
import java.io.IOException;

/**
 *
 * @author haroldg
 */
public class logotipo {
    
    public Image logo() throws BadElementException, IOException{

         Image logo = getInstance("C:\\GET\\Recursos\\logo.png");
            logo.setAbsolutePosition(500f, 750f);
            logo.scaleAbsolute(90, 70);

        return logo;

    }
    
     public Image logoincidencia() throws BadElementException, IOException{

         Image logo = getInstance("C:\\GET\\Recursos\\logo.png");
            logo.setAbsolutePosition(0, 750f);
            logo.scaleAbsolute(90, 70);

        return logo;

    }
    
}
