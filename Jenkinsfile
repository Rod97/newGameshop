  
pipeline {
    agent any
 //triggers {pollSCM('* * * * *')}
  stages {
    stage('Checkout') {
      steps {
        // Get some code from a GitHub repository
        git ' https://github.com/Rod97/newGameshop.git'
      }
    }
        stage('Build') {
      steps {
        sh 'chmod a+x mvnw'
        sh './mvnw clean package -DskipTests=true'
      }

          post {
        always {
          archiveArtifacts 'target/*.jar'
        }
          }
        }
        stage('DockerBuild') {
      steps {
        sh 'docker build -t rod97/newgameshop:latest .'
      }
        }
  }
}
