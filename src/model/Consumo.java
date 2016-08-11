package model;

public class Consumo {

    private int con_id;
    private String con_data;
    private String con_hora_ent;
    private String con_hora_sai;
    private float con_valor_t;
    private String con_status;
    private Funcionario funcionario;
    private Produto produto;
    private Cliente cliente;

    public int getCon_id() {
        return con_id;
    }

    public void setCon_id(int con_id) {
        this.con_id = con_id;
    }

    public String getCon_data() {
        return con_data;
    }

    public void setCon_data(String con_data) {
        this.con_data = con_data;
    }

    public String getCon_hora_ent() {
        return con_hora_ent;
    }

    public void setCon_hora_ent(String con_hora_ent) {
        this.con_hora_ent = con_hora_ent;
    }

    public String getCon_hora_sai() {
        return con_hora_sai;
    }

    public void setCon_hora_sai(String con_hora_sai) {
        this.con_hora_sai = con_hora_sai;
    }

    public float getCon_valor_t() {
        return con_valor_t;
    }

    public void setCon_valor_t(float con_valor_t) {
        this.con_valor_t = con_valor_t;
    }

    public String getCon_status() {
        return con_status;
    }

    public void setCon_status(String con_status) {
        this.con_status = con_status;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Consumo{" + "con_id=" + con_id + ", con_data=" + con_data + ", con_hora_ent=" + con_hora_ent + ", con_hora_sai=" + con_hora_sai + ", con_valor_t=" + con_valor_t + ", con_status=" + con_status + ", funcionario=" + funcionario + ", produto=" + produto + ", cliente=" + cliente + '}';
    }
     
    
}
