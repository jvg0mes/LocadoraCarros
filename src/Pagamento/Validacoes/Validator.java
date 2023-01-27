package Pagamento.Validacoes;


import java.util.Arrays;
import java.util.List;

public class Validator {


    public static boolean validaCpf(String cpf){

        Character[] numbers = {'0','1','2','3','4',
                '5','6','7','8','9'};

        List<Character> numbersL = Arrays.stream(numbers).toList();

        if (cpf.length() != 14){
            return false;
        }

        if (cpf.charAt(3) != '-' | cpf.charAt(7) != '-' | cpf.charAt(11) != '.'){
            return false;
        }

        String[] cpfPieces= cpf.split("\\.");

        if (cpfPieces[1].length()!=2){
            return false;
        }

        if(!Validator.checkIfAllCharsAreNumeric(cpfPieces[1])){
            return false;
        }


        String[] firstPiece = cpfPieces[0].split("-");

        for (String p: firstPiece){

            if(!Validator.checkIfAllCharsAreNumeric(p)){
                return false;
            }

        }

        return true;

    }

    public static boolean checkIfAllCharsAreNumeric(String numericString){

        Character[] numbers = {'0','1','2','3','4',
                '5','6','7','8','9'};

        List<Character> numbersL = Arrays.stream(numbers).toList();


        for(int i=0;i<numericString.length();i++){
            if(!numbersL.contains(numericString.charAt(i))){
                return false;
            }
        }

        return true;
    }

}


