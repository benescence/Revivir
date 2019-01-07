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
		
		else if (sector == Sector.CEMENTERIO)
			ret.add(SubSector.CEMENTERIO);
		
		return ret;
	}
	
}