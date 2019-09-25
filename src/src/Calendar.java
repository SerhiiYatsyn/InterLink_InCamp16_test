package src;

import java.lang.*;
import java.time.LocalDate;

public class Calendar {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now(); // отримуємо поточну дату, на випадок, якщо не буде введено аргументу
        boolean currentDate = true; // при запуску без аргументів, або коли дата введена повністю
        // буде відображатися поточна дата
        if (args.length == 1) { // якщо є аргументи
            try {
                String[] splittedDate = args[0].split("-"); // отримуємо масив, де нульовий елемент - рік,
                // перший - місяць, другий (якщо введено) - день
                int year = Integer.parseInt(splittedDate[0]);
                int month = Integer.parseInt(splittedDate[1]);
                int day = 1; // за замовчуванням день = 1
                if (splittedDate.length == 3) // якщо введено і день - він буде виділений
                    day = Integer.parseInt(splittedDate[2]);
                else currentDate = false; // а якщо не введено - то не буде виділятися
                date = LocalDate.of(year, month, day);
            } catch (Exception e) {
                System.out.println("The input data is incorrect"); //якщо введено невірно - користувач буде повідомлений
            }
        }
        printCalendarMonth(date, currentDate);
    }

    private static void printCalendarMonth(LocalDate enteredDate, boolean currentDate) {
        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        LocalDate FromFirstDayOfMonth = LocalDate.of(enteredDate.getYear(), enteredDate.getMonthValue(), 1);
        // змінна з датою початку місяця
        int month = FromFirstDayOfMonth.getMonthValue(); // для перевірки, чи місяць не змінився
        var currentDayOfWeek = FromFirstDayOfMonth.getDayOfWeek().getValue() - 1; // отримаємо кількість днів з
        // попереднього місяця які не потрібно відображати
        for (int i = 0; i < currentDayOfWeek; i++) {
            System.out.print("    "); // виводимо пропуски
        }
        while (FromFirstDayOfMonth.getMonthValue() == month) { // поки місяць не зміниться...
            if (currentDayOfWeek % 7 == 0) System.out.println(); // для форматування. 7 днів у рядок
            String space = (Integer.toString(FromFirstDayOfMonth.getDayOfMonth()).length() > 1) ? "  " : "   ";
            // також форматування. якщо у числі більше однієї цифри - 2 пробіли, у іншому випадку - 1 пробіл
            if (currentDate && FromFirstDayOfMonth.equals(enteredDate)) {
                // якщо введено дату з днем, або без аргументів та поточна дата збігається з введеною
                System.out.print("\u001B[36m" + FromFirstDayOfMonth.getDayOfMonth() + space + "\u001B[0m");
            } // то виділяємо день голубим кольором
            else if (FromFirstDayOfMonth.getDayOfWeek().getValue() % 6 == 0 || // якщо вихідний день
                    FromFirstDayOfMonth.getDayOfWeek().getValue() % 7 == 0) { // виведемо його червоним кольором
                System.out.print("\u001B[31m" + FromFirstDayOfMonth.getDayOfMonth() + "\u001B[0m" + space);
            } else // всі інші дні виводяться у звичайному форматі
                System.out.print(FromFirstDayOfMonth.getDayOfMonth() + space);
            FromFirstDayOfMonth = FromFirstDayOfMonth.plusDays(1); // інкрементуємо день
            currentDayOfWeek++; // інкрементуємо день (число)
        }
    }
}
