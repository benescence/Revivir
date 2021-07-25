package com.ungs.revivir.negocios.manager;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.entidades.PDF;
import com.ungs.revivir.persistencia.interfaces.PDFOBD;

public class PDFManager {

	public static void guardarPdf(PDF archivo){
		PDFOBD obd = FactoryOBD.crearPDFOBD();
		obd.insert(archivo);
	}
	
	public static PDF crearPdf(File archivo){
		PDF contenido = new PDF();
		OBD odb = new OBD();
		if (odb.selectLastID("formar_archivos")==null){
			contenido.setContenidoID(1);
		} else {
			contenido.setContenidoID(odb.selectLastID("formar_archivos")+1);
		}
		contenido.setNombrePDF(archivo.getName());
		try {
            byte[] pdf = new byte[(int) archivo.length()];
            InputStream input = new FileInputStream(archivo);
            input.read(pdf);
            contenido.setArchivoPDF(pdf);
            input.close();
        } catch (IOException ex) {
            contenido.setArchivoPDF(null);
            //System.out.println("Error al agregar archivo pdf "+ex.getMessage());
        }
		odb.desconectar();
		return contenido;
	}
	
	public static void editarPdf(PDF archivo){
		PDFOBD obd = FactoryOBD.crearPDFOBD();
		obd.update(archivo);
	}
	
	public static void eliminarPdf(PDF archivo){
		PDFOBD obd = FactoryOBD.crearPDFOBD();
		obd.delete(archivo);
	}
	
	public static void abrirPdf(Integer id){
		PDFOBD obd = FactoryOBD.crearPDFOBD();
		String nombrepdf = obd.abrir(id);
		try {
			String path = System.getProperty("java.io.tmpdir")+File.separatorChar+nombrepdf;
            Desktop.getDesktop().open(new File(path));
        } catch (Exception ex) {
        }

	}
	
	public static PDF traerPdfByID(Integer contenido) {
		PDFOBD obd = FactoryOBD.crearPDFOBD();
		return obd.traerPdf(contenido);
	}

}