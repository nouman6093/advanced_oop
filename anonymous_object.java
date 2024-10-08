public class Calculator {
    public int add(int a, int b){
        return a + b;
    }
}

public class Main {
    public static void main(String[] args){
        System.out.println(new Calculator().add(5, 15));
    }
}
