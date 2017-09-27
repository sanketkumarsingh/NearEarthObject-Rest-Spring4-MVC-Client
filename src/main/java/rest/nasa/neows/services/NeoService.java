package rest.nasa.neows.services;

import rest.nasa.neows.domain.Neo;
import rest.nasa.neows.domain.NeoEarthObject;

/**
 * @author sanket
 * This interface contains methods to get the largest and closest near earth objects.
 */
public interface NeoService {

	/* 
	 * This method returns the largest near earth object based on the maximum expected diameter. 
	 * The nearearthobject which have largest expected diameter is considered as the largest Neo to earth.
	 * neo - NeoEarthObject which contains list of objects based on recent web service call.
	 * nearestNeo - Neo - the largest near earth object until previous iteration.
	 * Return Neo - the largest near earth object until current iteration.
	 */
	public Neo getLargestNeo(NeoEarthObject neo, Neo largestNeo);

	/* 
	 * This method returns the closest near earth object based on miss distance. 
	 * The nearearthobject which have smallest miss distance is considered as the one closest to earth.
	 * neo - NeoEarthObject which contains list of objects based on recent web service call.
	 * nearestNeo - Neo - the closest near earth object until preivous iteration.
	 * Return Neo - the nearest near earth object until current iteration.
	 */
	public Neo getNearestNeo(NeoEarthObject neo, Neo nearestNeo);

}
