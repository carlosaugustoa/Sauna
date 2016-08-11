package model;

public class Cliente {

    private int cli_id;
    private String cli_nome;

    public int getCli_id() {
        return cli_id;
    }

    public void setCli_id(int cli_id) {
        this.cli_id = cli_id;
    }

    public String getCli_nome() {
        return cli_nome;
    }

    public void setCli_nome(String cli_nome) {
        this.cli_nome = cli_nome;
    }

    @Override
    public String toString() {
        return "Cliente{" + "cli_id=" + cli_id + ", cli_nome=" + cli_nome + '}';
    }
    
}
