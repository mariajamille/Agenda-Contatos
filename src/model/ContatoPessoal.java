package model;

import java.time.LocalDate;

public class ContatoPessoal extends Contato {
    private LocalDate dataAniversario;

    public ContatoPessoal(String nome, Endereco endereco, LocalDate dataAniversario) {
        super(nome, endereco);
        this.dataAniversario = dataAniversario;
    }

    public LocalDate getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(LocalDate dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    @Override
    public String toString() {
        return super.toString() + "\nAniversário: " + dataAniversario;
    }
}
