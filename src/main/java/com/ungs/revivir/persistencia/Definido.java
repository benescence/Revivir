package com.ungs.revivir.persistencia;

import com.ungs.revivir.persistencia.definidos.Rol;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;

public class Definido {
	
	public static Integer tipoFallecimiento(TipoFallecimiento tipo) {
		Integer ret = null;
		if (tipo == TipoFallecimiento.TRAUMATICO)
			ret = 1;
		else if (tipo == TipoFallecimiento.NO_TRAUMATICO)
			ret = 2;
		return ret;
	}

	public static TipoFallecimiento tipoFallecimiento(Integer tipo) {
		TipoFallecimiento ret = null;
		if (tipo == 1)
			ret = TipoFallecimiento.TRAUMATICO;
		else if (tipo == 2)
			ret = TipoFallecimiento.NO_TRAUMATICO;
		return ret;
	}

	public static Integer subsector(SubSector subsector) {
		Integer ret = null;
		
		if (subsector == SubSector.ADULTOS)
			ret = 1;
		else if (subsector == SubSector.ANGELITOS)
			ret = 2;
		else if (subsector == SubSector.COMPRADA)
			ret = 3;
		else if (subsector == SubSector.INDIGENTES)
			ret = 4;
		else if (subsector == SubSector.PALMERAS_ATAUD)
			ret = 5;
		else if (subsector == SubSector.PALMERAS_CENIZAS)
			ret = 6;
		else if (subsector == SubSector.PALMERAS_RESTOS)
			ret = 7;
		else if (subsector == SubSector.PALMERAS_SEPULTURAS)
			ret = 8;
		else if (subsector == SubSector.NICHERA)
			ret = 9;
		else if (subsector == SubSector.CENIZARIO)
			ret = 10;
		else if (subsector == SubSector.BOVEDA)
			ret = 11;
		else if (subsector == SubSector.DEPOSITO1)
			ret = 12;
		else if (subsector == SubSector.DEPOSITO2)
			ret =13;
		else if (subsector == SubSector.DEPOSITO3)
			ret = 14;
		else if (subsector == SubSector.OTRO_CEMENTERIO)
			ret = 15;
		return ret;
	}
	
	public static SubSector subsector(Integer subsector) {
		SubSector ret = null;
		
		if (subsector == 1)
			ret = SubSector.ADULTOS;
		else if (subsector == 4)
			ret = SubSector.INDIGENTES;
		else if (subsector == 2)
			ret = SubSector.ANGELITOS;
		else if (subsector == 3)
			ret = SubSector.COMPRADA;
		else if (subsector == 5)
			ret = SubSector.PALMERAS_ATAUD;
		else if (subsector == 6)
			ret = SubSector.PALMERAS_CENIZAS;
		else if (subsector == 7)
			ret = SubSector.PALMERAS_RESTOS;
		else if (subsector == 8)
			ret = SubSector.PALMERAS_SEPULTURAS;
		else if (subsector == 9)
			ret = SubSector.NICHERA;
		else if (subsector == 10)
			ret = SubSector.CENIZARIO;
		else if (subsector == 11)
			ret = SubSector.BOVEDA;
		else if (subsector == 12)
			ret = SubSector.DEPOSITO1;
		else if (subsector == 13)
			ret = SubSector.DEPOSITO2;
		else if (subsector == 14)
			ret = SubSector.DEPOSITO3;
		else if (subsector == 15)
			ret = SubSector.OTRO_CEMENTERIO;
		return ret;
	}
	
	public static Integer rol(Rol rol) {
		Integer ret = null;
		if (rol == Rol.ADMINISTRATIVO)
			ret = 2;
		else if (rol == Rol.SUPERVISOR)
			ret = 1;
		return ret;
	}

	public static Rol rol(Integer rol) {
		Rol ret = null;
		if (rol == 2)
			ret = Rol.ADMINISTRATIVO;
		else if (rol == 1)
			ret = Rol.SUPERVISOR;
		return ret;
	}

}