package controller;


import dao.ClienteDao;
import java.util.List;
import model.Cliente;
  
public class ClienteCTRL {

    private ClienteDao dao;
    
    public ClienteCTRL() {
        dao = new ClienteDao();
    }
    
    public boolean adcionar(int Cli_id, int Cli_seq, String Cli_data, String Cli_nome, int Cli_status) {
        Cliente cliente = new Cliente();
        cliente.setCli_id(Cli_id);
        cliente.setCli_seq(Cli_seq);
        cliente.setCli_data(Cli_data);
        cliente.setCli_nome(Cli_nome);
        cliente.setCli_status(Cli_status);
        return dao.insert(cliente);
    }
    
    public boolean atualizar(int Cli_id, int Cli_seq, String Cli_data, String Cli_nome, int Cli_status) {
        Cliente cliente = new Cliente();
        cliente.setCli_id(Cli_id);
        cliente.setCli_seq(Cli_seq);
        cliente.setCli_data(Cli_data);
        cliente.setCli_nome(Cli_nome);
        cliente.setCli_status(Cli_status);
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