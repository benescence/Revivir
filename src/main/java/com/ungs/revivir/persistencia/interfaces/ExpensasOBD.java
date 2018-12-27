package com.ungs.revivir.persistencia.interfaces;

import java.util.Date;
import java.util.List;

import com.ungs.revivir.persistencia.entidades.Expensas;

public interface ExpensasOBD {

	
		public void insert(Expensas expensas);
		
		public void update(Expensas expensas);
		
		public void delete(Expensas expensas);

		public List<Expensas> select();
		
		public Expensas  selectByID(Integer iD);
		
		public Expensas  ultimoInsertado();
		

		public 	List<Expensas>  selectByFechaVencimiento(Date fecha_vencimiento);
		public  List<Expensas>  selectByResponsable(Integer responsable);
		public  List<Expensas>  selectByUbicacion(Integer ubicacion);

	
}
