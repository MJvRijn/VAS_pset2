package nl.mjvrijn.matthewvanrijn_pset2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Story {

    private boolean complete = false;
    private Pattern neededWord = Pattern.compile("<(.*?)>");
    private String story;
    private String nextType;

    public Story(Scanner s) {
        story = s.nextLine();
        setNextType();
    }

    public void putWord(String word) {
        if(!complete) {
            story = story.replaceFirst(neededWord.pattern(), word);
        }

        setNextType();
    }

    public String getNextType() {
        return nextType;
    }

    public String getStory() {
        return story;
    }

    public boolean isComplete() {
        return complete;
    }

    private void setNextType() {
        Matcher matcher = neededWord.matcher(story);
        if(matcher.find()) {
            nextType =  matcher.group(1).replace('-', ' ');
        } else {
            complete = true;
        }
    }
}
