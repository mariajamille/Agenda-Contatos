package agenda;

import model.Contato;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contato> contatos;

    //CONSTRUTOR
    public Agenda() {
        this.contatos = new ArrayList<>();
    }

    //ADICIONAR CONTATO
    public void adicionarContato(Contato contato) {
        contatos.add(contato);
    }

    //REMOVER CONTATO
    public boolean removerContato(String nome) {
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).getNome().equalsIgnoreCase(nome)) {
                contatos.remove(i);
                return true; 
            }
        }
        return false;
    }

    //BUSCAR CONTATO
    public Contato buscarPorNome(String nome) {
        return contatos.stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    //LISTAR CONTATO
    public void listarTodos() {
        if (contatos.isEmpty()) {
            System.out.println("Agenda vazia.");
        } else {
            contatos.forEach(c -> {
                System.out.println("--------------------------");
                System.out.println(c);
            });
        }
    }

    //EDITAR CONTATO
    public void editarContato(String nome, Contato novoContato) {
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).getNome().equalsIgnoreCase(nome)) {
                contatos.set(i, novoContato);
                break;
            }
        }
    }
}
