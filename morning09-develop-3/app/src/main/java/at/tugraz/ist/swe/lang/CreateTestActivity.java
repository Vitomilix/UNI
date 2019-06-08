package at.tugraz.ist.swe.lang;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;


public class CreateTestActivity extends AppCompatActivity {

    private TextView name_of_test;
    private Button saveTest;
    private ListView vocabList;
    Vocabulary vocabulary;
    JSONArray vocabArray = new JSONArray();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_create);

        name_of_test = (EditText)findViewById(R.id.test_name);
        saveTest = findViewById(R.id.saveTest_btn);
        vocabList = (ListView)findViewById(R.id.vocabList);
        vocabulary = new Vocabulary(getApplicationContext());
        vocabulary.init();
        vocabArray = vocabulary.getVocabArray();

        try {
            JSONArray jsonArray = vocabulary.getVocabArray();
            ArrayList<String> wordArray = new ArrayList<String>();

            for (int i = 0; i < jsonArray.length(); i++) {
                String entry = jsonArray.getJSONObject(i).getString("english") + " : " + jsonArray.getJSONObject(i).getString("german");
                wordArray.add(entry);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateTestActivity.this, android.R.layout.simple_list_item_multiple_choice, wordArray);
            vocabList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            vocabList.setAdapter(adapter);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        saveTest.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selected = "";
                int cntChoice = vocabList.getCount();

                if (name_of_test.getText().length() == 0)
                {
                    Toast.makeText(getBaseContext(), "Empty name!", Toast.LENGTH_LONG).show();
                }
                else if (cntChoice == 1)
                {
                    Toast.makeText(getBaseContext(), "Choose at least one word!", Toast.LENGTH_LONG).show();
                }
                else {

                    SparseBooleanArray sparseBooleanArray = vocabList.getCheckedItemPositions();

                    for (int i = 0; i < cntChoice; i++) {

                        if (sparseBooleanArray.get(i)) {

                            selected += vocabList.getItemAtPosition(i).toString() + "\n";
                        }
                    }
                    Toast.makeText(CreateTestActivity.this, name_of_test.getText() +
                            " saved!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
