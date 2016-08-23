package controller;

import dao.ProdutoDao;
import java.util.List;
import model.Produto;
  
public class ProdutoCTRL {

    private ProdutoDao dao;
    
    public ProdutoCTRL() {
        dao = new ProdutoDao();
    }
    
    public boolean adcionar(String nome) {
        Produto produto = new Produto();
        produto.setPro_nome(nome.toUpperCase());
        return dao.insert(produto);
    }
    
    public boolean atualizar(String id, String nome) {
        Produto produto = new Produto();
        produto.setPro_id(id);
        produto.setPro_nome(nome.toUpperCase());
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
