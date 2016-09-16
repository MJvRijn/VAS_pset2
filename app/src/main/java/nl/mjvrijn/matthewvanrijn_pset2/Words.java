package nl.mjvrijn.matthewvanrijn_pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.Scanner;

public class Words extends AppCompatActivity {

    Story story;
    EditText field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.madlib1_tarzan));
        story = new Story(scanner);
        field = (EditText) findViewById(R.id.wordField);

        nextWord();

        //System.out.println(story.getStory());

    }

    public void submitWord(View view) {
        String word = field.getText().toString().trim();
        story.putWord(word);
        nextWord();
    }

    private void nextWord() {
        if(story.isComplete()) {
            Intent intent = new Intent(Words.this, FinalStory.class);
            intent.putExtra("story", story);
            startActivity(intent);
        } else {
            field.setText("");
            field.setHint(story.getNextType());
        }
    }

}
