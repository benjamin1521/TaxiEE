package ua.training.controller.command.admin;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.client.CreateOrder;
import ua.training.model.entities.Coupon;
import ua.training.model.entities.User;
import ua.training.model.service.OrderService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class CreateCoupon implements Command {
    private static final Logger logger = Logger.getLogger(CreateOrder.class);
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        User admin = (User) request.getSession().getAttribute("user");

        User user = userService.findById(Long.parseLong(request.getParameter("id")));

        Coupon coupon = Coupon.newBuilder()
                .user(user)
                .discountPercent(Double.parseDouble(request.getParameter("percent")))
                .build();

        if (userService.createCoupon(coupon)) {
            logger.info(String.format("admin %d created coupon for user %s", admin.getId(),user.getId()));
        }
        return "redirect:/client/orders?page=1";
    }
}
