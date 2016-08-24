package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDao {

    private DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public ProdutoDao() {
        db = new DataBase();
    }
    
    public boolean insert(Produto produto) {
        if (db.open()) {
            sql = "INSERT INTO tb_produtos (pro_id, pro_nome, pro_valor) VALUES (?,?,?)";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, produto.getPro_id());
                ps.setString(2, produto.getPro_nome());
                ps.setFloat(3, produto.getPro_valor());
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
    
    public boolean delete(Produto produto) {
        if (db.open()) {
            sql = "DELETE FROM tb_produtos WHERE fun_id = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, produto.getPro_id());
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
    
    public boolean update(Produto produto) {
        if (db.open()) {
            sql = "UPDATE tb_produtos SET pro_nome = ? WHERE pro_id = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, produto.getPro_nome());
                ps.setFloat(2, produto.getPro_valor());
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
    
    public List<Produto> selectAll() {
        if (db.open()) {
            List<Produto> produtos = new ArrayList();
            sql = "SELECT * FROM tb_produtos";
            try {
                ps = db.connection.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Produto produto = new Produto();
                    produto.setPro_id(rs.getString(1));
                    produto.setPro_nome(rs.getString(2));
                    produto.setPro_valor(rs.getFloat(3));
                    produtos.add(produto);
                }
                rs.close();
                ps.close();
                db.close();
                return produtos;
            } catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    
    public List<Produto> selectFilter(String filter) {
        if (db.open()) {
            List<Produto> produtos = new ArrayList();
            String filtro = "%" + filter + "%";
            sql = "SELECT * FROM tb_produtos WHERE pro_id = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, filtro);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Produto produto = new Produto();
                    produto.setPro_id(rs.getString(1));
                    produto.setPro_nome(rs.getString(2));
                    produto.setPro_valor(rs.getFloat(3));
                    produtos.add(produto);
                }
                rs.close();
                ps.close();
                db.close();
                return produtos;
            } catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    
    public Produto select(int id) {
        if (db.open()) {
            Produto produto = new Produto();
            sql = "SELECT * FROM tb_produtos WHERE pro_id = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    produto.setPro_id(rs.getString(1));
                    produto.setPro_nome(rs.getString(2));
                    produto.setPro_valor(rs.getFloat(3));
                    rs.close();
                    ps.close();
                    db.close();
                    return produto;
                }
            } catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.close();
        return null;
    }
    
}

