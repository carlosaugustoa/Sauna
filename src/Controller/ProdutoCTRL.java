package controller;

import dao.ProdutoDao;
import java.util.List;
import javax.swing.JOptionPane;
import model.Produto;
  
public class ProdutoCTRL {

    private ProdutoDao dao;
    
    public ProdutoCTRL() {
        dao = new ProdutoDao();
    }
    
    public boolean adcionar(String Pro_id, String Pro_nome, int Pro_valor) {
        Produto produto = new Produto();
        produto.setPro_id(Pro_id);
        produto.setPro_nome(Pro_nome);
        produto.setPro_valor(Pro_valor);
        return dao.insert(produto);
    }
    
    
    
    public boolean atualizar(String Pro_id, String Pro_nome, int Pro_valor) {
        Produto produto = new Produto();
        produto.setPro_id(Pro_id);
        produto.setPro_nome(Pro_nome.toUpperCase());
        produto.setPro_valor(Pro_valor);
        return dao.update(produto);
    }
    
    
    
    public List<Produto> listar(String filter) {
        if (filter == null) {
            return dao.selectAll();
        } else {
            return dao.selectFilter(filter);
        }
    }
    
    public boolean remover(String Pro_id) {
        Produto produto = new Produto();
        produto.setPro_id(Pro_id);
        return dao.delete(produto);
    }
    
}
