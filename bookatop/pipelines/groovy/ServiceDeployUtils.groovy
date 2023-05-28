class ServiceDeployUtils {

    private void checkServiceExists(String serviceName) {
        sh """
     #!/bin/sh

     serviceJarName = "${serviceName}-service.jar"
     echo Check new service \$serviceJarName is exist

     if ! [ -f \$serviceJarName ]; then
        echo Oops! The service \$serviceJarName is not exist!
        exit 1
     fi
  """
    }

    private void checkServiceStatus(String serviceName) {
        sh """
     #!/bin/sh

     TRY_CHECK_SERVICE = 3
     echo Check service \$serviceName status

     i=0
     while [ \$(curl -X GET '${BACKEND_URL}/actuator/health' | jq -r '.status') != "UP" ]
     do
       if [ \$i -eq \$TRY_CHECK_SERVICE ];
       then
         echo Oops! Could not start service \$serviceName after \$TRY_CHECK_SERVICE tries!
         exit 1
       fi
       sleep 1
       i=\$((\$i+1))
     done
  """
    }

    private void stopService(String serviceName) {
        sh """
     #!/bin/sh

     serviceName = "${serviceName}.service"
     TRY_STOP_SERVICE = 3
     
     echo Stop service \$serviceName

     i=0
     while [ \$(curl -X GET '${BACKEND_URL}/actuator/health' | jq -r '.status') = "UP" ]
     do
       if [ \$i -eq \$TRY_STOP_SERVICE ];
       then
          echo Oops! Could not stop service \$serviceName after \$TRY_STOP_SERVICE tries!
          exit 1
       fi
       curl -X POST '${BACKEND_URL}/actuator/shutdown'
       sleep 1
       i=\$((\$i+1))
     done
  """
    }

    private void stopDaemon(String daemonName) {
        sh """
     #!/bin/sh

     daemonName = "${daemonName}"
     TRY_STOP_DAEMON = 3 
     echo Stop daemon \$daemonName

     i=0
     #Continue execution when exit result is non-zero
     set +e
     while [ \$(sudo systemctl is-active \$daemonName) -eq "active" ]
     do
       if [ \$i -eq \$TRY_STOP_DAEMON ];
       then
         echo Oops! Could not stop daemon \$daemonName after \$TRY_STOP_DAEMON tries!
         set -e
         exit 1
       fi
       sudo systemctl stop \$daemonName
       sleep 1
       i=\$((\$i+1))
     done
     set -e
  """
    }

    private void replaceService(String serviceName, Integer buildNumber) {
        sh """
     #!/bin/sh

     serviceJarName = "${serviceName}-service.jar"
     sourceJarFile = "${SOUR_PATH}/${serviceName}-service-${buildNumber}.jar"
     destJarFile = "${DEST_PATH}/\$serviceJarName"     

     echo Replace service \$destJarFile

     if ! [ -d $DEST_PATH ]; then
       mkdir -p $DEST_PATH
     fi
     rm -f $DEST_PATH/\$serviceJarName
     cp -n \$sourceJarFile \$destJarFile
  """
    }

    private void launchDaemon(String daemonName) {
        sh """
     #!/bin/sh

     TRY_START_DAEMON = 3

     echo Launch daemon \$daemonName

     i=0
     set +e
     while [ \$(sudo systemctl is-active \$daemonName) != "active" ]
     do
       if [ \$i -eq $TRY_START_DAEMON ];
       then
         set -e
         echo Oops! Could not start daemon \$daemonName after $TRY_START_DAEMON tries!
         exit 1
       fi
       sudo systemctl start \$daemonName
       sleep 1
       i=\$((\$i+1))
     done
     set -e
    """
    }
}


