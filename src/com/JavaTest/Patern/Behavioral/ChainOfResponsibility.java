//Этот пример показывает как пользовательские данные проходят
// последовательную аутентификацию в множестве обработчиков,
// связанных в одну цепь.
package com.JavaTest.Patern.Behavioral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * Базовый класс цепочки.
 */
abstract class BaseHandler {
    private BaseHandler next;

    /**
     * Помогает строить цепь из объектов-проверок.
     */
    public BaseHandler setNext(BaseHandler next) {
        this.next = next;
        return next;
    }

    /**
     * Подклассы реализуют в этом методе конкретные проверки.
     */
    public abstract boolean check(String email, String password);

    /**
     * Запускает проверку в следующем объекте или завершает проверку, если мы в
     * последнем элементе цепи.
     */
    protected boolean nextCheck(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}
/**
 * Конкретный элемент цепи обрабатывает запрос по-своему.
 * В данном случае провека на превышение лимита запросов.
 */
 class ThrottlingHandler extends BaseHandler {
    private int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingHandler(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    /**
     * Обратите внимание, вызов checkNext() можно вставить как в начале этого
     * метода, так и в середине или в конце.
     *
     * Это даёт еще один уровень гибкости по сравнению с проверками в цикле.
     * Например, элемент цепи может пропустить все остальные проверки вперёд и
     * запустить свою проверку в конце.
     */
    public boolean check(String email, String password) {
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;

        if (request > requestPerMinute) {
            System.out.println("Request limit exceeded!");
            return false;
        }
        return nextCheck(email, password);
    }
}

/**
 * Класс сервера приложения.
 */
class Server {
    private Map<String, String> users = new HashMap<>();
    private BaseHandler handler;

    /**
     * Клиент подаёт готовую цепочку в сервер. Это увеличивает гибкость и
     * упрощает тестирование класса сервера.
     */
    public void setChainOfHandlers(BaseHandler handler) {
        this.handler = handler;
    }

    /**
     * Сервер получает email и пароль от клиента и запускает проверку
     * авторизации у цепочки.
     */
    public boolean logIn(String email, String password) {
        if (handler.check(email, password)) {
            System.out.println("Authorization have been successful!");

            // Здесь должен быть какой-то полезный код, работающий для
            // авторизированных пользователей.

            return true;
        }
        return false;
    }

    public void register(String email, String password) {
        users.put(email, password);
    }

    public boolean hasEmail(String email) {
        return users.containsKey(email);
    }

    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password);
    }
}
/**
 * Проверка пароля
 */
 class UserExistsHandler extends BaseHandler {
    private Server server;

    public UserExistsHandler(Server server) {
        this.server = server;
    }

    public boolean check(String email, String password) {
        if (!server.hasEmail(email)) {
            System.out.println("This email is not registered!");
            return false;
        }
        if (!server.isValidPassword(email, password)) {
            System.out.println("Wrong password!");
            return false;
        }
        return nextCheck(email, password);
    }
}
/**
 * Проверка роли
 */
 class RoleCheckHandler extends BaseHandler {
    public boolean check(String email, String password) {
        if (email.equals("admin@example.com")) {
            System.out.println("Hello, admin!");
            return true;
        }
        System.out.println("Hello, user!");
        return nextCheck(email, password);
    }
}
public class ChainOfResponsibility {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private static void init() {
        server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");

        // Проверки связаны в одну цепь. Клиент может строить различные цепи,
        // используя одни и те же компоненты.
        BaseHandler handler = new ThrottlingHandler(2);
        handler.setNext(new UserExistsHandler(server))
                .setNext(new RoleCheckHandler());

        // Сервер получает цепочку от клиентского кода.
        server.setChainOfHandlers(handler);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean success;
        do {
            System.out.print("Enter email: ");
            String email = reader.readLine();
            System.out.print("Input password: ");
            String password = reader.readLine();
            success = server.logIn(email, password);
        } while (!success);
    }
}