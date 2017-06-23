/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    public int getMovieSize(){
        return myMovies.size();
    }
    public int getRaterSize(){
        return myRaters.size();
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> avgList = new ArrayList<Rating>();
        for(Movie m: myMovies){
            String movieID = m.getID();
            double avg = getAverageByID(movieID,minimalRaters);
            if(avg > 0.0){
                Rating rating = new Rating(movieID, avg);
                avgList.add(rating);
            }
        }
        return avgList;
    }
    public String getTitle(String id){
        for(Movie m: myMovies){
            String movieId = m.getID();
            if(movieId.equals(id)){
                return m.getTitle();
            }
        }
        return "No such a movie";
    }
    public String getID(String title){
        for(Movie m: myMovies){
            String movieTitle = m.getTitle();
            if(movieTitle.equals(title)){
                return m.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
    private double getAverageByID(String movieId,int minimalRaters){
        double avg = 0.0;
        double sum = 0.0;
        int count = 0;
        ArrayList<Double> rateList = new ArrayList<Double>();
        //HashMap<String, ArrayList<double>> movie_rating = new HashMap<String, ArrayList<Integer>>();
        for(Rater r: myRaters){
            //ArrayList<Rating> ratingList = new ArrayList<Rating>();
            ArrayList<String> itemList = r.getItemsRated();
            /*for(String item: itemList){
                double rates = r.getRating(item);
                if(movie_rating.containsKey(item)){
                    movie_rating.put(item, movie_rating.get(item).add(rates));
                }else{
                    ArrayList<double> rateList = new ArrayList<double>();
                    rateList.add(rates);
                    movie_rating.put(item,rateList);
                }
            }*/
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
}