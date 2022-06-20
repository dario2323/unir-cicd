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
             echo "${env.JOB_NAME}"
             echo "${env.BUILD_NUMBER}"
             echo "${env.BUILD_URL}"
        }
        success {  
             echo 'This will run only if successful'  
             echo 'Nombre del trabajo:' + "${env.JOB_NAME}"
             echo 'número de ejecución:' + "${env.BUILD_NUMBER}"
        }
    }
}
