package sample;


import java.io.Serializable;

public class ArbolB implements Serializable{

    Nodo raiz;
    private String recoValues ="";

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
    public void crear_arbolAfirmativo(String sSujeto, String sVerbo, String sComplemento){
        Nodo verbo = new Nodo();
        verbo.value = sVerbo;

        Nodo sujeto = new Nodo();
        sujeto.value = sSujeto;
        sujeto.izq = null;
        sujeto.der = null;
        Nodo complemento = new Nodo();
        complemento.value = sComplemento;
        complemento.izq = null;
        complemento.der = null;
        verbo.izq = sujeto;
        verbo.der = complemento;
        raiz = verbo;

    }

    public void crear_arbolNegativo2(String sSujeto, String sVerbo, String sComplemento,String sAuxiliar){
        Nodo verbo = new Nodo();
        verbo.value = sVerbo; //
        raiz = verbo;

        Nodo sujeto = new Nodo();
        sujeto.value = sSujeto; //
        sujeto.der = null;
        sujeto.izq = null;

        Nodo auxNot = new Nodo();
        auxNot.value = "not";
        auxNot.izq = null;
        auxNot.der = null;

        Nodo complemento = new Nodo();
        complemento.value = sComplemento; //
        complemento.der = null;
        complemento.izq = null;

        Nodo Auxiliar = new Nodo();
        Auxiliar.value = sAuxiliar; //


        Auxiliar.izq = sujeto;
        Auxiliar.der = auxNot;

        verbo.der = complemento;
        verbo.izq = Auxiliar;



    }

    public void crear_arbolNegativo1(String sSujeto, String sVerbo, String sComplemento){
        Nodo verbo = new Nodo();
        verbo.value = sVerbo; //
        raiz = verbo;

        Nodo sujeto = new Nodo();
        sujeto.value = sSujeto; //
        sujeto.der = null;
        sujeto.izq = null;

        Nodo auxNot = new Nodo();
        auxNot.value = "not";
        Nodo complemento = new Nodo();
        complemento.value = sComplemento; //
        complemento.der = null;
        complemento.izq = null;

        verbo.izq = sujeto;
        verbo.der = auxNot;
        auxNot.der = complemento;
        auxNot.izq = null;
    }

    public void crear_arbolInte(String sSujeto, String sVerbo, String sComplemento,String sAuxiliar){
        Nodo verbo = new Nodo();
        verbo.value = sVerbo; //
        raiz = verbo;

        Nodo sujeto = new Nodo();
        sujeto.value = sSujeto; //
        sujeto.der = null;
        verbo.izq = sujeto;


        Nodo Aux = new Nodo();
        Aux.value = sAuxiliar; //
        Aux.der = null;
        Aux .izq = null;
        sujeto.izq = Aux;

        Nodo Complemento = new Nodo();
        Complemento.value = sComplemento; //
        Nodo Q_Mark = new Nodo();
        Q_Mark.value = "?";
        Q_Mark.izq = null;
        Q_Mark.der = null;
        Complemento.izq = null;
        Complemento.der = Q_Mark;

        verbo.der = Complemento;
    }

    public void crear_arbolInt_Negativo( String sSujeto, String sVerbo, String sComplemento,String sAuxiliar ){
        Nodo verbo = new Nodo();
        verbo.value = sVerbo; //
        raiz = verbo;

        Nodo auxNot = new Nodo();
        auxNot.value="not";
        verbo.izq = auxNot;
        Nodo Aux = new Nodo();
        Aux.value = sAuxiliar; //
        Aux.izq = null;
        Aux.der = null;
        Nodo sujeto = new Nodo();
        sujeto.value = sSujeto;
        sujeto.izq = null;
        sujeto.der = null;
        auxNot.izq = Aux;
        auxNot.der = sujeto;

        Nodo Complemento = new Nodo();
        Complemento.value = sComplemento;
        Nodo Q_Mark = new Nodo();
        Q_Mark.value = "?";
        Q_Mark.izq = null;
        Q_Mark.der = null;
        Complemento.izq = null;
        Complemento.der = Q_Mark;

        verbo.der = Complemento;



    }

    private void setInOrder (Nodo reco){
        if (reco != null)
        {
            setInOrder (reco.izq);
            //System.out.print(reco.info + " ");
            //reco.value
            this.recoValues = recoValues+" "+reco.value;
            setInOrder (reco.der);
        }

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
              //System.out.print(reco.info + " ");
              this.recoValues = recoValues+" "+reco.value;
              inOrder (reco.der);
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

    public String getRecoValues(){
        inOrder();
        return this.recoValues;
    }
}