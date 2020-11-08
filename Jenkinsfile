pipeline {
    //agent { docker 'maven:3.3.3' }
    agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
                sh 'echo "Hello World"'
                sh 'mvn clean package'
                sh 'nohup java -jar ./target/spring-boot-with-jenkins-maven-sample-0.0.1-SNAPSHOT.jar &'
            }
        }
    }
}