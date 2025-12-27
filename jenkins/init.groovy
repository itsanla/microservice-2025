import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import org.jenkinsci.plugins.plaincredentials.impl.*

def instance = Jenkins.getInstance()
def dockerUsername = System.getenv('DOCKER_HUB_USERNAME')
def dockerToken = System.getenv('DOCKER_HUB_TOKEN')

if (dockerUsername && dockerToken) {
    def domain = Domain.global()
    def store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()
    
    def dockerCredentials = new StringCredentialsImpl(
        CredentialsScope.GLOBAL,
        "docker-hub-token",
        "Docker Hub Token",
        Secret.fromString(dockerToken)
    )
    
    def usernameCredentials = new StringCredentialsImpl(
        CredentialsScope.GLOBAL,
        "docker-hub-username", 
        "Docker Hub Username",
        Secret.fromString(dockerUsername)
    )
    
    store.addCredentials(domain, dockerCredentials)
    store.addCredentials(domain, usernameCredentials)
    
    println "Docker Hub credentials configured successfully!"
}

instance.save()