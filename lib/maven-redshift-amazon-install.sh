#!/bin/sh
# install the redshift JDBC driver in maven so we can build the plugin
#
BASEDIR=$(dirname $0)

mvn install:install-file -DgroupId=com.amazon -DartifactId=redshift.jdbc41.Driver -Dversion=1.1.17.1017 -Dpackaging=jar -Dfile=${BASEDIR}/RedshiftJDBC41-1.1.17.1017.jar -DgeneratePom=true 
