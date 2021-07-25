package com.ungs.revivir.persistencia.interfaces;

import com.ungs.revivir.persistencia.entidades.PDF;

public interface PDFOBD {
	
	public void insert(PDF fallecido);
	
	public void update(PDF fallecido);
	
	public void delete(PDF fallecido);
	
	public String abrir(Integer id);
	
	public PDF traerPdf(Integer id);

}