package com.example.alpha;

import java.util.Date;

public class f_matchlive {
    public f_matchlive(int id, int comp_id, String formatted_date, String season, String venue, String venueid, String timel, String local_team_id, String local_team_name, String local_team_score, String visitor_team_id, String visitor_team_name, String visitor_team_score, String full_time, String half_time) {
        this.id = id;
        this.comp_id = comp_id;
        this.formatted_date = formatted_date;
        Season = season;
        Venue = venue;
        Venueid = venueid;
        this.timel = timel;
        this.local_team_id = local_team_id;
        this.local_team_name = local_team_name;
        this.local_team_score = local_team_score;
        this.visitor_team_id = visitor_team_id;
        this.visitor_team_name = visitor_team_name;
        this.visitor_team_score = visitor_team_score;
        this.full_time = full_time;
        this.half_time = half_time;

    }

    public f_matchlive(String local_team_name, String visitor_team_name, String full_time,String penalty,String yellow,String red,String possession,String fauls,String corner) {
        this.local_team_name = local_team_name;
        this.visitor_team_name = visitor_team_name;
        this.full_time = full_time;
        this.corner=corner;
        this.penalty=penalty;
        this.possession=possession;
        this.yellow=yellow;
        this.red=red;
        this.fauls=fauls;
    }

    public int getId() {
        return id;
    }

    public int getComp_id() {
        return comp_id;
    }

    public String getFormatted_date() {
        return formatted_date;
    }

    public String getSeason() {
        return Season;
    }

    public String getVenue() {
        return Venue;
    }

    public String getVenueid() {
        return Venueid;
    }

    public String getTimel() {
        return timel;
    }

    public String getLocal_team_id() {
        return local_team_id;
    }

    public String getLocal_team_name() {
        return local_team_name;
    }

    public String getLocal_team_score() {
        return local_team_score;
    }

    public String getVisitor_team_id() {
        return visitor_team_id;
    }

    public String getVisitor_team_name() {
        return visitor_team_name;
    }

    public String getVisitor_team_score() {
        return visitor_team_score;
    }

    public String getFull_time() {
        return full_time;
    }

    public String getHalf_time() {
        return half_time;
    }



    int id;
    int comp_id;
    String formatted_date;
    String Season;
    String Venue;
    String Venueid;
    String timel;
    String local_team_id;
    String local_team_name;
    String local_team_score;
    String visitor_team_id;
    String visitor_team_name;
    String visitor_team_score;
    String full_time;
    String half_time;
    String penalty;

    String yellow;
    String red;
    String corner;
    String fauls;
    String possession;

}
