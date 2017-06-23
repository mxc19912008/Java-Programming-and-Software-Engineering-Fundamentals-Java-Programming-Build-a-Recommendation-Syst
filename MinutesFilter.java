
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
	private int minMinutes;
	private int maxMinutes;
	public MinutesFilter(int minMinute, int maxMinute) {
		minMinutes = minMinute;
		maxMinutes = maxMinute;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getMinutes(id) >= minMinutes && MovieDatabase.getMinutes(id) <= maxMinutes;
	}

}

