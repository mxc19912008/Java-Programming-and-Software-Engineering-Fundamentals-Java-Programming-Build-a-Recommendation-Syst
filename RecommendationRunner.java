
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate (){
        ArrayList<String> res = new ArrayList<String>();
        int numToDisplay = 10;
        int minimalRaters = 5;
        ArrayList<String> res1 = MovieDatabase.filterBy(new TrueFilter());
        Random rand = new Random();
        for(int i = 0; i < numToDisplay; i++){
            int r = rand.nextInt(res1.size());
            String title = res1.get(r);
            if (!res.contains(title)) {
                res.add(title);
            }
        }
        return res;
    }
    public void printRecommendationsFor (String webRaterID){
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID,1,1);
        int length = 10;
        if(ratings.size()<10){
            length = ratings.size();
        }
        if(ratings.size() == 0){
            int index = 0;
            ArrayList<String> res1 = MovieDatabase.filterBy(new TrueFilter());
            Random rand = new Random();
            HashSet <String> titles = new HashSet <String>();
            for(int i =0; i < 10; i++){
                int r = rand.nextInt(res1.size());
                String title = res1.get(r);
                if (ratings.size()!=0 && !titles.contains(title)) {
                    ratings.add(new Rating(title, 5.00));
                    titles.add(title);
                    index++;
                }
                if(index > 10){
                    break;
                }
            }
        }
        System.out.println("<style>");
        System.out.println("h2.error {");
        System.out.println("  background-color: #FFD700;");
        System.out.println("    color: #DC143C;");
        System.out.println("  margin: 5;");
        System.out.println("}");
        System.out.println("</style>");
        System.out.println("<div class=\"content\">");
        System.out.println("  <div class=\"ui-widget\">");
        System.out.println("    <html>");
        System.out.println("<style>");
        System.out.println("  #customers,");
        System.out.println("  h2 {");
        System.out.println("    font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;");
        System.out.println("    border-collapse: collapse;");
        System.out.println("    width: 100%;");
        System.out.println("    text-align: center;");
        System.out.println("  }");
        System.out.println("  #customers td,");
        System.out.println("  #customers th,");
        System.out.println("  h2 {");
        System.out.println("    border: 1px solid #ddd;");
        System.out.println("    padding: 8px;");
        System.out.println("  }");
        System.out.println("  #customers tr:nth-child(even) {");
        System.out.println("    background-color: #f2f2f2;");
        System.out.println("  }");
        System.out.println("  #customers tr:hover {");
        System.out.println("    background-color: #ddd;");
        System.out.println("  }");
        System.out.println("  #customers th {");
        System.out.println("    padding-top: 12px;");
        System.out.println("    padding-bottom: 12px;");
        System.out.println("    text-align: center;");
        System.out.println("    background-color: #4CAF50;");
        System.out.println("    color: white;");
        System.out.println("  }");
        System.out.println("  #customers img {");
        System.out.println("    height: 50%;");
        System.out.println("  }");
        System.out.println("  h2 {");
        System.out.println("    background-color: #4CAF50;");
        System.out.println("  }");
        System.out.println("</style><h2>Xiaochun Ma brings you the following movies that you may like.</h2>");
        System.out.println("<table id=\"customers\">");
        System.out.println("  <tr>");
        System.out.println("    <th>#</th>");
        System.out.println("    <th>Poster</th>");
        System.out.println("    <th>Title</th>");
        System.out.println("    <th>Genre</th>");
        System.out.println("    <th>Year</th>");
        System.out.println("    <th>Time</th>");
        System.out.println("  </tr>  <tr>");
        for(int i=0; i< length; i++) {
            int num = i+1;
            System.out.println("    <td>"+num+"</td>");
            System.out.println("    <td><img src=");
            System.out.println(     "\""+MovieDatabase.getPoster(ratings.get(i).getItem())+"\"");
            System.out.println(" /> </td>");
            System.out.println("    <td>"+MovieDatabase.getTitle(ratings.get(i).getItem())+"</td>");
            System.out.println("    <td>"+MovieDatabase.getCountry(ratings.get(i).getItem())+"</td>");
            System.out.println("    <td>"+MovieDatabase.getYear(ratings.get(i).getItem())+"</td>");
            System.out.println("    <td>"+MovieDatabase.getMinutes(ratings.get(i).getItem())+" Minutes"+"</td>");
            System.out.println("  </tr>  <tr>");
        }       
    }
}
