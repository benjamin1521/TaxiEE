package ua.training.controller.command.client;

import ua.training.controller.command.Command;
import ua.training.model.dto.PaginationOrders;
import ua.training.model.entities.Order;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Status;
import ua.training.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class ShowOrders implements Command {
    private OrderService orderService = new OrderService();

    private static final int ORDERS_PER_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String urlRole = user.getRole().toString().toLowerCase();
        int page = Integer.parseInt(request.getParameter("page"));
        String status = Status.getOrNull(request.getParameter("status"));

        if (page < 1) {
            return String.format("redirect:/%s/orders?page=1&status=%s", urlRole, status);
        }

        PaginationOrders result = orderService.getPage(user, page, ORDERS_PER_PAGE, status);

        if (result.getTotal() == 0) {
            request.setAttribute("noOrders", true);
            return String.format("/%s/orders", urlRole);
        }

        if (result.getTotal() <= ORDERS_PER_PAGE * (page - 1)) {
            return String.format("redirect:/%s/orders?page=%s&status=%s",
                    urlRole,
                    (int) Math.ceil((double) result.getTotal() / ORDERS_PER_PAGE),
                    status);
        }

        request.setAttribute("paginationOrders", result);
        return String.format("/%s/orders", urlRole);
    }
}


