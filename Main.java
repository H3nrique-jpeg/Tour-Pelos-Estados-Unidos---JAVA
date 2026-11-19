
import java.util.Scanner;

public class Main {

    private static Scanner entrada = new Scanner(System.in);
    private static Grafo<String> grafo = new Grafo<>();

    public static void main(String[] args) {

        try {
            carregarDados();
            System.out.println("Mapa dos Estados Unidos carregado!");
            System.out.println("Cidades: " + grafo.getVertices().size());

        } catch (Exception e) {
            System.out.println("Erro ao carregar mapa: " + e.getMessage());
            return;
        }

        int opcao;

        do {
            exibirMenu();

            try {
                opcao = lerInteiro("Escolha uma opção: ");

                switch (opcao) {

                    case 1:
                        grafo.exibir();
                        break;

                    case 2:
                        exibirMatriz();
                        break;

                    case 3:
                        cadastrarCidade();
                        break;

                    case 4:
                        cadastrarEstrada();
                        break;

                    case 5:
                        removerCidade();
                        break;

                    case 6:
                        calcularMenorRota();
                        break;

                    case 7:
                        consultarCidade();
                        break;

                    case 0:
                        System.out.println("Encerrando sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                opcao = -1;
            }

        } while (opcao != 0);

        entrada.close();
    }

    // =====================================
    // CARREGAR MAPA
    // =====================================

    
private static void carregarDados() throws Exception {

    // =================================
    // CIDADES
    // =================================

    String[] cidades = {
        "Seattle", "Portland", "Boise", "Yellowstone",
        "Minneapolis", "Milwaukee", "Chicago", "Detroit",
        "Albany", "Boston", "New York", "Washington",
        "Richmond", "San Francisco", "Lake Tahoe",
        "Salt Lake City", "Las Vegas", "Grand Canyon",
        "Palm Springs", "Los Angeles", "San Diego",
        "Phoenix", "Denver", "Vail", "Aspen",
        "Colorado Springs", "Omaha", "Kansas City",
        "St Louis", "Indianapolis", "Nashville", "Memphis",
        "Little Rock", "Dallas", "San Antonio", "Houston",
        "New Orleans", "Atlanta", "Orlando",
        "Fort Lauderdale", "Miami"
    };

    for (String cidade : cidades) {
        grafo.adicionarVertice(cidade);
    }

    // =================================
    // NOROESTE
    // =================================

    estrada(170, "Seattle", "Portland");
    estrada(500, "Seattle", "Boise");
    estrada(720, "Portland", "Boise");
    estrada(640, "Portland", "San Francisco");
    estrada(580, "Portland", "Lake Tahoe");
    estrada(300, "San Francisco", "Lake Tahoe");
    estrada(420, "Boise", "Lake Tahoe");
    estrada(670, "Boise", "Yellowstone");
    estrada(1340, "Yellowstone", "Minneapolis");
    estrada(930, "Yellowstone", "Denver");

    // =================================
    // CALIFÓRNIA E SUDOESTE
    // =================================

    estrada(380, "San Francisco", "Los Angeles");
    estrada(780, "Lake Tahoe", "Palm Springs");
    estrada(520, "Lake Tahoe", "Salt Lake City");
    estrada(420, "Salt Lake City", "Las Vegas");
    estrada(530, "Salt Lake City", "Denver");
    estrada(660, "Salt Lake City", "Phoenix");

    estrada(270, "Los Angeles", "Las Vegas");
    estrada(160, "Los Angeles", "Palm Springs");
    estrada(120, "Los Angeles", "San Diego");
    estrada(140, "San Diego", "Palm Springs");
    estrada(250, "San Diego", "Phoenix");

    estrada(290, "Palm Springs", "Las Vegas");
    estrada(440, "Palm Springs", "Grand Canyon");
    estrada(470, "Las Vegas", "Grand Canyon");
    estrada(560, "Grand Canyon", "Phoenix");

    // =================================
    // COLORADO
    // =================================

    estrada(80, "Denver", "Vail");
    estrada(160, "Vail", "Aspen");
    estrada(180, "Aspen", "Colorado Springs");
    estrada(120, "Denver", "Colorado Springs");

    // =================================
    // REGIÃO CENTRAL
    // =================================

    estrada(380, "Minneapolis", "Omaha");
    estrada(340, "Minneapolis", "Milwaukee");
    estrada(410, "Minneapolis", "Chicago");
    estrada(90, "Milwaukee", "Chicago");
    estrada(470, "Omaha", "Chicago");
    estrada(540, "Omaha", "Denver");
    estrada(190, "Omaha", "Kansas City");

    estrada(280, "Chicago", "Detroit");
    estrada(300, "Chicago", "St Louis");
    estrada(180, "Chicago", "Indianapolis");
    estrada(700, "Chicago", "Washington");

    estrada(250, "Kansas City", "St Louis");
    estrada(550, "Kansas City", "Dallas");

    estrada(250, "St Louis", "Indianapolis");
    estrada(400, "St Louis", "Dallas");
    estrada(290, "St Louis", "Little Rock");

    // =================================
    // NORDESTE
    // =================================

    estrada(650, "Detroit", "Albany");
    estrada(640, "Detroit", "New York");
    estrada(530, "Detroit", "Washington");

    estrada(170, "Albany", "Boston");
    estrada(150, "Albany", "New York");
    estrada(210, "Boston", "New York");
    estrada(240, "New York", "Washington");
    estrada(200, "Washington", "Richmond");
    estrada(590, "Indianapolis", "Washington");

    // =================================
    // SUL
    // =================================

    estrada(290, "Indianapolis", "Nashville");
    estrada(210, "Memphis", "Nashville");
    estrada(240, "Nashville", "Atlanta");
    estrada(710, "Nashville", "Richmond");
    estrada(560, "Atlanta", "Richmond");

    estrada(140, "Little Rock", "Memphis");
    estrada(390, "Memphis", "Atlanta");
    estrada(320, "Little Rock", "Dallas");
    estrada(390, "Little Rock", "Atlanta");

    // =================================
    // TEXAS
    // =================================

    estrada(730, "Denver", "Dallas");
    estrada(1070, "Phoenix", "Dallas");
    estrada(990, "Phoenix", "San Antonio");
    estrada(280, "Dallas", "San Antonio");
    estrada(250, "Dallas", "Houston");
    estrada(310, "San Antonio", "Houston");

    // =================================
    // GOLFO DO MÉXICO E FLÓRIDA
    // =================================

    estrada(530, "Houston", "New Orleans");
    estrada(470, "New Orleans", "Atlanta");
    estrada(790, "Dallas", "Atlanta");
    estrada(640, "New Orleans", "Orlando");
    estrada(860, "New Orleans", "Miami");
    estrada(440, "Atlanta", "Orlando");
    estrada(230, "Orlando", "Miami");
    estrada(50, "Fort Lauderdale", "Miami");
    estrada(180, "Orlando", "Fort Lauderdale");
    
    System.out.println("Mapa carregado com sucesso!");
}


    // =====================================
    // CADASTRAR ESTRADA BIDIRECIONAL
    // =====================================

    private static void estrada(
        double distancia,
        String origem,
        String destino
    ) throws Exception {

        grafo.adicionarAresta(distancia, origem, destino);
        grafo.adicionarAresta(distancia, destino, origem);
    }

    // =====================================
    // MENU
    // =====================================

    private static void exibirMenu() {

        System.out.println("\n======================================");
        System.out.println("       TOUR PELOS ESTADOS UNIDOS");
        System.out.println("======================================");
        System.out.println("1 - Exibir grafo");
        System.out.println("2 - Exibir matriz esparsa");
        System.out.println("3 - Adicionar cidade");
        System.out.println("4 - Adicionar estrada");
        System.out.println("5 - Remover cidade");
        System.out.println("6 - Calcular menor rota");
        System.out.println("7 - Consultar cidade");
        System.out.println("0 - Sair");
        System.out.println("======================================");
    }

    // =====================================
    // LEITURA
    // =====================================

    private static int lerInteiro(String mensagem) throws Exception {

        System.out.print(mensagem);

        try {
            return Integer.parseInt(entrada.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new Exception("Digite um número inteiro válido!");
        }
    }

    private static double lerDecimal(String mensagem) throws Exception {

        System.out.print(mensagem);

        try {
            return Double.parseDouble(
                entrada.nextLine().trim().replace(',', '.')
            );
        } catch (NumberFormatException e) {
            throw new Exception("Digite um número válido!");
        }
    }

    private static String lerTexto(String mensagem) {

        System.out.print(mensagem);
        return entrada.nextLine().trim();
    }

    // =====================================
    // OPERAÇÕES
    // =====================================

    private static void exibirMatriz() throws Exception {

        grafo.montarMatriz();

        System.out.println("\nMATRIZ ESPARSA");
        System.out.println(grafo.getMatriz());
    }

    private static void cadastrarCidade() throws Exception {

        String cidade = lerTexto("Nome da cidade: ");

        grafo.adicionarVertice(cidade);

        System.out.println("Cidade adicionada!");
    }

    private static void cadastrarEstrada() throws Exception {

        String origem = lerTexto("Origem: ");
        String destino = lerTexto("Destino: ");
        double distancia = lerDecimal("Distância: ");

        estrada(distancia, origem, destino);

        System.out.println("Estrada bidirecional cadastrada!");
    }

    private static void removerCidade() throws Exception {

        String cidade = lerTexto("Cidade para remover: ");

        grafo.removerVertice(cidade);

        System.out.println("Cidade removida!");
    }

    // =====================================
    // MENOR CAMINHO
    // =====================================

    private static void calcularMenorRota() throws Exception {

        String origem = lerTexto("Cidade de origem: ");
        String destino = lerTexto("Cidade de destino: ");

        Percurso<String> percurso =
            grafo.menorCaminho(origem, destino);

        System.out.println("\n======================================");
        System.out.println("             MENOR ROTA");
        System.out.println("======================================");

        for (int i = 0; i < percurso.cidades.size(); i++) {

            System.out.print(
                percurso.cidades.get(i).getDado()
            );

            if (i < percurso.trechos.size()) {

                System.out.printf(
                    " --(%.1f km)--> ",
                    percurso.trechos.get(i)
                );
            }
        }

        System.out.println("\n--------------------------------------");

        System.out.printf(
            "Distância total: %.2f km%n",
            percurso.total
        );

        System.out.println("Quantidade de cidades: " +
            percurso.cidades.size());

        System.out.println("======================================");
    }

    // =====================================
    // CONSULTAR CIDADE
    // =====================================

    private static void consultarCidade() throws Exception {

        String cidade = lerTexto("Cidade: ");

        Vertice<String> v = grafo.getVertice(cidade);

        if (v == null)
            throw new Exception("Cidade inexistente!");

        System.out.println("\n[" + v.getDado() + "]");

        System.out.println("\nEstradas de saída:");

        for (int i = 0; i < v.getArestasSaida().getQuantidade(); i++) {

            Aresta<String> a = v.getArestasSaida().getPosicao(i);

            System.out.println(
                a.getFim().getDado() + " - " +
                a.getPeso() + " km"
            );
        }

        System.out.println("\nEstradas de entrada:");

        for (int i = 0; i < v.getArestasEntrada().getQuantidade(); i++) {

            Aresta<String> a = v.getArestasEntrada().getPosicao(i);

            System.out.println(
                a.getInicio().getDado() + " - " +
                a.getPeso() + " km"
            );
        }
    }
}
