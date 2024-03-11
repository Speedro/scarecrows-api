pipeline {
    agent any
    stages {
        stage('Hello') {
            steps {
               dockerComposeBuilder(true, 'docker-compose.yml')
            }
        }
    }
}
