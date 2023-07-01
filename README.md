# springboot-micro-service-jpa
App is a micro service I have used springboot and JPA as technology stack . 
	
In this springboot app I have setup github actions CICD process which will first build the app and then generate image and push that image to dockerhub.
Following are the commands to pull image from dockerhub and run locally. 

	
	docker pull 61444/springboot-projects-githubactions
	docker run --publish 8080:8080 springboot-projects-githubactions:latest
 
Other commands:
        docker push springboot-projects-githubactions:latest
	docker ps
	docker images

 Command to run springboot app from cmd.
 
	mvn spring-boot:run
