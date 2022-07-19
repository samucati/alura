package br.com.alura.ecommerce;

import br.com.alura.ecommerce.model.dto.Email;
import br.com.alura.ecommerce.model.dto.Order;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderServlet extends HttpServlet {

    private final KafkaDispatcher<Order> orderDispatcher = new KafkaDispatcher();
    private final KafkaDispatcher<Email> emailDispatcher = new KafkaDispatcher();

    @Override
    public void destroy() {
        super.destroy();
        emailDispatcher.close();;
        orderDispatcher.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            var email = req.getParameter("email");

            var orderId = UUID.randomUUID().toString();
            var amount = new BigDecimal(req.getParameter("amount"));
            var emailEntity = new Email(email, "Email test");
            var order = new Order(orderId, amount, emailEntity);

            orderDispatcher.send(ProducersNames.ECOMMERCE_NEW_ORDER.name(), email, order);
            emailDispatcher.send(ProducersNames.ECOMMERCE_SEND_EMAIL.name(), email, emailEntity);

            System.out.println("New order sent successfully.");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("New order sent.");
        } catch (ExecutionException | InterruptedException e) {
            throw new ServletException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
