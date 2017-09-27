package rest.nasa.neows.domain;

public class CloseApproachData {

	String close_approach_date;
	
	MissDistance  miss_distance;

	public String getClose_approach_date() {
		return close_approach_date;
	}

	public void setClose_approach_date(String close_approach_date) {
		this.close_approach_date = close_approach_date;
	}

	public MissDistance getMiss_distance() {
		return miss_distance;
	}

	public void setMiss_distance(MissDistance miss_distance) {
		this.miss_distance = miss_distance;
	}
	
}
