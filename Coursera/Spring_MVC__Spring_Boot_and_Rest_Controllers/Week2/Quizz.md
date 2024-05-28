
# Spring MVC, Spring Boot and Rest Controllers | Week-2

### Question 1

How would you inject into a Controller method an optional parameter from the request i.e. url?id=55 where the id parameter may or may not be present

--
               
Annotate the method argument with @RequestParam(“id”) int id

**Annotate the method argument with @RequestParam(“id”) Optional<Integer> optional (CORRECT)**

Annotate the method argument with @PathVariable(“id”) Optional<Integer> optiona

Annotate the method argument with @PathVariable(“id”) int id



### Question 2

Question 2
When accessing a Rest Service at url /abc with an HTTP GET, using RestTemplate, what method would you use to get at the response body immediately.

--

String string = new RestTemplate().exchange/abc, null, String.class);

ResponseEntity<String> entity = new RestTemplate().getForEntity(/abc, String.class);

**String string = new RestTemplate().getForObject(/abc, String.class); (CORRECT)**


ResponseEntity<String> responseEntity = new ResponseEntity<String> responseEntity = new RestTemplate().exchange("/abc", HttpMethod.GET, null, String.class);



### Question 3

What attribute would you add to a @GetMapping(“/”) annotated RestController method to return XML or json

--

@GetMapping(path=”/”, consumes=={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON _VALUE})

@GetMapping(path=”/”, produces=MediaType.APPLICATION_XML

@GetMapping(“/”)

**@GetMapping(path=”/”, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) (CORRECT)**



### Question 4

How can you extract the Header “user-agent” out of the request in a RestController method, to be available in that method for processing

--

@RequestParam("user-agent") Optional<String> optional)

**@RequestHeader("user-agent") Optional<String> optional) (CORRECT)**

**@RequestHeader("user-agent") String agent) (CORRECT)**

@PathVariable("user-agent") String agent)



### Question 5

How can you extract the request payload and inject it into a RestController method for processing

--

@Header

**@RequestBody (CORRECT)**

@RequestParam

@Pathvariable



### Question 6

Which annotation marks an input argument to a RestController method that can ONLY be mandatory

--

@RequestHeader

@RestController

**@PathVariable (CORRECT)**

@RequestParam



### Question 7

Which annotation marks an input argument to a RestController as an optional argument from a QueryString

--

@PathVariable

@RequestHeader

@RestController

**@RequestParam (CORRECT)**



### Question 8

In a Spring Boot project using the default setup, If an accept header is not provided in a GET request of a resource representation of a Java object, what MediaType will the response payload take

--

Text

XML

Java Object

**Json (CORRECT)**



### Question 9

In order for converters to translate a request payload on a Post Request to a RestController method, what annotation do you use for the RestControllers method argument

--

@PathParam

@RequestParam

**@RequestBody (CORRECT)**

@PostMapping


### Question 10

Which of these methods on RestTemplate must you provide the desired HTTP method as one of the arguments

--
            
postForEntity

getForObject

**exchange (CORRECT)**

getForEntity