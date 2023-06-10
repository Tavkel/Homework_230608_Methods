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
        if (checkIfLeapYear(year)) {
            System.out.println(year + " год — високосный год");
        } else {
            System.out.println(year + " год — невисокосный год");
        }
    }

    private static boolean checkIfLeapYear(int year) {
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
        Phone phone = new Phone(rng.nextInt(2), rng.nextInt(23) + 2000);
        System.out.println("Телефон: " + Phone.getDeviceOSName(phone.getDeviceOS()) + ", выпущен в  " + phone.getDeviceYear() + " году");
        System.out.printf(getInstallationMessage(phone.getDeviceOS(), phone.getDeviceYear()));
    }

    private static String getInstallationMessage(int osId, int year) {
        //это не должно быть тут
        final int outOfSupport = 2005;
        final int oldPhoneCotOffPoint = LocalDate.now().getYear() - 3;

        if (year < outOfSupport || Phone.getDeviceOSName(osId) == null) {
            return "Упс! Похоже, ваш телефон не поддерживается :(\n";
        } else if (year < oldPhoneCotOffPoint) {
            return String.format("Установите облегченную версию приложения для %s по ссылке\n", Phone.getDeviceOSName(osId));
        } else {
            return String.format("Установите версию приложения для %s по ссылке\n", Phone.getDeviceOSName(osId));
        }
    }

    //Задача 3
    //Возвращаемся к задаче на расчет дней доставки банковской карты.
    //Ваша задача — доработать код, а именно написать метод, который на вход принимает дистанцию и возвращает итоговое
    //количество дней доставки.
    final static int firstLimit = 20;
    final static int secondLimit = 60;
    final static int thirdLimit = 100;

    private static void task3() {
        int deliveryDistance = rng.nextInt(120);
        int deliveryTime = getDeliveryTime(deliveryDistance);
        if (checkIfDeliveryAvailable(deliveryTime)) {
            System.out.printf("Вы находитесь в радиусе %d километров, доставка займет %d дня\n", deliveryDistance, deliveryTime);
        } else {
            System.out.println("Слишком далеко, доставки нет");
        }
    }

    public static boolean checkIfDeliveryAvailable(int deliveryTime) {
        return deliveryTime >= 0 && deliveryTime <= 100;
    }

    private static int getDeliveryTime(int deliveryDistance) {

        int deliveryTime = 1;

        if (deliveryDistance <= firstLimit) {
            return deliveryTime;
        } else if (deliveryDistance <= secondLimit) {
            deliveryTime += 1;
            return deliveryTime;
        } else if (deliveryDistance <= thirdLimit) {
            deliveryTime += 2;
            return deliveryTime;
        } else {
            return -1;
        }
    }
}