package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ShowUpdateUser implements Command {
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("user");
        String urlRole = currentUser.getRole().toString().toLowerCase();

        User user = userService.findById(Long.parseLong(request.getParameter("id")));

        request.setAttribute("user", currentUser);
        request.setAttribute("client",user);
        return String.format("/%s/details", urlRole);
    }
}
