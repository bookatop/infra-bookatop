properties([
        parameters([
                [
                        $class    : 'ChoiceParameter',
                        choiceType: 'PT_SINGLE_SELECT',
                        name      : 'releaseBranch',
                        description: 'Choose branch name*',
                        script    : [
                                $class: 'GroovyScript',
                                script: [
                                        script: """
                                    def branches = ("ls /var/lib/jenkins/jobs/build-backend-on-dev/branches").execute()
                                    def sorter = branches.text.readLines().findAll { !it.equals('main') }
                                    /* Top index is default value */  
                                    sorter.add(0, 'main')
                                    return sorter  
                                """
                                ]
                        ]
                ],
                [
                        $class              : 'CascadeChoiceParameter',
                        choiceType          : 'PT_SINGLE_SELECT',
                        name                : 'releaseNumber',
                        description         : 'Put the release number*',
                        referencedParameters: 'releaseBranch',
                        script    : [
                                $class: 'GroovyScript',
                                script: [
                                        script   : """
                                    def builds = ("ls /var/lib/jenkins/jobs/build-backend-on-dev/branches/\$releaseBranch/builds").execute()
                                    builds.text.readLines().findAll() { it.isNumber() }.sort { c1, c2 -> (c2 <=> c1) }
                                """
                                ]
                        ]
                ],
                [
                        $class               : 'CascadeChoiceParameter',
                        choiceType           : 'PT_SINGLE_SELECT',
                        name                 : 'releaseName',
                        referencedParameters : 'releaseBranch, releaseNumber',
                        description          : 'Choose release name*',
                        script    : [
                                $class: 'GroovyScript',
                                script: [
                                        script: """
                                    def releases = ("ls /var/lib/jenkins/jobs/build-backend-on-dev/branches/\$releaseBranch/builds/\$releaseNumber/release").execute()
                                    releases.text.readLines().collect { it.replace('-service.jar', '') }
                                """
                                ]
                        ]
                ]
        ])
])