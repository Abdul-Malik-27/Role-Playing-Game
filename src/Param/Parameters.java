package Param;

public abstract class Parameters implements Fighter {

    private String name;
    private int HP;
    private int power;
    private int dexterity;
    private int xp;
    private int gold;

    public Parameters(String name, int HP, int power, int dexterity, int xp, int gold) {
        this.name = name;
        this.HP = HP;
        this.power = power;
        this.dexterity = dexterity;
        this.xp = xp;
        this.gold = gold;
    }

    @Override
    public int attack() {
        if (dexterity * 3 > getRandomValue()) {
            if ((int) (Math.random() * 10) == 7) {
                return power * 2;
            }
            return power;
        }
        else return  0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHP() {
        return HP;
    }
    public void setHP(int healthPoints) {
        this.HP = healthPoints;
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public int getDexterity() {
        return dexterity;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }

    @Override
    public String toString() {
        return name + " жизнь:" + HP;
    }
}