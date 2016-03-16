package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML TextField tfOracion;
    @FXML TextField tfAfirmativo;
    @FXML TextField tfNegativo;
    @FXML TextField tfInterrogativo;
    @FXML TextField tfInt_Negativo;

    ArbolB arbolAfirmativo = new ArbolB();
    ArbolB arbolNegativo = new ArbolB();
    ArbolB arbolInterrogativo = new ArbolB();
    ArbolB arbolInt_Negativo = new ArbolB();
    LinkedList list = new LinkedList();

    @FXML private void analizar(){
        list.clear();
        tfAfirmativo.clear();
        tfInterrogativo.clear();
        tfNegativo.clear();
        tfInt_Negativo.clear();

        try {
            String oracion = tfOracion.getText();

            String[] split = oracion.split(" ");
            for (String aux: split) {
                list.add(new Token(aux));
            }

            // Identifificar a cual sintaxis pertenece
            // Afirmativo, Negativo, pregunta, pregunta negativa.


            switch (tipoOracion(list)){
                case "Afirmativo":
                    System.out.println("Oracion afirmativa");

                    // llenar arboles e imprimir en pre-order
                    String sujeto = ((Token) list.get(0)).value;
                    String verbo = ((Token) list.get(1)).value;
                    String complement = ((Token) list.get(2)).value;
                    System.out.println("Sujeto de la lista: "+sujeto);
                    arbolInt_Negativo.crear_arbolNegativo(sujeto,verbo,complement);

                    break;

                case "Negativo":
                    System.out.println("Oracion Negativo");
                    break;

                case "Interrogativo":
                    System.out.println("Oracion Interrogativa");
                    break;

                case "Int_Negativo":
                    System.out.println("Oracion Interrogativa negativa");
                    break;
            }


            /*for (Object aux: list) {

                taSalida.appendText(((Token) aux).ID + "\t");

            }*/

            arbolInt_Negativo.preOrder();

        }catch (Exception e){
            System.out.println("Error: "+e);
        }
    }


    private String tipoOracion(LinkedList oracion){

        if (((Token)oracion.get(0)).ID.compareTo("Auxiliar") == 0){
            if (((Token)oracion.get(1)).ID.compareTo("Sujeto") == 0){
                if (((Token)oracion.get(2)).ID.compareTo("Auxiliar") == 0){
                    if (((Token)oracion.get(3)).ID.compareTo("Verbo") == 0){
                        return "Afirmativo";
                    }else if (((Token)oracion.get(3)).ID.compareTo("aux_negativo") == 0){
                        return "Int_Negativo";
                    }else {
                        System.out.println("Error");
                    }
                }else if (((Token)oracion.get(2)).ID.compareTo("Verbo") == 0){
                    if (((Token)oracion.get(3)).ID.compareTo("Complemento") == 0){
                        if (((Token)oracion.get(4)).ID.compareTo("Q_Mark") == 0){
                            return "Interrogativo";
                        }else {
                            System.out.println("Error: Q_Mark");
                        }
                    }
                }else if (((Token)oracion.get(2)).ID.compareTo("aux_negativo") == 0){
                    if (((Token)oracion.get(3)).ID.compareTo("Verbo") == 0){
                        if (((Token)oracion.get(4)).ID.compareTo("Complemento") == 0){
                            if (((Token)oracion.get(5)).ID.compareTo("Q_Mark") == 0){
                                return "Int_Negativo";
                            }else {
                                System.out.println("Error: Falta Question Mark");
                            }
                        }else {
                            System.out.println("Error: Falta Complemento");
                        }
                    }else{
                        System.out.println("Error: Falta verbo");
                    }
                }else {
                    System.out.println("Error: Falta auxiliar negativo");
                }

            }else {

                System.out.println("Error");
                //System.exit(0);
            }
        }else if (((Token)oracion.get(0)).ID.compareTo("Sujeto") == 0){
            if (((Token)oracion.get(1)).ID.compareTo("Verb_to_be") == 0){
                if (((Token)oracion.get(2)).ID.compareTo("aux_negativo") == 0){
                    return "Negativo";
                }else {
                    if (((Token)oracion.get(2)).ID.compareTo("Verbo") == 0){
                        if (((Token)oracion.get(3)).ID.compareTo("Complemento") == 0){
                            return "Afirmativo";
                        }
                    } else {
                        System.out.println("Error");
                    }
                }
            }else if (((Token)oracion.get(1)).ID.compareTo("Auxiliar") == 0){
                if (((Token)oracion.get(2)).ID.compareTo("aux_negativo") == 0){
                    if (((Token)oracion.get(3)).ID.compareTo("Verbo") == 0){
                        if (((Token)oracion.get(4)).ID.compareTo("Complemento") == 0){
                            return "Negativo";
                        }

                    }
                }else {
                    System.out.println("Error");
                }

            }else {
                System.out.println("Error");
            }
        }else {
            System.out.println("Error");
        }


        return "";
    }



    private void guardarAlrbol(){
        try {
            final FileOutputStream fo = new FileOutputStream("arbolAfirmativo.bin");
            final ObjectOutputStream oos = new ObjectOutputStream(fo);
            oos.writeObject(arbolAfirmativo);
            oos.flush();
            oos.close();

        }catch (FileNotFoundException e){
            System.out.println(e);
        }catch (IOException e){

        }
    }

    private void leerArbol(){
        try
        {
            final FileInputStream fis = new FileInputStream("arbolAfirmativo.bin");
            final ObjectInputStream ois = new ObjectInputStream(fis);
            final Object deserializedObject = ois.readObject();

            System.out.println("Tipo de objeto " + deserializedObject.getClass().getName());

            if (deserializedObject instanceof ArbolB) {
                arbolAfirmativo = (ArbolB) deserializedObject;
            }
            else {
                System.out.println("No se esperaba " + deserializedObject.getClass().getName());
            }
            ois.close();

        }
        catch (Exception ex)
        {
            System.out.println("Exeption <<<<: "+ex);
        }
    }

    @Override
    public void initialize(URL arg, ResourceBundle rb) {
    }

}