#!/bin/bash

set -e

echo "🔨 Building Docker images..."

# Eureka
echo "Building eureka..."
docker build -t itsanla/eureka:latest ./eureka

# Marketplace
echo "Building produk..."
docker build -t itsanla/produk:latest ./Marketplace/Produk

echo "Building pelanggan..."
docker build -t itsanla/pelanggan:latest ./Marketplace/Pelanggan

echo "Building order..."
docker build -t itsanla/order:latest ./Marketplace/Order

echo "Building marketplace-gateway..."
docker build -t itsanla/marketplace-gateway:latest ./Marketplace/api-gateway

# Perpustakaan
echo "Building buku..."
docker build -t itsanla/buku:1.0.0 -t itsanla/buku:latest ./Perpustakaan/Buku

echo "Building anggota..."
docker build -t itsanla/anggota:latest ./Perpustakaan/anggota

echo "Building peminjaman..."
docker build -t itsanla/peminjaman:latest ./Perpustakaan/Peminjaman

echo "Building pengembalian..."
docker build -t itsanla/pengembalian:latest ./Perpustakaan/Pengembalian

echo "Building perpustakaan-gateway..."
docker build -t itsanla/perpustakaan-gateway:latest ./Perpustakaan/api-gateway

echo "✅ All images built successfully!"
