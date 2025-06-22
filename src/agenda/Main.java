package agenda;

import model.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner sc = new Scanner(System.in);
        boolean continuarPrograma = true;

        while (continuarPrograma) {
            // MENU DE OPÇÕES
            System.out.println("\n=== Menu da Agenda ===");
            System.out.println("1. Adicionar Contato Pessoal");
            System.out.println("2. Adicionar Contato Profissional");
            System.out.println("3. Listar Contatos");
            System.out.println("4. Buscar Contato");
            System.out.println("5. Editar Contato");
            System.out.println("6. Remover Contato");

            // LER A OPÇÃO ESCOLHIDA PELO USUÁRIO
            int op = lerInteiro(sc, "Escolha: ");

            // TRATAMENTO DE EXCEÇÕES
            try {
                // ADICIONA CONTATOS
                if (op == 1 || op == 2) {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Rua: ");
                    String rua = sc.nextLine();
                    System.out.print("Cidade: ");
                    String cidade = sc.nextLine();
                    System.out.print("Estado: ");
                    String estado = sc.nextLine();
                    System.out.print("CEP: ");
                    String cep = sc.nextLine();
                    Endereco endereco = new Endereco(rua, cidade, estado, cep);

                    Contato contato;
                    if (op == 1) {
                        System.out.print("Data de Aniversário (AAAA-MM-DD): ");
                        LocalDate data = LocalDate.parse(sc.nextLine());
                        contato = new ContatoPessoal(nome, endereco, data);
                    } else {
                        System.out.print("Empresa: ");
                        String empresa = sc.nextLine();
                        System.out.print("Cargo: ");
                        String cargo = sc.nextLine();
                        contato = new ContatoProfissional(nome, endereco, empresa, cargo);
                    }

                    System.out.print("Telefone: ");
                    contato.adicionarTelefone(sc.nextLine());
                    System.out.print("Email: ");
                    contato.adicionarEmail(sc.nextLine());

                    agenda.adicionarContato(contato);
                    System.out.println("Contato adicionado!");

                    // LISTAR CONTATOS
                } else if (op == 3) {
                    agenda.listarTodos();

                    //BUSCAR CONTATO
                } else if (op == 4) {
                    System.out.print("Digite o nome para busca: ");
                    Contato c = agenda.buscarPorNome(sc.nextLine());
                    System.out.println(c != null ? c : "Contato não encontrado.");

                    //EDITAR CONTATO
                } else if (op == 5) {
                    System.out.print("Nome do contato a editar: ");
                    String nome = sc.nextLine();
                    Contato original = agenda.buscarPorNome(nome);

                    if (original != null) {
                        System.out.println("\nEditando contato:");
                        System.out.print("Novo nome [" + original.getNome() + "]: ");
                        String novoNome = sc.nextLine();
                        if (!novoNome.isEmpty())
                            original.setNome(novoNome);

                        System.out.print("Nova rua [" + original.getEndereco().getRua() + "]: ");
                        String novaRua = sc.nextLine();
                        if (!novaRua.isEmpty())
                            original.getEndereco().setRua(novaRua);

                        System.out.print("Nova cidade [" + original.getEndereco().getCidade() + "]: ");
                        String novaCidade = sc.nextLine();
                        if (!novaCidade.isEmpty())
                            original.getEndereco().setCidade(novaCidade);

                        System.out.print("Novo estado [" + original.getEndereco().getEstado() + "]: ");
                        String novoEstado = sc.nextLine();
                        if (!novoEstado.isEmpty())
                            original.getEndereco().setEstado(novoEstado);

                        System.out.print("Novo CEP [" + original.getEndereco().getCep() + "]: ");
                        String novoCep = sc.nextLine();
                        if (!novoCep.isEmpty())
                            original.getEndereco().setCep(novoCep);

                        System.out.print("Novo telefone [" + original.getTelefones().get(0) + "]: ");
                        String novoTel = sc.nextLine();
                        if (!novoTel.isEmpty()) {
                            original.getTelefones().clear();
                            original.adicionarTelefone(novoTel);
                        }

                        System.out.print("Novo email [" + original.getEmails().get(0) + "]: ");
                        String novoEmail = sc.nextLine();
                        if (!novoEmail.isEmpty()) {
                            original.getEmails().clear();
                            original.adicionarEmail(novoEmail);
                        }

                        // CONTATO PESSOAL: EDIÇÃO DA DATA DE ANIVERSÁRIO
                        if (original instanceof ContatoPessoal) {
                            ContatoPessoal pessoal = (ContatoPessoal) original;
                            System.out.print(
                                    "Nova data de aniversário [" + pessoal.getDataAniversario() + "] (AAAA-MM-DD): ");
                            String novaData = sc.nextLine();
                            if (!novaData.isEmpty()) {
                                pessoal.setDataAniversario(LocalDate.parse(novaData));
                            }
                        }

                        // CONTATO PROFISSIONAL: EDIÇÃO DE CARGO E EMPRESA
                        if (original instanceof ContatoProfissional) {
                            ContatoProfissional profissional = (ContatoProfissional) original;
                            System.out.print("Nova empresa [" + profissional.getEmpresa() + "]: ");
                            String novaEmpresa = sc.nextLine();
                            if (!novaEmpresa.isEmpty())
                                profissional.setEmpresa(novaEmpresa);

                            System.out.print("Novo cargo [" + profissional.getCargo() + "]: ");
                            String novoCargo = sc.nextLine();
                            if (!novoCargo.isEmpty())
                                profissional.setCargo(novoCargo);
                        }

                        System.out.println("Contato atualizado com sucesso!");

                    } else {
                        System.out.println("Contato não encontrado.");
                    }
                }

                //REMOÇÃO DO CONTATO
                else if (op == 6) {
                    System.out.print("Nome do contato a remover: ");
                    if (agenda.removerContato(sc.nextLine())) {
                        System.out.println("Contato removido.");
                    } else {
                        System.out.println("Contato não encontrado na lista.");
                    }

                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }
        sc.close();
    }

    //MÉTODO lerInteiro()
    public static int lerInteiro(Scanner sc, String mensagem) {
        int numero;
        while (true) {
            try {
                System.out.print(mensagem);
                numero = Integer.parseInt(sc.nextLine());
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            }
        }
    }
}
