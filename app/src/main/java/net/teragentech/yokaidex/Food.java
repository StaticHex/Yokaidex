package net.teragentech.yokaidex;

/**
 * Created by indeg on 12/24/2015.
 */
public class Food {
    String _list; // Holds list of foods yokai likes to eat
    String _best; // Holds yokai's favorite food;

    public Food() {
        _list = "";
        _best = "";
    }

    public Food(String newList, String newBest) {
        _list = newList;
        _best = newBest;
    }

    //// Getters and Setters //// Getters and Setters
    public String getList ()               { return _list; }
    public String getBest ()               { return _best; }
    public void   setList (String newList) { _list = newList; }
    public void   setBest (String newBest) { _best = newBest; }
}
