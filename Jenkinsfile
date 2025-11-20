stage('Deploy to EKS') {
    steps {
        script {
            sh """
            sed -i 's#image: .*#image: ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO_NAME}:${IMAGE_TAG}#' k8s/deployment.yml

            kubectl apply -f k8s/deployment.yml
            kubectl apply -f k8s/service.yml
            """
        }
    }
}


