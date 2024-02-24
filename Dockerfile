# Базовый образ, содержащий среду Java времени выполнения
FROM openjdk:21

# Добавить информацию о владельце
LABEL maintainer="Zubov Vitaly <zubov.vp@yandex.ru>"

# Файл jar приложения
ARG JAR_FILE

# Добавить файл jar приложения в контейнер
COPY ${JAR_FILE} app.jar

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

FROM openjdk:21

#Add volume pointing to /tmp
VOLUME /tmp

#Copy unpackaged application to new container
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# запустить приложение
ENTRYPOINT ["java","-cp","app:app/lib/*","ru.zubov.licensingservice.LicensingServiceApplication"]