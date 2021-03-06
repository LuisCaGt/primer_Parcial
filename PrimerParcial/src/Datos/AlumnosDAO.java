/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import Dominio.Alumnos;
import Datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leelu
 */
public class AlumnosDAO {
    private static final String SQL_SELECT = "SELECT carnet_alumno, nombre_alumno, direccion_alumno, telefono_alumno, email_alumno, estatus_alumno FROM alumnos";
    private static final String SQL_INSERT = "insert into alumnos values(?,?,?,?,?,?)";
    private static final String SQL_DELETE = "delete from alumnos where carnet_alumno = ?";  
    private static final String SQL_UPDATE = "UPDATE alumnos SET nombre_alumno=?, direccion_alumno=?, telefono_alumno=?, email_alumno=?, estatus_alumno=? WHERE carnet_alumno=?";
    private static final String SQL_QUERY = "SELECT carnet_alumno, nombre_alumno, direccion_alumno, telefono_alumno, email_alumno, estatus_alumno FROM alumnos WHERE carnet_alumno = ?";
   
    
    public List<Alumnos> select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Alumnos alumnos = null;
        List<Alumnos> sede = new ArrayList<Alumnos>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                String carnet_alumno = rs.getString("carnet_alumno");
                String nombre_alumno = rs.getString("nombre_alumno");
                String direccion_alumno = rs.getString("direccion_alumno");
                String telefono_alumno = rs.getString("telefono_alumno");
                String email_alumno = rs.getString("email_alumno");
                String estatus_alumno = rs.getString("estatus_alumno");
                
                alumnos = new Alumnos();
                alumnos.setCodigo(carnet_alumno);
                alumnos.setNombre(nombre_alumno);
                alumnos.setDireccion(direccion_alumno);
                alumnos.setTelefono(telefono_alumno);
                alumnos.setEmail(email_alumno);
                alumnos.setEstatus(estatus_alumno);
                
                sede.add(alumnos);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return sede;
    }
    public int insert(Alumnos alumnos){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, alumnos.getCodigo());
            stmt.setString(2, alumnos.getNombre());
            stmt.setString(3, alumnos.getDireccion());
            stmt.setString(4, alumnos.getTelefono());
            stmt.setString(5, alumnos.getEmail());
            stmt.setString(6, alumnos.getEstatus());
//            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    public int delete(Alumnos alumnos){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, alumnos.getCodigo());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    public int update(Alumnos alumnos){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
//            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, alumnos.getNombre());
            stmt.setString(2, alumnos.getDireccion());
            stmt.setString(3, alumnos.getTelefono());
            stmt.setString(4, alumnos.getEmail());
            stmt.setString(5, alumnos.getEstatus());
            stmt.setString(6, alumnos.getCodigo());
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
    public Alumnos query(Alumnos alumnos) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
//            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, alumnos.getCodigo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String carnet_alumno = rs.getString("carnet_alumno");
                String nombre_alumno = rs.getString("nombre_alumno");
                String direccion_alumno = rs.getString("direccion_alumno");
                String telefono_alumno = rs.getString("telefono_alumno");
                String email_alumno = rs.getString("email_alumno");
                String estatus_alumno = rs.getString("estatus_alumno");
                alumnos = new Alumnos();
                alumnos.setCodigo(carnet_alumno);
                alumnos.setNombre(nombre_alumno);
                alumnos.setDireccion(direccion_alumno);
                alumnos.setTelefono(telefono_alumno);
                alumnos.setEmail(email_alumno);
                alumnos.setEstatus(estatus_alumno);
                rows++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return alumnos;
    }
}
