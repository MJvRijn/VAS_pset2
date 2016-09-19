package nl.mjvrijn.matthewvanrijn_pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;

public class Words extends AppCompatActivity {

    private Story story;
    private EditText field;

    /* There are two situations in which onCreate is called. The first is when coming from the main
     * menu, the second is when the screen is rotated. When the screen is rotated the state of the
     * previously saved state of the story is restored. When coming from the main menu a new Story
     * instance is created
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        Toolbar actionBar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState != null) {    // Restore from rotation
            story = (Story) savedInstanceState.getSerializable("story");
        } else {    // Create new instance
            int resource = getIntent().getIntExtra("file", -1);
            Scanner scanner = new Scanner(getResources().openRawResource(resource));
            story = new Story(scanner);
        }

        // Setup ENTER key listener for continuing to the next word
        field = (EditText) findViewById(R.id.wordField);
        field.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    submitWord(field);
                    return true;
                } else {
                    return false;
                }
            }
        });

        nextWord();
    }

    /* onSaveInstanceState saves the state of the story. It is called when the screen is rotated or
     * when the story is complete and the app goes to the story display activity. When the app goes
     * to the story display activity it sets the story back one step to allow the user to return
     * to it later using the back button.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        boolean rotating = isChangingConfigurations();
        if(!rotating) {
            story.goBack();
        }
        outState.putSerializable("story", story);
    }

    /* Override the back button to make the interface go back one word when back is pressed instead
    * of returning to the main menu. If there is no word to return to it goes back to the main menu.
    */
    @Override
    public void onBackPressed() {
        if(!story.goBack()) {
            finish();
        }
        nextWord();
    }

    /* Insert the user's word into the story upon sumbission and advance to the next word */
    public void submitWord(View view) {
        String word = field.getText().toString().trim();
        story.putWord(word);
        nextWord();
    }

    /* nextWord updates all display elements for the next word. If there are no more words to
     * fill in it continues to the story display activity.
     */
    private void nextWord() {
        if(story.numLeft() == 0) {  // Continue to display
            Intent intent = new Intent(Words.this, FinalStory.class);
            intent.putExtra("story", story);
            startActivity(intent);
        } else {                    // Update UI elements
            TextView wordsLeft = (TextView) findViewById(R.id.wordsLeft);
            wordsLeft.setText(String.format("%d word%s remaining", story.numLeft(), story.numLeft() > 1 ? "s" : ""));

            field.setText(story.getWord());
            field.setHint(story.getType());
        }
    }

}
