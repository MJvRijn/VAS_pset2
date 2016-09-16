package nl.mjvrijn.matthewvanrijn_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Toolbar actionBar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(actionBar);

        setTitle("Mad Libs");
    }

    /* Handle the main menu button presses to start the game. Map the pressed button to the desired
     * resource file and send it to the activity for entering words.
     */
    public void start(View view) {
        int resource;
        switch(view.getId()) {
            case R.id.madlib_button_1:
                resource = R.raw.madlib1_tarzan;
                break;
            case R.id.madlib_button_2:
                resource = R.raw.madlib2_university;
                break;
            case R.id.madlib_button_3:
                resource = R.raw.madlib3_clothes;
                break;
            case R.id.madlib_button_4:
                resource = R.raw.madlib4_dance;
                break;
            default:
                resource = R.raw.madlib0_simple;
        }

        Intent intent = new Intent(Intro.this, Words.class);
        intent.putExtra("file", resource);
        startActivity(intent);
    }
}
