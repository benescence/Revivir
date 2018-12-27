package com.ungs.revivir.negocios;

import java.sql.Date;
import java.util.Calendar;

public class Almanaque {
	
	public static Date hoy() {
		return new Date(new java.util.Date().getTime());
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	public static Date normalizar(Date fecha) {
		Calendar cal = Calendar.getInstance();
	    cal.set( cal.YEAR, fecha.getYear() );
	    cal.set( cal.MONTH, fecha.getMonth());
	    cal.set( cal.DATE, fecha.getDate());
	    cal.set( cal.HOUR_OF_DAY, 0 );
	    cal.set( cal.MINUTE, 0 );
	    cal.set( cal.SECOND, 0 );
	    cal.set( cal.MILLISECOND, 0 );
	    
	    return new Date(cal.getTime().getTime());
	}
	
}