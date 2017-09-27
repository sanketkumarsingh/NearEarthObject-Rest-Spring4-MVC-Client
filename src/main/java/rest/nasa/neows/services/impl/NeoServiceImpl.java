package rest.nasa.neows.services.impl;

import java.util.List;


import org.springframework.stereotype.Service;

import rest.nasa.neows.domain.CloseApproachData;
import rest.nasa.neows.domain.Neo;
import rest.nasa.neows.domain.NeoEarthObject;
import rest.nasa.neows.services.NeoService;


/*
 * This is a service implementation. It contains method to find the nearest and the largest
 * near earth objects.
 * 
 */
@Service("neoService")
public class NeoServiceImpl implements NeoService {

	
	
	@Override
	public Neo getLargestNeo(NeoEarthObject neo, Neo largestNeo) {
		if(neo==null){
			return largestNeo;
		}
		List<Neo> neoList = neo.getNear_earth_objects();
		double largestDiameter = Double.MIN_VALUE;
		Neo currLargestNeo = null;
		if (neoList != null && !neoList.isEmpty()) {
			for (Neo nearEarthObject : neoList) {
				if (nearEarthObject.getEstimated_diameter() != null
						&& nearEarthObject.getEstimated_diameter().getKilometers() != null) {
					if (largestDiameter < nearEarthObject.getEstimated_diameter().getKilometers()
							.getEstimated_diameter_max()) {
						largestDiameter = nearEarthObject.getEstimated_diameter().getKilometers()
								.getEstimated_diameter_max();
						currLargestNeo = nearEarthObject;
					}
				}
			}
		}
		if (currLargestNeo == null) {
			return largestNeo;
		} else if (largestNeo == null) {
			return currLargestNeo;
		} else {
			if (largestNeo.getEstimated_diameter().getKilometers().getEstimated_diameter_max() > currLargestNeo
					.getEstimated_diameter().getKilometers().getEstimated_diameter_max()) {
				return largestNeo;
			} else {
				return currLargestNeo;
			}
		}
	}

	@Override
	public Neo getNearestNeo(NeoEarthObject neo, Neo nearestNeo) {
		if(neo==null){
			return nearestNeo;
		}
		List<Neo> neoList = neo.getNear_earth_objects();
		Double closestDist = Double.MAX_VALUE;
		Neo currNearestNeo = null;
		if (neoList != null && !neoList.isEmpty()) {
			for (Neo nearEarthObject : neoList) {
				List<CloseApproachData> closeNeoList = nearEarthObject.getClose_approach_data();
				if (closeNeoList != null && !closeNeoList.isEmpty()) {
					for (CloseApproachData closeNeo : closeNeoList) {
						if (closeNeo.getMiss_distance() != null && closeNeo.getMiss_distance().getKilometers() != null
								&& !closeNeo.getMiss_distance().getKilometers().isEmpty()) {
							double currDist = Double.valueOf(closeNeo.getMiss_distance().getKilometers());
							if (currDist < closestDist) {
								closestDist = currDist;
								currNearestNeo = nearEarthObject;
							}
						}
					}
				}
			}
		}
		if (currNearestNeo == null) {
			return nearestNeo;
		} else if (nearestNeo == null) {
			return currNearestNeo;
		} else {
			List<CloseApproachData> closeNeoList = nearestNeo.getClose_approach_data();
			if (closeNeoList != null && !closeNeoList.isEmpty()) {
				for (CloseApproachData closeNeo : closeNeoList) {
					double currDist = Double.valueOf(closeNeo.getMiss_distance().getKilometers());
					if (currDist < closestDist) {
						return nearestNeo;
					}
				}
			}
			return currNearestNeo;
		}
	}

}
