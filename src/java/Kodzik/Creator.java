package Kodzik;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Creator {

    private File charactersFile = null;

    String[] namePool;
    String[] mountainPool;
    List<String[]> racesPool = new ArrayList<>();
    List<String[]> professionsPool = new ArrayList<>();
    List<Character> characters = new LinkedList<>();

    public Creator() {

        try{
            charactersFile = new File("characters");
        } catch(Exception e) {
        }


        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("database/names").getFile());
        loadNamePool(file);

        file = new File(classLoader.getResource("database/mountains").getFile());
        loadMountainPool(file);

        file = new File(classLoader.getResource("database/races").getFile());
        loadRacesPool(file);

        file = new File(classLoader.getResource("database/professions").getFile());
        loadProfessionsPool(file);

    }

    public Character createCharacter(String name, String mountain, String race, String profession) {
        Character character = new Character();
        if(name != null) {
            character.setName(name);
        } else {
            character.drawName(namePool);
        }

        if(mountain != null) {
            character.setMountain(mountain);
        } else {
            character.drawMountain(mountainPool);
        }

        int index = indexOfMountain(character.getMountain());
        if(race != null) {
            character.setRace(race);
        } else {
            character.drawRace(racesPool.get(index));
        }

        int index2 = indexOfRace(character.getRace(), index);
        if(profession != null) {
            character.setProfession(profession);
        } else {
            character.drawProfession(professionsPool.get(index2 + indexOfProffesion(index)));
        }

        character.generatePrimary();
        character.genereteSecodary();
        return character;
    }


    public void loadNamePool(File file) {
        String string = null;
        try (FileInputStream input = new FileInputStream(file)){
            byte[] temp = new byte[input.available()];
            input.read(temp);
            string = new String(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (string != null) {
            namePool = string.split(",");
        }
    }

    public void loadMountainPool(File file) {
        String string = null;
        try (FileInputStream input = new FileInputStream(file)){
            byte[] temp = new byte[input.available()];
            input.read(temp);
            string = new String(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (string != null) {
            mountainPool = string.split("\r\n");
        }
    }

    public void loadRacesPool(File file) {
        String string = null;
        try (FileInputStream input = new FileInputStream(file)){
            byte[] temp = new byte[input.available()];
            input.read(temp);
            string = new String(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (string != null) {
            String[] table = string.split("\r\nnext:\r\n");
            for (int i = 0; i < table.length; i++) {
                String[] temp = table[i].split(",");
                racesPool.add(temp);
            }
        }
    }

    public void loadProfessionsPool(File file) {
        String string = null;
        try (FileInputStream input = new FileInputStream(file)){
            byte[] temp = new byte[input.available()];
            input.read(temp);
            string = new String(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (string != null) {
            String[] table = string.split("\r\nnext:\r\n");
            for (int i = 0; i < table.length; i++) {
                professionsPool.add(table[i].split(","));
            }
        }
    }

    public void saveCharacter(Character character) throws CloneNotSupportedException {
        characters.add((Character)character.clone());
        saveCharacters();
    }

    public void saveCharacters() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(charactersFile))){
            output.writeObject(characters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCharacters() throws IOException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(charactersFile))){
            characters = (List<Character>) input.readObject();
        } catch (IOException | ClassNotFoundException e ) {
            throw new IOException("Cant read characters file");
        }
    }

    public void deleteFile() {
        charactersFile.delete();
    }

    public int indexOfMountain(String mountain) {
        for (int i = 0; i < mountainPool.length; i++) {
            if(mountain.equals(mountainPool[i])) {
                return i;
            }
        }
        return 0;
    }

    public int indexOfRace(String race, int n) {
        for (int i = 0; i < racesPool.get(n).length; i++) {
            if(race.equals(racesPool.get(n)[i])) {
                return i;
            }
        }
        return 0;
    }

    public int indexOfProffesion(int n) {
        int temp = 0;
        for (int i = 0; i < n; i++) {
            temp += racesPool.get(i).length;
        }
        return temp;
    }

    public boolean contains(Character o) {
        for (Character character: characters) {
            if(character.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    public Character getCharacter(int i) {
        return characters.get(i);
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public int getAmountOfCharacters() {
        return characters.size();
    }

    public String[] getMountainPool() {
        return mountainPool;
    }

    public List<String[]> getRacesPool() {
        return racesPool;
    }

    public List<String[]> getProfessionsPool() {
        return professionsPool;
    }


}
