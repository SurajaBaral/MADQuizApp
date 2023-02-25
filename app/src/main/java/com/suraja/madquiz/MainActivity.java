package com.suraja.madquiz;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvTotalques;
    TextView tvQues;
    Button btnAnsA, btnAnsB, btnAnsC;
    Button btnSubmit;
    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTotalques = findViewById(R.id.tvTotalques);
        tvQues = findViewById(R.id.tvQues);
        btnAnsA = findViewById(R.id.btnAnsA);
        btnAnsB = findViewById(R.id.btnAnsB);
        btnAnsC = findViewById(R.id.btnAnsC);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnAnsA.setOnClickListener(this);
        btnAnsB.setOnClickListener(this);
        btnAnsC.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvTotalques.setText(" Total Questions: " + totalQuestion);
        loadNewQuestion();
    }
    @Override
    public void onClick(View v) {
        btnAnsA.setBackgroundColor(Color.BLACK);
        btnAnsB.setBackgroundColor(Color.BLACK);
        btnAnsC.setBackgroundColor(Color.BLACK);
        Button clickedButton = (Button) v;
            if (clickedButton.getId() == R.id.btnSubmit) {
                if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])) {
                    score++;
                } else {
                    //show an alert dialog if no answer is selected
                    if (selectedAnswer.isEmpty()) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setMessage("Choose Your Answer")
                                .setPositiveButton("OK", (dialogInterface, i) -> {
                                })
                                .setCancelable(false)
                                .show();
                        return;
                    }
                }
                currentQuestionIndex++;
                if(currentQuestionIndex == totalQuestion){
                    finishQuiz();
                }else{
                loadNewQuestion();}
            }
            else {
                //choices button clicked
                selectedAnswer = clickedButton.getText().toString();
                clickedButton.setBackgroundColor(Color.CYAN);
            }

        }
    void loadNewQuestion() {
        tvQues.setText(QuestionAnswer.question[currentQuestionIndex]);
        btnAnsA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        btnAnsB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        btnAnsC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
    }

   private void finishQuiz() {
        String passStatus = "";
        if (score > totalQuestion*0.60){
            passStatus = "You Won";
        }else{
            passStatus= "Try Again";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+totalQuestion)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;

        Intent intent= new Intent(MainActivity.this, NameActivity.class);
        startActivity(intent);
    }
}