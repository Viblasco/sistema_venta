package controladores;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Cliente;

public class ClientesData {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean agregarCliente(Cliente c1) {
        String sql = "INSERT INTO cliente (nombre,apellido,domicilio,telefono) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, c1.getNombre());
            ps.setString(2, c1.getApellido());
            ps.setString(3, c1.getDomicilio());
            ps.setString(4, c1.getTelefono());
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
//listar cliente

    public ArrayList listarClientes() {
        ArrayList<Cliente> listaC1 = new ArrayList();

        String sql = "SELECT * FROM cliente";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c1 = new Cliente();
                c1.setIdCliente(rs.getInt("id_Cliente"));
                c1.setNombre(rs.getString("nombre"));
                c1.setApellido(rs.getString("apellido"));
                c1.setDomicilio(rs.getString("domicilio"));
                c1.setTelefono(rs.getString("telefono"));
                listaC1.add(c1);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaC1;

    }
//eliminar clientes

    public boolean eliminarCliente(int idCliente) {
        String sql = "DELETE FROM `cliente` WHERE `cliente`.`id_cliente` = ?";
        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
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

    public boolean modificarCliente(Cliente c1) {
        String sql = "UPDATE cliente SET nombre =?,apellido =?, domicilio =?, telefono =? WHERE id_cliente=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, c1.getNombre());
            ps.setString(2, c1.getApellido());
            ps.setString(3, c1.getDomicilio());
            ps.setString(4, c1.getTelefono());
            ps.setInt(5, c1.getIdCliente());
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
