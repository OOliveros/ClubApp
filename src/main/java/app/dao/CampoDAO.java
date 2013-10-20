
package app.dao;

import app.model.Campo;
import app.model.Local;
import app.zelper.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CampoDAO extends BaseDAO{
    
     public List<Campo> list(){
        List<Campo> lista = new ArrayList<Campo>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            String query = "select * from campo order by descripcion;";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            
            LocalDAO localDAO = new LocalDAO();
            
            while (rs.next()) {
                Campo item = new Campo();
                item.setId(rs.getInt("id"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setCostoHora(Double.parseDouble(rs.getString("costo_hora")));
                item.setEstado(rs.getInt("estado"));
                
                Local local = new Local();
                local.setId(rs.getInt("id_local"));
                item.setLocal(localDAO.get(local));
                
                lista.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return lista;
    }

    public Campo get(Campo campo) {
        String query = "select * from campo where id = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Campo item = new Campo();
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, campo.getId());

            rs = stmt.executeQuery();

             LocalDAO localDAO = new LocalDAO();
            
            while (rs.next()) {
                item.setId(rs.getInt("id"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setCostoHora(Double.parseDouble(rs.getString("costo_hora")));
                item.setEstado(rs.getInt("estado"));
                
                Local local = new Local();
                local.setId(rs.getInt("id_local"));
                item.setLocal(localDAO.get(local));
                
                
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return item;
    }

    public Campo save(Campo campo) {
        String query = "insert into campo(descripcion,costo_hora,id_local) values (?,?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);

            stmt.setString(1, campo.getDescripcion());
            stmt.setDouble(2, campo.getCostoHora());
            stmt.setLong(3, campo.getLocal().getId());
            
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo insertar");
            }
            int id = 0;
            query = "select last_insert_id()";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            campo.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return campo;
    }

    public Campo update(Campo campo) {
        String query = "update campo set descripcion=?,costo_hora=?,id_local=? where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
              stmt.setString(1, campo.getDescripcion());
            stmt.setDouble(2, campo.getCostoHora());
            stmt.setLong(3, campo.getLocal().getId());
            stmt.setLong(4, campo.getId());

            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo actualizar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return campo;
    }

    public void delete(Campo campo) {
        String query = "delete from campo WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, campo.getId());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo eliminar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
    } 
    
    
}
