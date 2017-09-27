package rest.nasa.neows.controller.test;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import rest.nasa.neows.configuration.test.TestConfig;

/**
 * @author sanket
 * This is a test class for NeoController class
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestConfig.class })
public class NeoControllerTest {
	
	 @Autowired
	 private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	/**
	 * @throws Exception
	 * This method tests the largest neo retrieval.
	 * Change the api_key as per your account. Default value given is DEMO_KEY
	 */
	@Test
	public void testGetLargestNeo() throws Exception {
		MvcResult result = mockMvc.perform(get("/largest/neo/{api_key}/{topKPage}", "DEMO_KEY", 1))
				.andExpect(status().isOk()).andReturn();
		String stringResult = result.getResponse().getContentAsString();
		assertTrue(stringResult.equals("Largest Neo id:2021277 and Neo name:21277 (1996 TO5)"));
	}
	
	/**
	 * @throws Exception
	 * This method tests the nearest neo retrieval.
	 * Change the api_key as per your account. Default value given is DEMO_KEY
	 */
//	@Test
//	public void testGetNearestNeo() throws Exception {
//		
//		MvcResult result = mockMvc.perform(get("/nearest/neo/{api_key}/{topKPage}", "DEMO_KEY", 1))
//				.andExpect(status().isOk()).andReturn();
//		String stringResult = result.getResponse().getContentAsString();
//		assertTrue(stringResult.equals("Nearest Neo id:2431394 and Neo name:431394 (2007 FS35)"));
//	}
}
