
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter {
	private String mygenre;
	
	public GenreFilter(String genre) {
		mygenre = genre;
	}
	
	@Override
	public boolean satisfies(String id) {
	    String genres = MovieDatabase.getGenres(id);
	    if(genres.indexOf(mygenre)!=-1){
	        return true;
	    }
		return false;
	}

}
