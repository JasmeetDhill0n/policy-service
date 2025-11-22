pipeline {
    agent any

    environment {
        SONARQUBE_ENV = credentials('sonarqube-token')     // Your Sonar token
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/JasmeetDhill0n/policy-service'
            }
        }

      stage('SonarQube Analysis') {
          steps {
              withMaven(maven: 'MAVEN3') {
                  withSonarQubeEnv('SonarQube-Local') {
                      sh """
                          mvn clean verify sonar:sonar \
                              -Dsonar.projectKey=policy-service \
                              -Dsonar.host.url=http://host.docker.internal:9001 \
                              -Dsonar.login=${SONARQUBE_ENV}
                      """
                  }
              }
          }
      }
        stage('Build Maven') {
            steps {
                withMaven(maven: 'MAVEN3') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t policy-service:latest .'
            }
        }

       stage('Docker Run') {
           steps {
               sh '''
                   docker rm -f policy-service || true
                   docker run -d -p 8085:8080 --name policy-service policy-service:latest
               '''
           }
       }


        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/deployment.yml'
                sh 'kubectl apply -f k8s/service.yml'
            }
        }
    }
}
