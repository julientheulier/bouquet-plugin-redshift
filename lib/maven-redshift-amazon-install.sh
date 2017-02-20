#!/bin/sh
# install the redshift JDBC driver in maven so we can build the plugin
#
BASEDIR=$(dirname $0)

mvn install:install-file -DgroupId=com.amazon -DartifactId=redshift.jdbc41.Driver -Dversion=1.2.1.1001 -Dpackaging=jar -Dfile=${BASEDIR}/RedshiftJDBC41-1.2.1.1001.jar -DgeneratePom=true 
