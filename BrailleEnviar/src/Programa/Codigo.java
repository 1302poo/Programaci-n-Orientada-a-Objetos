package Programa;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class Codigo implements Interface {

    public void texto(String txt) {
        this.caracter = txt;
    }

    private String caracter;

    @Override
    public void Creacion(String txt) {
        Document Doc = new Document();
        try {
            PdfWriter.getInstance(Doc, new FileOutputStream(txt));
            Doc.open();
            Paragraph parrafoTitulo = new Paragraph("Sistema Braille");
            parrafoTitulo.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
            Doc.add(parrafoTitulo);
            int var1 = 30;
            int var2 = 650;
            File miDir = new File(".");
            String Ruta = "";
            try {
                Ruta = miDir.getCanonicalPath() + "/src/Imagenes/";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se encontro la imagen", "Información", JOptionPane.ERROR_MESSAGE);
            }
            String ect = "";
            Paragraph parrafoOriginal = new Paragraph("\nTexto " + caracter + "\nBraille");
            parrafoOriginal.setAlignment(Element.ALIGN_LEFT);
            Doc.add(parrafoOriginal);
            boolean Verificador = true, Aux = true;
            for (int i = 0; i < caracter.length(); i++) {
                if (caracter.charAt(i) == ' ') {
                    var2 = var2 - 70;
                    var1 = 30;
                }

                Image imagen = Image.getInstance(Ruta + ".jpg");
                
                if (caracter.charAt(i) != ' ') {
                    if ((caracter.charAt(i) == '0') || (caracter.charAt(i) == '1')
                            || (caracter.charAt(i) == '2') || (caracter.charAt(i) == '3')
                            || (caracter.charAt(i) == '4') || (caracter.charAt(i) == '5')
                            || (caracter.charAt(i) == '6') || (caracter.charAt(i) == '7')
                            || (caracter.charAt(i) == '8') || (caracter.charAt(i) == '9')) {
                        Verificador = true;
                        if (Aux == true) {
                            imagen = Image.getInstance(Ruta + "#.jpg"); //idetifica numeros
                            imagen.scaleAbsoluteWidth(imagen.getWidth() / 3);
                            imagen.scaleAbsoluteHeight(imagen.getHeight() / 3);
                            var1 += (imagen.getWidth() / 3) + 5;
                            imagen.setAbsolutePosition(var1, var2);
                            Doc.add(imagen);
                            Aux = false;
                        }
                    }
                    if ((caracter.charAt(i) != '0') && (caracter.charAt(i) != '1') 
                            && (caracter.charAt(i) != '2') && (caracter.charAt(i) != '3') 
                            && (caracter.charAt(i) != '4') && (caracter.charAt(i) != '5')
                            && (caracter.charAt(i) != '6') && (caracter.charAt(i) != '7') 
                            && (caracter.charAt(i) != '8') && (caracter.charAt(i) != '9')) {
                        Aux = true;
                        if (Verificador == true) {
                            imagen = Image.getInstance(Ruta + "##.jpg");//idetifica alfabeto
                            imagen.scaleAbsoluteWidth(imagen.getWidth() / 3);
                            imagen.scaleAbsoluteHeight(imagen.getHeight() / 3);
                            var1 += (imagen.getWidth() / 3) + 5;
                            imagen.setAbsolutePosition(var1, var2);
                            Doc.add(imagen);
                            Verificador = false;
                        }
                    }
                    imagen = Image.getInstance(Ruta + caracter.charAt(i) + ".jpg");
                    imagen.scaleAbsoluteWidth(imagen.getWidth() / 3);
                    var1 += (imagen.getWidth() / 3) + 5;
                    imagen.scaleAbsoluteHeight(imagen.getHeight() / 3);
                    imagen.setAbsolutePosition(var1, var2);
                    Doc.add(imagen);
                }
            }
            Doc.close();
        } catch (DocumentException | IOException ex) {
        }
    }
    
    public void Abrir(String archivo) {
        try {
            File objetofile = new File(archivo);
            Desktop.getDesktop().open(objetofile);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
