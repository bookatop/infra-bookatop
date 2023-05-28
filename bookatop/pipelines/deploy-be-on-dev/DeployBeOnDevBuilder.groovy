import ServiceDeployUtils

def deploy(
        String branchName,
        String serviceName,
        String daemonName,
        Integer buildNumber,
        String sourcePath,
        String destPath
) {

    utils = new ServiceDeployUtils()

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

return this