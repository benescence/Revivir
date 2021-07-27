package com.ungs.revivir.persistencia.entidades;

public class PDF {

    /*Todo los atributos*/
   private Integer contenido_ID;
   private String nombrepdf;
   private byte[] archivopdf;

    public PDF() {
    	
    }

    /*Todo los codigos get*/
    public Integer getContenidoID() {
        return contenido_ID;
    }

    public String getNombrePDF() {
        return nombrepdf;
    }

    public byte[] getArchivoPDF() {
        return archivopdf;
    }


    /*Todo los codigos set*/
    public void setContenidoID(Integer codigopdf) {
        this.contenido_ID = codigopdf;
    }

    public void setNombrePDF(String nombrepdf) {
        this.nombrepdf = nombrepdf;
    }

    public void setArchivoPDF(byte[] archivopdf) {
        this.archivopdf = archivopdf;
    }

}