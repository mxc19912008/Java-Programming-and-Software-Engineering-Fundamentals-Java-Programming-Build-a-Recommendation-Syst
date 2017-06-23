
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        System.out.println("Raters number: "+sr.getRaterSize());
        int minimalRaters = 35;
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Movies number: "+MovieDatabase.size());
        ArrayList<Rating> avgList = sr.getAverageRatings(minimalRaters);
        System.out.println("found : "+avgList.size()+" movies");
        Collections.sort(avgList);
        for(Rating r: avgList){
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printAverageRatingsByYear(){
        int year = 2000;
        YearAfterFilter yearFilter = new YearAfterFilter(year);
        
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        System.out.println("Raters number: "+sr.getRaterSize());
        int minimalRaters = 20;
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Movies number: "+MovieDatabase.size());
        
        ArrayList<Rating> avgList = sr.getAverageRatingsByFilter(minimalRaters, yearFilter);
        System.out.println("found : "+avgList.size()+" movies");
        Collections.sort(avgList);
        for(Rating r: avgList){
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())
            +" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printAverageRatingsByGenre(){
        String genre = "Comedy";
        GenreFilter genreFilter = new GenreFilter(genre);
        
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        System.out.println("Raters number: "+sr.getRaterSize());
        int minimalRaters = 20;
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Movies number: "+MovieDatabase.size());
        
        ArrayList<Rating> avgList = sr.getAverageRatingsByFilter(minimalRaters, genreFilter);
        System.out.println("found : "+avgList.size()+" movies");
        Collections.sort(avgList);
        for(Rating r: avgList){
            System.out.println(r.getValue()+
            " "+MovieDatabase.getTitle(r.getItem()));
           System.out.println("    "+MovieDatabase.getGenres(r.getItem()));
        }
    }
    public void printAverageRatingsByMinutes(){
        int mimMinutes = 105;
        int maxMinutes = 135;
        MinutesFilter minutesFilter = new MinutesFilter(mimMinutes, maxMinutes);
        
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        System.out.println("Raters number: "+sr.getRaterSize());
        int minimalRaters = 5;
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Movies number: "+MovieDatabase.size());
        
        ArrayList<Rating> avgList = sr.getAverageRatingsByFilter(minimalRaters, minutesFilter);
        System.out.println("found : "+avgList.size()+" movies");
        Collections.sort(avgList);
        for(Rating r: avgList){
            System.out.println(r.getValue()+ " Time: "+MovieDatabase.getMinutes(r.getItem())+
            " "+MovieDatabase.getTitle(r.getItem()));
           
        }
    }
    public void printAverageRatingsByDirectors(){
        String director =  "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        DirectorsFilter directorsFilter = new DirectorsFilter(director);
        
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        System.out.println("Raters number: "+sr.getRaterSize());
        int minimalRaters = 4;
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Movies number: "+MovieDatabase.size());
        
        ArrayList<Rating> avgList = sr.getAverageRatingsByFilter(minimalRaters, directorsFilter);
        System.out.println("found : "+avgList.size()+" movies");
        Collections.sort(avgList);
        for(Rating r: avgList){
            System.out.println(r.getValue()+
            " "+MovieDatabase.getTitle(r.getItem()));
           System.out.println("    "+MovieDatabase.getDirector(r.getItem()));
        }
    }
    public void printAverageRatingsByYearAfterAndGenre(){
        String genre = "Drama";
        GenreFilter genreFilter = new GenreFilter(genre);
        int year = 1990;
        YearAfterFilter yearFilter = new YearAfterFilter(year);
        AllFilters af = new AllFilters();
        af.addFilter(yearFilter);
        af.addFilter(genreFilter);
        
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        System.out.println("Raters number: "+sr.getRaterSize());
        int minimalRaters = 8;
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Movies number: "+MovieDatabase.size());
        
        ArrayList<Rating> avgList = sr.getAverageRatingsByFilter(minimalRaters, af);
        System.out.println("found : "+avgList.size()+" movies");
        Collections.sort(avgList);
        for(Rating r: avgList){
            System.out.println(r.getValue()+
            " "+MovieDatabase.getYear(r.getItem())+
            " "+MovieDatabase.getTitle(r.getItem()));
           System.out.println("    "+MovieDatabase.getGenres(r.getItem()));
        }
    }
    public void printAverageRatingsByDirectorsAndMinutes(){
        int mimMinutes = 90;
        int maxMinutes = 180;
        MinutesFilter minutesFilter = new MinutesFilter(mimMinutes, maxMinutes);
        String director =  "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        DirectorsFilter directorsFilter = new DirectorsFilter(director);
        AllFilters af = new AllFilters();
        af.addFilter(minutesFilter);
        af.addFilter(directorsFilter);
        
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        System.out.println("Raters number: "+sr.getRaterSize());
        int minimalRaters = 3;
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Movies number: "+MovieDatabase.size());
        
        ArrayList<Rating> avgList = sr.getAverageRatingsByFilter(minimalRaters, af);
        System.out.println("found : "+avgList.size()+" movies");
        Collections.sort(avgList);
        for(Rating r: avgList){
            System.out.println(r.getValue()+
            " Time: "+MovieDatabase.getMinutes(r.getItem())+
            " "+MovieDatabase.getTitle(r.getItem()));
           System.out.println("    "+MovieDatabase.getDirector(r.getItem()));
        }
    }
}
