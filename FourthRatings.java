
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
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
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
    public int getRaterSize(){
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        return myRaters.size();
    }
    private double dotProduct(Rater me, Rater r) {
    	double dotProduct = 0;
    	ArrayList<String> myMovies = me.getItemsRated();
    	for (String id: myMovies)
		{
			if (r.hasRating(id))
			{
				double myRating = me.getRating(id);
				double rRating = r.getRating(id);
				myRating -= 5;
				rRating -= 5;
				dotProduct += myRating * rRating;
			}
		}
		return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
    	ArrayList<Rating> list = new ArrayList<Rating>();
    	Rater me = RaterDatabase.getRater(id);
    	for(Rater r: RaterDatabase.getRaters()) {
    		// add dot_product(r, me) to list if r!= me
    		if(!r.equals(me)) {
    			double product = dotProduct(me, r);
    			if (product > 0)
    				list.add(new Rating(r.getID(), product));
    		}
    	}
    	Collections.sort(list, Collections.reverseOrder());
    	return list;
    }
    
    public ArrayList<Rating> getRecommendations(String id, int numRaters) {
    	ArrayList<Rating> list = getSimilarities(id);
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	for(String movieID: movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
    		for(int k=0; k < numRaters; k++) {
    			Rating r = list.get(k);
    			String raterID = r.getItem();
    			double weight = r.getValue();
    			Rater myRater = RaterDatabase.getRater(raterID);
    			if(myRater.hasRating(movieID)) {
    				countRaters++;
    				sum += weight * myRater.getRating(movieID);
    			}
    		}
    		weightedAverage = sum / countRaters;
    		res.add(new Rating(movieID, weightedAverage));
    	}
		Collections.sort(res, Collections.reverseOrder());
		return res;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());	
	    for (String movieID : movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    		
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
			}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;		
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
	    for (String movieID : movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    		
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
			}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;	
    }
}

