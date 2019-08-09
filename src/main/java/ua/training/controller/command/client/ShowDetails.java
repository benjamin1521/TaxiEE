package ua.training.controller.command.client;

import ua.training.controller.command.Command;
import ua.training.model.entities.Order;
import ua.training.model.entities.User;
import ua.training.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class ShowDetails implements Command {
    private OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String urlRole = user.getRole().toString().toLowerCase();

        Order order = orderService.findById(Long.parseLong(request.getParameter("id")));

        request.setAttribute("user", user);
        request.setAttribute("order", order);
        return String.format("/%s/details", urlRole);

    }
}
