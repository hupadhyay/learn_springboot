### AWS docker deploy... 
https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/single-container-docker-configuration.html 

###AWS docker deployment: for single container when in memory H2 is used.
Remove _singleContainer and uncomment h2 related setting from application.properties file. Comment the mysql related setting.

###AWS multi docker deployment: Use Dockerrun.aws.json file.

