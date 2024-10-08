public class InvalidAgeException extends Exception{
    InvalidAgeException(String message) {
        super(message);
    }
}

public class Person {
    private int age;

    public void setAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150){
            throw new InvalidAgeException("Age must be between 0 and 150");
        } else {
            this.age = age;
        }
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Person p = new Person();

        try {
            System.out.print("Enter Age: ");
            int age = sc.nextInt();

            p.setAge(age);
        } catch (InvalidAgeException e){
            System.out.println(e.getMessage());
        }
    }
}
