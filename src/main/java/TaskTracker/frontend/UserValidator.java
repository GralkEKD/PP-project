package TaskTracker.frontend;


import TaskTracker.businessLogic.services.UserServiceImpl;
import TaskTracker.database.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Autowired
    public UserServiceImpl userService;

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty", "Поле логин обязательно для заполнения");
        if (user.getUserName().length() < 6 || user.getUserName().length() > 25) {
            errors.rejectValue("userName", "size.user.username", "Логин должен быть больше 6 и не больше 25 символов");
        }

//        if (userService.getUser(user.getUserName()) != null) {
//            errors.rejectValue("username", "duplicate.user.username", "Пользователь с таким логином уже существует");
//        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "NotEmpty", "Поле пароль обязательно для заполнения");
        if (user.getUserPassword().length() < 8 || user.getUserPassword().length() > 25) {
            errors.rejectValue("userPassword", "size.user.password", "Пароль должен быть больше 6 и не больше 25 символов");
        }
    }
}