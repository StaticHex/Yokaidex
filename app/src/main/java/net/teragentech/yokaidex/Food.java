package net.teragentech.yokaidex;

/**
 * Created by indeg on 12/24/2015.
 */
public class Food {
    String _type; // Holds the type of food (Chinese, Rice Ball, etc.)
    String _list; // Holds list of foods yokai likes to eat
    String _best; // Holds yokai's favorite food;

    public Food() {
        _type = "unknown";
        _list = "---";
        _best = "---";
    }

    public Food(String newType, String newList, String newBest) {
        _type = newType;
        _list = newList;
        _best = newBest;
    }

    //// Getters and Setters //// Getters and Setters
    public String getType ()               { return _type;    }
    public String getList ()               { return _list;    }
    public String getBest ()               { return _best;    }
    public void   setType (String newType) { _type = newType; }
    public void   setList (String newList) { _list = newList; }
    public void   setBest (String newBest) { _best = newBest; }
}
