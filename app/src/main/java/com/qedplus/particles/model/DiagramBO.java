package com.qedplus.particles.model;

public class DiagramBO {
    String diagramResourceId;
    String diagramCaption;

    public DiagramBO(String diagramResourceId, String diagramCaption) {
        this.diagramResourceId = diagramResourceId;
        this.diagramCaption = diagramCaption;
    }

    public String getDiagramResourceId() {
        return diagramResourceId;
    }

    public void setDiagramResourceId(String diagramResourceId) {
        this.diagramResourceId = diagramResourceId;
    }

    public String getDiagramCaption() {
        return diagramCaption;
    }

    public void setDiagramCaption(String diagramCaption) {
        this.diagramCaption = diagramCaption;
    }

    @Override
    public String toString() {
        return "DiagramBO{" +
                "diagramResourceId='" + diagramResourceId + '\'' +
                ", diagramCaption='" + diagramCaption + '\'' +
                '}';
    }
}
