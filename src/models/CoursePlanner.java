package models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import dao.CourseDAO;
import dao.StudiesDAO;
import dao.TranscriptDAO;
import dao.MajorDAO;

public class CoursePlanner {
    private final Connection conn;

    public CoursePlanner(Connection conn) {
        this.conn = conn;
    }

    public List<String[]> generatePlan(String perm) throws SQLException {
        StudiesDAO studiesDAO = new StudiesDAO(conn);
        TranscriptDAO transcriptDAO = new TranscriptDAO(conn);
        CourseDAO courseDAO = new CourseDAO(conn);
        MajorDAO majorDAO = new MajorDAO(conn);
    
        List<String[]> plan = new ArrayList<>();
        Set<String> completed = new HashSet<>();
        for (String[] record : transcriptDAO.getAllTranscriptRecords(perm)) {
            completed.add(record[2]);
        }
    
        int majorId = studiesDAO.getStudentMajorId(perm);
        List<String> neededCourses = new ArrayList<>();
        Map<String, String> titles = new HashMap<>();
    
        for (String courseInfo : studiesDAO.getMissingRequiredCourses(perm)) {
            String[] split = courseInfo.split(" \\(");
            if (split.length == 2) {
                String courseNo = split[1].replace(")", "");
                neededCourses.add(courseNo);
                titles.put(courseNo, split[0]);
            }
        }
    
        int electivesLeft = studiesDAO.getRemainingElectives(perm);
        for (String elective : majorDAO.getElectiveOptions(majorId)) {
            if (!completed.contains(elective) && electivesLeft > 0) {
                neededCourses.add(elective);
                titles.put(elective, courseDAO.getCourseTitle(elective));
                electivesLeft--;
            }
        }
    
        String[] quarters = {"Fall 2025", "Winter 2026", "Spring 2026"};
        int maxPerQuarter = 3;
    
        for (String quarterLabel : quarters) {
            String quarterCode = quarterLabel.contains("Fall") ? "F" :
                                 quarterLabel.contains("Winter") ? "W" : "S";
    
            Iterator<String> iter = neededCourses.iterator();
            int filled = 0;
    
            while (iter.hasNext() && filled < maxPerQuarter) {
                String course = iter.next();
                List<String[]> offerings = courseDAO.getCourseOfferings(course);
    
                for (String[] offering : offerings) {
                    String offerQ = offering[1];
                    if (offerQ.equals(quarterCode)) {
                        String type = majorDAO.getMustTakeCourses(majorId).contains(course) ? "Required" : "Elective";
                        plan.add(new String[]{course, titles.get(course), type, quarterLabel});
                        iter.remove();
                        filled++;
                        break;
                    }
                }
            }
        }
    
        return plan;
    }
    
    
}