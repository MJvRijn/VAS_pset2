package nl.mjvrijn.matthewvanrijn_pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class FinalStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_story);

        Intent i = getIntent();
        Story story = (Story) i.getSerializableExtra("story");

        TextView text = (TextView) findViewById(R.id.finalStoryText);
        text.setText(story.getStory());
    }

}
