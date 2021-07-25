package com.ungs.revivir.persistencia.mysql;

import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.entidades.PDF;
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

	public PDF traerPdf(Integer id) {
		PDF ret = new PDF();
		String sql = "SELECT * FROM formar_archivos WHERE (ID = "+id+");";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = getConexion().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ret.setContenidoID(rs.getInt(1));
				ret.setNombrePDF(rs.getString(2));
				ret.setArchivoPDF(rs.getBytes(3));
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
    public ArrayList<PDF> Listar_Pdf() {
        ArrayList<PDF> list = new ArrayList<PDF>();
        
        String sql = "SELECT * FROM formar_archivos";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PDF pdf = new PDF();
                pdf.setContenidoID(rs.getInt(1));
                pdf.setNombrePDF(rs.getString(2));
                pdf.setArchivoPDF(rs.getBytes(3));
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
    public void insert(PDF vo) {
        String sql = "INSERT INTO formar_archivos (ID, nombre, archivo) VALUES(?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = getConexion().prepareStatement(sql);
            ps.setInt(1, vo.getContenidoID());
            ps.setString(2, vo.getNombrePDF());
            ps.setBytes(3, vo.getArchivoPDF());
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
    public void update(PDF vo) {
    	
        String sql = "UPDATE formar_archivos SET nombre = ?, archivo = ? WHERE ID = ?;";
        PreparedStatement ps = null;
        try {
            ps = getConexion().prepareStatement(sql);
            ps.setString(1, vo.getNombrePDF());
            ps.setBytes(2, vo.getArchivoPDF());
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
    public void delete(PDF vo) {
    	
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