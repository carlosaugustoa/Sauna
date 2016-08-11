package controller;

import dao.FuncionarioDao;
import java.util.List;
import model.Funcionario;
  
public class FuncionarioCTRL {

    private FuncionarioDao dao;
    
    public FuncionarioCTRL() {
        dao = new FuncionarioDao();
    }
    
    public boolean adcionar(String nome) {
        Funcionario funcionario = new Funcionario();
        funcionario.setFun_nome(nome.toUpperCase());
        return dao.insert(funcionario);
    }
    
    public boolean atualizar(int id, String nome) {
        Funcionario funcionario = new Funcionario();
        funcionario.setFun_id(id);
        funcionario.setFun_nome(nome.toUpperCase());
        return dao.update(funcionario);
    }
    
    public List<Funcionario> listar(String filter) {
        if (filter == null) {
            return dao.selectAll();
        } else {
            return dao.selectFilter(filter);
        }
    }
    
    public boolean remover(int id) {
        Funcionario funcionario = new Funcionario();
        funcionario.setFun_id(id);
        return dao.delete(funcionario);
    }
    
}
