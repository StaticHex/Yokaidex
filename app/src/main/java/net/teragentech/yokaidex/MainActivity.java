package net.teragentech.yokaidex;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;
import android.widget.*;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {

    // Maps to hold yokai data and food data after being read in from XML
    private Map<String, Yokai> yokai; // Holds a searchable list of yokai
    private Map<String, Food> food; // Holds a searchable list of food

    // Buffered readers to hold data read in from files
    private BufferedReader yokaiIn;
    private BufferedReader foodIn;

    //Variable to hold trimmed formatted user input
    private String fInput;

    // Variable to hold a record which is fetched from a map
    Yokai uRecord;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attempt to open data files, if data files are missing or corrupt, throw an error
        try {
            yokaiIn = new BufferedReader(new InputStreamReader(getAssets().open("yokai.dat")));
            foodIn = new BufferedReader(new InputStreamReader(getAssets().open("food.dat")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize maps
        yokai = new HashMap<String, Yokai>();
        food = new HashMap<String, Food>();

        // UI Components
        final EditText uInput  = (EditText) findViewById(R.id.searchBar);
        final ImageView rClass = (ImageView)findViewById(R.id.imgClass );
        final ImageView rRank  = (ImageView)findViewById(R.id.imgRank  );
        final ImageView rPhoto = (ImageView)findViewById(R.id.imgYokai );
        final TextView  rElem  = (TextView) findViewById(R.id.txtElem  );
        final TextView  rEvol  = (TextView) findViewById(R.id.txtEvol  );
        final TextView  rList  = (TextView) findViewById(R.id.txtFood  );
        final TextView  rBest  = (TextView) findViewById(R.id.txtBest  );
        final Button    search = (Button)   findViewById(R.id.btnSearch);

        // temp variable to hold line read in from file for parsing
        String reader = null;

        //Variables to make populating the food map more readable
        String fName;
        String fList;
        String fBest;

        // Read in food.dat and populate foodList and foodBest maps with it, if something goes wrong
        // throw an exception
        try {
            // Read in a line from the "food.dat" file
            reader = foodIn.readLine();
            while(reader != null) {
                // Split data read in from file into a series of strings
                String[] fRecord = reader.split("\\|");

                // Split record into temp variables (make populating map more readable)
                fName = fRecord[0];
                fList = fRecord[1];
                fBest = fRecord[2];

                // Store record in the "food" map
                food.put(fName.toLowerCase(), new Food(fName,fList,fBest));

                // Read in another line from the "food.dat" file
                reader = foodIn.readLine();
            }

            // Close the food.dat file after the file has been fully read
            foodIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Variables to make populating yokai map more readable
        String yName ;
        String yClass;
        String yRank ;
        String yElem ;
        String yPhoto;
        String yEvol ;
        Food   yFood ;

        // Read in yokai.dat and populate yokai map with it/
        try {
            // Read in a line from the "yokai.dat file
            reader = yokaiIn.readLine();

            while(reader != null) {
                // Use the split function to separate data read from file into separate strings
                String[] yRecord = reader.split("\\|");

                // transfer split string data into temp variables for easier readability
                yName = yRecord[0];
                yClass = yRecord[1].toLowerCase();
                yRank = yRecord[2].toLowerCase();
                yElem = yRecord[3];
                yPhoto = yName.toLowerCase();
                yEvol = yRecord[4];
                yFood = food.get(yRecord[5].toLowerCase());

                //Format Evolution list to include newlines
                yEvol = yEvol.replaceAll(",","\n");

                //Format photo path, remove special characters and all spaces
                yPhoto = yPhoto.replaceAll(" ", "");
                yPhoto = yPhoto.replaceAll("'", "");
                yPhoto = yPhoto.replaceAll("-", "");

                // Add new record to the "yokai" map
                yokai.put(yName.toLowerCase(), new Yokai(yName, yClass, yRank, yElem, yPhoto, yEvol,
                          yFood));

                // Read another line from the "yokai.dat" file
                reader = yokaiIn.readLine();
            }

            // Once "yokai.dat" file has been succesfully read in, close the file
            yokaiIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Manual overrides and reasons for override
        yokai.get("nul").setPhoto("ynul"); // "nul" is a reserved windows filename

        //Set default properties on app launch
        rClass.setBackgroundResource(R.drawable.unknown);
        rRank.setBackgroundResource(R.drawable.x);
        rPhoto.setBackgroundResource(R.drawable.none);
        rElem.setText("---");
        rEvol.setText("---");
        rList.setText("---");
        rBest.setText("---");

        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    fInput = uInput.getText().toString();
                    fInput = fInput.toLowerCase();
                    fInput = fInput.trim();
                    uRecord = yokai.get(fInput); // get record user specified
                    //Populate GUI using record
                    uInput.setText(uRecord.getName());
                    rClass.setBackgroundResource(convertToDID(uRecord.getType()));
                    rRank.setBackgroundResource(convertToDID(uRecord.getRank()));
                    rPhoto.setBackgroundResource(convertToDID(uRecord.getPhoto()));
                    rElem.setText(uRecord.getElem());
                    rEvol.setText(uRecord.getEvol());
                    rList.setText(uRecord.getFoods().getList());
                    rBest.setText(uRecord.getFoods().getBest());
                } catch (NullPointerException e) {
                    uInput.setText("Yokai not found!");
                    rClass.setBackgroundResource(R.drawable.unknown);
                    rRank.setBackgroundResource(R.drawable.x);
                    rPhoto.setBackgroundResource(R.drawable.none);
                    rElem.setText("---");
                    rEvol.setText("---");
                    rList.setText("---");
                    rBest.setText("---");
                }
            }
        });

        uInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uInput.setText("");
            }
        });

    } // End OnCreate Function

    /***********************************************************************************
     * Convert To Drawable ID Function
     * @param name used to hold the string to convert to a drawable id
     * @return the ID number for the drawable resource corresponding to the sring fed in
     **********************************************************************************/
    public int convertToDID(String name) {
        int id = getResources().getIdentifier(name, "drawable", getPackageName());
        return id;
    }
}
