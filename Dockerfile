FROM openjdk:11
EXPOSE 8080
ADD target/springboot-images-github-actions.jar springboot-images-github-actions.jar
ENTRYPOINT ["java","-jar","/springboot-images-github-actions.jar"]