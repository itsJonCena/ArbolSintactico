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
    public void insertarIzquierda(String value,Nodo Actual){
        Nodo nuevo = new Nodo ();

        nuevo.value = value;
        nuevo.izq = null;
        nuevo.der = null;

        Actual.izq = nuevo;

        /*
        System.out.println("Actual.value: "+Actual.value);
        System.out.println("Actual.izq: "+Actual.izq.info);
        System.out.println("Actual.der: "+Actual.der+"\n");
        */

    }

    // Aqui podria retornar un Nodo
    public void insertarDerecha(String value,Nodo Actual){
        Nodo nuevo = new Nodo ();

        nuevo.value = value;
        nuevo.izq = null;
        nuevo.der = null;

        Actual.der = nuevo;

        /*
        System.out.println("Actual.Info: "+Actual.info);
        System.out.println("Actual.izq: "+Actual.izq.info);
        System.out.println("Actual.der: "+Actual.der+"\n");
        */

    }


    // Creacion de los distintos arboles
    public void crear_arbolAfirmativo(){
        Nodo verbo = new Nodo();
        Nodo sujeto = new Nodo();
        Nodo complemento = new Nodo();
        verbo.izq = sujeto;
        verbo.der = complemento;
        raiz = verbo;

    }

    public void crear_arbolNegativo(String sSujeto, String sVerbo, String sComplemento){
        Nodo verbo = new Nodo();
        verbo.value = sVerbo;
        raiz = verbo;

        Nodo sujeto = new Nodo();
        sujeto.value = sSujeto;
        sujeto.der = null;
        sujeto.izq = null;

        Nodo auxNot = new Nodo();
        auxNot.value = "not";
        Nodo complemento = new Nodo();
        complemento.value = sComplemento;
        complemento.der = null;
        complemento.izq = null;

        verbo.izq = sujeto;
        verbo.der = auxNot;
        auxNot.der = complemento;
        auxNot.izq = null;

    }

    public void crear_arbolInte(){
        Nodo verbo = new Nodo();
        raiz = verbo;

        Nodo sujeto = new Nodo();
        sujeto.der = null;
        verbo.izq = sujeto;


        Nodo Aux = new Nodo();
        Aux.der = null;
        Aux .izq = null;
        sujeto.izq = Aux;

        Nodo Complemento = new Nodo();
        Nodo Q_Mark = new Nodo();
        Q_Mark.izq = null;
        Q_Mark.der = null;
        Complemento.izq = null;
        Complemento.der = Q_Mark;
    }

    public void crear_arbolInt_Negativo(){
        Nodo verbo = new Nodo();
        raiz = verbo;

        Nodo auxNot = new Nodo();
        auxNot.value="not";
        verbo.izq = auxNot;
        Nodo Aux = new Nodo();
        Aux.izq = null;
        Aux.der = null;
        Nodo sujeto = new Nodo();
        sujeto.izq = null;
        sujeto.der = null;
        auxNot.izq = Aux;
        auxNot.der = sujeto;

        Nodo Complemento = new Nodo();
        Nodo Q_Mark = new Nodo();
        Q_Mark.izq = null;
        Q_Mark.der = null;
        Complemento.izq = null;
        Complemento.der = Q_Mark;



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
              System.out.print(reco.value + "\n");
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