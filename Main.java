public class Main {
    public static void main(String[] args) {

        try {
            Grafo<String> grafo = new Grafo<>();

            grafo.adicionarVertice("Los Angeles");
            grafo.adicionarVertice("Las Vegas");
            grafo.adicionarVertice("Phoenix");
            grafo.adicionarVertice("San Francisco");

            grafo.adicionarAresta(450, "Los Angeles", "Las Vegas");
            grafo.adicionarAresta(470, "Las Vegas", "Los Angeles");
            grafo.adicionarAresta(575, "Los Angeles", "Phoenix");
            grafo.adicionarAresta(300, "Phoenix", "Las Vegas");

            grafo.exibir();

            grafo.montarMatriz();

            System.out.println("Matriz criada com sucesso!");

        } catch (Exception erro) {
            System.out.println("Erro: " + erro.getMessage());
        }
    }
}