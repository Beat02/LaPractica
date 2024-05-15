package laPractica;

public class Util {
    public static String RandomString() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int index = (int)(letras.length() * Math.random());
            sb.append(letras.charAt(index));
        }
        return sb.toString();
    }

    public static String RandomBetweenAandD() {
        return String.valueOf((char)('A' + (int)(4 * Math.random())));
    }
}
