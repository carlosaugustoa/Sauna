package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDao {

    private DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public ClienteDao() {
        db = new DataBase();
    }
    
    public boolean insert(Cliente cliente) {
        if (db.open()) {
            sql = "INSERT INTO tb_clientes (cli_id, cli_seq, cli_data, cli_nome, cli_status) VALUES (?,?,?,?,?)";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setInt(1, cliente.getCli_id());
                ps.setInt(2, cliente.getCli_seq());
                ps.setString(3, cliente.getCli_data());
                ps.setString(4, cliente.getCli_nome());
                ps.setInt(5, cliente.getCli_status());
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
    
    public boolean delete(Cliente cliente) {
        if (db.open()) {
            sql = "DELETE FROM tb_contatos WHERE cli_id = ?, cli_seq = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setInt(1, cliente.getCli_id());
                ps.setInt(2, cliente.getCli_seq());
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
    
    public boolean update(Cliente cliente) {
        if (db.open()) {
            sql = "UPDATE tb_clientes SET cli_nome = ? WHERE con_id = ?";
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
