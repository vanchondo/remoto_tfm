#!groovy
pipeline {
    agent any
    environment {
        CREDENTIALS = credentials('docker-registry-credentials')
        app_name = 'remoto-tfm'
        version = "0.${BUILD_NUMBER}"
    }
    stages {  
        stage('Gradle Build') {
            steps {
                sh './gradlew clean build'
            }
        }             
        stage('Docker Build') {
            steps {
                sh 'docker image build --build-arg secret_key=${JASYPT_SECRET_KEY} -t $app_name:${version} .'
                sh 'docker image tag $app_name:${version} ${REGISTRY_SERVER}/$app_name'
            }
        }
        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-registry-credentials', usernameVariable: 'CREDENTIALS_USERNAME', passwordVariable: 'CREDENTIALS_PASSWORD')]) {
                    sh 'echo $CREDENTIALS_PASSWORD |  docker login -u ${CREDENTIALS_USERNAME} --password-stdin ${REGISTRY_URL}'  
                    sh 'docker push ${REGISTRY_SERVER}/$app_name'
                }
            }
        } 
    }
    post {
        always {
            sh 'docker logout'
            sh 'docker system prune -af'
        }
    }
}