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
        failure {  
            echo 'Enviando mail.....'             
            echo  "<b>Se ha producido un error: </b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER}"
        }
        success {  
             echo 'This will run only if successful'               
        }
    }
}
