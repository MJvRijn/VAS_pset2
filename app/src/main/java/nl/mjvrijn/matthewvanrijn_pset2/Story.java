package nl.mjvrijn.matthewvanrijn_pset2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Story implements Serializable {

    private Pattern expression = Pattern.compile("<(.*?)>");
    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<String> types = new ArrayList<>();
    private int index = 0;
    private String story = "";

    public Story(Scanner s) {
        // Read the entire file into a string
        while(s.hasNextLine()) {
            story += s.nextLine().trim() + " ";
        }

        // Populate the array of word types
        Matcher m = expression.matcher(story);
        while(m.find()) {
            types.add(m.group().replace("-", " "));
        }
    }

    public void putWord(String word) {
        if(index < types.size()) {
            words.add(index, word);
        }
        index++;
    }

    public boolean goBack() {
        if(index > 0) {
            index--;
            return true;
        } else {
            return false;
        }
    }

    public String getType() {
        return types.get(index);
    }

    public String getWord() {
        if(words.size() > index) {
            return words.get(index);
        } else {
            return "";
        }
    }

    public int numLeft() {
        return types.size() - index;
    }

    public String getStory() {
        String finalStory = story;
        for(int i = 0; i<words.size(); i++) {
            finalStory = finalStory.replaceFirst(expression.pattern(), "@"+words.get(i)+"*");
        }
        return finalStory.replace("@", "<b>").replace("*", "</b>");
    }
}
