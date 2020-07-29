
package practica02;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author roberto n
 */
public class Arbol {
    
    public Nodo raiz, nn;
    
    int num_nodos;
    int alt;

    public Arbol() {
        raiz = null;
    }
    /*
    public boolean agregar(int dato) {
        Nodo nuevo = new Nodo(dato, null, null);
        insertar(nuevo, raiz);
        return true;
    }
    */
    
    //Metodo para insertar un dato en el arbol...no acepta repetidos :)
    public void insertar(int dato) {
        nn = new Nodo(dato, null, null);
        if (raiz == null){
            raiz = nn;
        }else{
            Nodo na = raiz, np;
            while(true){
                np = na;
                if (dato < np.dato){
                    na = na.izq;
                    if (na == null){
                        np.izq = nn;
                        return;
                    }
                }else{
                    na = na.der;
                    if (na == null){
                        np.der = nn;
                        return;
                    }
                }
            }
        }
    }

    
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    //Recorrido preorden, recibe el nodo a empezar (raiz) y una linkedlist para ir guardando el recorrido
    public ArrayList preOrden() {
        ArrayList recorrido = new ArrayList();
        preorden(raiz, recorrido);
        return recorrido;
    }
    
    public void preorden(Nodo aux, ArrayList recorrido) {
        if (aux != null) {
            recorrido.add(aux.getDato());
            preorden(aux.getIzq(), recorrido);
            preorden(aux.getDer(), recorrido);
        }
    }

    //Recorrido inorden, recibe el nodo a empezar (raiz) y una linkedlist para ir guardando el recorrido
    public ArrayList inOrden() {
        ArrayList recorrido = new ArrayList();
        inorden(raiz, recorrido);
        return recorrido;
    }
    
    public void inorden(Nodo aux, ArrayList recorrido) {
        if (aux != null) {
            inorden(aux.getIzq(), recorrido);
            recorrido.add(aux.getDato());
            inorden(aux.getDer(), recorrido);
        }
    }

    //Recorrido postorden, recibe el nodo a empezar (raiz) y una linkedlist para ir guardando el recorrido
    public ArrayList postOrden() {
        ArrayList recorrido = new ArrayList();
        postorden(raiz, recorrido);
        return recorrido;
    }
    public void postorden(Nodo aux, ArrayList recorrido) {
        if (aux != null) {
            postorden(aux.getIzq(), recorrido);
            postorden(aux.getDer(), recorrido);
            recorrido.add(aux.getDato());
        }
    }
    
     public JPanel grafica() {
        return new ArbolGrafico(this);
    }
}
