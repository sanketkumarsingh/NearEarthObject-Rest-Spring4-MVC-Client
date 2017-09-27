# NearEarthObject-Rest-Spring4-MVC-Client
This is a Rest Webservice Client to consume neows webservice. It also contains junits test cases

How to run?

Prerequisites to run:
1. Java 8
2. Apache Tomcat 8.0.21

Steps to directly test the webservices:
1. Download the war (neows.war) file and paste it in tomcat/webapps directory.
2. Start tomcat by running: ./startup.sh

Steps to install the code  and generate deployment(.war) file:
1. Download the code and import it as maven project in eclipse
2. Build the project with goal as: clean install
3. Copy the neows.war file and paste it in tomcat/webapps directory.
4. Start tomcat by running: ./startup.sh

The three Webservices can be tested as below.

This project exposes three main webservice call. 
1. Get the largest Near earth object: The assumption is that the largest NEO is the one which have maximum estimated diameter.
 
 This URL for the webservice is : http://localhost:8080/neows/largest/neo/api-key/topkpage
 In the URL
 a) replace api-key with one obtained by signing at : https://api.nasa.gov/index.html#apply-for-an-api-key
 b) replace topkpage with a number which represent the number of pages in which you want to find the largest Neo. This is required as the webservice which is consumed retrieves 20 near earth object at each call. Thus, to avoid the forbidden 403 request error, one can provide number of pages to look from which the largest NEO is retrieved.
 
 An example of the URL: http://localhost:8080/neows/largest/neo/DEMO_KEY/1
 
 The response on the browser gives String as: Largest Neo id:2021277 and Neo name:21277 (1996 TO5)
 
 2. Get the nearest Near Earth Object. The assumption is that nearest NEO is the one which have smallest miss distance.
 
  This URL for the webservice is : http://localhost:8080/neows/nearest/neo/api-key/topkpage
 In the URL
 a) replace api-key with one obtained by signing at : https://api.nasa.gov/index.html#apply-for-an-api-key
 b) replace topkpage with a number which represent the number of pages in which you want to find the largest Neo. This is required as the webservice which is consumed retrieves 20 near earth object at each call. Thus, to avoid the forbidden 403 request error, one can provide number of pages to look from which the largest NEO is retrieved.
 
 An example of the URL: http://localhost:8080/neows/largest/neo/DEMO_KEY/1
 
 The response on the browser gives String as: Nearest Neo id:2431394 and Neo name:431394 (2007 FS35)
 
 3. Get the Near Earth Object detail by id.
 
 One can also retrieve some of the details of a near earth object obtained using above web sevice calls using this service.
 The URL for the service is: http://localhost:8080/neows/getDetailById/api-key/id
  In the URL
 a) replace api-key with one obtained by signing at : https://api.nasa.gov/index.html#apply-for-an-api-key
 b) id the Neo id 
 An example of the URL: http://localhost:8080/neows/getDetailById/DEMO_KEY/2021277
 and the corresponding response is: 
 
 {
    neo_reference_id: 2021277,
    name: "21277 (1996 TO5)",
    estimated_diameter: {
                          kilometers: {
                                         estimated_diameter_min: 1.6770846216,
                                         estimated_diameter_max: 3.750075218
                                      }
                        },
  close_approach_data: [
                            {
                              close_approach_date: "1945-06-07",
                              miss_distance: {
                                                kilometers: "5000510.5"
                                              }
                             }
                        ]
  }
 
 If there are any question/suggestion, please leave a comment.
