package com.ungs.revivir.persistencia.entidades;

public class PDF {

   private Integer contenidoID;
   private String nombrePDF;
   private byte[] archivoPDF;

    public PDF() {
    	
    }

    public Integer getContenidoID() {
        return contenidoID;
    }

    public String getNombrePDF() {
        return nombrePDF;
    }

    public byte[] getArchivoPDF() {
        return archivoPDF;
    }

    public void setContenidoID(Integer codigopdf) {
        this.contenidoID = codigopdf;
    }

    public void setNombrePDF(String nombrepdf) {
        this.nombrePDF = nombrepdf;
    }

    public void setArchivoPDF(byte[] archivopdf) {
        this.archivoPDF = archivopdf;
    }

}