def branches = ("ls '/var/lib/jenkins/jobs/build-backend-on-dev/branches'").execute()
branches.text.readLines().collect {
    "'".concat(it.toLowerCase()).concat("'")
}
