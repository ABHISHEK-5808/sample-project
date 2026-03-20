package com.sociollens.model;

/**
 * Represents the recommendation result for a specific image, including its computed attention scores.
 */
public class RecommendationResult {
    private Image image;
    private double finalScore;
    private double attentionLevel1;
    private double attentionLevel2;

    public RecommendationResult() {}

    public RecommendationResult(Image image, double finalScore, double attentionLevel1, double attentionLevel2) {
        this.image = image;
        this.finalScore = finalScore;
        this.attentionLevel1 = attentionLevel1;
        this.attentionLevel2 = attentionLevel2;
    }

    // Getters and Setters
    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }

    public double getFinalScore() { return finalScore; }
    public void setFinalScore(double finalScore) { this.finalScore = finalScore; }

    public double getAttentionLevel1() { return attentionLevel1; }
    public void setAttentionLevel1(double attentionLevel1) { this.attentionLevel1 = attentionLevel1; }

    public double getAttentionLevel2() { return attentionLevel2; }
    public void setAttentionLevel2(double attentionLevel2) { this.attentionLevel2 = attentionLevel2; }
}
