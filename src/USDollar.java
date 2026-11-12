
public class USDollar implements CurrencyStrategy {

    @Override
    public float changeTo(float amount) {
        return amount * 3.75f;
    }

    @Override
    public String getSymbol() {
        return "$";
    }
}