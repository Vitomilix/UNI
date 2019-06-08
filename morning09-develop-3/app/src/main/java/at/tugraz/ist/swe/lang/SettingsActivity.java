package at.tugraz.ist.swe.lang;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    private Button btnStore;
    private Button btnLoad;
    private Button btnManage;
    private String inputString = "";
    Vocabulary vocabulary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        vocabulary = new Vocabulary(getApplicationContext());
        vocabulary.init();

        btnStore = findViewById(R.id.btnStoreBackup);
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeBackup();
            }
        });

        btnLoad = findViewById(R.id.btnLoadBackup);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBackup();
            }
        });

        btnManage = findViewById(R.id.btnManage);
        btnManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageBackup();
            }
        });

    }

    public void manageBackup() {
        String path = getApplicationContext().getFilesDir().toString();
        File directory = new File(path);
        File[] files = directory.listFiles();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tap to remove");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SettingsActivity.this, android.R.layout.select_dialog_singlechoice);

        for(File file:files)
        {
            arrayAdapter.add(file.getName());
        }



        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String choice = arrayAdapter.getItem(which);
                vocabulary.loadFileByName(choice);
                vocabulary.deleteVocab();
                vocabulary.loadFileByName("vocabulary.json");
                Toast.makeText(getBaseContext(), "deleted: "+choice, Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    public void storeBackup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Backup Name");

        final EditText inputTxt = new EditText(this);

        inputTxt.setInputType(InputType.TYPE_CLASS_TEXT);

        String backupString = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault()).format(new Date());

        inputTxt.setText(backupString);

        builder.setView(inputTxt);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputString = inputTxt.getText().toString();

                vocabulary.storeFileByName(inputString);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void openBackup() {

        String path = getApplicationContext().getFilesDir().toString();
        File directory = new File(path);
        File[] files = directory.listFiles();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Backup Name");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SettingsActivity.this, android.R.layout.select_dialog_item);

        for(File file:files)
        {
            arrayAdapter.add(file.getName());
        }



        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String choice = arrayAdapter.getItem(which);
                vocabulary.loadFileByName(choice);

                Toast.makeText(getBaseContext(), "Loaded: "+choice, Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
}
