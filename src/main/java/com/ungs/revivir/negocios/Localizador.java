package com.ungs.revivir.negocios;

import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.definidos.SubSector;

public class Localizador {
	
	public static List<Sector> traerSectores() {
		List<Sector> ret = new ArrayList<>();
		for (Sector sector : Sector.values())
			ret.add(sector);
		return ret;
	}
	
	public static List<SubSector> traerSubSectores(Sector sector) {
		List<SubSector> ret = new ArrayList<>();
		
		if (sector == Sector.SEPULTURAS) {
			ret.add(SubSector.COMPRADA);
			ret.add(SubSector.ANGELITOS);			
			ret.add(SubSector.ADULTOS);
			ret.add(SubSector.INDIGENTES);
		}
		
		else if (sector == Sector.PALMERAS) {
			ret.add(SubSector.PALMERAS_ATAUD);
			ret.add(SubSector.PALMERAS_CENIZAS);
			ret.add(SubSector.PALMERAS_RESTOS);
			ret.add(SubSector.PALMERAS_SEPULTURAS);
		}

		else if (sector == Sector.NICHERA) 
			ret.add(SubSector.NICHERA);
			
		else if (sector == Sector.DEPOSITO) {
			ret.add(SubSector.DEPOSITO1);
			ret.add(SubSector.DEPOSITO2);
			ret.add(SubSector.DEPOSITO3);
		}
		
		else if (sector == Sector.CENIZARIO)
			ret.add(SubSector.CENIZARIO);
		
		else if (sector == Sector.BOVEDA)
			ret.add(SubSector.BOVEDA);
		
		else if (sector == Sector.OTRO_CEMENTERIO)
			ret.add(SubSector.OTRO_CEMENTERIO);
		
		return ret;
	}
	
	public static Sector traerSector(SubSector subSector) {

		if (subSector == SubSector.COMPRADA
				|| subSector == SubSector.ADULTOS
				|| subSector == SubSector.ANGELITOS
				|| subSector == SubSector.INDIGENTES)
			return Sector.SEPULTURAS;

		if (subSector == SubSector.PALMERAS_ATAUD
				|| subSector == SubSector.PALMERAS_CENIZAS
				|| subSector == SubSector.PALMERAS_RESTOS
				|| subSector == SubSector.PALMERAS_SEPULTURAS)
			return Sector.PALMERAS;
		
		if (subSector == SubSector.NICHERA)
			return Sector.NICHERA;

		if (subSector == SubSector.DEPOSITO1
				|| subSector == SubSector.DEPOSITO2
				|| subSector == SubSector.DEPOSITO3)
			return Sector.DEPOSITO;

		if (subSector == SubSector.CENIZARIO)
			return Sector.CENIZARIO;
		
		if (subSector == SubSector.BOVEDA)
			return Sector.BOVEDA;

		if (subSector == SubSector.OTRO_CEMENTERIO)
			return Sector.OTRO_CEMENTERIO;
		
		return null;
	}
	
}