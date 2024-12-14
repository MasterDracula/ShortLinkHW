package org.example;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static final Map<UUID, User> users = new HashMap<>();

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        UrlShortLink urlShort = new UrlShortLink();
        Scanner scanner = new Scanner(System.in);

        User user = null;

        while (true) {
            System.out.println("Добро пожаловать в сервис сокращения ссылок!");

            System.out.println("Хотите создать нового пользователя или войти с существующим UUID? (новый/существующий)");
            String userResponse = scanner.nextLine();

            if (userResponse.equalsIgnoreCase("новый")) {
                user = new User();
                users.put(user.getUuid(), user);
                System.out.println("Создан новый пользователь с UUID: " + user.getUuid());
            } else if (userResponse.equalsIgnoreCase("существующий")) {
                while (true) {
                    System.out.print("Введите UUID существующего пользователя: ");
                    String uuidStr = scanner.nextLine();
                    try {
                        UUID uuid = UUID.fromString(uuidStr);
                        if (users.containsKey(uuid)) {
                            user = users.get(uuid);
                            System.out.println("Вход выполнен для пользователя с UUID: " + user.getUuid());
                            break;
                        } else {
                            System.out.println("Пользователь с таким UUID не найден. Попробовать снова или создать нового пользователя? (повторить/новый)");
                            String retryOrNew = scanner.nextLine();
                            if (retryOrNew.equalsIgnoreCase("новый")) {
                                user = new User();
                                users.put(user.getUuid(), user);
                                System.out.println("Создан новый пользователь с UUID: " + user.getUuid());
                                break;
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Неверный формат UUID. Попробовать снова или создать нового пользователя? (повторить/новый)");
                        String retryOrNew = scanner.nextLine();
                        if (retryOrNew.equalsIgnoreCase("новый")) {
                            user = new User();
                            users.put(user.getUuid(), user);
                            System.out.println("Создан новый пользователь с UUID: " + user.getUuid());
                            break;
                        }
                    }
                }
            } else {
                System.out.println("Неверный ввод. Попробуйте снова.");
                continue;
            }

            while (true) {
                System.out.println("Введите URL для сокращения: ");
                String shortToShorten = scanner.nextLine();
                System.out.println("1. Создание короткой ссылки");
                String shortUrl = urlShort.shortenUrl(shortToShorten, user.getUuid(), 5);
                System.out.println("Короткая ссылка: " + shortUrl);

                System.out.println("2. Переход по короткой ссылке");
                System.out.print("Введите короткую ссылку: ");
                String inputShortUrl = scanner.nextLine();
                String originalUrl = urlShort.getOriginalUrl(inputShortUrl);

                if (originalUrl != null) {
                    System.out.println("Перенаправление на: " + originalUrl);
                    Desktop.getDesktop().browse(new URI(originalUrl));
                } else {
                    System.out.println("Неверная или просроченная короткая ссылка.");
                }

                System.out.println("Хотите создать еще одну короткую ссылку? (да/нет)");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("нет")) {
                    break;
                }
            }

            System.out.println("Хотите войти через другого пользователя? (да/нет)");
            String anotherUserResponse = scanner.nextLine();
            if (anotherUserResponse.equalsIgnoreCase("нет")) {
                break;
            }
        }

        System.out.println("Удаление просроченных ссылок...");
        urlShort.cleanupExpiredUrls();
        System.out.println("Все просроченные ссылки были удалены.");

        while (true) {
            System.out.println("Что вы хотите сделать дальше?");
            System.out.println("1. Создать нового пользователя");
            System.out.println("2. Войти с существующим UUID");
            System.out.println("3. Выйти из программы");
            String menuResponse = scanner.nextLine();

            if (menuResponse.equals("1")) {
                user = new User();
                users.put(user.getUuid(), user);
                System.out.println("Создан новый пользователь с UUID: " + user.getUuid());
            } else if (menuResponse.equals("2")) {
                while (true) {
                    System.out.print("Введите UUID существующего пользователя: ");
                    String uuidStr = scanner.nextLine();
                    try {
                        UUID uuid = UUID.fromString(uuidStr);
                        if (users.containsKey(uuid)) {
                            user = users.get(uuid);
                            System.out.println("Вход выполнен для пользователя с UUID: " + user.getUuid());
                            break;
                        } else {
                            System.out.println("Пользователь с таким UUID не найден. Попробовать снова или создать нового пользователя? (повторить/новый)");
                            String retryOrNew = scanner.nextLine();
                            if (retryOrNew.equalsIgnoreCase("новый")) {
                                user = new User();
                                users.put(user.getUuid(), user);
                                System.out.println("Создан новый пользователь с UUID: " + user.getUuid());
                                break;
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Неверный формат UUID. Попробовать снова или создать нового пользователя? (повторить/новый)");
                        String retryOrNew = scanner.nextLine();
                        if (retryOrNew.equalsIgnoreCase("новый")) {
                            user = new User();
                            users.put(user.getUuid(), user);
                            System.out.println("Создан новый пользователь с UUID: " + user.getUuid());
                            break;
                        }
                    }
                }
            } else if (menuResponse.equals("3")) {
                System.out.println("Программа завершена.");
                break;
            } else {
                System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }
}