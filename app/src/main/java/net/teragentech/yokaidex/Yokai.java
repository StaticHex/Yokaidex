package net.teragentech.yokaidex;

/**
 * Created by indeg on 12/24/2015.
 */
public class Yokai
{
    private String _type; // Holds the path to the corresponding class image
    private String _rank;  // Holds the path to the corresponding rank image
    private String _photo; // Holds the path to the image for the yokai
    private String _desc;  // Holds the description for the yokai
    private Food   _foods; // Holds the food object for the yokai

    public Yokai() {
        _type  = "";
        _rank  = "";
        _photo = "";
        _desc = "";
        _foods = new Food();
    }

    public Yokai(String newClass, String newRank, String newPhoto, String newDesc, Food newFoods) {
        _type = newClass;
        _rank  = newRank;
        _photo = newPhoto;
        _desc = newDesc;
        _foods = newFoods;
    }

    //// Getters and Setters //// Getters and Setters
    public String getType  ()                 { return _type;      }
    public String getRank  ()                 { return _rank;      }
    public String getPhoto ()                 { return _photo;     }
    public String getDesc  ()                 { return _desc;      }
    public Food   getFoods ()                 { return _foods;     }
    public void   setType  ( String newType ) { _type = newType;   }
    public void   setRank  ( String newRank ) { _rank = newRank;   }
    public void   setPhoto ( String newPhoto) { _photo = newPhoto; }
    public void   setDesc  ( String newDesc ) { _desc = newDesc;   }
    public void   setFoods ( Food newFood   ) { _foods = newFood;  }
}
