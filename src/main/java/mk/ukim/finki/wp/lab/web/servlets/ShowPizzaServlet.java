package mk.ukim.finki.wp.lab.web.servlets;


import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.service.PizzaService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "pizza_servlet", urlPatterns = "/*")
public class ShowPizzaServlet extends HttpServlet {

    private final PizzaService pizzaService;
    private final SpringTemplateEngine springTemplateEngine;

    public ShowPizzaServlet(PizzaService pizzaService, SpringTemplateEngine springTemplateEngine) {
        this.pizzaService = pizzaService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("pizzas", pizzaService.listPizzas());
        resp.setContentType("text/html; charset=UTF-8");
        springTemplateEngine.process("listPizzas.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession httpSession = req.getSession(); //pristapuvame sesijata
        String pizzaName = req.getParameter("pizzaName"); //go zemame parametarot od korisnikot
        //picata sto ja izbral

        //grB
        Order order = new Order();
        order.setPizzaType(pizzaName);
        httpSession.setAttribute("order", order);
        httpSession.setAttribute("pizzaName", pizzaName);
        resp.sendRedirect("/selectPizza.do");

        //grA
//        httpSession.setAttribute("pizzaName", pizzaName);
//        /*izborot sto go napravil korisnikot treba da se zapamti*/
//        resp.sendRedirect("/selectPizza.do");
    }


}
