FROM java:8
EXPOSE 8099

VOLUME /tmp
ADD /target/renren-admin.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
