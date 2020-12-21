package com.classy.class_2021a_and_8;

public class Football {

    private String teamA = "";
    private String teamB = "";
    private int scoreA = 0;
    private int scoreB = 0;
    private int gameDurationSec = 5400;

    public Football() { }

    public Football(String teamA, String teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public String getTeamA() {
        return teamA;
    }

    public Football setTeamA(String teamA) {
        this.teamA = teamA;
        return this;
    }

    public String getTeamB() {
        return teamB;
    }

    public Football setTeamB(String teamB) {
        this.teamB = teamB;
        return this;
    }

    public int getScoreA() {
        return scoreA;
    }

    public Football setScoreA(int scoreA) {
        this.scoreA = scoreA;
        return this;
    }

    public int getScoreB() {
        return scoreB;
    }

    public Football setScoreB(int scoreB) {
        this.scoreB = scoreB;
        return this;
    }

    public int getGameDurationSec() {
        return gameDurationSec;
    }

    public Football setGameDurationSec(int gameDurationSec) {
        this.gameDurationSec = gameDurationSec;
        return this;
    }
}
