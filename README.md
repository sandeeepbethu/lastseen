Overview:
============
This rest API is aimed at providing last seen time stamp info of a person when we have their last visited time stamp.

Hence we have put in some sample info in app.yml file -
4 persons namely - a,b,c,d and their last visited times

This program computes the last seen time stamp based on the person name passed, 
that is viz a/b/c/d

If you want to test this out, run the program, hit the following GET end point::

http://localhost:8080/activities/v1/last_seen_data/{person_name}

Response is a json object which has both last seen crux, tick.

crux -> Simple time stamp lapsed

tick -> Exact time stamp lapsed

If you want to try fresh, replace a person name, time stamp in the configuration file in the same order. 

Tech Stack Used:
=================
Java 8

Spring boot 

Gradle 5

Junit 4.12

Points to note:
================
> Have the same count of persons, time stamps as this program assumes it to be
  a 1-1 mapping. 

> Sample time entered should be of the format yyyy/MM/dd HH:MM:SS

> Time zone is assumed to be local always.

> As we are not using an in memory (h2) or permanent data base sample data is 
fed through the config file.

In case if we want to deploy this code to any Open source cloud platform like k8s, have added drone, docker image files. Of course repo, cert paths i have assumed to be from reliance.  

