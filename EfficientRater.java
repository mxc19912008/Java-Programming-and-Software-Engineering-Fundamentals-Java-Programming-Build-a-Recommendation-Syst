
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }
    @Override
    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));
    }
    @Override
    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }
    @Override
    public String getID() {
        return myID;
    }
    @Override
    public double getRating(String item) {
        if (hasRating(item)){
            return myRatings.get(item).getValue();
        }
        return -1;
    }
    @Override
    public int numRatings() {
        return myRatings.size();
    }
    @Override
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String item: myRatings.keySet()){
            list.add(item);
        }
        return list;
    }

}
