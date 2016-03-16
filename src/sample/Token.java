package sample;

/**
 * Created by Alex on 12/03/16.
 */
public class Token {
    private String[] auxiliar = {"do","does"};
    private String[] sujeto = {"i","you","he","she","it","we","you","they"};
    private String[] verbos = {"play","clean","study","like","live","have"};
    private String[] verb_to_be = {"am","is","are",};


    String ID;
    String value;

    public Token(){

    }


    public Token(String value){
        // encontrar el tipo de token

        this.value = value;

        if (value.compareTo("not") == 0){
            this.ID = "aux_negativo";
            return;
        }

        if (value.compareTo("?") ==0){
            this.ID = "Q_Mark";
            return;
        }

        for (String aux: auxiliar) {
            if (value.compareTo(aux) == 0){
                this.ID = "Auxiliar";
                return;
            }
        }

        for (String aux: sujeto) {
            if (value.compareTo(aux) == 0){
                this.ID = "Sujeto";
                return;
            }
        }

        for (String aux: verbos) {
            if (value.compareTo(aux) == 0){
                this.ID = "Verbo";
                return;
            }
        }

        for (String aux: verb_to_be) {
            if (value.compareTo(aux) == 0){
                this.ID = "Verb_to_be";
                return;
            }
        }

        this.ID = "Complemento";


    }

    public boolean isComplement(String value){
        if (value.compareTo("not") == 0) {
            return false;
        }

        if (value.compareTo("?") ==0){
            return false;
        }

        for (String aux: auxiliar) {
            if (value.compareTo(aux) == 0){
                return false;
            }
        }

        for (String aux: sujeto) {
            if (value.compareTo(aux) == 0){
                return false;
            }
        }

        for (String aux: verbos) {
            if (value.compareTo(aux) == 0){
                return false;
            }
        }

        for (String aux: verb_to_be) {
            if (value.compareTo(aux) == 0){
                return false;
            }
        }

        return true;
        }


    }


