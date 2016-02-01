#!/bin/sh
# now we know why we hate them...
#
BASEDIR=$(dirname $0)

mvn install:install-file -DgroupId=com.amazon -DartifactId=redshift.jdbc41.Driver -Dversion=1.1.10.1010 -Dpackaging=jar -Dfile=${BASEDIR}/RedshiftJDBC41-1.1.10.1010.jar -DgeneratePom=true 
