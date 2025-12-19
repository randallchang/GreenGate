# builder
FROM public.ecr.aws/docker/library/maven:3.9.9-amazoncorretto-21-al2023 AS builder

WORKDIR /app

COPY frontend ./frontend
COPY backend ./backend
COPY pom.xml ./pom.xml
RUN mvn clean package -DskipTests=true

FROM public.ecr.aws/docker/library/amazoncorretto:21-al2023-headless

WORKDIR /app

COPY --from=builder /app/backend/target/api.jar api.jar

ENTRYPOINT [ \
    "java",\
    "-Xmx2048M", \
    "-Xms512M", \
    "-Dfile.encoding=utf-8", \
    "-jar", \
    "/app/api.jar"]
