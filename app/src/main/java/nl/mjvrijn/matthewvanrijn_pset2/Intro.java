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

    public void nextStep(View view) {
        Intent intent = new Intent(Intro.this, Words.class);
        startActivity(intent);
    }
}
