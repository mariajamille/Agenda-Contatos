package model;

public class ContatoProfissional extends Contato {
    private String empresa;
    private String cargo;

    public ContatoProfissional(String nome, Endereco endereco, String empresa, String cargo) {
        super(nome, endereco);
        this.empresa = empresa;
        this.cargo = cargo;
    }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    @Override
    public String toString() {
        return super.toString() + "\nEmpresa: " + empresa + "\nCargo: " + cargo;
    }
}
