package winters;

import javax.swing.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Boolean initial = true;
        Boolean changed = false;
        Random randy = new Random();
        ArrayList<Character> party = new ArrayList<>();
        party.add(new Character("Knight", new Weapon("sword", 6), 50, randy));
        party.add(new Character("Rogue", new Weapon("dagger", 3), 30, randy));
        party.add(new Character("Mage", new Weapon("magic", 9), 25, randy));
        party.add(new Character("Priest", new Weapon("prayers", 6), 30, randy));

        Character baddie = new Character("Ambiguous Monster", new Weapon("Attack", 15), 150, randy);
        baddie.rollInitiative();

        // Roll initiative
        for (Character c : party) {
            c.rollInitiative();
        }

        PriorityQueue<Character> q = new PriorityQueue<>();

        // Add everything to q
        for (Character c : party) {
            q.add(c);
        }
        q.add(baddie);

        // Turns
        while (baddie.getHitpoints() > 0) {

        	String attack = "";
            changed = false;

            for (Character c : q) {
                c.turn();
  
            }

            // Initiative check (Player first)
            while(q.peek().getInitiative() <= 0) {
                changed = true;
                Character c = q.poll();
                if (c.equals(baddie)) {
                	Character target = party.get(randy.nextInt(party.size()));
                    c.attack(target);
                    attack += "\n" + c.getName() + " attacks " + target.getName(); 
                } else {
                    c.attack(baddie);
                    attack += "\n" + c.getName() + " attacks " + baddie.getName(); 
                }
                c.rollInitiative();
                q.add(c);
            }

            // Party death check
            for (int i = 0; i < party.size(); i++) {
                Character character = party.get(i);
                if (character.getHitpoints() <= 0) {
                    party.remove(character);
                    q.remove(character);
                    System.out.println(character + " has been defeated");
                    JOptionPane.showMessageDialog(null, character + " has been defeated");
                }
            }

            if (party.size() == 0) {
                System.out.println("The Party wiped! " + baddie + " Reigns Supreme");
                JOptionPane.showMessageDialog(null, "The Party wiped! " + baddie + " Reigns Supreme");
                break;
                
            }

            // Baddie death check
            if (baddie.getHitpoints() <= 0) {
                System.out.println("The Adventurers are Victorious!");
                JOptionPane.showMessageDialog(null, "The Adventurers are Victorious!"); 
                break;
            }

            String results = "";
            results += baddie.getName() + ": " + baddie.getHitpoints();
            for (int i = 0; i < party.size(); i++) {
                Character current = party.get(i);
                results += "\n" + current.getName() + ": " + current.getHitpoints();
            }
            results += attack;
            if (initial || changed) {
                JOptionPane.showMessageDialog(null, results);
            }

            initial = false;

        }

        // End of turns loop
        System.out.println("The Encounter has concluded");

    }

}