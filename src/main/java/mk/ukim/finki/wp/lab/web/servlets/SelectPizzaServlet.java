package mk.ukim.finki.wp.lab.web.servlets;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.service.OrderService;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "select_pizza", urlPatterns = "/selectPizza.do")
public class SelectPizzaServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public SelectPizzaServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        //grB
        Order order = (Order) req.getSession().getAttribute("order");
        webContext.setVariable("order", order);
        resp.setContentType("text/html");
        springTemplateEngine.process("selectPizzaSize.html", webContext, resp.getWriter());


        //grA
//        String pizzaName = (String) req.getSession().getAttribute("pizzaName");
//        webContext.setVariable("pizzaName", pizzaName);
//        resp.setContentType("text/html");
//        springTemplateEngine.process("selectPizzaSize.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String pizzaSize = req.getParameter("pizza_size");

        //grB
        Order order = (Order) session.getAttribute("order");
        order.setPizzaSize(pizzaSize);
        session.setAttribute("order", order);
        resp.sendRedirect("/PizzaOrder.do");
    }

    //grA
//        session.setAttribute("pizzaSize",pizzaSize);
//        resp.sendRedirect("/PizzaOrder.do");    }

}