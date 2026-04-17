import java.util.ArrayList;
import java.util.Scanner;

public class SistemaEstoque {
    public static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE ESTOQUE ===");
        System.out.println("1- Cadastrar produto");
        System.out.println("2- Remover produto");
        System.out.println("3- Atualizar quantidade");
        System.out.println("4- Ver produtos");
        System.out.println("5- Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static String lerNomeProduto(Scanner scanner) {
        scanner.nextLine();
        String nome;

        do {
            System.out.print("Digite o nome do produto: ");
            nome = scanner.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("Erro: o nome não pode ser vazio.");
            }
        } while (nome.isEmpty());

        return nome;
    }

    public static int lerQuantidade(Scanner scanner) {
        int quantidade;

        do {
            System.out.print("Digite a quantidade: ");
            quantidade = scanner.nextInt();

            if (quantidade < 0) {
                System.out.println("Erro: a quantidade não pode ser negativa.");
            }
        } while (quantidade < 0);

        return quantidade;
    }

    public static double lerPreco(Scanner scanner) {
        double preco;

        do {
            System.out.print("Digite o preço: ");
            preco = scanner.nextDouble();

            if (preco < 0) {
                System.out.println("Erro: o preço não pode ser negativo.");
            }
        } while (preco < 0);

        return preco;
    }

    public static void listarProdutos(ArrayList<String> nomes, 
                                      ArrayList<Integer> quantidades, 
                                      ArrayList<Double> precos){
        if (nomes.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        
        for (int i = 0; i < nomes.size(); i++) {
            System.out.println("\nProduto " + (i + 1));
            System.out.println("Nome: " + nomes.get(i));
            System.out.println("Quantidade: " + quantidades.get(i));
            System.out.printf("Preço: R$" + precos.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(java.util.Locale.US);

        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<Integer> quantidades = new ArrayList<>();
        ArrayList<Double> precos = new ArrayList<>();

        int opcao;

        do {
            mostrarMenu();
            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    nomes.add(lerNomeProduto(scanner));
                    quantidades.add(lerQuantidade(scanner));
                    precos.add(lerPreco(scanner));
                    System.out.println("Produto cadastrado com sucesso.");
                    break;

                case 2:
                    listarProdutos(nomes, quantidades, precos);

                    System.out.print("Digite o número do produto para remover: ");
                    int indexRemover = scanner.nextInt() - 1;

                    if (indexRemover >= 0 && indexRemover < nomes.size()) {
                        nomes.remove(indexRemover);
                        quantidades.remove(indexRemover);
                        precos.remove(indexRemover);

                        System.out.println("Produto removido.");
                    } else {
                        System.out.println("Produto inválido.");
                    }
                    break;

                case 3: 
                
                    listarProdutos(nomes, quantidades, precos);

                    System.out.print("Escolha o produto: ");
                    int indexAtualizar = scanner.nextInt() - 1;

                    if (indexAtualizar >= 0 && indexAtualizar < quantidades.size()) {
                        int novaQuantidade = lerQuantidade(scanner);
                        quantidades.set(indexAtualizar, novaQuantidade);
                        System.out.println("Quantidade atualizada.");
                    } else {
                        System.out.println("Produto inválido.");
                    }
                    break;

                case 4:

                    listarProdutos(nomes, quantidades, precos);
                    break;

                case 5:

                    System.out.print("Encerrando...");
                    break;

                default:
                    
                    System.out.print("Opção inválida.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}