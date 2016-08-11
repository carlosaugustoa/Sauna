package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

public class FuncionarioDao {

    private DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public FuncionarioDao() {
        db = new DataBase();
    }
    
    public boolean insert(Funcionario funcionario) {
        if (db.open()) {
            sql = "INSERT INTO tb_funcionarios (fun_nome) VALUES (?)";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, funcionario.getFun_nome());
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
    
    public boolean delete(Funcionario funcionario) {
        if (db.open()) {
            sql = "DELETE FROM tb_funcionarios WHERE fun_id = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setInt(1, funcionario.getFun_id());
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
    
    public boolean update(Funcionario funcionario) {
        if (db.open()) {
            sql = "UPDATE tb_funcionarios SET fun_nome = ? WHERE con_id = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, funcionario.getFun_nome());
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
    
    public List<Funcionario> selectAll() {
        if (db.open()) {
            List<Funcionario> funcionarios = new ArrayList();
            sql = "SELECT * FROM tb_funcionarios";
            try {
                ps = db.connection.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setFun_id(rs.getInt(1));
                    funcionario.setFun_nome(rs.getString(2));
                    funcionarios.add(funcionario);
                }
                rs.close();
                ps.close();
                db.close();
                return funcionarios;
            } catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    
    public List<Funcionario> selectFilter(String filter) {
        if (db.open()) {
            List<Funcionario> funcionarios = new ArrayList();
            String filtro = "%" + filter + "%";
            sql = "SELECT * FROM tb_funcionarios WHERE fun_nome LIKE ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, filtro);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setFun_id(rs.getInt(1));
                    funcionario.setFun_nome(rs.getString(2));
                    funcionario.add(funcionario);
                }
                rs.close();
                ps.close();
                db.close();
                return funcionarios;
            } catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    
    public Funcionario select(int id) {
        if (db.open()) {
            Funcionario funcionario = new Funcionario();
            sql = "SELECT * FROM tb_funcionarios WHERE fun_id = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    funcionario.setFun_id(rs.getInt(1));
                    funcionario.setFun_nome(rs.getString(2));
                    rs.close();
                    ps.close();
                    db.close();
                    return funcionario;
                }
            } catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    
}
