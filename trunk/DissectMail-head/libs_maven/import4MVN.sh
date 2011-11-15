#!/bin/sh
# Shell script para adicionar uma linha com conteúdo definido
# pelo usuário a um arquivo qualquer
mvn install:install-file -Dfile=google-api-spelling-java-1.1.jar  -DgroupId=org.xeustechnologies.google-api -DartifactId=google-api-spelling-java -Dversion=1.1 -Dpackaging=jar
mvn install:install-file -Dfile=Asynchronously1.0.jar  -DgroupId=asynchronously -DartifactId=asynchronously -Dversion=0.1 -Dpackaging=jar
mvn install:install-file -Dfile=java_memcached-release_2.6.2.jar  -DgroupId=com.danga.MemCached -DartifactId=java-memcached -Dversion=2.6.2 -Dpackaging=jar
mvn install:install-file -Dfile=memcached-2.3.1.jar  -DgroupId=net.spy.memcached -DartifactId=net.spy.memcached -Dversion=2.3.1 -Dpackaging=jar
mvn install:install-file -Dfile=hibernate-memcached-1.2.2.jar -DgroupId=com.googlecode.hibernate.memcached-local -DartifactId=hibernate-memcached-local -Dversion=1.2.2 -Dpackaging=jar