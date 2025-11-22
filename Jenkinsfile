pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/JasmeetDhill0n/policy-service'
            }
        }

        stage('Build Maven') {
            steps {
                withMaven(maven: 'MAVEN3') {
                    sh "mvn clean package -DskipTests"
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
                sh 'docker run -d -p 8080:8080 --name policy-service policy-service:latest || true'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/deployment.yaml'
                sh 'kubectl apply -f k8s/service.yaml'
            }
        }
    }
}
