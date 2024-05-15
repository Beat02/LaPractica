package laPractica;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class PreguntaMatematicas extends Pregunta{
    private final String[] arraySimbolos = {"+", "-", "*"};
    private final int[] rangoDeNumerosMaximo = {2, 12};
    private final int[] rangoDeNumerosMaximoEnCalculo = {4, 8};

    public PreguntaMatematicas() {
        super(new TipoPregunta("Mates"));
        enunciadoRespuesta = generarCalculoAleatorio();
    }

    private EnunciadoRespuesta generarCalculoAleatorio(){
        int cantidadNumeros = (int)(Math.random() * (rangoDeNumerosMaximoEnCalculo[1] - rangoDeNumerosMaximoEnCalculo[0] + 1) + rangoDeNumerosMaximoEnCalculo[0]);
        String calculo = "";
        for (int i = 0; i < cantidadNumeros; i++) {
            calculo += (int)(Math.random() * (rangoDeNumerosMaximo[1] - rangoDeNumerosMaximo[0] + 1) + rangoDeNumerosMaximo[0]);
            if (i < cantidadNumeros - 1) {
                calculo += arraySimbolos[(int)(Math.random() * arraySimbolos.length)];
            }
        }
        Expression expression = new ExpressionBuilder(calculo).build();
        int resultado = (int)expression.evaluate();
        System.out.println("Resultado: " + resultado);
        return new EnunciadoRespuesta(calculo, String.valueOf(resultado));
    }
}
