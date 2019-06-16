package com.qedplus.particles.model;

public class RevisionPointBO {
    private String greeting;
    private String thePoint;

    public RevisionPointBO(String greeting, String thePoint) {
        this.greeting = greeting;
        this.thePoint = thePoint;
    }

    public String getGreeting() {
        return greeting;
    }

    public String getThePoint() {
        return thePoint;
    }

    @Override
    public String toString() {
        return "RevisionPointBO{" +
                "greeting='" + greeting + '\'' +
                ", thePoint='" + thePoint + '\'' +
                '}';
    }
}
