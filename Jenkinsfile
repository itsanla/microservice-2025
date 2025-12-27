pipeline {
    agent any
    
    tools {
        maven 'Maven-3.8'
        jdk 'JDK-17'
    }
    
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials')
        EUREKA_PORT = '8761'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build & Test') {
            parallel {
                stage('Eureka Server') {
                    steps {
                        dir('eureka') {
                            sh 'mvn clean compile test'
                        }
                    }
                }
                stage('Marketplace Services') {
                    steps {
                        script {
                            def services = ['Produk', 'Pelanggan', 'Order']
                            services.each { service ->
                                dir("Marketplace/${service}") {
                                    sh 'mvn clean compile test'
                                }
                            }
                        }
                    }
                }
                stage('Perpustakaan Services') {
                    steps {
                        script {
                            def services = ['Buku', 'anggota', 'Pengembalian', 'Peminjaman']
                            services.each { service ->
                                dir("Perpustakaan/${service}") {
                                    sh 'mvn clean compile test'
                                }
                            }
                        }
                    }
                }
            }
        }
        
        stage('Quality Gate') {
            steps {
                script {
                    sh './quality-check.sh'
                }
            }
        }
        
        stage('Package') {
            steps {
                sh './build-all.sh'
            }
        }
        
        stage('Docker Build') {
            steps {
                sh './build.sh'
            }
        }
        
        stage('Deploy to Registry') {
            when {
                branch 'main'
            }
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        sh './push.sh'
                    }
                }
            }
        }
        
        stage('Integration Tests') {
            steps {
                sh './test-integration.sh'
            }
        }
    }
    
    post {
        always {
            publishTestResults testResultsPattern: '**/target/surefire-reports/*.xml'
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/site',
                reportFiles: 'index.html',
                reportName: 'PMD Report'
            ])
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}