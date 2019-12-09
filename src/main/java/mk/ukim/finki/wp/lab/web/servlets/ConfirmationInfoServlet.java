package mk.ukim.finki.wp.lab.web.servlets;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.service.OrderService;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "confirmation_info", urlPatterns = "/ConfirmationInfo.do")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        //grB
        Order order = (Order) req.getSession().getAttribute("order");

        order = orderService.placeOrder(order.getPizzaType(), order.getPizzaSize(), order.getClientName(), order.getClientAddress());
        String ipAddress = req.getRemoteHost();
        String browser = req.getHeader("User-agent");


        webContext.setVariable("order", order);
        webContext.setVariable("ipAddress", ipAddress);
        webContext.setVariable("browser", browser);

        resp.setContentType("text/html");
        springTemplateEngine.process("confirmationInfo.html", webContext, resp.getWriter());

        //grA
//        String clientName = (String) req.getSession().getAttribute("clientName");
//        String clientAddress = (String) req.getSession().getAttribute("clientAddress");
//        String ipAddress = req.getRemoteAddr();
//        String browser = req.getHeader("User-agent");
//        String pizzaName = (String) req.getSession().getAttribute("pizzaName");
//        String pizzaSize = (String) req.getSession().getAttribute("pizzaSize");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().invalidate();
        resp.sendRedirect("/*");
    }
}
