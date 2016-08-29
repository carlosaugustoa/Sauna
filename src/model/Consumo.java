package model;

public class Consumo {

    private int con_id;
    private Cliente cliente;
    private Funcionario funcionario;
    private Produto produto;
    private int con_qtd;
    private float con_valor_t;

    public int getCon_id() {
        return con_id;
    }

    public void setCon_id(int con_id) {
        this.con_id = con_id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getCon_qtd() {
        return con_qtd;
    }

    public void setCon_qtd(int con_qtd) {
        this.con_qtd = con_qtd;
    }

    public float getCon_valor_t() {
        return con_valor_t;
    }

    public void setCon_valor_t(float con_valor_t) {
        this.con_valor_t = con_valor_t;
    }

    @Override
    public String toString() {
        return "Consumo{" + "con_id=" + con_id + ", cliente=" + cliente + ", funcionario=" + funcionario + ", produto=" + produto + ", con_qtd=" + con_qtd + ", con_valor_t=" + con_valor_t + '}';
    }

      
}
