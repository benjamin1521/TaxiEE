package ua.training.controller.command.client;

import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;
import ua.training.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class ShowCreate implements Command {
    private OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String urlRole = user.getRole().toString().toLowerCase();

        request.setAttribute("types", Type.values());
        request.setAttribute("streets", Street.values());
        request.setAttribute("coupons", orderService.getFreeCoupons(user));
        request.setAttribute("user", user);
        return String.format("/%s/create", urlRole);
    }
}
