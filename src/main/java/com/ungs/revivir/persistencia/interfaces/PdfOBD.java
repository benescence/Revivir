package com.ungs.revivir.persistencia.interfaces;

import com.ungs.revivir.persistencia.entidades.Pdf;

public interface PdfOBD {
	
	public void insert (Pdf alumno);
	
	public void update (Pdf alumno);
	
	public void delete (Pdf alumno);
	
	public String abrir (Integer id);
	
	public Pdf traerPdf(Integer id);

}
