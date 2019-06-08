package at.tugraz.ist.swe.lang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TakeTestActivity extends AppCompatActivity {

    private TextView wordToTest;
    private EditText testedWord;
    private Button tryTest;
    private int myScore = 0;
    private int myAttemps = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_test);

        wordToTest = findViewById(R.id.word_to_test);
        testedWord = findViewById(R.id.tested_word);
        tryTest = findViewById(R.id.try_test);

        wordToTest.setText("Try");

        tryTest.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAttemps++;
                String word1 = wordToTest.getText().toString();
                String word2 = testedWord.getText().toString();
                if (word1.equals(word2))
                {
                    myScore++;
                    Toast.makeText(TakeTestActivity.this,"Well done!!! You passed your " +
                            "exam with: " +myScore + " Correct Answers in " +myAttemps + " Attemps", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(TakeTestActivity.this,"Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}