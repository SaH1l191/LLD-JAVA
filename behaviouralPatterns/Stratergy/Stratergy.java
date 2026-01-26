package Stratergy;

interface PaymentStrategy {

    void processPayment();
}

class CreditCardPayment implements PaymentStrategy {

    public void processPayment() {
        System.out.println("Processing credit card payment...");
    }
}

class PayPalPayment implements PaymentStrategy {

    public void processPayment() {
        System.out.println("Processing PayPal payment...");
    }
}

class CryptoPayment implements PaymentStrategy {

    public void processPayment() {
        System.out.println("Processing crypto payment...");
    }
}

// Concrete strategy for Stripe payment
class StripePayment implements PaymentStrategy {

    public void processPayment() {
        System.out.println("Processing Stripe payment...");
    }
}

class PaymentProcessor {

    private PaymentStrategy paymentStrategy;

    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment() {
        paymentStrategy.processPayment();
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}

public class Stratergy {

    public static void main(String[] args) {
        PaymentStrategy creditCard = new CreditCardPayment();
        PaymentProcessor paypalProcessor = new PaymentProcessor(new PayPalPayment());
        paypalProcessor.processPayment();
        creditCard.processPayment();
    }
}
