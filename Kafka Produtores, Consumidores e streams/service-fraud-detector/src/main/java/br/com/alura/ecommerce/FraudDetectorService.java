package br.com.alura.ecommerce;

import br.com.alura.ecommerce.model.dto.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class FraudDetectorService {

    public static void main(String[] args) {
        var fraudService = new FraudDetectorService();
        try(var service = new KafkaService<>(FraudDetectorService.class.getSimpleName(),
                ProducersNames.ECOMMERCE_NEW_ORDER.name(),
                fraudService::parse,
                Order.class,
                new HashMap<>())) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Order> record) throws ExecutionException, InterruptedException {
        System.out.println("------------------------------------------");
        System.out.println("Processing new order, checking for fraud");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignoring
            e.printStackTrace();
        }

        var order = record.value();

        if (isFraud(order)){
            System.out.println("Order is Fraud!!! " + order);
            dispatcher.send(ProducersNames.ECOMMERCE_ORDER_REJECTED.name(), order.getUserEmail().getSubject(), order);
        } else {
            System.out.println("Order aproved!!! " + order);
            dispatcher.send(ProducersNames.ECOMMERCE_ORDER_APROVED.name(), order.getUserEmail().getSubject(), order);
        }

        System.out.println("Order processed");
    }

    private final KafkaDispatcher<Order> dispatcher = new KafkaDispatcher<>();

    private boolean isFraud(Order order) {
        return order.getAmount().compareTo(new BigDecimal("4500.00")) >= 0;
    }
}
