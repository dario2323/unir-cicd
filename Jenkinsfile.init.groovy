pipeline {
    agent any
    stages {
        stage('Source') {
            steps {
                git 'https://github.com/dario2323/unir-cicd.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Install!'
                sh 'sudo apt-get update && apt-get install make'
                echo 'Building stage!'
                sh 'make build'
            }
        }
        stage('Unit tests') {
            steps {
                sh 'make test-unit'
                archiveArtifacts artifacts: 'results/*.xml'
            }
        }
    }
    post {
        always {
            junit 'results/*_result.xml'
            cleanWs()
        }
    }
}
