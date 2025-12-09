#!/bin/bash

set -e

echo "🚀 Pushing Docker images to Docker Hub..."

# Eureka
echo "Pushing eureka..."
docker push itsanla/eureka:latest

# Marketplace
echo "Pushing produk..."
docker push itsanla/produk:latest

echo "Pushing pelanggan..."
docker push itsanla/pelanggan:latest

echo "Pushing order..."
docker push itsanla/order:latest

echo "Pushing marketplace-gateway..."
docker push itsanla/marketplace-gateway:latest

# Perpustakaan
echo "Pushing buku..."
docker push itsanla/buku:1.0.0
docker push itsanla/buku:latest

echo "Pushing anggota..."
docker push itsanla/anggota:latest

echo "Pushing peminjaman..."
docker push itsanla/peminjaman:latest

echo "Pushing pengembalian..."
docker push itsanla/pengembalian:latest

echo "Pushing perpustakaan-gateway..."
docker push itsanla/perpustakaan-gateway:latest

echo "✅ All images pushed successfully!"
