#!/bin/bash

# Variables
PROJECT_NAME="IBook"
TOMCAT_HOME="./apache-tomcat-9.0.89"
JAVA_REQUIRED_VERSION="17"
SRC_DIR="src"
WEB_DIR="web"
BUILD_DIR="build"
ROUTES_DIR="$SRC_DIR/com/$PROJECT_NAME/routes"
WAR_FILE="$BUILD_DIR/$PROJECT_NAME.war"
HASH_FILE="./conf/file_hashes.txt"
COMPILE_ERRORS_FILE="compile_errors.txt"

# Convierte las rutas de Windows a rutas de Unix para Git Bash
TOMCAT_HOME=$(cygpath -u "$TOMCAT_HOME")
SRC_DIR=$(cygpath -u "$SRC_DIR")
WEB_DIR=$(cygpath -u "$WEB_DIR")
BUILD_DIR=$(cygpath -u "$BUILD_DIR")

# librerias of java 
CLASSPATH="$TOMCAT_HOME/lib/*"

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

compile_and_deploy() {
    echo "Compilando y desplegando el proyecto..."

    # Elimina archivos en el directorio de compilación
    echo "Limpiando el directorio de compilación..."
    rm -rf "$BUILD_DIR"
    mkdir -p "$BUILD_DIR"

    # Verificar la existencia del directorio de rutas
    if [ ! -d "$ROUTES_DIR" ]; then
        echo "Error: Directorio no encontrado: $ROUTES_DIR"
        exit 1
    fi

    # Verificar la existencia de servlet-api.jar
    if [ ! -f "$TOMCAT_HOME/lib/servlet-api.jar" ]; then
        echo "Error: servlet-api.jar no encontrado en $TOMCAT_HOME/lib"
        exit 1
    fi

    # Compilar todos los archivos .java en el directorio de rutas
    echo "Compilando archivos Java en $ROUTES_DIR..."
    find "$SRC_DIR" -name "*.java" > ./conf/sources.txt
    if [$CLASSPATH == ""]; then
        echo "$CLASSPATH"
        echo "No se encontraron librerias"
        exit 1
    fi

    javac -cp "$CLASSPATH" -d "$BUILD_DIR" @"./conf/sources.txt" 2> ./conf/compile_errors.txt    


    # Verificar si la compilación tuvo errores
    if [ $? -ne 0 ]; then
        echo "Errores de compilación encontrados:"
        cat ./conf/compile_errors.txt
        exit 1
    else
        echo "Compilación exitosa."
        echo "Revisando la salida de compilación..."
        cat "./conf/compile_output.txt"
    fi

    # Verificar si el directorio com fue creado dentro de build
    if [ ! -d "$BUILD_DIR/com" ]; then
        echo "Errores de compilación encontrados:"
        echo "Error: Directorio de compilación no encontrado: $BUILD_DIR/com"
        cat ./conf/compile_errors.txt
        exit 1
    fi

    # Copiar los archivos web
    echo "Copiando archivos web desde $WEB_DIR a $BUILD_DIR..."
    cp -r "$WEB_DIR/"* "$BUILD_DIR/"

    # Organizar los archivos compilados
    echo "Organizando archivos compilados..."
    mkdir -p "$BUILD_DIR/WEB-INF/classes"
    mv "$BUILD_DIR/com" "$BUILD_DIR/WEB-INF/classes/"

    # Empaquetar el proyecto en un archivo WAR
    echo "Empaquetando el proyecto en un archivo WAR..."
    jar cvf "$WAR_FILE" -C "$BUILD_DIR" .

    # Limpiar el contenido del directorio webapps de Tomcat
    echo "Limpiando el directorio webapps de Tomcat..."
    rm -rf "$TOMCAT_HOME/webapps/$PROJECT_NAME"
    rm -rf "$TOMCAT_HOME/webapps/$PROJECT_NAME.war"

    # Desplegar el archivo WAR en Tomcat
    echo "Desplegando el archivo WAR en Tomcat..."
    cp "$WAR_FILE" "$TOMCAT_HOME/webapps/"

    # Reiniciar Tomcat

    sleep 10  # Espera un momento para que Tomcat se detenga completamente
    echo "Reiniciando Tomcat..."
    ./$TOMCAT_HOME/bin/./shutdown.bat > ./conf/tomcat-bash.txt 2>&1
    sleep 10 
    ./$TOMCAT_HOME/bin/startup.bat > ./conf/tomcat-bash.txt 2>&1    

    # Mensaje de confirmación
    echo "El servidor de Tomcat está corriendo en: http://localhost:8080/$PROJECT_NAME"
}

compile_and_deploy


