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
        int index=0, Estado = 0;
        String salida = "";

        while (index <= oracion.size()){

            switch (Estado){
                case 0:
                    switch (((Token)oracion.get(index)).ID){
                        case "Auxiliar":
                            Estado = 1;
                            break;

                        case "Sujeto":
                            Estado = 11;
                            break;

                    }

                    break;
                case 1:
                    if (((Token)oracion.get(index)).ID.compareTo("Sujeto") == 0 ){
                        Estado = 2;
                    } else if (((Token)oracion.get(index)).ID.compareTo("aux_negativo") == 0 ){
                        Estado = 6;
                    }

                    // Estado de Error
                    break;
                case 2:
                    if (((Token)oracion.get(index)).ID.compareTo("Verbo") == 0 ){
                        Estado = 3;
                    }else {

                        // Estado de error
                        System.out.println("Error: Esperaba un verbo");
                        return "";
                    }

                    break;
                case 3:

                    if (((Token)oracion.get(index)).ID.compareTo("Complemento") == 0 ){
                        Estado = 4;
                    }

                    break;
                case 4:

                    if (((Token)oracion.get(index)).ID.compareTo("Q_Mark") == 0 ){
                        Estado = 5;
                    }

                    break;
                case 5:
                    salida = "Interrogativo";
                    break;
                case 6:
                    if (((Token)oracion.get(index)).ID.compareTo("Sujeto") == 0 ){
                        Estado = 7;
                    }
                    break;
                case 7:
                    if (((Token)oracion.get(index)).ID.compareTo("Verbo") == 0 ){
                        Estado = 8;
                    }

                    break;
                case 8:
                    if (((Token)oracion.get(index)).ID.compareTo("Complemento") == 0 ){
                        Estado = 9;
                    }

                    break;
                case 9:

                    if (((Token)oracion.get(index)).ID.compareTo("Q_Mark") == 0 ){
                        Estado = 10;
                    }

                    break;
                case 10:

                    salida = "Int_Negativo";

                    break;
                case 11:

                    if (((Token)oracion.get(index)).ID.compareTo("Verbo") == 0 ){
                        Estado = 12;
                    }

                    if (((Token)oracion.get(index)).ID.compareTo("Verb_to_be") == 0 ){
                        Estado = 14;
                    }

                    if (((Token)oracion.get(index)).ID.compareTo("Auxiliar") == 0 ){
                        Estado = 17;
                    }

                    break;
                case 12:

                    if (((Token)oracion.get(index)).ID.compareTo("Complemento") == 0 ){
                        Estado = 13;
                    }

                    break;
                case 13:

                    salida = "Afirmativo";

                    break;
                case 14:
                    if (((Token)oracion.get(index)).ID.compareTo("aux_negativo") == 0 ){
                        Estado = 15;
                    }

                    break;
                case 15:
                    if (((Token)oracion.get(index)).ID.compareTo("Complemento") == 0 ){
                        Estado = 16;
                    }

                    break;
                case 16:
                    salida = "Negativo";

                    break;
                case 17:
                    if (((Token)oracion.get(index)).ID.compareTo("aux_negativo") == 0 ){
                        Estado = 18;
                    }

                    break;
                case 18:
                    if (((Token)oracion.get(index)).ID.compareTo("Verbo") == 0 ){
                        Estado = 19;
                    }

                    break;
                case 19:
                    if (((Token)oracion.get(index)).ID.compareTo("Complemento") == 0 ){
                        Estado = 20;
                    }

                    break;
                case 20:
                    salida = "Negativo";
                    break;
            }

            index++;


        }
        switch (Estado){
            case 5:
                salida = "Interrogativo";
                break;

            case 10:
                salida = "Int_Negativo";
                break;

            case 13:
                salida = "Afirmativo";
                break;

            case 16:
                salida = "Negativo";
                break;

            case 20:
                salida = "Negativo";
                break;
        }

        return salida;
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