pipeline {
    agent any

    environment {
        AWS_ACCOUNT_ID = "639092584351"
        AWS_REGION = "us-east-2"
        ECR_REPO = "policy-service"
        IMAGE_TAG = "latest"
    }

    stages {

        stage('Clone Repo') {
            steps {
                checkout scm
            }
        }

        stage('Build Maven') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh """
                    docker build -t $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPO:$IMAGE_TAG .
                """
            }
        }

        stage('Login to ECR') {
            steps {
                sh """
                    aws ecr get-login-password --region $AWS_REGION \
                    | docker login --username AWS --password-stdin \
                      $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com
                """
            }
        }

        stage('Push Image to ECR') {
            steps {
                sh """
                    docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPO:$IMAGE_TAG
                """
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh """
                    kubectl apply -f k8s/deployment.yml
                    kubectl apply -f k8s/service.yml
                """
            }
        }
    }
}
