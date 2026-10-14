public class Main {

    // adiciona a estrada nos dois sentidos (ida e volta)
    private static void estrada(Grafo<String> g, double peso, String a, String b) throws Exception {
        g.adicionarAresta(peso, a, b);
        g.adicionarAresta(peso, b, a);
    }

    public static void main(String[] args) throws Exception {
        Grafo<String> grafo = new Grafo<String>();
        grafo.adicionarVertice("Brooklyn");
        grafo.adicionarVertice("New York");
        grafo.adicionarVertice("Atlanta");
        grafo.adicionarVertice("Chicago");
        grafo.adicionarVertice("Washington");

        // pesos aproximados (milhas); troque pelos valores do enunciado
        estrada(grafo, 2,   "Brooklyn",   "New York");
        estrada(grafo, 230, "Brooklyn",   "Washington");
        estrada(grafo, 875, "Brooklyn",   "Atlanta");
        estrada(grafo, 795, "Brooklyn",   "Chicago");

        estrada(grafo, 225, "New York",   "Washington");
        estrada(grafo, 870, "New York",   "Atlanta");
        estrada(grafo, 790, "New York",   "Chicago");

        estrada(grafo, 640, "Washington", "Atlanta");
        estrada(grafo, 700, "Washington", "Chicago");

        estrada(grafo, 715, "Atlanta",    "Chicago");

        System.out.println(grafo);
    }
}
