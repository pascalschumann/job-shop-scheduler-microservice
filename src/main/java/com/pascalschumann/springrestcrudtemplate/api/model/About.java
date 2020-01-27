package com.pascalschumann.springrestcrudtemplate.api.model;

/**
 * Holds information about the service
 */
public class About {
    private String artifactId = null;
    private String artifactGroup = null;
    private String serviceName = null;
    private String serviceVersion = null;
    private String buildTime = null;
    private String javaVersion = null;
    private String[] springProfiles = null;

    public String[] getSpringProfiles() {
        return springProfiles;
    }

    public void setSpringProfiles(final String[] springProfiles) {
        this.springProfiles = springProfiles;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(final String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(final String artifactId) {
        this.artifactId = artifactId;
    }

    public String getArtifactGroup() {
        return artifactGroup;
    }

    public void setArtifactGroup(final String artifactGroup) {
        this.artifactGroup = artifactGroup;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(final String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(final String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(final String buildTime) {
        this.buildTime = buildTime;
    }
}
