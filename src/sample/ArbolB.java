package sample;


import java.io.Serializable;

public class ArbolB implements Serializable{

    Nodo raiz;

    public ArbolB() {
          raiz=null;
      }


    public void insertar (String info) {
        Nodo nuevo;
        nuevo = new Nodo ();
        nuevo.info = info;
        nuevo.izq = null;
        nuevo.der = null;

        if (raiz == null){
            raiz = nuevo;
        }

        System.out.println("nuevo.info: " + nuevo.info);
        System.out.println("nuevo.izq: " + nuevo.izq);
        System.out.println("nuevo.der: " + nuevo.der+"\n");

    }

    // Podria retornar un Nodo
    public void insertarIzquierda(String pregunta,Nodo Actual){
        Nodo nuevo = new Nodo ();

        nuevo.info = pregunta;
        nuevo.izq = null;
        nuevo.der = null;

        Actual.izq = nuevo;


        System.out.println("Actual.Info: "+Actual.info);
        System.out.println("Actual.izq: "+Actual.izq.info);
        System.out.println("Actual.der: "+Actual.der+"\n");

    }

    // Aqui podria retornar un Nodo
    public void insertarDerecha(String pregunta,Nodo Actual){
        Nodo nuevo = new Nodo ();

        nuevo.info = pregunta;
        nuevo.izq = null;
        nuevo.der = null;

        Actual.der = nuevo;

        System.out.println("Actual.Info: "+Actual.info);
        System.out.println("Actual.izq: "+Actual.izq.info);
        System.out.println("Actual.der: "+Actual.der+"\n");

    }

    public void inertarNodoInterno(String pregunta,String RespuestaIz,Nodo Actual){
        //Actual.der =Actual.info;
        String aux = Actual.info;
        Actual.info = pregunta;

        insertarIzquierda(RespuestaIz,Actual);
        insertarDerecha(aux,Actual);

        System.out.println("Actual.Info: "+Actual.info);
        System.out.println("Actual.izq: "+Actual.izq.info);
        System.out.println("Actual.der: "+Actual.der+"\n");
    }

    /**
     * Retorna el recorrido al nodo Izquierdo
     * @param Actual Actual -> al nodo derecho
     * @return
     */

    public Nodo recoIzq(Nodo Actual) {

        return Actual.izq;

    }

    /**
     * Retorna el recorrido al nodo Derecho
     *
     * @param Actual Actual -> al nodo derecho
     * @return
     */
    public Nodo recoDer(Nodo Actual){

        return Actual.der;
    }


    private void preOrder (Nodo reco){
          if (reco != null)
          {
              System.out.print(reco.info + "\n");
              preOrder (reco.izq);
              preOrder (reco.der);
          }
      }

    public void preOrder (){
          preOrder (raiz);
          System.out.println();
      }

    private void inOrder (Nodo reco){
          if (reco != null)
          {    
              inOrder (reco.izq);
              System.out.print(reco.info + " ");
              inOrder (reco.der);
          }else {
              System.out.print("Vacio ");
          }

      }

    public void inOrder (){
          inOrder (raiz);
          System.out.println();
      }

    public void limpiarArbol(){
        this.raiz = null;
    }

    public Nodo getRaiz(){
        return this.raiz;
    }
}