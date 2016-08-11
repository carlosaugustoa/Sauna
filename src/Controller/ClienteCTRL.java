package controller;

import dao.ClienteDao;
import java.util.List;
import model.Cliente;
  
public class ClienteCTRL {

    private ClienteDao dao;
    
    public ClienteCTRL() {
        dao = new ClienteDao();
    }
    
    public boolean adcionar(String nome) {
        Cliente cliente = new Cliente();
        cliente.setCli_nome(nome.toUpperCase());
        return dao.insert(cliente);
    }
    
    public boolean atualizar(int id, String nome) {
        Cliente cliente = new Cliente();
        cliente.setCli_id(id);
        cliente.setCli_nome(nome.toUpperCase());
        return dao.update(cliente);
    }
    
    public List<Cliente> listar(String filter) {
        if (filter == null) {
            return dao.selectAll();
        } else {
            return dao.selectFilter(filter);
        }
    }
    
    public boolean remover(int id) {
        Cliente cliente = new Cliente();
        cliente.setCli_id(id);
        return dao.delete(cliente);
    }
    
}