package sample;

/**
 * Created by Alex on 12/03/16.
 */
public class Token {
    private String[] auxiliar = {"do","does"};
    private String[] sujeto = {"i","you","he","she","it","we","you","they"};
    private String[] verbos = {"play","clean","study"};


    String ID;
    String value;

    public Token(String value){
        // encontrar el tipo de token

        this.value = value;

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

        this.ID = "Complemento";
    }


}
