/**
 * Created by Alperen on 23.07.2022.
 */
public class CustomException {
    public static void main(String[] args) {

        int x = 11; // 11 is prime
        int y = 15; // Throws an error

        try {
            new PrimeNumberChecker(x);
            new PrimeNumberChecker(y); // Throw error
        } catch (PrimeNumberException e) {
            e.printStackTrace();
        }
    }
}

// This exception throws an error when number is not prime
class PrimeNumberException extends Exception {
    public PrimeNumberException(String msg) {
        super(msg);
    }
}

// Prime number checker class throws PrimeNumberException
class PrimeNumberChecker {
    int data;

    public PrimeNumberChecker(int data) throws PrimeNumberException {
        this.data = data;
        checkNumber(data);
    }

    private void checkNumber(int data) throws PrimeNumberException {
        for (int i = 2; i < data; i++) {
            if (data % i == 0) {
                throw new PrimeNumberException(data + " is not prime");
            }
        }
    }
}