library(
      identifier: 'unir-jsl@master',
      retriever: modernSCM(
        [
          $class: 'GitSCMSource',
          remote: "https://github.com/dario2323/unir-cicd.git"
        ]
      )
    ) _

pipeline {
    agent any
    stages {
        stage('Commit info') {
            steps {
                jslInfo()
            }
        }
        stage('Repo details') {
            steps {
                echo "Repository name: ${jslGit.getRemoteRepoName()}"
                echo "Repository owner: ${jslGit.getRemoteRepoOwner()}"
            }
        }
    }
}
