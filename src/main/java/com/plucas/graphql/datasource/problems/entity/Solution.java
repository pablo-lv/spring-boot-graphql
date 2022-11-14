package com.plucas.graphql.datasource.problems.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="solutions")
public class Solution {

    @Id
    private UUID id;

    private String content;
    private String category;
    private int voteGoodCount;
    private int voteBadCount;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getVoteGoodCount() {
        return voteGoodCount;
    }

    public void setVoteGoodCount(int voteGoodCount) {
        this.voteGoodCount = voteGoodCount;
    }

    public int getVoteBadCount() {
        return voteBadCount;
    }

    public void setVoteBadCount(int voteBadCount) {
        this.voteBadCount = voteBadCount;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
