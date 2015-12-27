package net.teragentech.yokaidex;

/**
 * Created by indeg on 12/24/2015.
 */
public class Yokai
{
    private final String PREFIX = "";
    private String _name ;  // Holds the yokai's name (for future conversion to SQL database)
    private String _type ;  // Holds the path to the corresponding class image
    private String _rank ;  // Holds the path to the corresponding rank image
    private String _elem ;  // Holds the yokai's element
    private String _photo;  // Holds the path to the image for the yokai
    private String _evol ;  // Holds the evolution data for the yokai
    private Food   _foods;  // Holds the food object for the yokai

    public Yokai() {
        _name  = "Yokai not found";
        _type  = PREFIX+"unkown";
        _rank  = PREFIX+"x";
        _elem  = "---";
        _photo = PREFIX+"none";
        _evol  = "---";
        _foods = new Food();
    }

    public Yokai(String newName, String newType, String newRank, String newElem, String newPhoto,
                 String newEvol, Food newFood) {
        _name  = newName;
        _type  = PREFIX+newType;
        _rank  = PREFIX+newRank;
        _elem  = newElem;
        _photo = PREFIX+newPhoto;
        _evol  = newEvol;
        _foods = newFood;
    }

    //// Getters and Setters //// Getters and Setters
    public String getName  ()                 { return _name;             }
    public String getType  ()                { return _type;             }
    public String getRank  ()                 { return _rank;             }
    public String getElem  ()                 { return _elem;             }
    public String getPhoto ()                 { return _photo;            }
    public String getEvol  ()                 { return _evol;             }
    public Food   getFoods ()                 { return _foods;            }
    public void   setName  ( String newName ) { _name = newName;          }
    public void   setType ( String newType ) { _type = PREFIX+newType;   }
    public void   setRank  ( String newRank ) { _rank = PREFIX+newRank;   }
    public void   setElem  ( String newElem ) { _elem = newElem;          }
    public void   setPhoto ( String newPhoto) { _photo = PREFIX+newPhoto; }
    public void   setEvol  ( String newEvol ) { _evol  = newEvol;         }
    public void   setFoods ( Food newFood   ) { _foods = newFood;         }
}
