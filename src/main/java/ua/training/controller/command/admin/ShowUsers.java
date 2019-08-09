package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ShowUsers implements Command {
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String urlRole = user.getRole().toString().toLowerCase();

        request.setAttribute("users",userService.getAllUsers());
        request.setAttribute("types", Type.values());
        request.setAttribute("streets", Street.values());
        request.setAttribute("user", user);
        return String.format("/%s/users", urlRole);
    }
}
