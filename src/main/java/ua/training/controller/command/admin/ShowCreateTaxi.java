package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Type;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ShowCreateTaxi implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String urlRole = user.getRole().toString().toLowerCase();

        request.setAttribute("types", Type.values());
        request.setAttribute("user", user);
        return String.format("/%s/create", urlRole);
    }
}
