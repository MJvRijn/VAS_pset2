package nl.mjvrijn.matthewvanrijn_pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;

public class Words extends AppCompatActivity {

    Story story;
    EditText field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        Toolbar actionBar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Restore from rotation
        if(savedInstanceState != null) {
            story = (Story) savedInstanceState.getSerializable("story");

        // Return from story display
//        } else if(getIntent().getSerializableExtra("story") != null) {
//
//            story = (Story) getIntent().getSerializableExtra("story");
//            story.goBack();
//        // Start fresh
        } else {
            Scanner scanner = new Scanner(getResources().openRawResource(R.raw.madlib1_tarzan));
            story = new Story(scanner);
        }

        field = (EditText) findViewById(R.id.wordField);
        nextWord();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        boolean rotating = isChangingConfigurations();
        if(!rotating) {
            story.goBack();
        }
        outState.putSerializable("story", story);
    }

    @Override
    public void onBackPressed() {
        if(!story.goBack()) {
            finish();
        }
        nextWord();
    }

    public void submitWord(View view) {
        String word = field.getText().toString().trim();
        story.putWord(word);
        nextWord();
    }

    private void nextWord() {
        System.out.println(story.numLeft());
        if(story.numLeft() == 0) {
            Intent intent = new Intent(Words.this, FinalStory.class);
            intent.putExtra("story", story);
            startActivity(intent);
        } else {
            TextView wordsLeft = (TextView) findViewById(R.id.wordsLeft);
            wordsLeft.setText(String.format("%d word%s remaining", story.numLeft(), story.numLeft() > 1 ? "s" : ""));

            field.setText(story.getWord());
            field.setHint(story.getType());
        }
    }

}
