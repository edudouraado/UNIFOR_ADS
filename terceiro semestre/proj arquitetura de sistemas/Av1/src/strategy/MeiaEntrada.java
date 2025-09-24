package strategy;

public class MeiaEntrada implements CalculoIngresso {
    @Override
    public double calcular(double valorBase) {
        return valorBase * 0.5;
    }
}