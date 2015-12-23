package net.teragentech.yokaidex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;
import android.widget.*;
import android.view.inputmethod.InputMethodManager;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.content.*;

public class MainActivity extends AppCompatActivity {

    // Maps to hold yokai data and food data after being read in from XML
    private Map<String, String> yokai;
    private Map<String, String> foodList;
    private Map<String, String> foodBest;

    BufferedReader yokaiIn;
    BufferedReader foodIn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        try {
            yokaiIn = new BufferedReader(new InputStreamReader(getAssets().open("yokai.dat")));
            foodIn = new BufferedReader(new InputStreamReader(getAssets().open("food.dat")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        yokai = new HashMap<String, String>();
        foodList = new HashMap<String, String>();
        foodBest = new HashMap<String, String>();

        // UI Components
        final EditText uInput = (EditText)findViewById(R.id.searchBar);
        final TextView resultName = (TextView)findViewById(R.id.txtName);
        final TextView resultList = (TextView)findViewById(R.id.txtList);
        final TextView resultBest = (TextView)findViewById(R.id.txtBest);
        final Button search = (Button)findViewById(R.id.btnSearch);

        String reader = null;
        try {
            reader = yokaiIn.readLine();
            while(reader != null) {
                String[] yRecord = reader.split("\\|");
                yokai.put(yRecord[0].toLowerCase(), yRecord[1]);
                reader = yokaiIn.readLine();
            }
            yokaiIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader = foodIn.readLine();
            while(reader != null) {
                String[] fRecord = reader.split("\\|");
                foodList.put(fRecord[0].toLowerCase(), fRecord[1]);
                foodBest.put(fRecord[0].toLowerCase(), fRecord[2]);
                reader = foodIn.readLine();
            }
            foodIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultName.setText("Abodabat");
        resultList.setText(foodList.get(yokai.get(resultName.getText().toString().toLowerCase()).toLowerCase()));
        resultBest.setText(foodBest.get(yokai.get(resultName.getText().toString().toLowerCase()).toLowerCase()));

        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    resultName.setText(uInput.getText().toString());
                    resultList.setText(foodList.get(yokai.get(resultName.getText().toString().toLowerCase()).toLowerCase()));
                    resultBest.setText(foodBest.get(yokai.get(resultName.getText().toString().toLowerCase()).toLowerCase()));
                } catch (NullPointerException e) {
                    resultName.setText("Yo-kai not found");
                    resultList.setText("---");
                    resultBest.setText("---");
                }
            }
        });

        uInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uInput.setText("");
            }
        });
    }
}
