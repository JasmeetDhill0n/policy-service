pipeline {
    agent any

    environment {
        AWS_ACCOUNT_ID = "639092584351"
        AWS_REGION = "us-east-2"
        ECR_REPO = "policy-service"
        IMAGE_TAG = "latest"
        ECR_URL = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO}:${IMAGE_TAG}"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Maven') {
            steps {
                bat 'mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                bat "docker build -t %ECR_URL% ."
            }
        }

        stage('AWS ECR Login') {
            steps {
                bat """
                aws ecr get-login-password --region %AWS_REGION% ^
                | docker login --username AWS --password-stdin %AWS_ACCOUNT_ID%.dkr.ecr.%AWS_REGION%.amazonaws.com
                """
            }
        }

        stage('Push to ECR') {
            steps {
                bat "docker push %ECR_URL%"
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                bat "kubectl apply -f k8s\\deployment.yml"
                bat "kubectl apply -f k8s\\service.yml"
            }
        }
    }
}
