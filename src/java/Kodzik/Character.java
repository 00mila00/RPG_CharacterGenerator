package Kodzik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Character implements Serializable, Cloneable {
    String name;
    String mountain;
    String race;
    String profession;
    String description;
    int[] primary = new int[8];
    int[] secondary = new int[9];
    int luck;
    int sanity;

    void generatePrimary() {
        int points = 300;
        Random random = new Random();
        List<Integer> list = new ArrayList<Integer>(8);
        for (int i = 0; i < 8; i++) {
            list.add(i,i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < 8; i++) {
            int temp;
            if (points < 60) {
                temp = random.nextInt(points);
            } else {
                temp = random.nextInt(60);
            }
            primary[list.get(0)] = temp;
            list.remove(0);
            points -= temp;
        }
        luck = 40;
        for (int i = 0; i < 4; i++) {
            luck += random.nextInt(10);
        }
        sanity = primary[7];
    }

    void genereteSecodary() {
        secondary[0] = primary[0]/10 + primary[1]/10 + 6; // zyw
        secondary[1] = primary[0]/10;
        secondary[2] = primary[1]/10;
        secondary[3] = primary[2]/10 + 1;
        secondary[4] = primary[3]/10;
        secondary[5] = primary[4]/10;
        secondary[6] = primary[5]/10;
        secondary[7] = primary[6]/10;
        secondary[8] = primary[7]/10;
    }

    void drawName(String[] pool) {
        int length = pool.length;
        Random random = new Random();
        name = pool[random.nextInt(length)];
    }

    void drawMountain(String[] pool) {
        int length = pool.length;
        Random random = new Random();
        mountain = pool[random.nextInt(length)];
    }

    void drawRace(String[] pool) {
        int length = pool.length;
        Random random = new Random();
        race = pool[random.nextInt(length)];
    }

    void drawProfession(String[] pool) {
        int length = pool.length;
        Random random = new Random();
        profession = pool[random.nextInt(length)];
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Character character = new Character();
        character.setName(name);
        character.setMountain(mountain);
        character.setRace(race);
        character.setProfession(profession);
        character.setPrimary(primary.clone());
        character.setSecondary(secondary.clone());
        character.setSanity(sanity);
        character.setLuck(luck);

        return character;
    }


    public boolean equals(Character o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return this.getName().equals(o.getName())
                && this.getMountain().equals(o.getMountain())
                && this.getRace().equals(o.getRace())
                && this.getProfession().equals(o.getProfession());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMountain(String mountain) {
        this.mountain = mountain;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrimary(int[] primary) {
        this.primary = primary;
    }

    public void setSecondary(int[] secondary) {
        this.secondary = secondary;
    }

    public String getMountain() {
        return mountain;
    }

    public String getRace() {
        return race;
    }

    public String getProfession() {
        return profession;
    }

    public String getDescription() {
        return description;
    }

    public int[] getPrimary() {
        return primary;
    }

    public int[] getSecondary() {
        return secondary;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getSanity() {
        return sanity;
    }

    public void setSanity(int sanity) {
        this.sanity = sanity;
    }
}
