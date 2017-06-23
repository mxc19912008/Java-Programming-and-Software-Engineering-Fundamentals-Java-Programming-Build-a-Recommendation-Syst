
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        String file = "data/"+ filename;
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord csvRecord : parser) {
            String anID = csvRecord.get("id");
            String aTitle = csvRecord.get("title");
            String aYear = csvRecord.get("year");
            String theGenres = csvRecord.get("genre");
            String aDirector = csvRecord.get("director");
            String aCountry = csvRecord.get("country");
            String aPoster = csvRecord.get("poster");
            int theMinutes = Integer.parseInt(csvRecord.get("minutes"));
            Movie movie = new Movie(anID, aTitle, aYear, theGenres, aDirector, aCountry, aPoster, theMinutes);
            movies.add(movie);
        }
        return movies;
    }
    public void testLoadMovies(){
        ArrayList<Movie> movies = loadMovies("ratedmoviesfull.csv");
        HashMap<String, Integer> directors_movies = new HashMap<String, Integer>();
        System.out.println(movies.size());
        int Comedy = 0;
        int time150 = 0;
		int mostMovies = 0;
		String mostDirector = null;
        for(Movie m : movies){
            //System.out.println(m);
            if(m.getGenres().indexOf("Comedy") != -1){
                Comedy ++;
            }
            if(m.getMinutes()> 150){
                time150 ++;
            }
            String[] directors = m.getDirector().split(", ");
			/*for(int i=0; i < directors.length; i++){
				System.out.println("for "+m+": "+directors[i]);
			 }*/
            for(int i=0; i < directors.length; i++){
				String thisDir = directors[i];
                if(directors_movies.containsKey(thisDir)){
                    int num = directors_movies.get(thisDir);
                    directors_movies.put(thisDir, num+1);
                }else{
                    directors_movies.put(thisDir,1);
                }
            }
        }
        System.out.println("There is(are) "+Comedy+" comedy(ies).");
        System.out.println("There is(are) "+time150+" movies that is(are) longer than 150 mins.");
        for(String dir: directors_movies.keySet()){
			int currentMovie = directors_movies.get(dir);
			if(currentMovie > mostMovies){
				mostMovies = currentMovie;
				mostDirector = dir;
			}	
			System.out.println(dir +" directed "+directors_movies.get(dir)+" movies.");
        }
		System.out.println(mostDirector +" directed "+mostMovies+" most movies.");
    }
    public ArrayList<Rater> loadRaters(String filename){
       
		ArrayList<Rater> list = new ArrayList<Rater>();
		String file = "data/"+ filename;
		FileResource fr = new FileResource(file);
		CSVParser parser = fr.getCSVParser();
		int index = 0;
		for(CSVRecord rec: parser) {
			if(index == 0) {
				Rater r = new EfficientRater(rec.get("rater_id"));
				r.addRating(rec.get("movie_id"), Integer.parseInt(rec.get("rating")));
				list.add(index, r);
				index++;
			}
			else if(list.get(index-1).getID().equals(rec.get("rater_id"))) {
				list.get(index-1).addRating(rec.get("movie_id"), Integer.parseInt(rec.get("rating")));
			}
			else {
				Rater r = new EfficientRater(rec.get("rater_id"));
				r.addRating(rec.get("movie_id"), Integer.parseInt(rec.get("rating")));
				list.add(index, r);
				index++;
			}
		}
		return list;
	}
    
    public void testLoadRaters(){
        ArrayList<Rater> raters = loadRaters("ratings.csv");
        //HashMap<String, Integer> raterId_nums = new HashMap<String, Integer>();
        HashMap<String, ArrayList<Rating>> raterId_ratings = new HashMap<String, ArrayList<Rating>>();
		String specifiedRater_Id = "2";
		int maxRating = 0;
		String mostRatingRater = null;
		String movieId = "1798709";
		int movieRaters = 0;
		HashSet<String> movieSet = new HashSet<String>();
        System.out.println("Rater total number: "+raters.size());
        for(Rater r : raters){
            String rater_id = r.getID();
            ArrayList<Rating> ratingList = new ArrayList<Rating>();
            ArrayList<String> itemList = r.getItemsRated();
            for(String item: itemList){
                double rates = r.getRating(item);
                Rating rating = new Rating(item,rates);
                ratingList.add(rating);
            }
            raterId_ratings.put(rater_id, ratingList);
        }

        for(String raterId: raterId_ratings.keySet()){
            System.out.println(raterId+" posted "+raterId_ratings.get(raterId).size()+" ratings.");
            for(Rating rs : raterId_ratings.get(raterId)){
				//System.out.println(rs);
				if(rs.getItem().equals(movieId)){
					movieRaters++;
				}	
				movieSet.add(rs.getItem());
			}
			int currentRating = raterId_ratings.get(raterId).size();
			if(currentRating > maxRating){
				maxRating = currentRating;	
				mostRatingRater = raterId;
			}
			
			if(raterId.equals(specifiedRater_Id)){
				System.out.println("The specified rater : "+raterId+" posted "+raterId_ratings.get(raterId).size()+" ratings.");
			}	
        }
		System.out.println(mostRatingRater+" posted "+maxRating +" ratings, which is the most.");
		System.out.println(movieId+" was rated by "+movieRaters+" raters.");
		System.out.println(movieSet.size()+" movies were rated.");
		
    }
}
