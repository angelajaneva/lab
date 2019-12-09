package mk.ukim.finki.wp.lab.web.servlets;

import mk.ukim.finki.wp.lab.model.Order;
import org.springframework.context.event.EventListener;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "pizza_order", urlPatterns = "/PizzaOrder.do")
public class PizzaOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public PizzaOrderServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @EventListener
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req, resp, req.getServletContext());

        //grB
        Order order = (Order) req.getSession().getAttribute("order");

        context.setVariable("pizzaName", order.getPizzaType());
        context.setVariable("pizzaSize", order.getPizzaSize());


        //grA
//        String pizzaName = (String) req.getSession().getAttribute("pizzaName");
//        String pizzaSize = (String) req.getSession().getAttribute("pizzaSize");
//
//        context.setVariable("pizzaName", pizzaName);
//        context.setVariable("pizzaSize", pizzaSize);

        resp.setContentType("text/html");
        springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");

        //grB
        Order order = (Order) req.getSession().getAttribute("order");
        order.setClientName(clientName);
        order.setClientAddress(clientAddress);
        req.getSession().setAttribute("order", order);

        resp.sendRedirect("/ConfirmationInfo.do");

        //grA
//        req.getSession().setAttribute("clientName", clientName);
//        req.getSession().setAttribute("clientAddress", clientAddress);
//        resp.sendRedirect("/ConfirmationInfo.do");
    }
}
