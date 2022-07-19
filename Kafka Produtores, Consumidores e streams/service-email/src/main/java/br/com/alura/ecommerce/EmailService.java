package br.com.alura.ecommerce;

import br.com.alura.ecommerce.model.dto.Email;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.HashMap;

public class EmailService {

    public static void main(String[] args) {
        var emailService = new EmailService();
        try(var service = new KafkaService<>(EmailService.class.getSimpleName(),
                ProducersNames.ECOMMERCE_SEND_EMAIL.name(),
                emailService::parse,
                Email.class,
                new HashMap<>())) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Email> record) {
        System.out.println("-------------------->----------------------");
        System.out.println("Sending email...");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // ignoring
            e.printStackTrace();
        }
        System.out.println("Email sent!");
    }

}
