import 'course.dart';

class Semester {
  late String id;
  late String name;
  late int passedCourses;
  late int failedCourses;
  late double averageGrade;
  late String displayAverageGrade;
  late double weightedAverageGrade;
  late double displayWeightedAverageGrade;
  late int ects;
  late String displayEcts;
  late int credits;
  late String displayCredits;
  late List<Course> courses;
}