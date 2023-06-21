package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import modelo.Proveedor;


public class ProveedorData {
      Conexion cn = new Conexion();
      Connection con;
      PreparedStatement ps;
      ResultSet rs;

    public boolean agregarProveedor(Proveedor p1) {
        String sql = "INSERT INTO proveedor (razonSocial,domicilio,telefono) VALUES (?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, p1.getRazonSocial());           
            ps.setString(2, p1.getDomicilio());
             ps.setString(3, p1.getTelefono());
         
            ps.execute();
            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());

            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        
    }
//listar proveedores

    public ArrayList listarProveedores() {
        ArrayList<Proveedor> listaP1 = new ArrayList();

        String sql = "SELECT * FROM proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor p1 = new Proveedor();
                p1.setIdProveedor(rs.getInt("id_Proveedor"));
                p1.setRazonSocial(rs.getString("RazonSocial"));                
                p1.setDomicilio(rs.getString("domicilio"));
                p1.setTelefono(rs.getString("telefono"));
                listaP1.add(p1);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaP1;

    }
//eliminar proveedores

    public boolean eliminarProveedor(int idProveedor) {
        String sql = "DELETE FROM proveedor WHERE `id_proveedor` = ?";
        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, idProveedor);
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }

        }

    }
    //Modificar cliente

    public boolean modificarProveedor(Proveedor p1) {
        String sql = "UPDATE proveedor SET razonSocial=?, domicilio =?, telefono =? WHERE id_proveedor=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, p1.getRazonSocial());
            
            ps.setString(2, p1.getDomicilio());
            ps.setString(3, p1.getTelefono());
            ps.setInt(4, p1.getIdProveedor());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }

        }

    }
}


   

