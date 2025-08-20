public class Dessert {
    static int numDesserts;
    int flavor; int price;
    public Dessert (int f, int p) {
        this.flavor = f;
        this.price = p;
        numDesserts ++;
    }
    public void printDessert() {
        System.out.println(flavor + " " + price + " " + numDesserts);
    }
    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
