/**
 * Programming Exercise: Step Four
 * 
 * @author Kuei
 * @version August 10, 2016
 */

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
	public void printAverageRatings() {
	    RaterDatabase.initialize("ratings_short.csv");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmovies_short.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
	    int minimalRaters = 1;
	    ArrayList<Rating> ratings = fr.getAverageRatings(minimalRaters);
	    System.out.println("found " + ratings.size() + " movies");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
	    }
	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
	    RaterDatabase.initialize("ratings_short.csv");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmovies_short.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
	    int minimalRaters = 1;
	    int year = 1980;
	    String genre = "Romance";
	    AllFilters filterCriteria = new AllFilters();
	    filterCriteria.addFilter(new YearAfterFilter(year));
	    filterCriteria.addFilter(new GenreFilter(genre));
	    ArrayList<Rating> ratings = fr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
	    if (ratings.size() == 0 || ratings.size() == 1)
	    	System.out.println(ratings.size() + " movie matched");
	    else
	    	System.out.println(ratings.size() + " movies matched");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()) + "\n    " + MovieDatabase.getGenres(r.getItem()));
	    }			
	}
	
	public void printSimilarRatings() {
	    RaterDatabase.initialize("ratings.csv ");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "71";
		int numSimilarRaters = 20;
		int minimalRaters = 5;
		ArrayList<Rating> ratings = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
	    if (ratings.size() == 0 || ratings.size() == 1)
	    	System.out.println(ratings.size() + " movie matched");
	    else
	    	System.out.println(ratings.size() + " movies matched");
	    for(int i=0; i< ratings.size(); i++) {
	    	if (i<15)
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
		}
	}
	
	public void printSimilarRatingsByGenre() {
	    RaterDatabase.initialize("ratings.csv ");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "964";
		int numSimilarRaters = 20;
		int minimalRaters = 5;
		String genre = "Mystery";
	    Filter filterCriteria = new GenreFilter(genre);
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
	    if (ratings.size() == 0 || ratings.size() == 1)
	    	System.out.println(ratings.size() + " movie matched");
	    else
	    	System.out.println(ratings.size() + " movies matched");
	    for(int i=0; i< ratings.size(); i++) {
	    	if (i<15)
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
		}	
	}
	
	public void printSimilarRatingsByDirector() {
	    RaterDatabase.initialize("ratings.csv ");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "120";
		int numSimilarRaters = 10;
		int minimalRaters = 2;
		String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"; 
	    Filter filterCriteria = new DirectorsFilter(directors);
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
	    if (ratings.size() == 0 || ratings.size() == 1)
	    	System.out.println(ratings.size() + " movie matched");
	    else
	    	System.out.println(ratings.size() + " movies matched");
	    for(int i=0; i< ratings.size(); i++) {
	    	if (i<15)
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
		}		
	}
	
	public void printSimilarRatingsByGenreAndMinutes() {
	    RaterDatabase.initialize("ratings.csv ");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "168";
		int numSimilarRaters = 10;
		int minimalRaters = 3;
		String genre = "Drama";
		int min = 80;
		int max = 160;
	    AllFilters filterCriteria = new AllFilters();
	    filterCriteria.addFilter(new GenreFilter(genre));
	    filterCriteria.addFilter(new MinutesFilter(min, max));
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
	    if (ratings.size() == 0 || ratings.size() == 1)
	    	System.out.println(ratings.size() + " movie matched");
	    else
	    	System.out.println(ratings.size() + " movies matched");
	    for(int i=0; i< ratings.size(); i++) {
	    	if (i<15)
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
		}			
	}
	
	public void printSimilarRatingsByYearAfterAndMinutes() {
	    RaterDatabase.initialize("ratings.csv ");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "314";
		int numSimilarRaters = 10;
		int minimalRaters = 5;
		int year = 1975;
		int min = 70;
		int max = 200;
	    AllFilters filterCriteria = new AllFilters();
	    filterCriteria.addFilter(new YearAfterFilter(year));
	    filterCriteria.addFilter(new MinutesFilter(min, max));
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
	    if (ratings.size() == 0 || ratings.size() == 1)
	    	System.out.println(ratings.size() + " movie matched");
	    else
	    	System.out.println(ratings.size() + " movies matched");
	    for(int i=0; i< ratings.size(); i++) {
	    	if (i<15)
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), MovieDatabase.getTitle(ratings.get(i).getItem()));
		}		
	}
}