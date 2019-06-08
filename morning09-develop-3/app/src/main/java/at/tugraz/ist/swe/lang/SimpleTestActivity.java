package at.tugraz.ist.swe.lang;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimpleTestActivity extends AppCompatActivity {
    private String correctAnswer;
    public int score = 0;
    public int attemps = 0;
    private ArrayAdapter answersAdapter;
    ArrayList<String> quizQuestions = new ArrayList<String>();
    TextView tvScore, tvQuestion;
    ListView tvAnswers;
    Vocabulary vocabulary;
    int quizLength = 6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_test);

        vocabulary = new Vocabulary(getApplicationContext());
        vocabulary.init();

        if(vocabulary.getVocabArray().length() < quizLength)
        {
            quizLength = vocabulary.getVocabArray().length();

        }

        if(quizLength < 1)
        {
            Toast.makeText(getApplicationContext(),"Too few words for quiz",Toast.LENGTH_SHORT).show();

            finish();
            return;
        }


        tvScore = findViewById(R.id.score);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvAnswers = findViewById(R.id.multiple);
        generateQuiz("english", quizLength);

        updateTest();

        tvAnswers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                attemps++;
                correctAnswer = vocabulary.getTranslation("german", tvQuestion.getText().toString());
                String choice =  (String) tvAnswers.getItemAtPosition(position);
                if (correctAnswer.equals(choice)) {
                    score++;
                    if (score == quizLength) {
                        tvScore.setText("Score: " + score);
                        youWin();
                        score = 0;
                    }
                    else {
                        updateTest();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"WRONG!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void updateTest () {

        tvQuestion.setText(quizQuestions.get(score));

        correctAnswer = vocabulary.getTranslation("german", quizQuestions.get(score));
        ArrayList<String> words = new ArrayList<String>();


        JSONArray jsonArray = vocabulary.getVocabArray();
        Random rand = new Random();
        ArrayList<Integer> words_index = new ArrayList<Integer>();

        for (int i=0; i<quizLength-1; i++) {
            int random;

            try {
                do {
                    random = rand.nextInt(jsonArray.length());
                } while (words_index.contains(random) || jsonArray.getJSONObject(random).getString("german") == correctAnswer);
                words_index.add(random);
                words.add(jsonArray.getJSONObject(random).getString("german"));
            }
            catch(JSONException e){
                e.printStackTrace();
            }
        }
        words.add(correctAnswer);

        Collections.shuffle(words);

        answersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        tvAnswers.setAdapter(answersAdapter);
        tvScore.setText("Score: " + score);
    }

    public void youWin() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SimpleTestActivity.this);
        alertDialogBuilder
                .setMessage("Well done!!! You passed your exam with: " + score + " Correct Answers in " + attemps + " Attemps")
                .setCancelable(false)
                .setPositiveButton("Retry?",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                startActivity(new Intent(getApplicationContext(),SimpleTestActivity.class));
                                finish();

                            }
                        })
                .setNegativeButton("Exit",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                finish();

                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void generateQuiz(String lang, int quantity) {
        JSONArray jsonArray = vocabulary.getVocabArray();
        Random rand = new Random();
        ArrayList<Integer> quiz_words = new ArrayList<Integer>();
        quantity = (quantity >= jsonArray.length()) ? jsonArray.length() : quantity;
        for (int i=0; i<quantity; i++) {
            int random = rand.nextInt(jsonArray.length());

            while (quiz_words.contains(random)) {
                random = rand.nextInt(jsonArray.length());
            }

            quiz_words.add(random);

            try {
                quizQuestions.add(jsonArray.getJSONObject(random).getString(lang));
            }
            catch(JSONException e){
            e.printStackTrace();
            }
        }

        System.out.println(quizQuestions);
    }
}

