# springboot-micro-service-jpa
App is a micro service I have used springboot and JPA as technology stack . 
	
In this springboot app I have setup github actions CICD process which will first build the app and then generate image and push that image to dockerhub.
Following are the commands to pull image from dockerhub and run locally. 
	
	docker pull 61444/springboot-projects-githubactions
	docker run --publish 8080:8080 61444/springboot-projects-githubactions

Other commands

	docker push 61444/springboot-projects-githubactions:latest
	docker ps
	docker images

 Command to run springboot app from cmd.
 
	mvn clean install -DskipTests
	mvn spring-boot:run

Files that are needed to setup CICD. 

      .github/workflows/github-actions-demo.yml
       Dockerfile
		
Add following in pom.xml to give final name for jar.

	<finalName>springboot-images-github-actions</finalName>
