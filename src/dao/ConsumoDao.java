package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Consumo;
import model.Funcionario;
import model.Cliente;

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
            sql = "SELECT (Fun_id, Cli_id, Cli_seq, Pro_id, Con_qtd, Con_valor_t) VALUES (?,?,?,?,?,?) " +
                    "FROM tb_funcionarios inner join tb_consumos on fun_id = Con_fun_id " +
                                          "inner join tb_clientes on Cli_id = Con_cli_id and  Cli_seq = Con_cli_seq " +
                                          "inner join tb_produtos on Pro_id = Con_pro_id";
                        
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, consumo.getFun_id());
                ps.setInt(2, consumo.getCli_id());
                ps setInt(3, consumo.getCli_seq())
                ps.setString(4, consumo.getPro_id());
                ps.setInt(5, consumo.getCon_qtd());
                ps.setDouble(6, consumo.getCon_valor_t());
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
            sql = "DELETE FROM tb_produtos WHERE con_id = ? ";
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
            sql = "UPDATE tb_consumos SET Fun_id = ?, Cli_id = ?, Cli_seq = ?, Pro_id = ?, Con_qtd= ?, Con_valor_t = ? WHERE con_id = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, consumo.getFun_id());
                ps.setInt(2, consumo.getCli_id());
                ps setInt(3, consumo.getCli_seq());
                ps.setString(4, consumo.getPro_id());
                ps.setInt(5, consumo.getCon_qtd());
                ps.setDouble(6, consumo.getCon_valor_t());
                ps.setInt(7, consumo.getCon_id());
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
    
    public List<Consumo> selectAll() {
        if (db.open()) {
            List<Consumo> consumos = new ArrayList();
            sql = "SELECT * FROM tb_consumos";
            try {
                ps = db.connection.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Consumo consumo = new Cliente();
                    consumo.setCli_id(rs.getInt(1));
                    consumo.setFuncionario(rs.getString(2));
                    consumo.setCli_id(rs.getInt(3));
                    consumo.setCli_seq(rs.getInt(4));
                    consumo.setProduto(rs.getString(5));
                    consumo.setCon_qtd(rs.getInt(6));
                    consumo.setCon_valor_t(rs.getDouble(7));
                    consumo.add(consumo);
                }
                rs.close();
                ps.close();
                db.close();
                return consumos;
            } catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    
    public List<Consumo> selectFilter(String filter) {
        if (db.open()) {
            List<Consumo> consumos = new ArrayList();
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
