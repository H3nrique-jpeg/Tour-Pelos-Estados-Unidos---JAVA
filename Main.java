import java.util.Scanner;

public class Main {

    private static Scanner entrada = new Scanner(System.in);
    private static Grafo<String> grafo = new Grafo<>();

    public static void main(String[] args) {

        try {
            carregarDadosIniciais();
        } catch (Exception erro) {
            System.out.println("Erro ao carregar dados iniciais: " + erro.getMessage());
        }

        int opcao;

        do {
            opcao = -1; // se a leitura falhar, NÃO pode virar 0 (sair)
            exibirMenu();

            try {
                opcao = lerInteiro("Escolha uma opção: ");

                switch (opcao) {
                    case 1: cadastrarVertice(); break;
                    case 2: cadastrarAresta(); break;
                    case 3: grafo.exibir(); break;
                    case 4: removerVertice(); break;
                    case 5: exibirMatriz(); break;
                    case 6: consultarCidade(); break;
                    case 7: System.out.println("\n" + grafo); break;
                    case 8: executarTestesAutomaticos(); break;
                    case 0: System.out.println("\nEncerrando sistema..."); break;
                    default: System.out.println("\nOpção inválida!");
                }

            } catch (Exception erro) {
                // um erro numa opção NÃO derruba mais o programa
                System.out.println("\nErro: " + erro.getMessage());
            }

        } while (opcao != 0);

        entrada.close();
    }

    // =====================================
    // MENU
    // =====================================

    private static void exibirMenu() {
        System.out.println("\n============================================");
        System.out.println("       TOUR PELOS ESTADOS UNIDOS");
        System.out.println("============================================");
        System.out.println("1 - Adicionar cidade");
        System.out.println("2 - Adicionar rota");
        System.out.println("3 - Exibir grafo");
        System.out.println("4 - Remover cidade");
        System.out.println("5 - Exibir matriz esparsa");
        System.out.println("6 - Consultar cidade (rotas de entrada e saída)");
        System.out.println("7 - Exibir grafo (toString)");
        System.out.println("8 - Rodar testes automáticos");
        System.out.println("0 - Sair");
        System.out.println("============================================");
    }

    // =====================================
    // LEITURA SEGURA (não quebra com texto no lugar de número)
    // =====================================

