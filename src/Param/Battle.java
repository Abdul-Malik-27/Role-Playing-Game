package Param;

public class Battle {

    public void fight(Parameters hero, Parameters monster, Main.FightCallback fightCallback) {

        Runnable launch = () -> {
            int step = 1;

            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println();
                System.out.println("----Ход: " + step + "----");

                if (step++ % 2 != 0) {
                    isFightEnded = makeHit(monster, hero, fightCallback);
                } else {
                    isFightEnded = makeHit(hero, monster, fightCallback);
                }

                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(launch);
        thread.start();
    }


    private Boolean makeHit(Parameters defender, Parameters attacker, Main.FightCallback fightCallback) {

        int hit = attacker.attack();

        int defenderHealth = defender.getHP() - hit;

        if (hit != 0) {
            System.out.println(attacker.getName() + " Нанес урон в " + hit + " единиц!");
            System.out.println("У " + defender.getName() + " осталось " + defenderHealth + " единиц здоровья...");
        } else {
            System.out.println(attacker.getName() + " промахнулся!");
        }

        if (defenderHealth <= 0 && defender instanceof Hero) {
            System.out.println("Вы сражались достойно");
            System.out.println("*** WASTED ***");
            fightCallback.fightLost();
            return true;

        } else if(defenderHealth <= 0) {
            System.out.println("Монстер уничтожен! Вы получаете " + defender.getXp() + " опыт и " + defender.getGold() + " золота");
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            System.out.println("*** YOU WIN ***");
            fightCallback.fightWin();
            return true;

        } else {
            defender.setHP(defenderHealth);
            return false;
        }
    }
}
