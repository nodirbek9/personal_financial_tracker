package main;

import model.Transaction;
import model.User;
import services.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

 //Консольное приложение для управления личными финансами.

public class FinanceApp {
    private static final TransactionService transactionService = new TransactionService();
    private static final ReportService reportService = new ReportService();
    private static final AdminService adminService = new AdminService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();
        UserService userService = new UserService(new HashMap<>());
        BudgetService budgetService = new BudgetService();
        GoalService goalService = new GoalService();
        NotificationService notificationService = new NotificationService();




        while (true) {
            System.out.println("1. Регистрация\n2. Вход\n3. Выход");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Введите email:");
                String email = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();
                System.out.println("Введите имя:");
                String name = scanner.nextLine();
                User currentUser = authService.register(email, password, name);
            }else if (choice == 2) {
                System.out.println("Введите email:");
                String email = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();
                User currentUser = authService.login(email, password);

                if (currentUser != null) {
                    while (true) {
                        System.out.println("1. Управление пользователем\n" +
                                "2. Транзакции\n" +
                                "3. Бюджет\n" +
                                "4. Цели\n" +
                                "5. Аналитика\n" +
                                "6. Администрирование\n" +
                                "7. Выход");
                        int action = scanner.nextInt();
                        scanner.nextLine();

                        if (action == 1) {
                            System.out.println("1. Обновить профиль\n2. Удалить аккаунт");
                            int userAction = scanner.nextInt();
                            scanner.nextLine();
                            if (userAction == 1) {
                                System.out.println("Введите email:");
                                String newEmail = scanner.nextLine();
                                System.out.println("Введите новый пароль:");
                                String newPassword = scanner.nextLine();
                                System.out.println("Введите новое имя:");
                                String newName = scanner.nextLine();
                                userService.updateProfile(currentUser.getId(), newEmail, newPassword, newName);
                            } else if (userAction == 2) {
                                userService.deleteUser(currentUser.getId());
                                currentUser = null;
                                break;
                            }
                        } else if (action == 2) {
                            if (currentUser != null) {
                                System.out.println("Добро пожаловать, " + currentUser.getName() + "!");
                            }
                            TransactionService fm = new TransactionService();
                            while (true) {
                                System.out.println("1. Добавить транзакцию\n" +
                                        "2. Редактировать транзакцию" + "\n" +
                                        "3. Удалить транзакцию" + "\n" +
                                        "4. Просмотреть транзакции" + "\n" +
                                        "5. Выйти");
                                int choice1 = scanner.nextInt();
                                scanner.nextLine();

                                if (choice1 == 1) {
                                    System.out.println("Сумма:");
                                    double amount = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println("Категория:");
                                    String category = scanner.nextLine();
                                    System.out.println("Описание:");
                                    String description = scanner.nextLine();
                                    Transaction transaction = new Transaction(currentUser.getId(),
                                            amount, category,description,false);
                                    transactionService.addTransaction(currentUser.getEmail(), transaction);

                                } else if (choice1 == 2) {
                                    System.out.println("Введите ID транзакции:");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("Новая сумма:");
                                    double amount = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println("Новая категория:");
                                    String category = scanner.nextLine();
                                    System.out.println("Новое описание:");
                                    String description = scanner.nextLine();
                                    fm.updateTransaction(currentUser.getEmail(), id, amount, category, description);
                                } else if (choice1 == 3) {
                                    System.out.println("Введите ID транзакции:");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    fm.deleteTransaction(currentUser.getEmail(), id);
                                } else if (choice1 == 4) {
                                    List<Transaction> transactions = transactionService.getUserTransactions(currentUser.getEmail());
                                    transactions.forEach(System.out::println);
                                } else {
                                    break;
                                }
                            }
                        } else if (action == 3) {
                            // Управление бюджетом
                            System.out.println("1. Установить бюджет\\" +
                                    "n2. Просмотреть бюджет\\" +
                                    "n3. Назад");
                            int budgetAction = scanner.nextInt();
                            scanner.nextLine();

                            if (budgetAction == 1) {
                                System.out.println("Введите сумму бюджета на месяц:");
                                double budgetAmount = scanner.nextDouble();
                                scanner.nextLine();
                                budgetService.setBudget(currentUser.getEmail(), budgetAmount);
                            } else if (budgetAction == 2) {
                                System.out.println("Ваш бюджет: " + budgetService.getBudget(currentUser.getEmail()));
                            }else if (budgetAction == 3){
                                break;
                            }
                        } else if (action == 4) {
                            System.out.println("1. Установить цель\\n" +
                                    "2. Обновить прогресс\\n" +
                                    "3. Просмотреть цель\\n" +
                                    "4. Назад");
                            int goalAction = scanner.nextInt();
                            scanner.nextLine();

                            if (goalAction == 1) {
                                System.out.println("Введите сумму для накопления:");
                                double goalAmount = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.println("Введите описание цели:");
                                String description = scanner.nextLine();
                                goalService.setGoal(currentUser.getEmail(), description, goalAmount);
                            } else if (goalAction == 2) {
                                System.out.println("Введите сумму внесенного вклада:");
                                double deposit = scanner.nextDouble();
                                scanner.nextLine();
                                goalService.updateGoalProgress(currentUser.getEmail(), deposit);
                            } else if (goalAction == 3) {
                                System.out.println(goalService.getGoal(currentUser.getEmail()));
                                if (currentUser.getEmail().isEmpty()) {
                                    System.out.println("У вас нет целей.");
                                }
                            }
                        } else if (action == 5) {
                            List<Transaction> transactions = transactionService.getUserTransactions(currentUser.getEmail());
                            System.out.println(reportService.generateReport(currentUser, transactions));
                        } else if (action == 6) {
                            if (currentUser.getEmail()=="ADMIN") { // Проверяем, что пользователь - админ
                                System.out.println("У вас нет прав для выполнения этой операции.");
                                return;
                            }

                            System.out.println("1. Просмотр всех пользователей\n" +
                                    "2. Просмотр транзакций пользователя\n" +
                                    "3. Блокировка пользователя\n" +
                                    "4. Удаление пользователя");
                            int adminAction = scanner.nextInt();
                            scanner.nextLine();

                            System.out.println("1. Просмотреть пользователей\n" +
                                    "2. Удалить пользователя\n" +
                                    "3. Назад");
                            int choise = scanner.nextInt();
                            scanner.nextLine();
                            if (choice == 1){
                                adminService.getAllUsers().values().forEach(System.out::println);
                            } else if (choice == 2) {
                                System.out.print("Введите email пользователя для удаления: ");
                                String emailRemove = scanner.nextLine();
                                adminService.removeUser(emailRemove);
                            }else  if (choice == 3){
                                break;
                            }else {
                                System.out.println("Неверный ввод.");

                            }
                        }else if (action == 7){
                            break;
                        }else {
                            System.out.println("Неверный ввод.");
                        }
                    }
                }
            }else if (choice == 3) {
                break;
            } else {
                System.out.println("Вы ввели неправилное содержание!");
            }
        } scanner.close();
    }
}