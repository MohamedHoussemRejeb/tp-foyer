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
                    sh 'mvn sonar:sonar -Dsonar.projectKey=tp-foyer -Dsonar.host.url=http://localhost:9000 -Dsonar.login=squ_eb2921c0eb4985ea0933ed9e5e9f4ad1487b554d'
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                echo 'Déploiement du projet sur Nexus...'
                // Déployer le livrable sur Nexus 
nexusArtifactUploader artifacts: [[artifactId: 'tp-foyer', classifier: '', file: 'target/tp-foyer-5.0.0.war', type: 'war']], credentialsId: 'nexus3', groupId: 'tn.esprit', nexusUrl: '111.111.111.125:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'tpfoyer-release', version: '5.0.0'
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
