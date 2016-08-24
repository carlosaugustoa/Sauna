package controller;

import dao.FuncionarioDao;
import java.util.List;
import model.Funcionario;
  
public class FuncionarioCTRL {

    private FuncionarioDao dao;
    
    public FuncionarioCTRL() {
        dao = new FuncionarioDao();
    }
    
    public boolean adcionar(String Fun_nome) {
        Funcionario funcionario = new Funcionario();
        funcionario.setFun_nome(Fun_nome.toUpperCase());
        return dao.insert(funcionario);
    }
    
    public boolean atualizar( String Fun_nome ) {
        Funcionario funcionario = new Funcionario();
        funcionario.setFun_nome(Fun_nome.toUpperCase());
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
