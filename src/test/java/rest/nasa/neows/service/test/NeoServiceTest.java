package rest.nasa.neows.service.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rest.nasa.neows.domain.CloseApproachData;
import rest.nasa.neows.domain.Diameter;
import rest.nasa.neows.domain.Kilometers;
import rest.nasa.neows.domain.Link;
import rest.nasa.neows.domain.MissDistance;
import rest.nasa.neows.domain.Neo;
import rest.nasa.neows.domain.NeoEarthObject;
import rest.nasa.neows.services.NeoService;
import rest.nasa.neows.services.impl.NeoServiceImpl;

/**
 * @author sanket
 *  This is a test class for NeaServiceImpl class. 
 */
public class NeoServiceTest {
	
	 NeoEarthObject nearEarthObject = null;
	
	@Before
	public void setup(){
		nearEarthObject = new NeoEarthObject();
		
		List<Neo> neoList = new ArrayList();
		
		Neo neo_1 = new Neo();
		neo_1.setNeo_reference_id(1);
		neo_1.setName("neo_1_name");
		Diameter diameter_1 = new Diameter();
		Kilometers kms_1 = new Kilometers();
		kms_1.setEstimated_diameter_max(1000000);
		diameter_1.setKilometers(kms_1);
		neo_1.setEstimated_diameter(diameter_1);
		List<CloseApproachData> closeAppDataList_1 = new ArrayList();
		CloseApproachData data_1 = new CloseApproachData();
		MissDistance miss_distance_1 = new MissDistance();
		miss_distance_1.setKilometers("1000000");
		data_1.setMiss_distance(miss_distance_1);
		closeAppDataList_1.add(data_1);
		neo_1.setClose_approach_data(closeAppDataList_1);
		
		Neo neo_2 = new Neo();
		neo_2.setNeo_reference_id(2);
		neo_2.setName("neo_2_name");
		Diameter diameter_2 = new Diameter();
		Kilometers kms_2 = new Kilometers();
		kms_2.setEstimated_diameter_max(10000);
		diameter_2.setKilometers(kms_2);
		neo_2.setEstimated_diameter(diameter_2);
		CloseApproachData data_2 = new CloseApproachData();
		MissDistance miss_distance_2 = new MissDistance();
		miss_distance_2.setKilometers("10000");
		data_2.setMiss_distance(miss_distance_2);
		List<CloseApproachData> closeAppDataList_2 = new ArrayList();
		closeAppDataList_2.add(data_2);
		neo_2.setClose_approach_data(closeAppDataList_2);
		
		neoList.add(neo_2);
		neoList.add(neo_1);
		
		nearEarthObject.setNear_earth_objects(neoList);
		nearEarthObject.setLinks(new Link());
	}

	@Test
	public void testgetLargestNeo_WithNoNeo(){
		NeoService test = new NeoServiceImpl();
		Neo neo = test.getLargestNeo(null, null);
		assertTrue(neo == null);
	}
	
	@Test
	public void testgetLargestNeo_FromData(){
		
		Neo neo_1 = new Neo();
		neo_1.setNeo_reference_id(3);
		neo_1.setName("neo_3_name");
		Diameter diameter_1 = new Diameter();
		Kilometers kms_1 = new Kilometers();
		kms_1.setEstimated_diameter_max(10000000);
		diameter_1.setKilometers(kms_1);
		neo_1.setEstimated_diameter(diameter_1);
		List<CloseApproachData> closeAppDataList_1 = new ArrayList();
		CloseApproachData data_1 = new CloseApproachData();
		MissDistance miss_distance_1 = new MissDistance();
		miss_distance_1.setKilometers("10000000");
		data_1.setMiss_distance(miss_distance_1);
		closeAppDataList_1.add(data_1);
		neo_1.setClose_approach_data(closeAppDataList_1);
		
		
		NeoService test = new NeoServiceImpl();
		Neo neo = test.getLargestNeo(null, neo_1);
		assertTrue(neo == neo_1);
		
		neo = test.getLargestNeo(nearEarthObject, neo_1);
		assertTrue(neo.getName().equals("neo_3_name"));
		
		kms_1.setEstimated_diameter_max(1000);
		
		neo = test.getLargestNeo(nearEarthObject, neo_1);
		assertTrue(neo.getName().equals("neo_1_name"));
	}
	
	@Test
	public void testgetNearestNeo_WithNoNeo(){
		NeoService test = new NeoServiceImpl();
		Neo neo = test.getLargestNeo(null, null);
		assertTrue(neo == null);
	}
	
	@Test
	public void testgetNearestNeo_FromData(){
		
		Neo neo_1 = new Neo();
		neo_1.setNeo_reference_id(3);
		neo_1.setName("neo_3_name");
		Diameter diameter_1 = new Diameter();
		Kilometers kms_1 = new Kilometers();
		kms_1.setEstimated_diameter_max(10000000);
		diameter_1.setKilometers(kms_1);
		neo_1.setEstimated_diameter(diameter_1);
		List<CloseApproachData> closeAppDataList_1 = new ArrayList();
		CloseApproachData data_1 = new CloseApproachData();
		MissDistance miss_distance_1 = new MissDistance();
		miss_distance_1.setKilometers("10000000");
		data_1.setMiss_distance(miss_distance_1);
		closeAppDataList_1.add(data_1);
		neo_1.setClose_approach_data(closeAppDataList_1);
		
		
		NeoService test = new NeoServiceImpl();
		Neo neo = test.getNearestNeo(null, neo_1);
		assertTrue(neo == neo_1);
		
		neo = test.getNearestNeo(nearEarthObject, neo_1);
		assertTrue(neo.getName().equals("neo_2_name"));
		
		miss_distance_1.setKilometers("100");
		
		neo = test.getNearestNeo(nearEarthObject, neo_1);
		assertTrue(neo.getName().equals("neo_3_name"));
	}
	
}
