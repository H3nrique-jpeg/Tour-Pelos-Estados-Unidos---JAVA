import java.util.ArrayList;

public class MatrizEsparsa<V> {
    private ArrayList<Vertice<V>> vertices;
    private ListaSimplesDesordenada<ElementoMatriz<V>> elementos;

    public MatrizEsparsa(ArrayList<Vertice<V>> verticesDoGrafo)  {
        this.vertices = new ArrayList<Vertice<V>>(verticesDoGrafo);
        this.elementos = new ListaSimplesDesordenada<>();
    }

    public void adicionarVertice(Vertice<V> vertice) throws Exception {
        if(vertice == null) throw new Exception("Vertice Ausente!");

        if(this.vertices.contains(vertice)) throw new Exception("Vertice já cadastrado!");

        this.vertices.add(vertice);
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
    

    public void removerVertice(Vertice<V> vertice) throws Exception {
        if (vertice == null) throw new Exception("Vertice Ausente!");

        int indiceRemover = this.localizarIndice(vertice.getDado());
        

        for(int i = 0; i < this.elementos.getQuantidade(); ) {
            ElementoMatriz<V> elemento = this.elementos.getPosicao(i);

            if(elemento.getLinha() == indiceRemover || elemento.getColuna() == indiceRemover) {
                this.elementos.removaItemIndicado(i);
            } else {
                if(elemento.getLinha() > indiceRemover) {
                    elemento.setLinha(elemento.getLinha() - 1);
                }
                if(elemento.getColuna() > indiceRemover) {
                    elemento.setColuna(elemento.getColuna() - 1);
                }

                i++;
            }
            
        }

        this.vertices.remove(indiceRemover);

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