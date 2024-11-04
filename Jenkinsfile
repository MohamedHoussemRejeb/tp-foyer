pipeline {
    agent any // Utilise n'importe quel agent disponible

    stages {
        // Étape de clonage du dépôt Git
        stage('Checkout') {
            steps {
                echo 'Clonage du dépôt Git...'
                // Clonage du dépôt depuis GitHub
                git url: 'https://github.com/MohamedHoussemRejeb/tp-foyer.git', branch: 'main'
            }
        }

        // Étape de construction du projet
        stage('Build') {
            steps {
                echo 'Construction du projet avec Maven...'
                // Commande Maven pour nettoyer et construire le projet
                sh 'mvn clean install'
            }
        }

        // Étape de tests
        stage('Test') {
            steps {
                echo 'Exécution des tests...'
                // Commande Maven pour exécuter les tests
                sh 'mvn test'
            }
        }

        // Étape de packaging
        stage('Package') {
            steps {
                echo 'Packaging du projet...'
                // Commande Maven pour empaqueter le projet
                sh 'mvn package'
            }
        }

        // Étape de déploiement
        stage('Deploy') {
            steps {
                echo 'Déploiement du projet...'
                // Ajouter ici les étapes de déploiement si nécessaires, ex: copier des fichiers de package
                // Exemple : sh 'cp target/mon-projet.war /path/vers/le/deploiement/'
            }
        }
    }

    // Optionnel : ajouter des étapes de post-build
    post {
        success {
            echo 'Le pipeline s\'est exécuté avec succès!'
        }
        failure {
            echo 'Le pipeline a échoué!'
        }
    }
}
