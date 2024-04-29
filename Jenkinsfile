pipeline {
    environment {
        registry = "jaouherbelhadj/devops_validation"
        registryCredential = 'dockerhub'
        dockerImage = ''

        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCAL = "http"
        NEXUS_URL = "http://localhost:8081"
        NEXUS_REPOSITORY = "nexus-repo-devops"
        NEXUS_CREDENTIAL_ID = "nexus-user-creds"
    }
    agent any
    stages {

        stage('MVN CLEAN & INSTALL'){
            steps {
                sh 'mvn clean'
                sh 'mvn install'
            }
        }
        stage('ARTIFACT CONSTRUCTION') {
            steps {
                echo 'ARTIFACT CONSTRUCTION...'
                sh 'mvn package -Dmaven.test.skip=true'
            }
        }
        stage('TEST') {
            steps {
                sh 'mvn test'
            }
        }
        stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=administrator'
            }
        }
         stage('Publish to nexus'){
           steps{
                sh 'mvn deploy -DrepositoryId=${NEXUS_CREDENTIAL_ID} -Drepository.url=${NEXUS_URL}/repository/${NEXUS_REPOSITORY}'
           }
        }
        stage('Docker Compose') {
            steps {
                script {
                    sh 'docker-compose build'
                    def gitHash = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                    def gitHashTaggedImage = "jaouherbelhadj/devops_validation:${env.BUILD_NUMBER}"
                    sh "docker tag springboot-app $gitHashTaggedImage"

                    docker.withRegistry('', registryCredential) {
                        sh "docker push $gitHashTaggedImage"
                    }
                }
            }
        }
    }
}
