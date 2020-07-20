FROM hupadhyay/ubuntu-openjdk8

MAINTAINER Himanshu Upadhyay <himanshu2703@gmail.com>

WORKDIR /usr/local/lib

EXPOSE 8080

COPY target/learnSpringBoot.jar .

CMD ["java", "-jar", "learnSpringBoot.jar"]