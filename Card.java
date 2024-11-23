package highlowsim;

public class Card {
    protected int value;
    
    public Card(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public String toString() {
        return String.valueOf(value);
    }
    
    public boolean equals(Object o) {
        return this.value == ((Card) o).value;
    }
}