    private static int lerInteiro(String mensagem) throws Exception {
        System.out.print(mensagem);
        String texto = entrada.nextLine().trim();

        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            throw new Exception("Digite um número inteiro válido.");
        }
    }

    private static double lerDecimal(String mensagem) throws Exception {
        System.out.print(mensagem);
        String texto = entrada.nextLine().trim().replace(',', '.');

        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new Exception("Digite um número válido (ex: 450 ou 450.5).");
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.nextLine().trim();
    }

    // =====================================
    // DADOS INICIAIS
    // =====================================

    private static void carregarDadosIniciais() throws Exception {

        grafo.adicionarVertice("Los Angeles");
        grafo.adicionarVertice("Las Vegas");
        grafo.adicionarVertice("Phoenix");
        grafo.adicionarVertice("San Francisco");

        grafo.adicionarAresta(450, "Los Angeles", "Las Vegas");
        grafo.adicionarAresta(470, "Las Vegas", "Los Angeles");
        grafo.adicionarAresta(575, "Los Angeles", "Phoenix");
        grafo.adicionarAresta(300, "Phoenix", "Las Vegas");

        System.out.println("Dados iniciais carregados!");
    }

    // =====================================
    // OPÇÕES DO MENU
    // =====================================

    private static void cadastrarVertice() throws Exception {
        String cidade = lerTexto("\nNome da cidade: ");

        if (cidade.isEmpty())
            throw new Exception("O nome da cidade não pode ser vazio.");

        grafo.adicionarVertice(cidade);
        System.out.println("Cidade adicionada com sucesso!");
    }

    private static void cadastrarAresta() throws Exception {
        String origem  = lerTexto("\nCidade de origem: ");
        String destino = lerTexto("Cidade de destino: ");
        double peso    = lerDecimal("Distância (km): ");

        grafo.adicionarAresta(peso, origem, destino);
        System.out.println("Rota adicionada com sucesso!");
    }

    private static void removerVertice() throws Exception {
        String cidade = lerTexto("\nCidade que deseja remover: ");

        grafo.removerVertice(cidade);
        System.out.println("Cidade removida com sucesso!");
        System.out.println("Suas arestas relacionadas também foram removidas.");
    }

    private static void exibirMatriz() throws Exception {
        grafo.montarMatriz();

        System.out.println("\nMatriz esparsa atualizada!");
        System.out.println(grafo.getMatriz());
    }

    private static void consultarCidade() throws Exception {
        String cidade = lerTexto("\nCidade a consultar: ");

        Vertice<String> v = grafo.getVertice(cidade);

        if (v == null)
            throw new Exception("Cidade inexistente!");

        System.out.println("\n[" + v.getDado() + "]");

        System.out.println("  Saem daqui (" + v.getArestasSaida().getQuantidade() + "):");
        for (int i = 0; i < v.getArestasSaida().getQuantidade(); i++) {
            Aresta<String> a = v.getArestasSaida().getPosicao(i);
            System.out.println("    -> " + a.getFim().getDado() + " (" + a.getPeso() + " km)");
        }

        System.out.println("  Chegam aqui (" + v.getArestasEntrada().getQuantidade() + "):");
        for (int i = 0; i < v.getArestasEntrada().getQuantidade(); i++) {
            Aresta<String> a = v.getArestasEntrada().getPosicao(i);
            System.out.println("    <- " + a.getInicio().getDado() + " (" + a.getPeso() + " km)");
        }
    }

    // =====================================
    // TESTES AUTOMÁTICOS
    // (usam um grafo novo; NÃO mexem nos dados do menu)
    // =====================================

    private interface Acao {
        void executar() throws Exception;
    }

    private static int aprovados;
    private static int reprovados;

    private static void verifica(String nome, boolean condicao) {
        if (condicao) {
            aprovados++;
            System.out.println("  [OK]    " + nome);
        } else {
            reprovados++;
            System.out.println("  [FALHA] " + nome);
        }
    }

    /** Passa se a ação LANÇAR exceção. */
    private static void esperaErro(String nome, Acao acao) {
        try {
            acao.executar();
            verifica(nome + " (deveria lançar erro e não lançou)", false);
        } catch (Exception e) {
            verifica(nome, true);
        }
    }

    /** Passa se a ação NÃO lançar exceção. */
    private static void esperaSucesso(String nome, Acao acao) {
        try {
            acao.executar();
            verifica(nome, true);
        } catch (Exception e) {
            verifica(nome + " (lançou: " + e.getMessage() + ")", false);
        }
    }

    private static void executarTestesAutomaticos() {
        aprovados = 0;
        reprovados = 0;

        System.out.println("\n--- TESTES AUTOMÁTICOS ---");

        final Grafo<String> g = new Grafo<>();

        System.out.println("\nVértices:");
        esperaSucesso("adicionar cidade válida", () -> g.adicionarVertice("A"));
        esperaSucesso("adicionar segunda cidade", () -> g.adicionarVertice("B"));
        esperaSucesso("adicionar terceira cidade", () -> g.adicionarVertice("C"));
        esperaErro("rejeitar cidade repetida", () -> g.adicionarVertice("A"));
        esperaErro("rejeitar cidade null", () -> g.adicionarVertice(null));
        verifica("getVertice encontra cidade existente", g.getVertice("B") != null);
        verifica("getVertice devolve null para inexistente", g.getVertice("Z") == null);

        System.out.println("\nArestas:");
        esperaSucesso("adicionar rota A -> B", () -> g.adicionarAresta(10, "A", "B"));
        esperaSucesso("adicionar rota B -> A (sentido oposto)", () -> g.adicionarAresta(12, "B", "A"));
        esperaSucesso("adicionar rota B -> C", () -> g.adicionarAresta(5, "B", "C"));
        esperaErro("rejeitar rota com cidade inexistente", () -> g.adicionarAresta(1, "A", "Z"));
        esperaErro("rejeitar peso negativo", () -> g.adicionarAresta(-1, "A", "C"));
        esperaErro("rejeitar peso NaN", () -> g.adicionarAresta(Double.NaN, "A", "C"));
        verifica("A tem 1 saída", g.getVertice("A").getArestasSaida().getQuantidade() == 1);
        verifica("B tem 2 saídas", g.getVertice("B").getArestasSaida().getQuantidade() == 2);
        verifica("B tem 1 entrada", g.getVertice("B").getArestasEntrada().getQuantidade() == 1);
        verifica("C tem 1 entrada", g.getVertice("C").getArestasEntrada().getQuantidade() == 1);

        System.out.println("\nRota duplicada (provável falha: Grafo não valida):");
        esperaErro("rejeitar rota A -> B repetida", () -> g.adicionarAresta(99, "A", "B"));

        System.out.println("\nMatriz esparsa:");
        final Grafo<String> g2 = new Grafo<>();
        esperaSucesso("montar matriz de grafo vazio", () -> g2.montarMatriz());
        esperaSucesso("montar matriz com 3 cidades e 3 rotas", () -> {
            Grafo<String> h = new Grafo<>();
            h.adicionarVertice("A");
            h.adicionarVertice("B");
            h.adicionarVertice("C");
            h.adicionarAresta(10, "A", "B");
            h.adicionarAresta(12, "B", "A");
            h.adicionarAresta(5, "B", "C");
            h.montarMatriz();
        });

        System.out.println("\nRemoção:");
        esperaSucesso("remover cidade B", () -> g.removerVertice("B"));
        verifica("B não existe mais", g.getVertice("B") == null);
        verifica("A ficou sem saídas (rota A->B sumiu)", g.getVertice("A").getArestasSaida().getQuantidade() == 0);
        verifica("A ficou sem entradas (rota B->A sumiu)", g.getVertice("A").getArestasEntrada().getQuantidade() == 0);
        verifica("C ficou sem entradas (rota B->C sumiu)", g.getVertice("C").getArestasEntrada().getQuantidade() == 0);
        esperaErro("rejeitar remover cidade inexistente", () -> g.removerVertice("B"));
        esperaErro("rejeitar remover cidade null", () -> g.removerVertice(null));
        esperaSucesso("matriz continua montando depois da remoção", () -> g.montarMatriz());

        System.out.println("\nListas (removeVertices / adicionaArestas):");
        esperaSucesso("adicionaArestas com lista", () -> {
            Grafo<String> h = new Grafo<>();
            h.adicionarVertice("X");
            h.adicionarVertice("Y");

            ListaSimplesDesordenada<Aresta<String>> lista = new ListaSimplesDesordenada<>();
            lista.guardeUmItemNoFinal(new Aresta<String>(7, h.getVertice("X"), h.getVertice("Y")));

            h.adicionaArestas(lista);

            if (h.getVertice("X").getArestasSaida().getQuantidade() != 1)
                throw new Exception("aresta não foi adicionada");
        });
        esperaErro("adicionaArestas com lista null", () -> g.adicionaArestas(null));
        esperaErro("removeVertices com lista null", () -> g.removeVertices(null));

        System.out.println("\n--------------------------------");
        System.out.println("Resultado: " + aprovados + " OK, " + reprovados + " com falha.");
    }
}