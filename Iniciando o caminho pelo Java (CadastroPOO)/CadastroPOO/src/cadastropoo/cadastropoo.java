package cadastropoo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import model.PessoaF;
import model.PessoaF_Repo;
import model.PessoaJ;
import model.PessoaJ_Repo;

public class cadastropoo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PessoaF_Repo repo1 = new PessoaF_Repo();
        PessoaJ_Repo repo2 = new PessoaJ_Repo();

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("╔═══════════════════════════╗");
            System.out.println("║Selecione alguma opção:║");
            System.out.println("╚═══════════════════════════╝");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Exibir pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Salvar dados");
            System.out.println("7 - Recuperar dados");
            System.out.println("0 - Finalizar\n");
           

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Selecione o tipo (F - Física, J - Jurídica):");
                    String tipo1 = scanner.next().strip().toUpperCase();
                    scanner.nextLine(); 

                    if (tipo1.equals("F")) {
                        System.out.println("Digite o ID:");
                        int id1 = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.println("Digite o nome:");
                        String nome1 = scanner.nextLine();
                        System.out.println("Digite o CPF:");
                        String cpf = scanner.nextLine();
                        System.out.println("Digite a idade:");
                        int idade = scanner.nextInt();
                        scanner.nextLine();

                        PessoaF pessoaFisica = new PessoaF(id1, nome1, cpf, idade);
                        repo1.inserir(pessoaFisica);
                    } else if (tipo1.equals("J")) {

                        System.out.println("Digite o ID:");
                        int id2 = scanner.nextInt();
                        scanner.nextLine(); 

                        System.out.println("Digite o nome:");
                        String nome2 = scanner.nextLine();

                        System.out.println("Digite o CNPJ:");
                        String cnpj = scanner.nextLine();

                        
                        PessoaJ pessoaJuridica = new PessoaJ(id2, nome2, cnpj);
                        repo2.inserir(pessoaJuridica);
                    }
                    break;

                case 2:
                    
                    System.out.println("Selecione o tipo: F- Física | J- Jurídica:");
                    String tipo2 = scanner.next().strip().toUpperCase();
                    scanner.nextLine(); 

                    if (tipo2 .equals("F")) {
                        
                        System.out.println("Digite o ID da pessoa física a ser alterada:");
                        int id1 = scanner.nextInt();
                        scanner.nextLine(); 

                        PessoaF pessoaFisica = repo1.obter(id1);
                        if (pessoaFisica != null) {
                            
                            pessoaFisica.exibir();

                            System.out.println("Digite o novo nome:");
                            String nome1 = scanner.nextLine();

                            System.out.println("Digite o novo CPF:");
                            String cpf = scanner.nextLine();

                            System.out.println("Digite a nova idade:");
                            int idade = scanner.nextInt();
                            scanner.nextLine(); 

                            
                            pessoaFisica.setNome(nome1);
                            pessoaFisica.setCpf(cpf);
                            pessoaFisica.setIdade(idade);

                            
                            repo1.alterar(pessoaFisica);
                            System.out.println("Pessoa física alterada com sucesso.");
                        } else {
                            System.out.println("Pessoa física não encontrada.");
                        }
                    } else if (tipo2.equals("J")) {
                        
                        System.out.println("Digite o ID da pessoa jurídica a ser alterada:");
                        int id2 = scanner.nextInt();
                        scanner.nextLine(); 

                        PessoaJ pessoaJuridica = repo2.obter(id2);
                        if (pessoaJuridica != null) {
                            
                            pessoaJuridica.exibir();

                            System.out.println("Digite o novo nome:");
                            String nome2 = scanner.nextLine();

                            System.out.println("Digite o novo CNPJ:");
                            String cnpj = scanner.nextLine();

                            
                            pessoaJuridica.setNome(nome2);
                            pessoaJuridica.setCnpj(cnpj);

                            
                            repo2.alterar(pessoaJuridica);
                            System.out.println("Pessoa jurídica alterada com sucesso.");
                        } else {
                            System.out.println("Pessoa jurídica não encontrada.");
                        }
                    }
                    break;

                case 3:
                    
                    System.out.println("Selecione o tipo (F - Física, J - Jurídica):");
                   String tipo3 = scanner.next().strip().toUpperCase();
                    scanner.nextLine(); 

                    if (tipo3.equals("F")) {
                        
                        System.out.println("Digite o ID da pessoa física a ser excluída:");
                        int id1 = scanner.nextInt();
                        scanner.nextLine(); 

                        PessoaF pessoaFisica = repo1.obter(id1);
                        if (pessoaFisica != null) {
                            
                            pessoaFisica.exibir();
                            
                            repo1.excluir(id1);
                            System.out.println("Pessoa física excluída com sucesso.");
                        } else {
                            System.out.println("Pessoa física não encontrada.");
                        }
                    } else if (tipo3.equals("J")) {
                        
                        System.out.println("Digite o ID da pessoa jurídica a ser excluída:");
                        int id2 = scanner.nextInt();
                        scanner.nextLine(); 

                        PessoaJ pessoaJuridica = repo2.obter(id2);
                        if (pessoaJuridica != null) {
                            
                            pessoaJuridica.exibir();
                            
                            repo2.excluir(id2);
                            System.out.println("Pessoa jurídica excluída com sucesso.");
                        } else {
                            System.out.println("Pessoa jurídica não encontrada.");
                        }
                    }
                    break;

                case 4:

                    System.out.println("Digite o ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); 

                    PessoaF pessoaFisica = repo1.obter(id);
                    PessoaJ pessoaJuridica = repo2.obter(id);

                    if (pessoaFisica != null) {

                        pessoaFisica.exibir();
                    } else if (pessoaJuridica != null) {

                        pessoaJuridica.exibir();
                    } else {
                        System.out.println("Pessoa não encontrada.");
                    }
                    break;

                case 5:

                    System.out.println("Pessoas físicas:");
                    ArrayList<PessoaF> pessoasFisicas = repo1.obterTodos();
                    for (PessoaF pf : pessoasFisicas) {
                        pf.exibir();
                        System.out.println();
                    }

                    System.out.println("Pessoas jurídicas:");
                    ArrayList<PessoaJ> pessoasJuridicas = repo2.obterTodos();
                    for (PessoaJ pj : pessoasJuridicas) {
                        pj.exibir();
                        System.out.println();
                    }
                    break;

                case 6:
                    try {

                        System.out.println("Digite o nome do arquivo de persistência para pessoas físicas:");
                        String arquivo1 = scanner.nextLine();
                        repo1.persistir(arquivo1);

                        System.out.println("Digite o nome do arquivo de persistência para pessoas jurídicas:");
                        String arquivo2 = scanner.nextLine();
                        repo2.persistir(arquivo2);

                        System.out.println("Dados salvos com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar os dados.");
                        e.printStackTrace();
                    }
                    break;

                case 7:
                    try {

                        System.out.println("Digite o nome do arquivo de recuperação para pessoas físicas:");
                        String arquivo1 = scanner.nextLine();
                        repo1.recuperar(arquivo1);

                        System.out.println("Digite o nome do arquivo de recuperação para pessoas jurídicas:");
                        String arquivo2 = scanner.nextLine();
                        repo2.recuperar(arquivo2);

                        System.out.println("Dados recuperados com sucesso.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Erro ao recuperar os dados.");
                        e.printStackTrace();
                    }
                    break;

                case 0:

                    System.out.println("Finalizando o programa...");
                    break;

                default:

                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }
}
