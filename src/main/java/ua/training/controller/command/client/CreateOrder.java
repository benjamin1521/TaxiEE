package ua.training.controller.command.client;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandFactory;
import ua.training.model.entities.Order;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;
import ua.training.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;


public class CreateOrder implements Command {
    private static final Logger logger = Logger.getLogger(CreateOrder.class);
    private OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request) {
        User client = (User) request.getSession().getAttribute("user");
        Order order = Order.newBuilder()
                .startStreet(Street.valueOf(request.getParameter("startStreet")))
                .startHouse(Integer.parseInt(request.getParameter("startHouse")))
                .endStreet(Street.valueOf(request.getParameter("endStreet")))
                .endHouse(Integer.parseInt(request.getParameter("endHouse")))
                .type(Type.valueOf(request.getParameter("type")))
                .idCoupon(Long.parseLong(request.getParameter("coupon")))
                .build();
        if (order.getStartStreet().equals(order.getEndStreet())
                && order.getStartHouse() == order.getEndHouse()) {
            request.setAttribute("messageLocation","text.incorrect.location");
            return CommandFactory.getInstance().getCommandPage(request).execute(request);
        }

        if (orderService.createOrder(client,
                order)) {
            logger.info(String.format("client %d created order", client.getId()));
            return "redirect:/client/orders?page=1";
        } else {
            request.setAttribute("messageTaxi", "text.no.taxi");
        }
        return CommandFactory.getInstance().getCommandPage(request).execute(request);
    }
}
