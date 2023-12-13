package Utils;

public class Numeros {

    public static boolean isNumeric(String value){

        if(value == null || value.isEmpty()) return false;
        try{
            Integer.parseInt(value);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
}
