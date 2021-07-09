package com.ungs.revivir.persistencia.mysql;

import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.entidades.Pdf;
import com.ungs.revivir.persistencia.interfaces.PDFOBD;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PDFOBDMySQL  extends OBD implements PDFOBD{

	public Pdf traerPdf(Integer id) {
		Pdf ret = new Pdf();
		String sql = "SELECT * FROM formar_archivos WHERE (ID = "+id+");";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = getConexion().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ret.setContenidoID(rs.getInt(1));
				ret.setNombrepdf(rs.getString(2));
				ret.setArchivopdf(rs.getBytes(3));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				ps.close();
				rs.close();
				desconectar();
			} catch (Exception ex) {
			}
		}
		return ret;
	}
	
    /*Metodo listar*/
    public ArrayList<Pdf> Listar_Pdf() {
        ArrayList<Pdf> list = new ArrayList<Pdf>();
        
        String sql = "SELECT * FROM formar_archivos";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pdf pdf = new Pdf();
                pdf.setContenidoID(rs.getInt(1));
                pdf.setNombrepdf(rs.getString(2));
                pdf.setArchivopdf(rs.getBytes(3));
                list.add(pdf);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                desconectar();
            } catch (Exception ex) {
            }
        }
        return list;
    }


    /*Metodo agregar*/
    public void insert(Pdf vo) {
        String sql = "INSERT INTO formar_archivos (ID, nombre, archivo) VALUES(?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = getConexion().prepareStatement(sql);
            ps.setInt(1, vo.getContenidoID());
            ps.setString(2, vo.getNombrepdf());
            ps.setBytes(3, vo.getArchivopdf());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                desconectar();
            } catch (Exception ex) {
            }
        }
    }


    /*Metodo Modificar*/
    public void update(Pdf vo) {
    	
        String sql = "UPDATE formar_archivos SET nombre = ?, archivo = ? WHERE ID = ?;";
        PreparedStatement ps = null;
        try {
            ps = getConexion().prepareStatement(sql);
            ps.setString(1, vo.getNombrepdf());
            ps.setBytes(2, vo.getArchivopdf());
            ps.setInt(3, vo.getContenidoID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                desconectar();
            } catch (Exception ex) {
            }
        }
    }
    /*
    public void Modificar_Pdf2(Pdf vo) {
    	 ODB conec = new ODB();
        String sql = "UPDATE formar_pdf SET nombrepdf = ? WHERE contenido_ID = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getNombrepdf());
            ps.setInt(2, vo.getContenidoID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }*/

    /*Metodo Eliminar*/
    public void delete(Pdf vo) {
    	
        String sql = "DELETE FROM formar_archivos WHERE ID = ?;";
        PreparedStatement ps = null;
        try {
            ps = getConexion().prepareStatement(sql);
            ps.setInt(1, vo.getContenidoID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                desconectar();
            } catch (Exception ex) {
            }
        }
    }

    //Permite mostrar PDF contenido en la base de datos
    public String abrir(Integer id) {
    	PreparedStatement ps = null;
        ResultSet rs = null;
        byte[] b = null;
        String nombre = "";

        try {
            ps = getConexion().prepareStatement("SELECT archivo, nombre FROM formar_archivos WHERE ID = ?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                b = rs.getBytes(1);
                nombre = rs.getString(2);
            }
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);
            String path = System.getProperty("java.io.tmpdir")+File.separatorChar+ nombre;
            OutputStream out = new FileOutputStream(path);
            out.write(datosPDF);

            //abrir archivo
            out.close();
            bos.close();
            ps.close();
            rs.close();
            desconectar();

        } catch (IOException ex) {
            System.out.println("Error al abrir archivo PDF " + ex.getMessage());
        } catch (NumberFormatException ex) {
        	System.out.println("Error al abrir archivo PDF " + ex.getMessage());
        } catch (SQLException ex){
        	System.out.println("Error al abrir archivo PDF " + ex.getMessage());
        }
        return nombre;
    }
    
}