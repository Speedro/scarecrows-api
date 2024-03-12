pipeline {
    agent any
    stages {
        stage('Build image and deploy to dockerhub') {
            steps {
                echo 'Checking docker version'
                step([$class: 'DockerComposeBuilder', dockerComposeFile: 'docker-compose.yml', option: [], useCustomDockerComposeFile: false])
            }
        }
    }
}
