pipeline{
    agent any
    stages{
        stage('Checkout'){
            steps{
                git 'https://github.com/Rod97/game-shop.git'
            }
        }
        stage('Build'){
            steps{
                sh 'chmod a+x mvnw'
                sh './mvnw clean package -DskipTests=true'
            }

            post{
                always{
                    archiveArtifacts 'target/*.jar'
                }
            }
            }
            stage('DockerBuild'){
                steps{
                    sh 'docker build -t Rod97/game-shop/GameShopv2/GameShop:latest .'
                }
            }
        }
    }