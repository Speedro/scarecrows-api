pipeline {
    agent any
    stages {
        stage('Build image and deploy to dockerhub') {
            steps {
                echo 'Checking docker version'
                sh 'docker compose version'
                echo 'OK'
            }
        }
    }
}
