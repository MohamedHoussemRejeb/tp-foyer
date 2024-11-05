pipeline {
    agent any

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
                echo 'Analyse du projet avec SonarQube...'
                withSonarQubeEnv('sq1') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=tp-foyer -Dsonar.host.url=http://localhost:9000 -Dsonar.login=squ_1b70e0ff93897e6014af3777939e45e8efd8b0de'
                }
            }
        }

        // Contrôle de la Quality Gate
        stage("Quality Gate") {
            steps {
                script {
                    timeout(time: 10, unit: 'MINUTES') {
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

        stage('Deploy to Nexus') {
            steps {
                echo 'Déploiement du projet sur Nexus...'
                // Déployer le livrable sur Nexus tout en skippant les tests
                sh 'mvn deploy -DskipTests'
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
