package com.example.swissarmy.models;

import com.orm.SugarRecord;

/**
 * @author Jannis Thaler
 * @version 1.0.0
 * @date 16.11.2020
 */
public class Training extends SugarRecord{

    private Long id;
    private int points;
    private int best;

    public Training(){
    }

    public Training(Long id, int points, int best){
        this.id=id;
        this.points=points;
        this.best=best;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getBest() {
        return best;
    }

    public void setBest(int best) {
        this.best = best;
    }

}
