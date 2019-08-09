package ua.training.controller.command.admin;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.client.CreateOrder;
import ua.training.model.entities.Order;
import ua.training.model.entities.Taxi;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;
import ua.training.model.service.OrderService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class CreateTaxi implements Command {
    private static final Logger logger = Logger.getLogger(CreateOrder.class);
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        User admin = (User) request.getSession().getAttribute("user");

        Taxi taxi = Taxi.newBuilder()
                .driverName(request.getParameter("driverName"))
                .carNumber(request.getParameter("number"))
                .type(Type.valueOf(request.getParameter("type")))
                .locationHouse(1)
                .locationStreet(Street.Bondarska)
                .busy(false)
                .build();

        if (userService.createTaxi(taxi)) {
            logger.info(String.format("admin %d created taxi %s", admin.getId(),taxi.getCarNumber()));
        }

        return "redirect:/client/orders?page=1";
    }
}