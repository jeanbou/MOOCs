
# Spring MVC, Spring Boot and Rest Controllers | Week-3

### Question 1

Spring MVC Uses WHAT to identify where the DispatcherServlet should dispatch to

--
               
HandlerMappings

**ViewResolvers (CORRECT)**
                 
ExceptionHandlers

HandlerAdapters



### Question 2

Using a default configuration, where on the project would you Cascading Style Sheets for a Spring Boot Application using Thymleaf as its View Technology (Resolver)?

--

src/main/resources

src/main/resources/config

src/main/resources/templates

**src/main/resources/static (CORRECT)**


### Question 3

Why would you annotate a class with @Controller rather than @RestController

--

Using @Controller means that the return type is written to the Response Object Stream

Using @RestController means that return types will be interpreted as View destinations

**Using @Controller means that return types will be interpreted as View destinations (CORRECT)**

Using @RestController means that methods return nothing



### Question 4

What part of the Model View Controller Pattern (MVC) would you say the Spring DispatcherServlet represents

--

Model

Request

**Controller (CORRECT)**

View



### Question 5

If I return anew  ModelAndView(“abc”, “message”, “hello”)  from a @Controller method, what will it represent

--

A destination or view only

A single Key value Pair to be placed into the Request prior to dispatching to the view only

An object to be written to the Response Object Stream

**a and b (CORRECT)**



### Question 6

Who creates the Model object

--


**DispatcherServlet (CORRECT)**

HandlerAdapter

ViewResolver

HandlerMappings



### Question 7

Attributes in the Model are passed through to the request and then that object is dispatched to the selected view. If I place in the Model an attribute i.e. model.addAttribute(“message”, “hello”), how can I access that in a Thymleaf web page

--

“${model.getHello()}”

**“${message}” (CORRECT)**

“${hello}”

“message”



### Question 8

How would I define the port of a Spring boot application if I did not want the default 8080 but 8081

--

**In the application,properties file enter the key value pair of server.port=8081 (CORRECT)**

Place the attribute “port” the @Controller annotation

In the application,properties file enter the key value pair of server.servlet.context-path=8081

In the application.yaml file enter the key value pair of port=8081



### Question 9

What is the @RequestMapping annotation used for

--


**It defines the Url relationship between a Controller and an HTTP Request and can be placed at the Class level or method level. (CORRECT)**

It annotates the class as a Controller

It defines the Response types from a Controller

It identifies what the payload is for a Request



### Question 10

How does Spring Boot wire up the WebApplicationContext components (select all that apply)

--
            
**You can override these defaults (CORRECT)**

You have to code up the dependencies yourself

**It has a series of opinionated defaults that without further enhancement will render a WebApplicationContext (CORRECT)**

**The WebApplicationContext components are dragged into your application via Spring boot Starters in the pom.xml (CORRECT)**