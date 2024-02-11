def jarName     = "accountDto-service-0.0.1-SNAPSHOT.jar"
def deployPath  = "C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\my-account-service\\target"

pipeline { 
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'mvn --version'
                bat 'mvn clean package'
            }
        }

        stage('Test') {
                    steps {
                        bat 'mvn verify'
                        bat 'mvn test'
                    }
                }

        stage('kill jar') {
            steps {
                    catchError {
                              bat "TASKKILL /FI \"WINDOWTITLE eq ${jarName}\" || exit 0"
                        }
                }
            }
        stage("Deploy & Start") {
              steps {
                      bat "echo done2"
            }
        }
    }
}