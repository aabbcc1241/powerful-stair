package edu.hkcc.personalkcalmanagerhkcc.database.weekrecord;

import java.util.ArrayList;
import java.util.List;

import edu.hkcc.personalkcalmanagerhkcc.MainActivity;
import edu.hkcc.personalkcalmanagerhkcc.database.stairrecord.StairRecord;

/**
 * Created by beenotung on 2/28/15.
 */
public class WeekAchievement {
    private final MainActivity mainActivity;

    public WeekAchievement(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
    public  List<WeekAchievement> getAll(){
        List<WeekAchievement> result=new ArrayList<WeekAchievement>();

        List<WeekRecord> weekRecords = mainActivity.myDAO.weekRecordDAOItem.getAll();
        List<StairRecord> stairRecords;

        for(WeekRecord weekRecord:weekRecords){
            stairRecords=mainActivity.myDAO.stairRecordDAOItem.getAllInSameWeek(weekRecord.weekId);
        }

        a=mainActivity.myDAO.stairRecordDAOItem.getAllInSameWeek()

        return result;
    }
}
