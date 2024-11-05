pipeline {
    agent any

    environment {
        // Variables d'environnement pour SonarQube
        SONARQUBE_SERVER = 'sq1' // Nom du serveur SonarQube configuré dans Jenkins
        SONAR_TOKEN = 'squ_1b70e0ff93897e6014af3777939e45e8efd8b0de' // Jeton d'authentification SonarQube
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Clonage du dépôt Git...'
                git url: 'https://github.com/MohamedHoussemRejeb/tp-foyer.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                echo 'Construction du projet avec Maven...'
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Exécution des tests...'
                sh 'mvn test'
            }
        }

        // Étape d'analyse SonarQube
        stage('SonarQube Analysis') {
            steps {
                script {
                    // Récupération de l'outil SonarQube Scanner
                    def scannerHome = tool 'SonarQube Scanner'
                    withSonarQubeEnv(SONARQUBE_SERVER) {
                        sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=tp-foyer -Dsonar.sources=. -Dsonar.login=${SONAR_TOKEN}"
                    }
                }
            }
        }

        // Contrôle de la Quality Gate
        stage("Quality Gate") {
            steps {
                script {
                    timeout(time: 5, unit: 'MINUTES') {
                        waitForQualityGate abortPipeline: true
                    }
                }
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging du projet...'
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Déploiement du projet...'
                // Ajouter ici les étapes de déploiement si nécessaires
            }
        }
    }

    post {
        success {
            echo 'Le pipeline s\'est exécuté avec succès!'
        }
        failure {
            echo 'Le pipeline a échoué!'
        }
    }
}
