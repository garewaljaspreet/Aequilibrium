package com.example.navde.myapplication;

/**
 * @author jaspreet
 * This is model class for Player info.
 * We can get info about player from input fields entered by user and then save it using this model class
 * We'll be using Arraylist of BeansPalyer type to play with Player info
 */

public class BeansPlayer {

    // We'll be calling this constructor to add player info
    public BeansPlayer(String name,String type,int strength,int speed,int intelligence, int endurance, int firepower,int rank, int courage,int skill)
    {
        this.name=name;
        this.type=type;
        this.speed=speed;
        this.courage=courage;
        this.strength=strength;
        this.intelligence=intelligence;
        this.endurance=endurance;
        this.firepower=firepower;
        this.rank=rank;
        this.skill=skill;
        this.result=1;// we set def result to 1 to calculate survivors in losing team. At end we can check all player having 1 as result to get info about survivor. Loser's will have 0 for this.
    }

    String name,type;
    int strength;
    int intelligence;
    int speed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getCourage() {
        return courage;
    }

    public void setCourage(int courage) {
        this.courage = courage;
    }

    public int getFirepower() {
        return firepower;
    }

    public void setFirepower(int firepower) {
        this.firepower = firepower;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    int endurance;
    int rank;
    int courage;
    int firepower;
    int skill;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    int result;
}