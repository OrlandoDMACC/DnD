package winters;

import java.util.Objects;
import java.util.Random;

@SuppressWarnings("unused")
public class Character implements Comparable<Character> {

    private Weapon weapon;
    private int hitpoints;
    private int initiative;
    private Random randy;
    private String name;


    public Character(String name, Weapon weapon, int hitpoints, Random randy) {
        this.name = name;
        this.weapon = weapon;
        this.hitpoints = hitpoints;
        this.randy = randy;
        rollInitiative();
    }

    public void rollInitiative() {
        this.initiative = randy.nextInt(20) + 1;
    }

    public void turn() {
        initiative--;
    }

    public void attack(Character target) {
        target.setHitpoints(target.getHitpoints() - weapon.getDamage());
    }

    @Override
    public int compareTo(Character o) {
        return this.initiative - o.initiative;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return hitpoints == character.hitpoints &&
                initiative == character.initiative &&
                Objects.equals(weapon, character.weapon) &&
                Objects.equals(randy, character.randy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weapon, hitpoints, initiative, randy);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
