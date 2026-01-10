#!/bin/bash

cd jenkins

echo "🔨 Building custom Jenkins image with Docker CLI..."
docker build -t itsanla/jenkins-docker:latest .

echo "📤 Pushing to Docker Hub..."
docker push itsanla/jenkins-docker:latest

echo "✅ Done! Now apply the Kubernetes deployment:"
echo "   kubectl apply -f ../kubernetes-cluster/jenkins.yaml"
echo "   kubectl rollout restart deployment jenkins"
