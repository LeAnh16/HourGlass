package no.leanh.file;

import java.io.*;
import java.util.*;

public class FileHandler {



    public Map<String, List<String>> parseCSV() throws IOException {
        ArrayList<String> lecturers = new ArrayList<>();
        ArrayList<String> classes = new ArrayList<>();
        ArrayList<String> rooms = new ArrayList<>();
        ArrayList<String> subjects = new ArrayList<>();
        ArrayList<String> subjectsToLecturers = new ArrayList<>();
        ArrayList<String> classesToSubjects = new ArrayList<>();

        ArrayList<String> titles = new ArrayList<>(Arrays.asList("subject:", "class:", "room:", "lecturer:", "subject_Lecturer:", "class_Subject:"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("data.csv")));
        String l;
        int s = 0;
        while ((l = reader.readLine()) != null) {
            boolean broken = false;
            for (String i : titles) {
                if (l.equals(i)) {
                    s++;
                    broken = true;
                    break;
                }
            }
            if (l.equals("--") || broken) {
                continue;
            }
            switch (s) {
                case 1:
                    lecturers.add(l);
                    break;
                case 2:
                    subjects.add(l);
                    break;
                case 3:
                    classes.add(l);
                    break;
                case 4:
                    rooms.add(l);
                    break;
                case 5:
                    subjectsToLecturers.add(l);
                    break;
                case 6:
                    classesToSubjects.add(l);
            }

        }
        Map<String, List<String>> map = new HashMap<>();
        map.put("lecturers", lecturers);
        map.put("subjects", subjects);
        map.put("classes", classes);
        map.put("rooms", rooms);
        map.put("subjectToLecturers", subjectsToLecturers);
        map.put("classToSubjects", classesToSubjects);
        return map;
    }
}

