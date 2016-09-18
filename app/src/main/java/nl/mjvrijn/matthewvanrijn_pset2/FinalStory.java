package nl.mjvrijn.matthewvanrijn_pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

public class FinalStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_story);
        Toolbar actionBar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Your Story");

        // Get the story text and display it as HTML to show the chosen words in bold.
        Intent i = getIntent();
        Story story = (Story) i.getSerializableExtra("story");

        TextView text = (TextView) findViewById(R.id.finalStoryText);
        text.setText(Html.fromHtml(story.getStory()));
    }

    /* Override the UI up button. By default this button restarts the parent activity, but the
     * desired behaviour is to resume the parent activity in the same way the android back button
     * does. This is implemented by calling finish when pressed.  */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }

}
