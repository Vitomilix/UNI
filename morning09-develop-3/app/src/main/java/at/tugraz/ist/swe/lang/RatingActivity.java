package at.tugraz.ist.swe.lang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Set Content View 2" );
        setContentView(R.layout.activity_rating);
        TextView text = (TextView)findViewById(R.id.textViewEn);
    }
}

// TODO: problem könnte daran liegen, dass alles in addword ausgeführt wird und am ende noch mal
// die ratingactivity aufgerufen wird. 