import java.time.LocalDate;
import java.util.Random;

public class Main {

    private final static Random rng = new Random();

    public static void main(String[] args) {
        System.out.println("Задача 1");
        task1();

        System.out.println("\nЗадача 2");
        task2();

        System.out.println("\nЗадача 3");
        task3();
    }

    //Задача 1
    //Реализуйте метод, который получает в качестве параметра год, проверяет, является ли он високосным, и выводит результат в консоль.
    //Эту проверку вы уже реализовывали в задании по условным операторам.
    //Теперь проверку оберните в метод и используйте год, который приходит в виде параметра.
    //Результат программы выведите в консоль. Если год високосный, то напечатайте сообщение: «...  год — високосный год».
    //Если год невисокосный, то: «... год — невисокосный год».
    public static void task1() {
        int year = rng.nextInt(40_000);
        System.out.println("Randomly generated year: " + year);
        if (isLeapYear(year)) {
            System.out.println(year + " год — високосный год");
        } else {
            System.out.println(year + " год — невисокосный год");
        }
    }

    private static boolean isLeapYear(int year) {
        final int leapYearPeriod = 4;
        final int leapYearException = 100;
        final int leapYearExceptionOverride = 400;

        return year % leapYearPeriod == 0 && year % leapYearException != 0 || year % leapYearExceptionOverride == 0;
    }

    //Задача 2
    //Вспомните задание 2 из урока «Условные операторы», где вы предлагали пользователю облегченную версию приложения.
    //Напишите метод, куда подаются два параметра: тип операционной системы (0 — iOS, 1 — Android ) и год выпуска устройства.
    //Если устройство старше текущего года, предложите ему установить облегченную версию.
    //Текущий год можно получить таким способом:
    //int currentYear = LocalDate.now().getYear();
    //Или самим задать значение вручную — ввести в переменную числовое значение.
    //В результате программа должна выводить в консоль сообщение, какую версию приложения (обычную или облегченную) и
    //для какой ОС (Android или iOS) установить пользователю.
    public static void task2() {
        Phone phone = new Phone(rng.nextInt(3), rng.nextInt(23) + 2000);
        //System.out.println("Телефон: " + Phone.getDeviceOSName(phone.getDeviceOS()) + ", выпущен в  " + phone.getDeviceYear() + " году");
        System.out.printf(getInstallationMessage(phone.getDeviceOS(), phone.getDeviceYear()));
    }

    private static String getInstallationMessage(int osId, int year) {
        final int oldPhoneCutOffPoint = LocalDate.now().getYear() - 3;
        String result;

        if (Phone.getDeviceOSName(osId) == null) {
            result = "Упс! Похоже, ваш телефон не поддерживается :(\n";
        } else if (year < oldPhoneCutOffPoint) {
            result = String.format("Установите облегченную версию приложения для %s по ссылке\n", Phone.getDeviceOSName(osId));
        } else {
            result = String.format("Установите версию приложения для %s по ссылке\n", Phone.getDeviceOSName(osId));
        }
        return result;
    }

    //Задача 3
    //Возвращаемся к задаче на расчет дней доставки банковской карты.
    //Ваша задача — доработать код, а именно написать метод, который на вход принимает дистанцию и возвращает итоговое
    //количество дней доставки.


    private static void task3() {
        int deliveryDistance = rng.nextInt(120);
        int deliveryTime = getDeliveryTime(deliveryDistance);
        if (deliveryTime >= 1) {
            System.out.printf("Вы находитесь в радиусе %d километров, доставка займет %d дня\n", deliveryDistance, deliveryTime);
        } else {
            System.out.println("Слишком далеко, доставки нет");
        }
    }

    private static int getDeliveryTime(int deliveryDistance) {
        final int firstLimit = 20;
        final int secondLimit = 60;
        final int thirdLimit = 100;
        int deliveryTime;

        if (deliveryDistance <= firstLimit) {
            deliveryTime = 1;
        } else if (deliveryDistance <= secondLimit) {
            deliveryTime = 2;
        } else if (deliveryDistance <= thirdLimit) {
            deliveryTime = 3;
        } else {
            deliveryTime = -1;
        }
        return deliveryTime;
    }
}