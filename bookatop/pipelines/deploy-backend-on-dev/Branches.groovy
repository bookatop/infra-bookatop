def branches = ("ls '/var/lib/jenkins/jobs/build-backend-on-dev/branches'").execute()
return branches.text.readLines().collect {
    "'".concat(it.toLowerCase()).concat("'")
}
