package TaskTracker.frontend;

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
            switch (Integer.valueOf(status.toString())) {
                case 403:
                    message = "Доступ запрещён: ";
                    break;
                case 404:
                    message = "Страница не найдена: ";
                    break;
                case 405:
                    message = "Метод заблокирован: ";
                    break;
                case 500:
                    message = "Внутренняя ошибка сервера: ";
                    break;
            }
            message += status;
        }
        model.addAttribute("message", message);
        return "error";
    }
}
