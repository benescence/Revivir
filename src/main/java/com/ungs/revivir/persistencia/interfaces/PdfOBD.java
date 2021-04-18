package com.ungs.revivir.persistencia.interfaces;

import com.ungs.revivir.persistencia.entidades.Pdf;

public interface PdfOBD {
	
	public void insert (Pdf fallecido);
	
	public void update (Pdf fallecido);
	
	public void delete (Pdf fallecido);
	
	public String abrir (Integer id);
	
	public Pdf traerPdf(Integer id);

}
