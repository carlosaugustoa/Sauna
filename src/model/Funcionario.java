package model;

public class Funcionario {

    private String fun_id;
    private String fun_nome;

    public String getFun_id() {
        return fun_id;
    }

    public void setFun_id(String fun_id) {
        this.fun_id = fun_id;
    }

    public String getFun_nome() {
        return fun_nome;
    }

    public void setFun_nome(String fun_nome) {
        this.fun_nome = fun_nome;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "fun_id=" + fun_id + ", fun_nome=" + fun_nome + '}';
    }

   
}
