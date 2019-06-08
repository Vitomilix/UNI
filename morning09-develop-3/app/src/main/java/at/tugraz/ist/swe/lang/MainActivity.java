package at.tugraz.ist.swe.lang;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout addWords, btnCategory, translation, simpleTest,
            btnSettings, btnAdvanceTest, btnHelp, btnLegal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnLegal = findViewById(R.id.btnLegal);
        btnLegal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLegal();
            }
        });

        btnHelp = findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelp();
            }
        });


        addWords = findViewById(R.id.btMainAddWords);
        addWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddWordsActivity();
            }
        });

        btnCategory = findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategorizationActivity();
            }
        });

        translation = findViewById(R.id.main_btn_translate);
        translation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTranslationActivity();
            }
        });

        simpleTest = findViewById(R.id.simpleTest_btn);
        simpleTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSimpleTestActivity();
            }
        });

        btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });

        btnAdvanceTest = findViewById(R.id.test_btn);
        btnAdvanceTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvanceTesting();
            }
        });


    }

    private void openAdvanceTesting() {
        Intent advance_testing = new Intent(this, TestsActivity.class);
        startActivity(advance_testing);
    }

    public void openAddWordsActivity() {
        Intent intent_add = new Intent(this, AddWordsActivity.class);
        startActivity(intent_add);

    }

    public void openTranslationActivity() {
        Intent intent_tr = new Intent(this, TranslationActivity.class);
        startActivity(intent_tr);

    }

    public void openSimpleTestActivity() {
        Intent intent_test = new Intent(this, SimpleTestActivity.class);
        startActivity(intent_test);

    }

    public void openCategorizationActivity() {
        Intent intent = new Intent(this, CategorizationActivity.class);
        startActivity(intent);
    }

    public void openSettings() {
        Intent intent_settings = new Intent(this, SettingsActivity.class);
        startActivity(intent_settings);

    }

    public void openLegal() {
        Intent intent_legal = new Intent(this, legalActivity.class);
        startActivity(intent_legal);

    }

    public void openHelp() {
        Intent intent_help = new Intent(this, HelpActivity.class);
        startActivity(intent_help);

    }
}

