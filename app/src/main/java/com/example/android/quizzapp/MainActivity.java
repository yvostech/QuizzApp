package com.example.android.quizzapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private int questions = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkAnswers(View view) {
        Button btn = (Button)findViewById(R.id.submit_btn);
        String btnText = btn.getText().toString();
        if(!btnText.equals("Submit")) {
            btn.setText("Submit");
            points = 0;
            setContentView(R.layout.activity_main);
        }else {
            // Check if all questions have an answer
            EditText text = (EditText)findViewById(R.id.animal_name);
            String animal = (String) text.getText().toString();
            if(animal.isEmpty()) {
                Toast toast = Toast.makeText(getApplicationContext(), "You must answer all questions!", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            // Check radio buttons for question 2
            TextView viewAnswer = (TextView) findViewById(R.id.answer_textview_2);
            String rightAnswer = viewAnswer.getText().toString();
            // Is the button now checked?
            RadioGroup radiogroup1 = (RadioGroup)findViewById(R.id.radiogroup1);
            if(radiogroup1.getCheckedRadioButtonId() == -1) {
                Toast toast = Toast.makeText(getApplicationContext(), "You must answer all questions!", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            // Check radio buttons for question 3 and if answer is right
            TextView viewAnswer2 = (TextView) findViewById(R.id.answer_textview_3);
            String rightAnswer2 = viewAnswer2.getText().toString();
            // Is the button now checked?
            RadioGroup radiogroup2 = (RadioGroup)findViewById(R.id.radiogroup2);
            if(radiogroup2.getCheckedRadioButtonId() == -1) {
                Toast toast = Toast.makeText(getApplicationContext(), "You must answer all questions!", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            // Which checkboxes are checked
            CheckBox box1 = (CheckBox) findViewById(R.id.cbox1);
            boolean checkboxChecked1 = box1.isChecked();
            CheckBox box2 = (CheckBox) findViewById(R.id.cbox2);
            boolean checkboxChecked2 = box2.isChecked();
            CheckBox box3 = (CheckBox) findViewById(R.id.cbox3);
            boolean checkboxChecked3 = box3.isChecked();
            CheckBox box4 = (CheckBox) findViewById(R.id.cbox4);
            boolean checkboxChecked4 = box4.isChecked();

            if(!checkboxChecked1 && !checkboxChecked2 && !checkboxChecked3 && !checkboxChecked4) {
                Toast toast = Toast.makeText(getApplicationContext(), "You must answer all questions!", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }


            // Question 1. Check if text is ok
            animal = animal.toLowerCase();
            if(animal.equals("whale")) {
                points++;
                TextView tic = (TextView)findViewById(R.id.tic0);
                tic.setVisibility(View.VISIBLE);
            }

            // Question 2. Get right answer
            int radioBtnId = radiogroup1.getCheckedRadioButtonId();
            RadioButton btnChecked = (RadioButton)radiogroup1.findViewById(radioBtnId);
            String selectedText = btnChecked.getText().toString();
            if(rightAnswer.equals(selectedText)) {
                points++;
                TextView tic1 = (TextView)findViewById(R.id.tic1);
                tic1.setVisibility(View.VISIBLE);
            }

            // Question 3. Get right answer
            int radioBtnId2 = radiogroup2.getCheckedRadioButtonId();
            RadioButton btnChecked2 = (RadioButton)radiogroup2.findViewById(radioBtnId2);
            String selectedText2 = btnChecked2.getText().toString();
            if(rightAnswer2.equals(selectedText2)) {
                points++;
                TextView tic2 = (TextView)findViewById(R.id.tic2);
                tic2.setVisibility(View.VISIBLE);
            }


            // Question 4. Check answer
            if(checkboxChecked1 && checkboxChecked3 && !checkboxChecked2 && !checkboxChecked4) {
                points++;
                TextView tic3 = (TextView)findViewById(R.id.tic3);
                tic3.setVisibility(View.VISIBLE);
            }

            if(questions == points) {
                Toast toast = Toast.makeText(getApplicationContext(), "Greate all answers right!", Toast.LENGTH_SHORT);
                toast.show();
                btn.setText("Restart");
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), points + " answers right!", Toast.LENGTH_SHORT);
                toast.show();
                btn.setText("Try again");
            }
            points = 0;
        }

    }
}
