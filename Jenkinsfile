pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy') {
                    steps {
                        timeout(time: 15, unit: 'MINUTES') {
                            retry(1) {
                                sh 'mkdir -p /opt/app/spring-boot-with-jenkins--maven-sample/dev/'
                                sh 'mkdir -p /opt/app/spring-boot-with-jenkins--maven-sample/test/'
                                sh 'mkdir -p /opt/app/spring-boot-with-jenkins--maven-sample/uat/'
                                sh 'cp ./target/spring-boot-with-jenkins--maven-sample-0.0.1-SNAPSHOT.jar /opt/app/spring-boot-with-jenkins--maven-sample/dev/'
                                sh 'cp ./target/spring-boot-with-jenkins--maven-sample-0.0.1-SNAPSHOT.jar /opt/app/spring-boot-with-jenkins--maven-sample/test/'
                                sh 'cp ./target/spring-boot-with-jenkins--maven-sample-0.0.1-SNAPSHOT.jar /opt/app/spring-boot-with-jenkins--maven-sample/uat/'
                                sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /opt/app/spring-boot-with-jenkins--maven-sample/dev/spring-boot-with-jenkins--maven-sample-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev &'
                                sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /opt/app/spring-boot-with-jenkins--maven-sample/test/spring-boot-with-jenkins--maven-sample-0.0.1-SNAPSHOT.jar --spring.profiles.active=test &'
                                sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /opt/app/spring-boot-with-jenkins--maven-sample/uat/spring-boot-with-jenkins--maven-sample-0.0.1-SNAPSHOT.jar --spring.profiles.active=uat &'
                                sh 'jps -l'
                                sh 'echo deploy success'
                            }
                        }
                    }
        }
    }
}