////varSourceJarName = "${SERVICE_NAME}-service.jar"
////
////varSourceJarFile = "${SOUR_PATH}/${SERVICE_NAME}-service-${buildNumber}.jar"
////varDestJarFile = "${DEST_PATH}/${varSourceJarName}"
////
////varServiceName = "${SERVICE_NAME}.service"
////varDaemonName = "${SERVICE_NAME}.service"
//
//BACKEND_URL = "http://localhost:8081"
//TRY_STOP_SERVICE = 3
//TRY_START_SERVICE = 3
//TRY_STOP_DAEMON = 3
//TRY_START_DAEMON = 3
//TRY_CHECK_SERVICE = 3
//
//def deploy(String serviceName, String sourcePath, String destPath) {
//
//  varSourceJarName = "${serviceName}-service.jar"
//  varSourceJarFile = "${sourcePath}/${serviceName}-service-${buildNumber}.jar"
//  varDestJarFile = "${destPath}/${varSourceJarName}"
//  varServiceName = "${serviceName}.service"
//  varDaemonName = "${serviceName}.service"
//
//  println("deploy")
//
//
///*+-------------Check if new service exists to be replaced--------------*/
//
///*
//    sh """
//                      #!/bin/sh
//                      echo Check new service $varSourceJarFile is exist
//
//                      if ! [ -f $varSourceJarFile ]; then
//                         echo Oops! The service $varSourceJarFile is not exist!
//                         exit 1
//                      fi
//                    """
//*/
///*+-------------Stop service script-----------------*/
//
//    /*
//    sh """
//                      #!/bin/sh
//                      echo Stop service $varServiceName
//
//                      i=0
//                      while [ \$(curl -X GET '${BACKEND_URL}/actuator/health' | jq -r '.status') = "UP" ]
//                      do
//                        if [ \$i -eq $TRY_STOP_SERVICE ];
//                        then
//                          echo Oops! Could not stop service $varServiceName after $TRY_STOP_SERVICE tries!
//                          exit 1
//                        fi
//                        curl -X POST '${BACKEND_URL}/actuator/shutdown'
//                        sleep 1
//                        i=\$((\$i+1))
//                      done
//                    """
//*/
///*+-------------Stop daemon-----------------*/
///*
//    sh """
//                      #!/bin/sh
//                      echo Stop daemon $varDaemonName
//
//                      i=0
//                      #Continue execution when exit result is non-zero
//                      set +e
//                      while [ \$(sudo systemctl is-active $varDaemonName) -eq "active" ]
//                      do
//                        if [ \$i -eq $TRY_STOP_DAEMON ];
//                        then
//                          echo Oops! Could not stop daemon $varDaemonName after $TRY_STOP_DAEMON tries!
//                          set -e
//                          exit 1
//                        fi
//                        sudo systemctl stop $varDaemonName
//                        sleep 1
//                        i=\$((\$i+1))
//                      done
//                      set -e
//                    """
//*/
///*+-------------Replace service----------------------*/
///*
//    sh """
//                      #!/bin/sh
//                      echo Replace service $varDestJarFile
//
//                      if ! [ -d $DEST_PATH ]; then
//                          mkdir -p $DEST_PATH
//                      fi
//                      rm -f $DEST_PATH/$varSourceJarName
//                      cp -n $varSourceJarFile $varDestJarFile
//                    """
//*/
///*+-------------Launch daemon----------------------*/
///*
//    sh """
//                      #!/bin/sh
//                      echo Launch daemon $varDaemonName
//
//                      i=0
//                      set +e
//                      while [ \$(sudo systemctl is-active $varDaemonName) != "active" ]
//                      do
//                        if [ \$i -eq $TRY_START_DAEMON ];
//                        then
//                          set -e
//                          echo Oops! Could not start daemon $varDaemonName after $TRY_START_DAEMON tries!
//                          exit 1
//                        fi
//                        sudo systemctl start $varDaemonName
//                        sleep 1
//                        i=\$((\$i+1))
//                      done
//                      set -e
//                    """
//*/
///*-----------Check service status----------*/
///*
//    sh """
//                      #!/bin/sh
//                      echo Check service $varServiceName status
//
//                      i=0
//                      while [ \$(curl -X GET '${BACKEND_URL}/actuator/health' | jq -r '.status') != "UP" ]
//                      do
//                        if [ \$i -eq $TRY_CHECK_SERVICE ];
//                        then
//                          echo Oops! Could not start service $varServiceName after $TRY_CHECK_SERVICE tries!
//                          exit 1
//                        fi
//                        sleep 1
//                        i=\$((\$i+1))
//                      done
//                    """
//*/
///*-----------Service is success launched----------*/
//
//}

