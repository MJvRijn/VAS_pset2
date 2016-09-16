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

        Intent i = getIntent();
        Story story = (Story) i.getSerializableExtra("story");

        TextView text = (TextView) findViewById(R.id.finalStoryText);
        text.setText(Html.fromHtml(story.getStory()+story.getStory()+story.getStory()+story.getStory()));
    }

//    @Override
//    public void onBackPressed() {
//        Intent i = new Intent(FinalStory.this, Words.class);
//        i.putExtra("story", getIntent().getSerializableExtra("story"));
//        startActivity(i);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId() == android.R.id.home) {
//            Intent i = new Intent(FinalStory.this, Words.class);
//            i.putExtra("story", getIntent().getSerializableExtra("story"));
//            startActivity(i);
//        }
//
//        return true;
//    }

}
