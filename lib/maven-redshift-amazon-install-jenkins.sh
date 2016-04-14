#!/bin/sh
# now we know why we hate them...
#
mvn install:install-file -DgroupId=com.amazon -DartifactId=redshift.jdbc41.Driver -Dversion=1.1.13.1013 -Dpackaging=jar -Dfile=RedshiftJDBC41-1.1.13.1013.jar -DgeneratePom=true -DlocalRepositoryPath=${WORKSPACE}/.repository

