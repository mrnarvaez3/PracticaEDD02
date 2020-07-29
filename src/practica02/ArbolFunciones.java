package practica02;

/**
 *
 * @author roberto n
 */
import java.util.ArrayList;
import javax.swing.JPanel;

public class ArbolFunciones {
      Arbol miArbol = new Arbol();
      
    public ArbolFunciones() {
    
    }

    public void insertar2(Integer dato) {
        this.miArbol.insertar(dato);
    }
    //metodo para mostrar los recorridos del arbol en forma PreOrden
    public String preOrden() {
        ArrayList it = this.miArbol.preOrden();
        return (recorrido(it, "Recorrido PreOrden"));
    }
    //metodo para mostrar los recorridos del arbol en forma InOrden
    public String inOrden() {
        ArrayList it = this.miArbol.inOrden();
        return (recorrido(it, "Recorrido InOrden"));
    }
    //metodo para mostrar los recorridos del arbol en forma PostOrden
    public String postOrden() {
        ArrayList it = this.miArbol.postOrden();
        return (recorrido(it, "Recorrido PosOrden"));
    }
    
    //Metodo para Recorrer el arbol 
    private String recorrido(ArrayList it, String msg) {
        int i = 0;
        // variable que contiene el recorrido total del arbol o subarbol
        String recorrido = msg + "\n";
        while (i < it.size()) {
            recorrido += "\t" + it.get(i).toString() + "";
            i++;
        }
        return (recorrido);
    }
   // metodo que llama al graficador del arbol
    public JPanel getDibujo() {
        return this.miArbol.grafica();
    }  
}
