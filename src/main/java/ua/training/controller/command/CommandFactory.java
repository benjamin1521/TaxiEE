package ua.training.controller.command;

import ua.training.controller.command.admin.CreateCoupon;
import ua.training.controller.command.admin.CreateTaxi;
import ua.training.controller.command.admin.ShowCreateTaxi;
import ua.training.controller.command.admin.ShowUpdateUser;
import ua.training.controller.command.admin.ShowUsers;
import ua.training.controller.command.client.ConfirmOrder;
import ua.training.controller.command.client.CreateOrder;
import ua.training.controller.command.client.DeleteOrder;
import ua.training.controller.command.client.ShowCreate;
import ua.training.controller.command.client.ShowDetails;
import ua.training.controller.command.client.ShowOrders;
import ua.training.controller.command.guest.Login;
import ua.training.controller.command.guest.Registration;
import ua.training.controller.command.guest.ShowLogin;
import ua.training.controller.command.guest.ShowRegistration;
import ua.training.controller.command.redirects.LogOut;
import ua.training.controller.command.redirects.RedirectHome;
import ua.training.controller.command.redirects.ShowMainPage;
import ua.training.controller.command.shared.ChangeLanguage;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();

    public static CommandFactory getInstance() {
        return instance;
    }

    private Map<String, Command> commands = new HashMap<>();
    private Command defaultCommand = new RedirectHome();

    private CommandFactory() {
        //guest
        commands.put("/guest", new ShowLogin());
        commands.put("/guest/registration", new ShowRegistration());

        commands.put("login", new Login());
        commands.put("registration", new Registration());

        //client
        commands.put("/client", new ShowMainPage());
        commands.put("/client/orders", new ShowOrders());
        commands.put("/client/create", new ShowCreate());
        commands.put("/client/details", new ShowDetails());

        commands.put("createOrder", new CreateOrder());
        commands.put("deleteOrder", new DeleteOrder());
        commands.put("confirmOrder", new ConfirmOrder());

        //admin
        commands.put("/admin", new ShowMainPage());
        commands.put("/admin/users", new ShowUsers());
        commands.put("/admin/create", new ShowCreateTaxi());
        commands.put("/admin/details", new ShowUpdateUser());

        commands.put("createTaxi", new CreateTaxi());
        commands.put("createCoupon", new CreateCoupon());

        //common
        commands.put("logout", new LogOut());
        commands.put("changeLanguage", new ChangeLanguage());
    }

    public Command getCommandPage(HttpServletRequest request) {
        String path = request.getPathInfo() == null ? "/" : request.getPathInfo().toLowerCase();
        return commands.getOrDefault(path, defaultCommand);
    }

    public Command getCommand(HttpServletRequest request) {
        String command = request.getParameter("command");

        System.out.println("Command: " + command);
        if (command == null) {
            return getCommandPage(request);
        }

        String path = request.getPathInfo() == null ? "/" : request.getPathInfo().toLowerCase();
        return commands.getOrDefault(command, commands.getOrDefault(command + ":" + path, defaultCommand));
    }
}
