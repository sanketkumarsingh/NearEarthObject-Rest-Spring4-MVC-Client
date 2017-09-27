package rest.nasa.neows.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import rest.nasa.neows.domain.Neo;
import rest.nasa.neows.domain.NeoEarthObject;
import rest.nasa.neows.services.NeoService;

/**
 * @author sanket
 * This is the controller class. Dispatcher servlet will forward the request to this controller which then returns 
 * web service response.
 */
@RestController
public class NeoController {

	private static final StringBuilder URL = new StringBuilder("https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=");
	
	@Autowired
	NeoService neoService;
	
	/*
	 * This method returns the id and the name of the largest near earth object.
	 * Input: api_key - key can be obtained from https://api.nasa.gov/api.html#NeoWS
	 * topkPage - Webservice call returns 20 near earth object per page.  topkPage represent the number of pages or times
	 * the webservice call to make and the largest near earth object within those call will be returned. This is to
	 * control the bombardment of web service calls on the server hosting the service.
	 * Returns id and  name of near earth object. example: Largest Neo id:2021277 and Neo name:21277 (1996 TO5)
	 */
	@RequestMapping("/largest/neo/{api_key}/{topKPage}")
	public String getLargestNeo(@PathVariable String api_key, @PathVariable int topKPage) {
		RestTemplate restTemplate = getRestTemplate();
		String url =  URL.append(api_key).toString();
		Neo largestNeo = null;
		while (url != null && topKPage!= 0) {
			NeoEarthObject neo = restTemplate.getForObject(url, NeoEarthObject.class);
			largestNeo = neoService.getLargestNeo(neo, largestNeo);
			if (neo.getLinks().getNext() != null) {
				url = neo.getLinks().getNext();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				url = null;
			}
			topKPage--;
		}
		if (largestNeo != null) {
			return prepareResult(largestNeo, "Largest");
		}
		return "No Neo exists.";
	}

	
	/*
	 * This method returns the id and the name of the largest near earth object.
	 * Input: api_key - key can be obtained from https://api.nasa.gov/api.html#NeoWS
	 * topkPage - Webservice call returns 20 near earth object per page.  topkPage represent the number of pages or times
	 * the webservice call to make and the nearest near earth object within those call will be returned. This is to
	 * control the bombardment of web service calls on the server hosting the service.
	 * Returns id and  name of near earth object. example:Nearest Neo id:2431394 and Neo name:431394 (2007 FS35)
	 */
	
	@RequestMapping("/nearest/neo/{api_key}/{topKPage}")
	public String getNearestNeo(@PathVariable String api_key, @PathVariable int topKPage) {
		RestTemplate restTemplate = getRestTemplate();
		String url =  URL.append(api_key).toString();
		Neo closestNeo = null;
		while (url != null && topKPage!= 0) {
			NeoEarthObject neo = restTemplate.getForObject(url, NeoEarthObject.class);
			closestNeo = neoService.getNearestNeo(neo, closestNeo);
			if (neo.getLinks().getNext() != null) {
				url = neo.getLinks().getNext();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				url = null;
			}
			topKPage--;
		}

		if (closestNeo != null) {
			return prepareResult(closestNeo, "Nearest");
		}
		return "No Neo exists.";
	}

	@RequestMapping("/getDetailById/{api_key}/{id}")
	public Neo getDetail(@PathVariable String api_key, @PathVariable String id){
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.nasa.gov/neo/rest/v1/neo/" + id + "?api_key=" + api_key;
		Neo neo = restTemplate.getForObject(url, Neo.class);
		
		return neo;
	}

	private String prepareResult(Neo neo, String literal) {
		StringBuilder result = new StringBuilder(literal + " Neo id:")
				.append(String.valueOf(neo.getNeo_reference_id()));
		result.append(" and ");
		result.append("Neo name:").append(neo.getName());
		return result.toString();
	}
	

	private RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


}
