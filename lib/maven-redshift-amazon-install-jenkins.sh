#!/bin/sh
# install the redshift JDBC driver in maven so we can build the plugin
#
mvn install:install-file -DgroupId=com.amazon -DartifactId=redshift.jdbc41.Driver -Dversion=1.1.17.1017 -Dpackaging=jar -Dfile=RedshiftJDBC41-1.1.17.1017.jar -DgeneratePom=true -DlocalRepositoryPath=${WORKSPACE}/.repository

