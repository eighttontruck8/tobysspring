package org.example;

public class PaymentService {
    public Payment prepare() { // payment타입 객체를 return하는 prepare함수(매개변수x)
        return new Payment();
    }

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(); // 변수에 담아야 함
        System.out.println(payment); // 페이먼트 타입이 출력됨
    }

}
