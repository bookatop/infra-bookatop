@Library('ServiceDeployUtils') _
import ServiceDeployUtils

def deployBackEndOnDev(
        String branchName,
        String serviceName,
        String daemonName,
        Integer buildNumber,
        String sourcePath,
        String destPath
) {

    ServiceDeployUtils utils = new ServiceDeployUtils();

    println branchName
    println serviceName
    println daemonName
    println buildNumber
    println sourcePath
    println destPath

//        utils.checkServiceExists(serviceName)
//        utils.stopService(serviceName)
//        utils.stopDaemon(daemonName)
//        utils.replaceService(serviceName, buildNumber)
//        utils.launchDaemon(daemonName)
//        utils.checkServiceStatus(serviceName)
}

def deployBackEndOnProd(
        String branchName,
        String serviceName,
        String daemonName,
        Integer buildNumber,
        String sourcePath,
        String destPath
) {

}

def deployFrontEndOnDev() {

}

def deployFrontEndOnProd() {

}

return this