package nl.mjvrijn.matthewvanrijn_pset2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Story implements Serializable {

    private Pattern expression = Pattern.compile("<(.*?)>");
    private ArrayList<String> types = new ArrayList<>();
    private String[] words;
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
            types.add(m.group().replace("-", " ").toLowerCase());
        }

        // Initialise word array
        words = new String[types.size()];
    }

    /* Add a word at the current index and increment the index */
    public void putWord(String word) {
        if(index < words.length) {
            words[index] = word;
        }
        index++;
    }

    /* Decrement the current index if it is above 0 */
    public boolean goBack() {
        if(index > 0) {
            index--;
            return true;
        } else {
            return false;
        }
    }

    /* Return the type of the desired word for this index */
    public String getType() {
        return types.get(index);
    }

    /* Return the previously chosen word for this index if present, else return an empty string. */
    public String getWord() {
        if(words[index] != null) {
            return words[index];
        } else {
            return "";
        }
    }

    /* Return the number of words still to be filled */
    public int numLeft() {
        return types.size() - index;
    }

    /* Merge the chosen words and the story template to create the final story. */
    public String getStory() {
        String finalStory = story;
        for(String word : words) {
            finalStory = finalStory.replaceFirst(expression.pattern(), "@"+word+"*");
        }
        return finalStory.replace("@", "<b>").replace("*", "</b>");
    }
}
