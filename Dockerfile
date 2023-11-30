FROM eclipse-temurin:17-jdk-alpine
COPY target/BankWebApp-0.0.1-SNAPSHOT.jar app.jar
ENV JDBC_URL=""
ENV JDBC_USERNAME=""
ENV JDBC_PASSWORD=""
ENV JWT_SECRET=""
ENV JWT_EXPIRATION=""
ENV JWT_HEADER=""
ENTRYPOINT ["java","-jar","/app.jar"]