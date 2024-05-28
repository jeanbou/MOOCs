
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


