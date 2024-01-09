# Базовый образ, содержащий среду Java времени выполнения
FROM openjdk:17

# Добавить информацию о владельце
LABEL maintainer="Zubov Vitaly <zubov.vp@yandex.ru>"

# Файл jar приложения
ARG JAR_FILE

# Добавить файл jar приложения в контейнер
COPY ${JAR_FILE} app.jar

#EXPOSE 8080

# запустить приложение
ENTRYPOINT ["java","-jar","/app.jar"]