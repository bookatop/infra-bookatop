package com.bookatop.deployment

import com.bookatop.deployment.ServiceDeployUtils

def f1() {
    ServiceDeployUtils.f2()
}

return this

//static void deployBackEndOnDev(
//        String branchName,
//        String serviceName,
//        String daemonName,
//        Integer buildNumber,
//        String sourcePath,
//        String destPath
//) {
//    println "deployBackEndOnDev"

//    ServiceDeployUtils utils = new ServiceDeployUtils();

//    println branchName
//    println serviceName
//    println daemonName
//    println buildNumber
//    println sourcePath
//    println destPath

//        utils.checkServiceExists(serviceName)
//        utils.stopService(serviceName)
//        utils.stopDaemon(daemonName)
//        utils.replaceService(serviceName, buildNumber)
//        utils.launchDaemon(daemonName)
//        utils.checkServiceStatus(serviceName)
//}

//def deployBackEndOnProd(
//        String branchName,
//        String serviceName,
//        String daemonName,
//        Integer buildNumber,
//        String sourcePath,
//        String destPath
//) {
//    println "deployBackEndOnProd"
//}
//
//def deployFrontEndOnDev() {
//    println "deployFrontEndOnDev"
//}
//
//def deployFrontEndOnProd() {
//    println "deployFrontEndOnProd"
//}
//
//return this