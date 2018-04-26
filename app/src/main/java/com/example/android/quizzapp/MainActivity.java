package com.example.android.quizzapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkAnswers(View view) {
        Button btn = findViewById(R.id.submit_btn);
        String btnText = btn.getText().toString();
        if (!btnText.equals("Submit")) {
            btn.setText(R.string.submit);
            points = 0;
            setContentView(R.layout.activity_main);
        } else {
            // Check if all questions have an answer
            EditText text = findViewById(R.id.animal_name);
            String animal = text.getText().toString();
            if (animal.isEmpty()) {
                Toast toast = Toast.makeText(getApplicationContext(), "You must answer all questions!", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            // Check radio buttons for question 2
            TextView viewAnswer = findViewById(R.id.text_view_species_answer);
            String rightAnswer = viewAnswer.getText().toString();
            // Is the button now checked?
            RadioGroup radiogroup1 = findViewById(R.id.radiogroup_species);
            if (radiogroup1.getCheckedRadioButtonId() == -1) {
                Toast toast = Toast.makeText(getApplicationContext(), "You must answer all questions!", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            // Check radio buttons for question 3 and if answer is right
            TextView viewAnswer2 = findViewById(R.id.text_view_giraffes_answer);
            String rightAnswer2 = viewAnswer2.getText().toString();
            // Is the button now checked?
            RadioGroup radiogroup2 = findViewById(R.id.radiogroup_giraffes);
            if (radiogroup2.getCheckedRadioButtonId() == -1) {
                Toast toast = Toast.makeText(getApplicationContext(), "You must answer all questions!", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            // Which checkboxes are checked
            CheckBox box1 = findViewById(R.id.checbox_yak);
            boolean checkboxChecked1 = box1.isChecked();
            CheckBox box2 = findViewById(R.id.checkbox_parrot);
            boolean checkboxChecked2 = box2.isChecked();
            CheckBox box3 = findViewById(R.id.checkbox_okapi);
            boolean checkboxChecked3 = box3.isChecked();
            CheckBox box4 = findViewById(R.id.checkbox_sardines);
            boolean checkboxChecked4 = box4.isChecked();

            if (!checkboxChecked1 && !checkboxChecked2 && !checkboxChecked3 && !checkboxChecked4) {
                Toast toast = Toast.makeText(getApplicationContext(), "You must answer all questions!", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            // Question 1. Check if text is ok
            animal = animal.toLowerCase();
            if (animal.equals("whale")) {
                points++;
                TextView tic = findViewById(R.id.tic_animal);
                tic.setVisibility(View.VISIBLE);
            }

            // Question 2. Get right answer
            int radioBtnId = radiogroup1.getCheckedRadioButtonId();
            RadioButton btnChecked = radiogroup1.findViewById(radioBtnId);
            String selectedText = btnChecked.getText().toString();
            if (rightAnswer.equals(selectedText)) {
                points++;
                TextView tic1 = findViewById(R.id.tic_species);
                tic1.setVisibility(View.VISIBLE);
            }

            // Question 3. Get right answer
            int radioBtnId2 = radiogroup2.getCheckedRadioButtonId();
            RadioButton btnChecked2 = radiogroup2.findViewById(radioBtnId2);
            String selectedText2 = btnChecked2.getText().toString();
            if (rightAnswer2.equals(selectedText2)) {
                points++;
                TextView tic2 = findViewById(R.id.tic_giraffes);
                tic2.setVisibility(View.VISIBLE);
            }

            // Question 4. Check answer
            if (checkboxChecked1 && checkboxChecked3 && !checkboxChecked2 && !checkboxChecked4) {
                points++;
                TextView tic3 = findViewById(R.id.tic_mammals);
                tic3.setVisibility(View.VISIBLE);
            }

            // Number of questions in the quizz
            int number_of_questions = 4;
            if (number_of_questions == points) {
                Toast toast = Toast.makeText(getApplicationContext(), "Greate all answers right!", Toast.LENGTH_SHORT);
                toast.show();
                btn.setText(R.string.restart);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), points + " answers right!", Toast.LENGTH_SHORT);
                toast.show();
                btn.setText(R.string.try_again);
            }
            points = 0;
        }
    }
}
