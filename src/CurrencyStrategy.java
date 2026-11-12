public interface CurrencyStrategy {
    float changeTo(float amount);
    String getSymbol();
}