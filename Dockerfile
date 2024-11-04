# Étape 1 : Utiliser une image de construction
FROM openjdk:17-jdk-slim AS builder

# Étape 2 : Définir le répertoire de travail
WORKDIR /app

# Étape 3 : Copier le fichier pom.xml et le wrapper Maven
COPY pom.xml ./
COPY mvnw ./
COPY .mvn ./.mvn

# Étape 4 : Copier le code source
COPY src ./src

# Étape 5 : Construire l'application
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Étape 6 : Utiliser une image Java plus légère pour exécuter l'application
FROM openjdk:17-jdk-slim
COPY --from=builder /app/target/tp-foyer-5.0.0.jar tp-foyer.jar
ENTRYPOINT ["java", "-jar", "tp-foyer.jar"]
