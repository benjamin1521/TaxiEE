package ua.training.controller.command.client;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class DeleteOrder implements Command {
    private static final Logger logger = Logger.getLogger(DeleteOrder.class);

    private OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        long order = Long.parseLong(request.getParameter("id"));
        if (orderService.deleteOrder(order)) {
            logger.info(String.format("client %d deleted Order %d", user.getId(), order));
        }

        return "redirect:/client/Orders?page=1";
    }
}
