package run;

import bussiness.entity.User;
import bussiness.service.*;
import bussiness.service.imp.*;
import bussiness.util.BCrypt;
import bussiness.util.InputMethods;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class CarDealer {
    public static User loginAccount;
    public  static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
    public  static DateTimeFormatter formatMonth = DateTimeFormatter.ofPattern("MM");
    public static final IUserService userService = new UserService();
    public static final ICatalogService catalogService = new CatalogService();
    public static final ICarService carService = new CarService();
    public static final IOrderService orderService = new OrderService();
    public static ICartService cartService;

    public static void main(String[] args) {
        LoginRegisterManager.showLoginRegister();
    }
}
