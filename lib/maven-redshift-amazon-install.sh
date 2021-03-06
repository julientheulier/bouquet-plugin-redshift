#!/bin/sh
# install the redshift JDBC driver in maven so we can build the plugin
#
BASEDIR=$(dirname $0)

mvn install:install-file -DgroupId=com.amazon -DartifactId=redshift.jdbc42.Driver -Dversion=1.2.8.1005 -Dpackaging=jar -Dfile=${BASEDIR}/RedshiftJDBC42-1.2.8.1005.jar -DgeneratePom=true 
