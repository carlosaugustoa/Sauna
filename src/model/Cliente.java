package model;

public class Cliente {

    private int cli_id;
    private int cli_seq;
    private String cli_data;
    private String cli_nome;
    private int cli_status;

    public int getCli_id() {
        return cli_id;
    }

    public void setCli_id(int cli_id) {
        this.cli_id = cli_id;
    }

    public int getCli_seq() {
        return cli_seq;
    }

    public void setCli_seq(int cli_seq) {
        this.cli_seq = cli_seq;
    }

    public String getCli_data() {
        return cli_data;
    }

    public void setCli_data(String cli_data) {
        this.cli_data = cli_data;
    }

    public String getCli_nome() {
        return cli_nome;
    }

    public void setCli_nome(String cli_nome) {
        this.cli_nome = cli_nome;
    }

    public int getCli_status() {
        return cli_status;
    }

    public void setCli_status(int cli_status) {
        this.cli_status = cli_status;
    }

    @Override
    public String toString() {
        return "Cliente{" + "cli_id=" + cli_id + ", cli_seq=" + cli_seq + ", cli_data=" + cli_data + ", cli_nome=" + cli_nome + ", cli_status=" + cli_status + '}';
    }
        
    
    
}
