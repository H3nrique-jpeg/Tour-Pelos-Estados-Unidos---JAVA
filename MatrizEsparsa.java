import java.util.ArrayList;

public class MatrizEsparsa<V> {
    private ArrayList<Vertice<V>> vertices;
    private ListaSimplesDesordenada<ElementoMatriz<V>> elementos;

    public MatrizEsparsa(ArrayList<Vertice<V>> verticesDoGrafo) throws Exception {
        if (verticesDoGrafo == null) {
            throw new Exception("Lista de vértices ausente!");
        }

        this.vertices = new ArrayList<Vertice<V>>(verticesDoGrafo);
        this.elementos = new ListaSimplesDesordenada<>();
    }

    private int localizarIndice(V dado) throws Exception {      // Fazer a procura das cidades pelo índice
        if (dado == null) throw new Exception("Dado ausente!");

        for (int i = 0; i < this.vertices.size(); i++) {
            Vertice<V> vertice = this.vertices.get(i);

            if (vertice.getDado() != null && vertice.getDado().equals(dado)) {
                return i;
            }
        }

        throw new Exception("Vértice inexistente!");
    }

    private boolean existeElemento(int linha, int coluna) throws Exception {     // Compara os caminhos, ex: Los Angeles -> Las Vegas = 450; Las Vegas -> Los Angeles = 470
        for (int i = 0; i < this.elementos.getQuantidade(); i++) {
            ElementoMatriz<V> elemento = this.elementos.getPosicao(i);

            if (elemento.getLinha() == linha && elemento.getColuna() == coluna) {
                return true;
            }
        }
        return false;
    }

    public void adicionarElemento(V origem, V destino, double distancia) throws Exception {
        if (origem == null || destino == null) throw new Exception("Origem e destino não podem ser nulos!");
        if (distancia < 0 || Double.isNaN(distancia)) throw new Exception("Distância inválida!");

        int linha = localizarIndice(origem);
        int coluna = localizarIndice(destino);

        if (existeElemento(linha, coluna)) {
            throw new Exception("Conexão já cadastrada!");
        }

        ElementoMatriz<V> novo = new ElementoMatriz<>(linha, coluna, distancia);
        this.elementos.guardeUmItemNoFinal(novo);
    }
}