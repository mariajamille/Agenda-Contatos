package model;

import java.util.ArrayList;
import java.util.List;

public class Contato {
    private String nome;
    private List<String> telefones;
    private List<String> emails;
    private Endereco endereco;

    public Contato(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefones = new ArrayList<>();
        this.emails = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    public List<String> getTelefones() { return telefones; }
    public void setTelefones(List<String> telefones) { this.telefones = telefones; }

    public List<String> getEmails() { return emails; }
    public void setEmails(List<String> emails) { this.emails = emails; }

    public void adicionarTelefone(String telefone) {
        this.telefones.add(telefone);
    }

    public void adicionarEmail(String email) {
        this.emails.add(email);
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
               "\nTelefones: " + telefones +
               "\nEmails: " + emails +
               "\nEndereço: " + endereco;
    }
}
