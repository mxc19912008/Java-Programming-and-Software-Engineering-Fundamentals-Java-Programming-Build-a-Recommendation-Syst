
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String[] mydirectors;
    
    public DirectorsFilter(String directors) {
        mydirectors = directors.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        String directors = MovieDatabase.getDirector(id);
        for(int i= 0; i<mydirectors.length; i++){
            if(directors.indexOf(mydirectors[i])!= -1){
                return true;
            }
        }
        return false;
    }

}

