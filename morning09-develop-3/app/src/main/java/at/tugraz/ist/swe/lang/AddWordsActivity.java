package at.tugraz.ist.swe.lang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddWordsActivity extends AppCompatActivity {

    Button btnAdd;
    EditText ptAddEnglish, ptAddGerman;
    ListView lvWordList;
    Vocabulary vocabulary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        ptAddEnglish = (EditText)findViewById(R.id.ptAddEnglish);
        ptAddGerman = (EditText)findViewById(R.id.ptAddGerman);
        lvWordList = (ListView)findViewById(R.id.lvWordList);
        btnAdd = (Button)findViewById(R.id.btnAdd);

        vocabulary = new Vocabulary(getApplicationContext());
        vocabulary.init();

        updateListView();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String englishWord = ptAddEnglish.getText().toString();
                String germanWord = ptAddGerman.getText().toString();

                if(englishWord.length() == 0 || germanWord.length() == 0){
                    Toast.makeText(getBaseContext(), "Empty input!", Toast.LENGTH_LONG).show();
                }
                else{
                    vocabulary.add(germanWord,englishWord);
                    vocabulary.storeFile();
                    updateListView();
                }
                ptAddEnglish.getText().clear();
                ptAddGerman.getText().clear();
            }
        });
    }

    public void updateListView() {
        try{
            JSONArray jsonArray = vocabulary.getVocabArray();
            ArrayList<String> wordArray = new ArrayList<String>();

            for(int i=0; i < jsonArray.length();i++){
                String entry = jsonArray.getJSONObject(i).getString("english") + " : " + jsonArray.getJSONObject(i).getString("german");
                wordArray.add(entry);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddWordsActivity.this, android.R.layout.simple_list_item_1, wordArray);
            lvWordList.setAdapter(adapter);
            lvWordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //setContentView(R.layout.activity_rating);
                    //openRatingActivity(position);

                    System.out.println("Position Value is:");
                    String Value = (String) lvWordList.getItemAtPosition(position);

                    System.out.println(Value);
                    openRatingActivity(position);
                    System.out.println(Value);


                }

            });

        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void openRatingActivity(int position) {
        Intent intent = new Intent(this, RatingActivity.class);
        startActivity(intent);

        try {
            System.out.println("Position Clicked is:");
            System.out.println(position);
            System.out.println("Position Clicked was:");

            String Voc = (String) lvWordList.getItemAtPosition(position);

            JSONArray myJson = vocabulary.getVocabArray();
            JSONObject myVocabulary = myJson.getJSONObject(vocabulary.findByName(Voc));
            System.out.println(myVocabulary.getString("german"));
            System.out.println(myVocabulary.getString("english"));

        }
        catch(JSONException e) {
            e.printStackTrace();
            //
        }


        System.out.println("Set Content View 1" );
        setContentView(R.layout.activity_rating);

        TextView textViewDe = (TextView)findViewById(R.id.textViewDe);
        TextView textViewEn = (TextView)findViewById(R.id.textViewEn);
        System.out.println("Text Views Found:");

        String[] sel_word_de;
        sel_word_de =  getResources().getStringArray(R.array.dewords);
        String[] sel_word_en;
        sel_word_en =  getResources().getStringArray(R.array.enwords);
        System.out.println("Vocabulary is:" );

        textViewDe.setText(sel_word_de[position]);
        textViewEn.setText(sel_word_en[position]);


    }
    }
