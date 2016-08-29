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
    
    public boolean adcionar(String pro_id, String pro_nome, Float pro_valor) {
        Produto produto = new Produto();
        produto.setPro_id(pro_id);
        produto.setPro_nome(pro_nome.toUpperCase());
        produto.setPro_valor(pro_valor);
        return dao.insert(produto);
    }
    
    public boolean atualizar(String id, String nome, Float valor) {
        Produto produto = new Produto();
        produto.setPro_id(id);
        produto.setPro_nome(nome.toUpperCase());
        produto.setPro_valor(valor);
        return dao.update(produto);
    }
    
    public List<Produto> listar(String filter) {
        if (filter == null) {
            return dao.selectAll();
        } else {
            return dao.selectFilter(filter);
        }
    }
    
    public boolean remover(String id) {
        Produto produto = new Produto();
        produto.setPro_id(id);
        return dao.delete(produto);
    }
    
}
