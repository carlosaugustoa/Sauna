package model;

public class Produto {

    private int pro_id;
    private String pro_nome;
    private float pro_valor;

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_nome() {
        return pro_nome;
    }

    public void setPro_nome(String pro_nome) {
        this.pro_nome = pro_nome;
    }

    public Float getPro_valor() {
        return pro_valor;
    }

    public void setPro_valor(Float pro_valor) {
        this.pro_valor = pro_valor;
    }

    @Override
    public String toString() {
        return "Produto{" + "pro_id=" + pro_id + ", pro_nome=" + pro_nome + ", pro_valor=" + pro_valor + '}';
    }
}
