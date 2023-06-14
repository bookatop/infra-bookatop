private static String branchesPath() {
    '/var/lib/jenkins/jobs/build-backend-on-dev/branches'
}

def static branches() {
    def branches = ("ls ${branchesPath()}").execute()
    branches.text.readLines().collect {
        "'".concat(it.toLowerCase()).concat("'")
    }
}

def static releases(String branch) {
    def releases = ("ls ${branchesPath()}/${branch}/builds/${lastRelease(branch)}/release").execute()
    releases.text.readLines().collect {
        "'".concat(it.replace('-service.jar', '')).concat("'")
    }
}

def static lastRelease(String branch) {
    def builds = ("ls ${branchesPath()}/${branch}/builds").execute()
    builds.text.readLines().findAll() {
        it.isNumber()
    }.max()
}