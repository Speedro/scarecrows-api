pipeline {
    agent any
    stages {
        stage('Build image and deploy to dockerhub') {
            steps {
                echo 'Checking docker version'
                step([$class: 'DockerComposeBuilder', dockerComposeFile: 'docker-compose.yml', option: [$class: 'ExecuteCommandInsideContainer', command: 'docker compose build', index: 1, privilegedMode: false, service: 'event-mnanager', workDir: '.'], useCustomDockerComposeFile: false])
            }
        }
    }
}
