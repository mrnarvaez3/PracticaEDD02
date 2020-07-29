
package practica02;

/**
 *
 * @author https://gist.github.com/godie007
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class ArbolGrafico extends JPanel{
    private Arbol miArbol;
    private HashMap posicionNodos = null;
    private HashMap subtreeSizes = null;
    private boolean lleno = true;
    private int parent2child = 20, child2child = 30;
    private Dimension vacio = new Dimension(0,0);
    private FontMetrics fm = null;
    
     /**
     * Constructor de la clase ArbolGrafico.
     * Tiene como objetivo incicalizar los atributos de esta clase
     * Se llama al metodo repaint() que es quien graficará el Arbol
     */
    public ArbolGrafico(Arbol miArbol) 
    {
          this.miArbol = miArbol;
          this.setBackground(Color.WHITE);
          posicionNodos = new HashMap();
          subtreeSizes = new HashMap();
          lleno = true;
          repaint();      
    }
    
    
    
    /**
     * Calcula las posiciones donde se van a situar los subarboles, y cada nodo
     * que conforman este subarbol para saber en que posicion iran dibujados los 
     * siguientes nodoso por ingresar
     */
    private void calcularPosiciones() 
    {
         posicionNodos.clear();
         subtreeSizes.clear();
         Nodo raiz = this.miArbol.getRaiz();
         if (raiz != null) 
         {
             calcularTamañoSubarbol(raiz);
             calcularPosicion(raiz, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
         }
    }
    
    
    
    
    /**
     * Metodo que calucla el tamaño de cada subarbol y lo agrega al objeti subtreeSize
     * el cual va a contener la coleccion de todos los subarboles que contiene nuestro arbol
     */
    private Dimension calcularTamañoSubarbol(Nodo n) 
    {
          if (n == null) 
              return new Dimension(0,0);
 
          Dimension izq = calcularTamañoSubarbol(n.getIzq());
          Dimension der = calcularTamañoSubarbol(n.getDer());
          
          int altura = fm.getHeight() + parent2child + Math.max(izq.height, der.height);
          int anchura = izq.width + child2child + der.width;
          
          Dimension d = new Dimension(anchura, altura);
          subtreeSizes.put(n, d);
          
          return d;
    }
    
    
    /**
     * MEtodo para obtener la ubicacion de cada nodo de cada subarbol y agrega cada 
     * nodo con un objeto de tipo Rectangule que tiene la ubicacion y la informacion 
     * especifica de donde el arbol será graficado
     * @param left: int con alineación y orientación a la izquierda.
     * @param right: int con alineación y orientación a la derecha.
     * @param top: int con el tope.
     */
    private void calcularPosicion(Nodo n, int izq, int der, int top) 
    {
      if (n == null) 
          return;
      
      Dimension ld = (Dimension) subtreeSizes.get(n.getIzq());
      if (ld == null) 
          ld = vacio;
      
      Dimension rd = (Dimension) subtreeSizes.get(n.getDer());
      if (rd == null) 
          rd = vacio;
      
      int center = 0;
      
      if (der != Integer.MAX_VALUE)
          center = der - rd.width - child2child/2;
      else if (izq != Integer.MAX_VALUE)
          center = izq + ld.width + child2child/2;
      int width = fm.stringWidth(n.getDato()+"");
 
      posicionNodos.put(n,new Rectangle(center - width/2 - 3, top, width + 6, fm.getHeight()));
      
      calcularPosicion(n.getIzq(), Integer.MAX_VALUE, center - child2child/2, top + fm.getHeight() + parent2child);
      calcularPosicion(n.getDer(), center + child2child/2, Integer.MAX_VALUE, top + fm.getHeight() + parent2child);
    }
    
    
    
    /**
     * Este metodo dibuja el arbol segun las ubicaciones de los nodos y los
     * subarboles que conforman el arbol final, 
     * @param g: Objeto de la clase Graphics2D que permite realizar el dibujo de las líneas, rectangulos y del String de la información que contiene el Nodo.
     * @param n: Objeto de la clase NodoB <T> que se utiliza como referencia para dibujar el árbol.
     * @param puntox: int con la posición en x desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param puntoy: int con la posición en y desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param yoffs: int con la altura del FontMetrics.
     */
    private void dibujarArbol(Graphics2D g, Nodo n, int puntox, int puntoy, int yoffs) 
    {
     if (n == null) 
         return;
     
     Rectangle r = (Rectangle) posicionNodos.get(n);
     g.draw(r);
     g.drawString(n.getDato()+"", r.x + 3, r.y + yoffs);
   
     if (puntox != Integer.MAX_VALUE)
       
     g.drawLine(puntox, puntoy, (int)(r.x + r.width/2), r.y);
     
     dibujarArbol(g, n.getIzq(), (int)(r.x + r.width/2), r.y + r.height, yoffs);
     dibujarArbol(g, n.getDer(), (int)(r.x + r.width/2), r.y + r.height, yoffs);
     
   }
    
    
    
    /**
     * metodo que se encarga de sobreescribir el metodo paint se encarga de graficar
     * el arbol de forma completa
     * @param g: Objeto de la clase Graphics.
     */
    @Override
   public void paint(Graphics g) 
   {
         super.paint(g);
         fm = g.getFontMetrics();

         if (lleno) 
         {
           calcularPosiciones();
           lleno = false;
         }
         
         Graphics2D g2d = (Graphics2D) g;
         g2d.translate(getWidth() / 2, parent2child);
         dibujarArbol(g2d, this.miArbol.getRaiz(), Integer.MAX_VALUE, Integer.MAX_VALUE, 
                  fm.getLeading() + fm.getAscent());
         fm = null;
   }
    
}
