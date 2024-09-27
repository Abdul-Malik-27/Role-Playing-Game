package Param;
import java.util.Scanner;

public class Trader implements Seller {
    Scanner sc = new Scanner(System.in);
    public void inventory(Parameters parameters) {
        System.out.println("Здравствуй герой, покупай что хочешь, для тебя у меня скидки 10% на все !!!");
        System.out.println("1. Зелье лечения");
        System.out.println("2. Улучшить мечь");
        buy(sc.next(), parameters);
    }

    public void buy(String str, Parameters parameters) {
        switch (str) {
            case "1" -> {
                System.out.println("Сколько золото вы хотите потратить на ЛЕЧЕНИЕ ?");
                int num = sc.nextInt();
                if (num > 0 && num <= parameters.getGold()) {
                    parameters.setHP(parameters.getHP() + num);
                    parameters.setGold(parameters.getGold() - num);
                    System.out.println("СТАТЫ ОБНОВЛЕНЫ !!!");
                } else if (num < 0) {
                    System.out.println("Введите положительное число");
                    buy(str, parameters);
                } else {
                    System.out.println("У тебя недостаточно золото");
                    System.out.println("Иди домой");
                }
            }
            case "2" -> {
                System.out.println("Сколько золото вы хотите потратить на улучшения МЕЧЯ ?");
                int num = sc.nextInt();
                if (num > 0 && num <= parameters.getGold()) {
                    parameters.setPower(parameters.getPower() + num);
                    parameters.setGold(parameters.getGold() - num);
                    System.out.println("СТАТЫ ОБНОВЛЕНЫ !!!");
                } else if (num < 0) {
                    System.out.println("Введите положительное число");
                    buy(str, parameters);
                } else {
                    System.out.println("У тебя недостаточно золото");
                    System.out.println("Иди домой");
                }
            }
        }
    }
}
