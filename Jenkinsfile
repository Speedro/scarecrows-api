pipeline {
    agent any
    stages {
        stage('Build image and deploy to dockerhub') {
            def composeFile: String = 'docker-compose.yml'
            steps {
                step {
                    echo 'Checking docker version'
                }
            }
        }
    }
}
