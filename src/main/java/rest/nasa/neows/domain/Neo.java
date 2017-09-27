package rest.nasa.neows.domain;

import java.util.List;

/**
 * @author sanket
 * This class represent an individual Near Earth Object with its attributes.
 */
public class Neo {
	
	long neo_reference_id;
	String name;
	Diameter estimated_diameter;
	List<CloseApproachData> close_approach_data;
	
	public long getNeo_reference_id() {
		return neo_reference_id;
	}
	public void setNeo_reference_id(long neo_reference_id) {
		this.neo_reference_id = neo_reference_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Diameter getEstimated_diameter() {
		return estimated_diameter;
	}
	
	public List<CloseApproachData> getClose_approach_data() {
		return close_approach_data;
	}
	public void setClose_approach_data(List<CloseApproachData> close_approach_data) {
		this.close_approach_data = close_approach_data;
	}
	public void setEstimated_diameter(Diameter estimated_diameter) {
		this.estimated_diameter = estimated_diameter;
	}
	
	
}
