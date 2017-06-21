
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
        System.out.println("Movies number: "+sr.getMovieSize());
        System.out.println("Raters number: "+sr.getRaterSize());
        int minimalRaters = 12;
        ArrayList<Rating> avgList = sr.getAverageRatings(minimalRaters);
        Collections.sort(avgList);
        for(Rating r: avgList){
            System.out.println(r.getValue()+" "+sr.getTitle(r.getItem()));
        }
    }
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
        String title = "Vacation"; 
        String movieId = sr.getID(title);
        int minimalRaters = 0;
        ArrayList<Rating> avgList = sr.getAverageRatings(minimalRaters);
        for(Rating r: avgList){
            if(r.getItem().equals(movieId)){
                System.out.println(title+" "+r.getValue());
            }
        }
    }
    
}
