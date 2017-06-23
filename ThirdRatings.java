
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    public int getRaterSize(){
        return myRaters.size();
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> avgList = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String m: movies){
            String movieID = m;
            double avg = getAverageByID(movieID,minimalRaters);
            if(avg > 0.0){
                Rating rating = new Rating(movieID, avg);
                avgList.add(rating);
            }
        }
        return avgList;
    }
    private double getAverageByID(String movieId,int minimalRaters){
        double avg = 0.0;
        double sum = 0.0;
        int count = 0;
        ArrayList<Double> rateList = new ArrayList<Double>();
        for(Rater r: myRaters){
            ArrayList<String> itemList = r.getItemsRated();
            for(String item: itemList){
                if(item.equals(movieId)){
                    rateList.add(r.getRating(item));
                }
            }
        }
        for(double rate: rateList){
            sum += rate;
            count++;
        }
        if(count >= minimalRaters){
            avg = sum / count;
        }
        return avg;
    }
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, 
            Filter filterCriteria){
           ArrayList<Rating> result = new ArrayList<Rating>();
           ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriteria);
           for(String movieId: filteredMovies){
               if(getAverageByID(movieId,minimalRaters) > 0.0){
                  Rating rating = new Rating(movieId,getAverageByID(movieId,minimalRaters));
                  result.add(rating);
               }
           }
           return result;
    }
}

