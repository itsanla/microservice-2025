#!/bin/bash

# Setup Docker Registry Secret untuk Kaniko
# Ganti dengan credentials Anda

read -p "Docker Hub Username: " DOCKER_USER
read -sp "Docker Hub Password/Token: " DOCKER_PASS
echo ""
read -p "GitHub Username: " GITHUB_USER
read -sp "GitHub Token: " GITHUB_TOKEN
echo ""

# Create Docker config.json
cat > /tmp/config.json <<EOF
{
  "auths": {
    "https://index.docker.io/v1/": {
      "auth": "$(echo -n ${DOCKER_USER}:${DOCKER_PASS} | base64)"
    },
    "ghcr.io": {
      "auth": "$(echo -n ${GITHUB_USER}:${GITHUB_TOKEN} | base64)"
    }
  }
}
EOF

# Create Kubernetes secret
kubectl create secret generic docker-registry-secret \
  --from-file=config.json=/tmp/config.json \
  --dry-run=client -o yaml | kubectl apply -f -

# Cleanup
rm /tmp/config.json

echo "✅ Docker registry secret created successfully!"
