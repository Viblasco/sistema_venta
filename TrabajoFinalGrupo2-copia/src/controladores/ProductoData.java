package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import modelo.Producto;

public class ProductoData {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Agregar producto
    public boolean agregarProducto(Producto pr1) {
        String sql = "INSERT INTO producto (descripcion,precioActual,stock,estado) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr1.getDescripcion());
            ps.setDouble(2, pr1.getPrecioActual());
            ps.setInt(3, pr1.getStock());
            ps.setBoolean(4, pr1.isEstado());
            ps.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

    }
//listar Producto

    public ArrayList listarProductos() {
        ArrayList<Producto> listarPR1 = new ArrayList();

        String sql = "SELECT * FROM producto";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto pr1 = new Producto();
                pr1.setIdProducto(rs.getInt("idProductoP"));
                pr1.setDescripcion(rs.getString("descP"));
                pr1.setPrecioActual(rs.getFloat("precioActP"));
                pr1.setStock(rs.getInt("stockP"));

                listarPR1.add(pr1);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarPR1;

    }
//eliminar Producto

    public boolean eliminarProducto(int idProducto) {
        String sql = "DELETE FROM `producto` WHERE `producto`.`id_producto` = ?";
        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, idProducto);
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
    //Modificar Producto

    public boolean modificarProducto(Producto pr1) {
        String sql = "UPDATE producto SET descripcion=?, precioActual=?, stock =? , estado= ? WHERE id_cliente=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pr1.getDescripcion());
            ps.setDouble(2, pr1.getPrecioActual());
            ps.setInt(3, pr1.getStock());
            ps.setBoolean(4, pr1.isEstado());
            ps.setInt(5, pr1.getIdProducto());
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
