package at.tugraz.ist.swe.lang;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.icu.lang.UCharacter.toLowerCase;

public class CategorizationActivity extends AppCompatActivity {

    Button btnAlphabeticSort;
    Spinner spLanguageSort, spTagSort;
    Vocabulary vocabulary;
    ListView lvVocabulary;
    private JSONArray vocabArray = new JSONArray();
    ArrayList<String> languageList = new ArrayList<String>();
    ArrayList<String> tagList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorization);



        btnAlphabeticSort = (Button)findViewById(R.id.btnAlphabeticSort);
        spLanguageSort = (Spinner)findViewById(R.id.spLanguageSort);
        spTagSort = (Spinner)findViewById(R.id.spTagSort);
        lvVocabulary = (ListView)findViewById(R.id.lvVocabulary);
        vocabulary = new Vocabulary(getApplicationContext());
        vocabulary.init();
        vocabArray = vocabulary.getVocabArray();

        updateLanguages();
        updateTags();
        updateVocabulary();
        btnAlphabeticSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("Select language".compareTo(spLanguageSort.getSelectedItem().toString()) !=0){
                    sortAlphabet(spLanguageSort.getSelectedItem().toString());
                }
                else
                {
                    Toast.makeText(getBaseContext(), "No language selected!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    public void sortAlphabet(String choice) {
        try{
            JSONArray jsonArray = vocabulary.getVocabArray();
            JSONArray sortedJsonArray = new JSONArray();

            List<JSONObject> jsonValues = new ArrayList<JSONObject>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonValues.add(jsonArray.getJSONObject(i));
            }
            final String temp_choice = choice;
            Collections.sort( jsonValues, new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject a, JSONObject b) {
                    String KEY_NAME = temp_choice;
                    String valA = "";
                    String valB = "";

                    try {
                        valA = (String) a.get(KEY_NAME);
                        valB = (String) b.get(KEY_NAME);
                    }
                    catch (JSONException e) {
                    }
                    String lowerA = valA.toLowerCase();
                    String lowerB = valB.toLowerCase();
                    return lowerA.compareTo(lowerB);
                }
            });

            for (int i = 0; i < jsonArray.length(); i++) {
                sortedJsonArray.put(jsonValues.get(i));
            }

            vocabArray = sortedJsonArray;

        } catch(JSONException e){
            e.printStackTrace();
        }

        updateVocabulary();

    }

    public void filterTags() {
        // needs tag implementation first, shifting to new issue 
    }

    public void updateLanguages() {

        languageList.add("Select language");
        languageList.add("german");
        languageList.add("english");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CategorizationActivity.this, android.R.layout.simple_dropdown_item_1line, languageList);
        spLanguageSort.setAdapter(adapter);

    }

    public void updateTags() {

        tagList.add("Select Tag");
        tagList.add("Fruit");
        tagList.add("Hardcore");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CategorizationActivity.this, android.R.layout.simple_dropdown_item_1line, tagList);
        spTagSort.setAdapter(adapter);

    }

    public void updateVocabulary() {
        try{
            JSONArray jsonArray = vocabArray;//
            ArrayList<String> wordArray = new ArrayList<String>();

            for(int i=0; i < jsonArray.length();i++){
                String entry = jsonArray.getJSONObject(i).getString("english") + " : " + jsonArray.getJSONObject(i).getString("german");
                wordArray.add(entry);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(CategorizationActivity.this, android.R.layout.simple_list_item_1, wordArray);
            lvVocabulary.setAdapter(adapter);

        } catch(JSONException e){
            e.printStackTrace();
        }
    }


}
