package com.ungs.revivir.persistencia.entidades;

public class Pdf {

    /*Todo los atributos*/
   private Integer contenido_ID;
   private String nombrepdf;
   private byte[] archivopdf;

    public Pdf() {
    	
    }

    /*Todo los codigos get*/
    public Integer getContenidoID() {
        return contenido_ID;
    }

    public String getNombrepdf() {
        return nombrepdf;
    }

    public byte[] getArchivopdf() {
        return archivopdf;
    }


    /*Todo los codigos set*/
    public void setContenidoID(Integer codigopdf) {
        this.contenido_ID = codigopdf;
    }

    public void setNombrepdf(String nombrepdf) {
        this.nombrepdf = nombrepdf;
    }

    public void setArchivopdf(byte[] archivopdf) {
        this.archivopdf = archivopdf;
    }

}