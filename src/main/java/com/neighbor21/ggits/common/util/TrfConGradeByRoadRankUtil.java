package com.neighbor21.ggits.common.util;

/**
 * 교통혼잡도 계산
 *
 * @author : Charles Kim
 * @fileName :  TrfConGradeByRoadRankUtil
 * @since : 2023-10-25
 */
public class TrfConGradeByRoadRankUtil {


    /**
     * 정체타입 계산
     * @param roadRank
     * @param speed
     * @return
     * 1 : 원활
     * 2 : 서행(지체)
     * 3 : 정체 
     */
    public String getGrade(String roadRank, Double speed){
        String grade = "1";
        if(roadRank.equals("101")) {
            if(speed >= 0 && speed <30){
                grade = "3";
            }else if(speed >= 30 && speed <70) {
                grade = "2";
            }else {
                grade = "1";
            }
        }else if(roadRank.equals("102")){
            if(speed >= 0 && speed <30){
                grade = "3";
            }else if(speed >= 30 && speed <50) {
                grade = "2";
            }else {
                grade = "1";
            }
        }else {
            if(speed >= 0 && speed <20){
                grade = "3";
            }else if(speed >= 20 && speed <40) {
                grade = "2";
            }else {
                grade = "1";
            }
        }
        return grade;
    }
}

