package Param;

import java.util.Scanner;

public class Main {
    private static int level = 1;
    private static Scanner sc;
    private static Battle battle;
    private static Parameters player;
    private static Trader trader;

    public static void main(String[] args) {

        sc = new Scanner(System.in);
        battle = new Battle();
        trader = new Trader();


        System.out.println("Придумайте имя герою: ");
        command(sc.nextLine());

    }

    public static void navigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К торговцу");
        System.out.println("2. В тёмный лес");
        System.out.println("3. Характеристики");
        System.out.println("4. На выход");
        System.out.println("5. Сразится с драконом");
    }

    private static void nextLevel() {
        if (player.getXp() >= 100) {
            level++;
            player.setXp(player.getXp()-100);
        }
    }
    public static void command(String play) {

        int power = 1 + (int) (Math.random() * 60);
        int dexterity = 1 + (int) (Math.random() * 50);

        if (player == null) {
            player = new Hero(play, 100, power, dexterity, 0, 0);
            System.out.println("Этому миру нужен герой... " + player.getName());
            navigation();
        }
        nextLevel();
        switch (play) {
            case "1" -> {
                trader.inventory(player);
                navigation();
            }
            case "2" -> {
                commitFight("2");
            }
            case "3" -> {
                specifications();
                navigation();
            }
            case "4" -> System.exit(1);
            case "5" -> {
                commitFight("5");
            }
            case "да" -> command("2");
            case "нет" -> {
                navigation();
                command(sc.nextLine());
            }
        }
        command(sc.nextLine());
    }

    private static void commitFight(String s) {
        battle.fight(player, createMonster(s), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(player.getName() + " победил! Теперь у вас " + player.getXp() + " опыта и " + player.getGold() + " золота, а также осталось " + player.getHP() + " едениц здоровья.");
                System.out.println("Желаете продолжить поход ? (да/нет)");
                try {
                    command(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("*******************************************************");
                }
            }
            @Override
            public void fightLost() {
                System.out.println("Герой проиграл эту битву");
                command("4");
            }
        });
    }

    interface FightCallback {
        void fightWin();
        void fightLost();
    }

    private static Parameters createMonster(String s) {

        if (s.equals("5")) {
                int power = 500 + (int) (Math.random() * 500);
                int dexterity = (int) (Math.random() * 50);
                int xp = 500 + (int) (Math.random() * 500);
                int gold = 400 + (int) (Math.random() * 600);

                return new Dragon("Dragon", 1000, power, dexterity, xp, gold);
        }
            int num = (int) (Math.random() * 10);
            int power = (int) (Math.random() * 50);
            int dexterity = (int) (Math.random() * 50);
            int xp = (int) (Math.random() * 100);
            int gold = (int) (Math.random() * 100);

            if (num < 5) {
                return new Goblin("Goblin", 100, power, dexterity, xp, gold);
            } else {
                return new Skeleton("Skeleton", 100, power, dexterity, xp, gold);
            }
    }

    private static void specifications() {
        System.out.println("*******  ХАРАКТЕРИСТИКИ  *******");
        System.out.println("Уровень: " + level);
        System.out.println("Золото: " + player.getGold() + "\nОпыт: " + player.getXp());
        System.out.println("Здоровье: " + player.getHP() + "\nСила: " + player.getPower());
        System.out.println("--------------------------------");
    }
}
