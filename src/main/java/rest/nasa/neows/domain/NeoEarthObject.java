package rest.nasa.neows.domain;

import java.util.List;

/**
 * @author sanket
 * This class is main domain object and contain references to list of Neo.
 */
public class NeoEarthObject {

	Link links;
	Page  page;
	List<Neo> near_earth_objects;
	
	public Link getLinks() {
		return links;
	}
	public void setLinks(Link links) {
		this.links = links;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<Neo> getNear_earth_objects() {
		return near_earth_objects;
	}
	public void setNear_earth_objects(List<Neo> near_earth_objects) {
		this.near_earth_objects = near_earth_objects;
	}
	
}
