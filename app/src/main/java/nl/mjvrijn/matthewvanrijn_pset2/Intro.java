package nl.mjvrijn.matthewvanrijn_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Scanner;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.madlib1_tarzan));
        Story story = new Story(scanner);

        while(!story.isComplete()) {
            System.out.println(story.getNextType());
            story.putWord("NOPE");
        }

        System.out.println(story.getStory());
    }

    public void nextStep(View view) {
        Intent intent = new Intent(Intro.this, Words.class);
        startActivity(intent);
    }
}
