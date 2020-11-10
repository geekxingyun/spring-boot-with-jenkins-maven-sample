pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                sh 'maven clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'maven test'
            }
        }
        stage('Deploy') {
                    steps {
                        timeout(time: 15, unit: 'MINUTES') {
                            retry(1) {
                                sh 'cp ./target/spring-boot-with-jenkins-sample-0.0.1-SNAPSHOT.jar /opt/app/spring-boot-with-jenkins-sample/dev/'
                                sh 'cp ./target/spring-boot-with-jenkins-sample-0.0.1-SNAPSHOT.jar /opt/app/spring-boot-with-jenkins-sample/test/'
                                sh 'cp ./target/spring-boot-with-jenkins-sample-0.0.1-SNAPSHOT.jar /opt/app/spring-boot-with-jenkins-sample/uat/'
                                sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /opt/app/spring-boot-with-jenkins-sample/dev/spring-boot-with-jenkins-sample-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev &'
                                sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /opt/app/spring-boot-with-jenkins-sample/test/spring-boot-with-jenkins-sample-0.0.1-SNAPSHOT.jar --spring.profiles.active=test &'
                                sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /opt/app/spring-boot-with-jenkins-sample/uat/spring-boot-with-jenkins-sample-0.0.1-SNAPSHOT.jar --spring.profiles.active=uat &'
                                sh 'jps -l'
                                sh 'echo deploy success'
                            }
                        }
                    }
        }
    }
}