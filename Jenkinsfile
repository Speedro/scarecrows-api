pipeline {
    agent any

    def composeFile = './docker-compose.yml'

    stages {
        stage('Build image and deploy to dockerhub') {
            steps {
                sh 'docker compose build .'
            }
        }
    }
}
