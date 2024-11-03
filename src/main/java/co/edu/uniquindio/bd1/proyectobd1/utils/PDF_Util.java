package co.edu.uniquindio.bd1.proyectobd1.utils;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.FileOutputStream;

public class PDF_Util {

    public static void generarPDF(String html,String ruta,String nombre) {
        // Generar el PDF
        ruta+="/"+nombre;
        try (FileOutputStream fos = new FileOutputStream(ruta)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null); // Agregar el contenido HTML
            builder.toStream(fos); // Salida al archivo
            builder.run(); // Generar el PDF
            System.out.println("PDF generado con éxito: " + ruta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
