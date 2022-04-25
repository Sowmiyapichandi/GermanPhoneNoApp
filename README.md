# German-phone-numbers-service-app
Simple Application API with Java, and Spring Boot

# Docker Installation
To install the project inside the container, run the following docker commands

  ## Build Jar file
 
  mvn clean install
  
  ## Build Docker Image
  
  docker build -f Dockerfile -t navvis:phoneNoService .
  
  ## Install the Image where the container has stock application
  
  docker run -p 8080:8080 navvis:phoneNoService

# Documentation
Full API documentation and you can test your application in Swagger UI
http://localhost:8080/swagger-ui/index.html
