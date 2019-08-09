package ua.training.controller.command.client;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class ConfirmOrder implements Command {
    private static final Logger logger = Logger.getLogger(ConfirmOrder.class);
    private OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Long order = Long.parseLong(request.getParameter("id"));

        if (orderService.confirmOrder(order, user)) {
            logger.info(String.format("user %d confirmed order %d",
                    user.getId(), order));
        }

        return "redirect:/inspector/reports?page=1";
    }
}
