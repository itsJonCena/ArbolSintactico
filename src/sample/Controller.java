package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

    Alert Result = new Alert(Alert.AlertType.ERROR);

    ArbolB arbolAfirmativo = new ArbolB();
    ArbolB arbolNegativo = new ArbolB();
    ArbolB arbolInterrogativo  = new ArbolB();
    ArbolB arbolInt_Negativo  = new ArbolB();

    LinkedList list = new LinkedList();
    String[] values;

    @FXML private void analizar(){

        leerArboles();

        list.clear();
        tfAfirmativo.clear();
        tfInterrogativo.clear();
        tfNegativo.clear();
        tfInt_Negativo.clear();

        String sujeto;
        String auxiliar;
        String verbo;
        String complement;

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

                    sujeto = ((Token) list.get(0)).value;

                    if (sujeto.compareTo("he") ==0 || sujeto.compareTo("she") ==0 || sujeto.compareTo("it") ==0){
                        auxiliar = "does";
                    }else {
                        auxiliar = "do";
                    }

                    verbo = ((Token) list.get(1)).value;
                    complement = ((Token) list.get(2)).value;

                    arbolNegativo.setInOrder(new String[]{sujeto,auxiliar,verbo,complement});
                    arbolInterrogativo.setInOrder(new String[]{auxiliar,sujeto,verbo,complement});
                    arbolInt_Negativo.setInOrder(new String[]{auxiliar,sujeto,verbo,complement});

                    tfNegativo.setText(arbolNegativo.getRecoValues());
                    tfInterrogativo.setText(arbolInterrogativo.getRecoValues());
                    tfInt_Negativo.setText(arbolInt_Negativo.getRecoValues());

                    
                    break;

                case "Negativo1":
                    System.out.println("Oracion Negativo1");
                    break;

                case "Negativo2":
                    System.out.println("Oracion Negativo2");

                    sujeto = ((Token) list.get(0)).value;
                    auxiliar = ((Token) list.get(1)).value;
                    verbo = ((Token) list.get(3)).value;
                    complement = ((Token) list.get(4)).value;


                    arbolAfirmativo.setInOrder(new String[]{sujeto,verbo,complement});
                    arbolInterrogativo.setInOrder(new String[]{auxiliar,sujeto,verbo,complement});
                    arbolInt_Negativo.setInOrder(new String[]{auxiliar,sujeto,verbo,complement});


                    tfAfirmativo.setText(arbolAfirmativo.getRecoValues());
                    tfInterrogativo.setText(arbolInterrogativo.getRecoValues());
                    tfInt_Negativo.setText(arbolInt_Negativo.getRecoValues());

                    break;

                case "Interrogativo":
                    System.out.println("Oracion Interrogativa");
                    // do i play piano ?

                    auxiliar = ((Token) list.get(0)).value;
                    sujeto = ((Token) list.get(1)).value;
                    verbo = ((Token) list.get(2)).value;
                    complement = ((Token) list.get(3)).value;

                    arbolAfirmativo.setInOrder(new String[]{sujeto,verbo,complement});
                    arbolInt_Negativo.setInOrder(new String[]{auxiliar,sujeto,verbo,complement});
                    arbolNegativo.setInOrder(new String[]{sujeto,auxiliar,verbo,complement});


                    tfAfirmativo.setText(arbolAfirmativo.getRecoValues());
                    tfNegativo.setText(arbolNegativo.getRecoValues());
                    tfInt_Negativo.setText(arbolInt_Negativo.getRecoValues());

                    break;

                case "Int_Negativo":
                    System.out.println("Oracion Interrogativa negativa");

                    auxiliar = ((Token) list.get(0)).value;
                    sujeto = ((Token) list.get(2)).value;
                    verbo = ((Token) list.get(3)).value;
                    complement = ((Token) list.get(4)).value;

                    arbolAfirmativo.setInOrder(new String[]{sujeto,verbo,complement});
                    arbolNegativo.setInOrder(new String[]{sujeto,auxiliar,verbo,complement});
                    arbolInterrogativo.setInOrder(new String[]{auxiliar,sujeto,verbo,complement});

                    tfAfirmativo.setText(arbolAfirmativo.getRecoValues());
                    tfNegativo.setText(arbolNegativo.getRecoValues());
                    tfInterrogativo.setText(arbolInterrogativo.getRecoValues());

                    break;
            }


            /*for (Object aux: list) {

                taSalida.appendText(((Token) aux).ID + "\t");

            }*/

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
                    String aux = ((Token) oracion.get(index-1)).value;


                    if (((Token)oracion.get(index)).ID.compareTo("Sujeto") == 0 ){
                        String auxSujeto = ((Token) oracion.get(index)).value;

                        if (aux.compareTo("does") == 0){
                            if (auxSujeto.compareTo("he")==0 || auxSujeto.compareTo("she")==0 || auxSujeto.compareTo("it")==0){
                                Estado = 2;
                            }else {
                                Result.setTitle("Error");
                                Result.setHeaderText("Pronombre: "+auxSujeto+" o Auxiliar: "+aux+" incorrecto");
                                Result.showAndWait();
                                return "";
                            }

                        }else if (auxSujeto.compareTo("he")==0 || auxSujeto.compareTo("she")==0 || auxSujeto.compareTo("it")==0){
                            Result.setTitle("Error");
                            Result.setHeaderText("Pronombre incorrecto: "+auxSujeto);
                            Result.showAndWait();
                            return "";
                        }else {
                            Estado = 2;

                        }

                    } else if (((Token)oracion.get(index)).ID.compareTo("aux_negativo") == 0 ){
                        Estado = 6;
                    }

                    break;
                case 2:
                    if (((Token)oracion.get(index)).ID.compareTo("Verbo") == 0 ){
                        Estado = 3;
                    }else {
                        Result.setTitle("Error");
                        Result.setHeaderText("Esperaba un verbo: "+((Token)oracion.get(index)).value);
                        Result.showAndWait();
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
                    }else {
                        Result.setTitle("Error");
                        Result.setHeaderText("Esperaba un verbo: "+((Token)oracion.get(index)).value);
                        Result.showAndWait();
                        return "";
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
                        Estado = 18;
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
                    if (((Token)oracion.get(index)).ID.compareTo("Verbo") == 0 ){
                        Estado = 16;
                    }

                    break;
                case 16:
                    if (((Token)oracion.get(index)).ID.compareTo("Complemento") == 0 ){
                        Estado = 17;
                    }

                    break;
                case 17:
                    salida = "Negativo1";

                    break;
                case 18:
                    if (((Token)oracion.get(index)).ID.compareTo("aux_negativo") == 0 ){
                        Estado = 19;
                    }


                    break;
                case 19:
                    if (((Token)oracion.get(index)).ID.compareTo("Verbo") == 0 ){
                        Estado = 20;
                    }

                    break;
                case 20:
                    if (((Token)oracion.get(index)).ID.compareTo("Complemento") == 0 ){
                        Estado = 21;
                    }

                    break;
                case 21:
                    salida = "Negativo2";
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


    public void crearArboles(){
        arbolAfirmativo = new ArbolB();
        arbolAfirmativo.setName("Arbol_Afirmativo");
        arbolAfirmativo.crear_arbolAfirmativo();
        guardarAlrbol(arbolAfirmativo);

        arbolNegativo = new ArbolB();
        arbolNegativo.setName("Arbol_Negativo");
        arbolNegativo.crear_arbolNegativo2();
        guardarAlrbol(arbolNegativo);


        arbolInterrogativo = new ArbolB();
        arbolInterrogativo.setName("Arbol_Interrogativo");
        arbolInterrogativo.crear_arbolInte();
        guardarAlrbol(arbolInterrogativo);

        arbolInt_Negativo = new ArbolB();
        arbolInt_Negativo.setName("Arbol_Interrogativo_Negativo");
        arbolInt_Negativo.crear_arbolInt_Negativo();
        guardarAlrbol(arbolInt_Negativo);


    }

    private void guardarAlrbol(ArbolB tree ){
        try {
            final FileOutputStream fo = new FileOutputStream(tree.name+".bin");
            final ObjectOutputStream oos = new ObjectOutputStream(fo);
            oos.writeObject(tree);
            oos.flush();
            oos.close();

        }catch (FileNotFoundException e){
            System.out.println(e);
        }catch (IOException e){

        }
    }

    private void leerArboles(){
        String[] strings ={"Arbol_Afirmativo.bin","Arbol_Negativo.bin","Arbol_Interrogativo.bin","Arbol_Interrogativo_Negativo.bin"};
        ArbolB[] arboles = {arbolAfirmativo,arbolNegativo,arbolInterrogativo,arbolInt_Negativo};
        int index =0;

        for (String name:strings) {

            try
            {
                final FileInputStream fis = new FileInputStream(name);
                final ObjectInputStream ois = new ObjectInputStream(fis);
                final Object deserializedObject = ois.readObject();

                System.out.println("Tipo de objeto " + deserializedObject.getClass().getName());

                if (deserializedObject instanceof ArbolB) {
                    arboles[index] = (ArbolB) deserializedObject;
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

            index++;
        }
        arbolAfirmativo = arboles[0];
        arbolNegativo = arboles[1];
        arbolInterrogativo = arboles[2];
        arbolInt_Negativo = arboles[3];
    }

    @Override
    public void initialize(URL arg, ResourceBundle rb) {
        //crearArboles();
        leerArboles();
    }

}