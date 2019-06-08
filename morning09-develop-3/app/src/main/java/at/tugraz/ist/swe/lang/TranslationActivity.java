package at.tugraz.ist.swe.lang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class TranslationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayAdapter adapter_de;
    private ArrayAdapter adapter_en;
    ListView lvVocabulary;
    Vocabulary vocabulary;
    private JSONArray vocabArray = new JSONArray();
    ArrayList<String> wordArrayEn = new ArrayList<String>();
    ArrayList<String> wordArrayGer = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        String[] dewords = getResources().getStringArray(R.array.dewords);
        String[] enwords = getResources().getStringArray(R.array.enwords);
        Button btn_de = findViewById(R.id.btnGerman);
        Button btn_en = findViewById(R.id.btnEnglish);
        lvVocabulary = (ListView)findViewById(R.id.lvVocabulary);
        vocabulary = new Vocabulary(getApplicationContext());
        vocabulary.init();
        vocabArray = vocabulary.getVocabArray();

        updateVocabulary();

        lvVocabulary.setAdapter(adapter_de);
        btn_de.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                lvVocabulary.setAdapter(adapter_de);
            }
        });
        btn_en.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                lvVocabulary.setAdapter(adapter_en);
            }
        });

        lvVocabulary.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView <?> parent, View view, int index, long arg3) {
        TextView text = (TextView)findViewById(R.id.tvTranslatedWord);
        String sel_word;

        if (parent.getAdapter() == adapter_de) {
            sel_word = wordArrayEn.get(index);
        }
        else
        {
            sel_word = wordArrayGer.get(index);
        }
        text.setText(sel_word);
    }

    public void updateVocabulary() {
        try {
            JSONArray jsonArray = vocabArray;


            for (int i = 0; i < jsonArray.length(); i++) {
                String entry = jsonArray.getJSONObject(i).getString("english");
                wordArrayEn.add(entry);
            }

            for (int i = 0; i < jsonArray.length(); i++) {
                String entry = jsonArray.getJSONObject(i).getString("german");
                wordArrayGer.add(entry);
            }
            adapter_en = new ArrayAdapter<String>(TranslationActivity.this, android.R.layout.simple_list_item_1, wordArrayEn);
            adapter_de = new ArrayAdapter<String>(TranslationActivity.this, android.R.layout.simple_list_item_1, wordArrayGer);
            //lvVocabulary.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();


        }
    }
}
