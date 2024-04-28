pipeline {
    environment {
      registry  = "eya/devops_esprit"
      registryCredential = 'dockerHub'
      NEXUS_VERSION = "nexus3"
      NEXUS_PROTOCOL = "http"
      NEXUS_URL="172.16.1.251:8081"
      NEXUS_REPOSITORY = "maven_nexus_repo"
      NEXUS_CREDENTIAL_ID = "nexus-user-credentials"
      dockerImage = ''
}
    agent any
    stages {
         stage('MVN CLEAN && MVN INSTALL'){
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
   
            stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
        }
        
  stage('publish to nexus'){

           steps{

              sh 'mvn deploy -DrepositoryId=${NEXUS_CREDENTIAL_ID} -Drepository.url=${NEXUS_URL}/repository/${NEXUS_REPOSITORY}'

            }

        }
      
  stage('Docker Compose') {
            steps {
                script {
                    // Run Docker Compose
                    sh 'docker-compose build'
                    def gitHash = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                    def gitHashTaggedImage = "azyzdarragi/devops_esprit:${env.BUILD_NUMBER}"
                    sh "docker tag springboot-app $gitHashTaggedImage"
                    // Push the images to Docker Hub
                    docker.withRegistry('', 'dockerHub') {
                        sh "docker push $gitHashTaggedImage"
                }
                }
            }
        }     
}}
