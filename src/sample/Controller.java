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
    @FXML TextArea taSalida;

    ArbolB arbolAfirmativo = new ArbolB();

    @FXML private void analizar(){
        taSalida.clear();
        try {
            String oracion = tfOracion.getText();

            String[] split = oracion.split(" ");

            //System.out.println(split[0].charAt(split[0].length()-1));

            LinkedList list = new LinkedList();
            for (String aux: split) {
                list.add(new Token(aux));


            }

            for (Object aux: list) {
                taSalida.appendText(((Token) aux).ID + "\t");


            }


        }catch (Exception e){

        }
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