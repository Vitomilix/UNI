package at.tugraz.ist.swe.lang;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {

    Button addHelp;
    Button translateHelp;
    Button simpleHelp;
    Button advancedHelp;
    Button categoryHelp;
    Button settingsHelp;
    Button importExportHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        addHelp = findViewById(R.id.AddHelp);
        addHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHelp();
            }
        });


        translateHelp = findViewById(R.id.TranslateHelp);
        translateHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translatehelp();
            }
        });


        simpleHelp = findViewById(R.id.SimpleHelp);
        simpleHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleTestHelp();
            }
        });

        advancedHelp = findViewById(R.id.AdvancedHelp);
        advancedHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advancedTestHelp();
            }
        });

        categoryHelp = findViewById(R.id.CategoryHelp);
        categoryHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryHelp();
            }
        });

        settingsHelp = findViewById(R.id.SettingHelp);
        settingsHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsHelp();
            }
        });

        importExportHelp = findViewById(R.id.ImportExportHelp);
        importExportHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImportExportHelp();
            }
        });

    }


    public void addHelp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HelpActivity.this);
        alertDialogBuilder
                .setMessage("To add a word, you tap on Add a word, enter the german and english Word in the respective text fields and click Add word \n" +
                        "Or you can tap on import a word and select the file a friend sent you\n" +
                        "You can also tap on an indevidual Word in the list to rate it.")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                startActivity(new Intent(getApplicationContext(),HelpActivity.class));
                                finish();

                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void translatehelp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HelpActivity.this);
        alertDialogBuilder
                .setMessage("A list is populated by the Words you enterd and you can select the words you want the words to appear in. If you tap on a word, you can see it\'s translation to the other language. ")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                startActivity(new Intent(getApplicationContext(),HelpActivity.class));
                                finish();

                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void simpleTestHelp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HelpActivity.this);
        alertDialogBuilder
                .setMessage("A Word will be selected at random and you have to choose it\'s correct translation from the list of other randomly selected words.")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                startActivity(new Intent(getApplicationContext(),HelpActivity.class));
                                finish();

                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void advancedTestHelp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HelpActivity.this);
        alertDialogBuilder
                .setMessage("Tap on create Test and select words you would like to be tested on and then you can take a test similar to the simple test but with your own selection of Words")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                startActivity(new Intent(getApplicationContext(),HelpActivity.class));
                                finish();

                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void categoryHelp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HelpActivity.this);
        alertDialogBuilder
                .setMessage("If you have a lot of words, you can sort through them based on categories.\n" +
                        "If you tap on a Word, you can share it with your friends who can then import it into their app.")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                startActivity(new Intent(getApplicationContext(),HelpActivity.class));
                                finish();

                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public void settingsHelp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HelpActivity.this);
        alertDialogBuilder
                .setMessage("You can save your entire Vocabulary as a backup and revert to one from an earlier state and the ability to manage your backups is also available")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                startActivity(new Intent(getApplicationContext(),HelpActivity.class));
                                finish();

                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void ImportExportHelp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HelpActivity.this);
        alertDialogBuilder
                .setMessage("To import: go to Add a word and tap on Import a Word and select a file to import \n" +
                        "To Export, go to Categories and tap on the desired word. Follow the Share sheet and send ")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                startActivity(new Intent(getApplicationContext(),HelpActivity.class));
                                finish();

                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
