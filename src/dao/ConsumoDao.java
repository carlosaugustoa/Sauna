package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Consumo;

public class ConsumoDao {

    private DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public ConsumoDao() {
        db = new DataBase();
    }
    
    public boolean insert(Consumo consumo) {
        if (db.open()) {
            sql = "INSERT INTO tb_consumos (con_hora_ent, con_hora_sai, con_valor_t, con_status, funcionario, produto, cliente) "
                    + "VALUES (?,?,?,?,?,?,?)";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, consumo.getCon_hora_ent());
                ps.setString(2, consumo.getCon_hora_sai());
                ps.setInt(3, (int) consumo.getCon_valor_t());
                ps.setString(4, consumo.getCon_status());
                ps.setString(5, consumo.getfuncionario());
                ps.setString(6, consumo.getproduto());
                ps.setString(7, consumo.getcliente());
                if (ps.executeUpdate() == 1) {
                    ps.close();
                    db.close();
                    return true;
                }
            } catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.close();
        return false;
    }
    
    public boolean delete(Consumo consumo) {
        if (db.open()) {
            sql = "DELETE FROM tb_produtos WHERE pro_id = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setInt(1, consumo.getCon_id());
                if (ps.executeUpdate() == 1) {
                    ps.close();
                    db.close();
                    return true;
                }
            } catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.close();
        return false;
    }
    
    public boolean update(Consumo consumo) {
        if (db.open()) {
            sql = "UPDATE tb_consumos SET con_nome = ? WHERE con_id = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, cliente.getCli_nome());
                if (ps.executeUpdate() == 1) {
                    ps.close();
                    db.close();
                    return true;
                }
            } catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.close();
        return false;
    }
    
    public List<Cliente> selectAll() {
        if (db.open()) {
            List<Cliente> clientes = new ArrayList();
            sql = "SELECT * FROM tb_clientes";
            try {
                ps = db.connection.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setCli_id(rs.getInt(1));
                    cliente.setCli_nome(rs.getString(2));
                    clientes.add(cliente);
                }
                rs.close();
                ps.close();
                db.close();
                return clientes;
            } catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    
    public List<Cliente> selectFilter(String filter) {
        if (db.open()) {
            List<Cliente> clientes = new ArrayList();
            String filtro = "%" + filter + "%";
            sql = "SELECT * FROM tb_clientes WHERE cli_nome LIKE ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, filtro);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setCli_id(rs.getInt(1));
                    cliente.setCli_nome(rs.getString(2));
                    cliente.add(cliente);
                }
                rs.close();
                ps.close();
                db.close();
                return clientes;
            } catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    
    public Cliente select(int id) {
        if (db.open()) {
            Cliente cliente = new Cliente();
            sql = "SELECT * FROM tb_cliente WHERE cli_id = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    cliente.setCli_id(rs.getInt(1));
                    cliente.setCli_nome(rs.getString(2));
                    rs.close();
                    ps.close();
                    db.close();
                    return cliente;
                }
            } catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    
}
