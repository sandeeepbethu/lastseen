FROM docker.reliancejio.com/assessment/alpine-jre

ADD build/distributions/lastseen.tar /

ENTRYPOINT ["/lastseen/bin/lastseen"]
