package com.bookatop.deployment

def f2() {
    println "f2"
}

return this

//class ServiceDeployUtils {
//
//    static void checkServiceExists(String serviceName) {
//        println "checkServiceExists"
////       sh """
////        #!/bin/sh
////
////     serviceJarName = "${serviceName}-service.jar"
////     echo Check new service \$serviceJarName is exist
////
////     if ! [ -f \$serviceJarName ]; then
////        echo Oops! The service \$serviceJarName is not exist!
////        exit 1
////     fi
////  """
//    }
//
//    static void checkServiceStatus(String serviceName) {
//        println "checkServiceStatus"
////        sh """
////     #!/bin/sh
////
////     TRY_CHECK_SERVICE = 3
////     echo Check service \$serviceName status
////
////     i=0
////     while [ \$(curl -X GET '${BACKEND_URL}/actuator/health' | jq -r '.status') != "UP" ]
////     do
////       if [ \$i -eq \$TRY_CHECK_SERVICE ];
////       then
////         echo Oops! Could not start service \$serviceName after \$TRY_CHECK_SERVICE tries!
////         exit 1
////       fi
////       sleep 1
////       i=\$((\$i+1))
////     done
////  """
//    }
//
//    static void stopService(String serviceName) {
//        println "stopService"
////        sh """
////     #!/bin/sh
////
////     serviceName = "${serviceName}.service"
////     TRY_STOP_SERVICE = 3
////
////     echo Stop service \$serviceName
////
////     i=0
////     while [ \$(curl -X GET '${BACKEND_URL}/actuator/health' | jq -r '.status') = "UP" ]
////     do
////       if [ \$i -eq \$TRY_STOP_SERVICE ];
////       then
////          echo Oops! Could not stop service \$serviceName after \$TRY_STOP_SERVICE tries!
////          exit 1
////       fi
////       curl -X POST '${BACKEND_URL}/actuator/shutdown'
////       sleep 1
////       i=\$((\$i+1))
////     done
////  """
//    }
//
//    static void stopDaemon(String daemonName) {
//        println "stopDaemon"
////        sh """
////     #!/bin/sh
////
////     daemonName = "${daemonName}"
////     TRY_STOP_DAEMON = 3
////     echo Stop daemon \$daemonName
////
////     i=0
////     #Continue execution when exit result is non-zero
////     set +e
////     while [ \$(sudo systemctl is-active \$daemonName) -eq "active" ]
////     do
////       if [ \$i -eq \$TRY_STOP_DAEMON ];
////       then
////         echo Oops! Could not stop daemon \$daemonName after \$TRY_STOP_DAEMON tries!
////         set -e
////         exit 1
////       fi
////       sudo systemctl stop \$daemonName
////       sleep 1
////       i=\$((\$i+1))
////     done
////     set -e
////  """
//    }
//
//    static void replaceService(String serviceName, Integer buildNumber) {
//        println "replaceService"
////        sh """
////     #!/bin/sh
////
////     serviceJarName = "${serviceName}-service.jar"
////     sourceJarFile = "${SOUR_PATH}/${serviceName}-service-${buildNumber}.jar"
////     destJarFile = "${DEST_PATH}/\$serviceJarName"
////
////     echo Replace service \$destJarFile
////
////     if ! [ -d $DEST_PATH ]; then
////       mkdir -p $DEST_PATH
////     fi
////     rm -f $DEST_PATH/\$serviceJarName
////     cp -n \$sourceJarFile \$destJarFile
////  """
//    }
//
//    static void launchDaemon(String daemonName) {
//        println "launchDaemon"
////        sh """
////     #!/bin/sh
////
////     TRY_START_DAEMON = 3
////
////     echo Launch daemon \$daemonName
////
////     i=0
////     set +e
////     while [ \$(sudo systemctl is-active \$daemonName) != "active" ]
////     do
////       if [ \$i -eq $TRY_START_DAEMON ];
////       then
////         set -e
////         echo Oops! Could not start daemon \$daemonName after $TRY_START_DAEMON tries!
////         exit 1
////       fi
////       sudo systemctl start \$daemonName
////       sleep 1
////       i=\$((\$i+1))
////     done
////     set -e
////    """
//    }
//}