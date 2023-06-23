public class PoligonoIrregular {
        private Coordenada[] vertices;
        private int cantidadVertices;
        private int indiceVertice;
        
	public PoligonoIrregular(int vertices) {
                this.cantidadVertices = vertices;
                this.vertices = new Coordenada[vertices];
                this.indiceVertice = 0;
        }
        
	public void anadeVertice(Coordenada vertice) {
                if (this.indiceVertice == this.cantidadVertices) {
                        System.out.println("Ya no se pueden anadir mas vertices!");
                        return;
                }
                this.vertices[this.indiceVertice++] = vertice;
        }
        
	public String toString() {
                String str = "";
                for(int i = 0; i < this.indiceVertice; i++) {
                        str += String.valueOf(i) + "<->" + this.vertices[i] + "\n";
                }
                return str;
        }
         
}
