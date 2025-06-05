pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "notificationservice:${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
               git branch: 'master',
                    url: 'https://github.com/Arnold311/notificationservice.git'
            }
        }

        stage('Build') {
            steps {
                powershell './mvnw clean package'
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    powershell "docker build -t ${env.DOCKER_IMAGE} ."
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    powershell "docker-compose down"
                    powershell "docker-compose up -d --build"
                }
            }
        }
    }
}
