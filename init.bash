#!/bin/bash

# Variables
PROJECT_NAME="IBook"
TOMCAT_HOME="apache-tomcat-9.0.89"
JAVA_REQUIRED_VERSION="17"

# Comprueba que JAVA_HOME esté configurado y sea la versión requerida
if [ -z "$JAVA_HOME" ]; then
    echo "Error: JAVA_HOME no está configurado."
    exit 1
fi

JAVA_VERSION=$("$JAVA_HOME/bin/java" -version 2>&1 | awk -F[\".] 'NR==1 {print $2}')

if [ "$JAVA_VERSION" != "$JAVA_REQUIRED_VERSION" ]; then
    echo "Error: Se requiere Java 17, pero se encontró Java $JAVA_VERSION."
    exit 1
fi

# Compila el servlet
javac -cp "lib/servlet-api.jar" -d build src/com/$PROJECT_NAME/RouteServlet.java

# Copia los archivos web
cp -r web/* build/

# Organiza los archivos compilados
mkdir -p build/WEB-INF/classes
cp -r build/com build/WEB-INF/classes/

# Empaqueta el proyecto en un archivo WAR
jar cvf "$PROJECT_NAME.war" -C build .

# Despliega el archivo WAR en Tomcat
cp "$PROJECT_NAME.war" "$TOMCAT_HOME/webapps/"

# Inicia Tomcat
cd "$TOMCAT_HOME/bin"

./startup.bat

cd ../..

# Mensaje de confirmación
echo "El servidor de Tomcat está corriendo en: http://localhost:8080/$PROJECT_NAME"


