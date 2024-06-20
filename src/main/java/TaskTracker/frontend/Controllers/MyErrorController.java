package TaskTracker.frontend.Controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        String message = "Произошла ошибка: ";

        if (status != null) {
            message = switch (Integer.parseInt(status.toString())) {
                case 403 -> "Доступ запрещён: ";
                case 404 -> "Страница не найдена: ";
                case 405 -> "Метод заблокирован: ";
                case 500 -> "Внутренняя ошибка сервера: ";
                default -> message;
            };
            message += status;
        }
        model.addAttribute("message", message);
        return "error";
    }
}
