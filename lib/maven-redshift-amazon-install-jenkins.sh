#!/bin/sh
# now we know why we hate them...
#
mvn install:install-file -DgroupId=com.amazon -DartifactId=redshift.jdbc41.Driver -Dversion=1.1.10.1010 -Dpackaging=jar -Dfile=RedshiftJDBC41-1.1.10.1010.jar -DgeneratePom=true -DlocalRepositoryPath=${WORKSPACE}/.repository

