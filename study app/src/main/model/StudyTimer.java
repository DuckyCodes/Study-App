//package model;
//
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//
//public class StudyTimer {
//    private int studyTimeInSeconds;
//    private ScheduledExecutorService timer;
//
//    public StudyTimer() {
//        studyTimeInSeconds = 0;
//        timer = Executors.newSingleThreadScheduledExecutor();
//    }
//
//    public void start() {
//        // Schedule a task to increment study time every second
//        timer.scheduleAtFixedRate(() -> {
//            studyTimeInSeconds++;
//            System.out.println("Studying for: " + formatTime(studyTimeInSeconds));
//        }, 1, 1, TimeUnit.SECONDS);
//    }
//
//    //Effects: Stop the timer and print the final study time
//    public void stop() {
//
//        timer.shutdown();
//        System.out.println("Total study time: " + formatTime(studyTimeInSeconds));
//    }
//
//    public double getStudyTimeInMinutes() {
//        return studyTimeInSeconds / 60.0;
//    }
//
//    // Helper method to format time in HH:MM:SS
//    private String formatTime(int seconds) {
//        int hours = seconds / 3600;
//        int minutes = (seconds % 3600) / 60;
//        int remainingSeconds = seconds % 60;
//        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
//    }
//}
