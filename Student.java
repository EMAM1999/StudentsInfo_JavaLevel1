package pkgnew;


import java.util.Scanner;

/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
/**

 @author EMAM
 */
public class Student {


          public static Scanner in = new Scanner(System.in);

//                    pointers
          public static int nameRow = 0;
          public static int idRow = 1;
          public static int cityRow = 2;
          public static int mopileNoRow = 3;
          public static int emailRow = 4;
          public static int ageRow = 5;
          public static int GPARow = 6;
          public static int studentsNumber = 0;

          public static String[][] students = new String[7][100];



          public static void main(String[] args) {
                    while ( true ) {
                              System.out.println("Enter what you want : "
                                      + "\n1. ADD"
                                      + "\n2. SEARCH"
                                      + "\n3. DELETE"
                                      + "\n4. PRINT"
                                      + "\n5. STATISTICS"
                                      + "\n6. UPDATE_GPA");
                              int num = in.nextInt();
                              switch ( num ) {
                                        default:
                                                  System.out.println("Invalid input :(");
                                                  continue;
                                        case 1:
                                                  add();
                                                  continue;
                                        case 2:
                                                  if ( studentsNumber == 0 ) {
                                                            System.out.println("No students yet");
                                                  } else {
                                                            search();
                                                  }
                                                  continue;
                                        case 3:
                                                  if ( studentsNumber == 0 ) {
                                                            System.out.println("No students yet");
                                                  } else {
                                                            delete();
                                                  }
                                                  continue;
                                        case 4:
                                                  if ( studentsNumber == 0 ) {
                                                            System.out.println("No students yet");
                                                  } else {
                                                            printSorted();
                                                  }
                                                  continue;
                                        case 5:
                                                  if ( studentsNumber == 0 ) {
                                                            System.out.println("No students yet");
                                                  } else {
                                                            top5();
                                                            statistics();
                                                  }
                                                  continue;

                                        case 6: if ( studentsNumber == 0 ) {
                                                            System.out.println("No students yet");
                                                  } else {
                                                            update();
                                                  }
                                                  continue;
                                        case 0:
                                                  System.out.println("Good Bye :)");
                              }
                              break;
                    }
          }



          public static void add() {
                    System.out.println("Enter the name : ");
                    String name = in.next().trim().toLowerCase();
                    System.out.println("Enter the ID : ");
                    String id = in.next().trim().toLowerCase();
                    while ( true ) {
                              if ( search(id) != -1 ) {
                                        System.out.println("This id is already exist "
                                                + "\ndo you want to try again (Y/N) :");
                                        String answer = in.next().trim().toLowerCase();
                                        switch ( answer ) {
                                                  case "yes":
                                                  case "y":
                                                            continue;
                                                  case "no":
                                                  case "n":
                                                            return;
                                        }
                              }
                              break;
                    }
                    System.out.println("Enter the city : ");
                    String city = in.next().trim().toLowerCase();
                    System.out.println("Enter the mopile number : ");
                    String number = in.next().trim().toLowerCase();
                    System.out.println("Enter the email : ");
                    String email = in.next().trim().toLowerCase();
                    String age;
                    while ( true ) {
                              System.out.println("Enter the age : ");
                              age = in.next().trim().toLowerCase();
                              if ( !isNumber(age) ) {
                                        System.out.println("invalid number "
                                                + "\ndo you want to try again (Y/N) :");
                                        String answer = in.next().trim().toLowerCase();
                                        switch ( answer ) {
                                                  case "yes":
                                                  case "y": continue;
                                                  default: return;
                                        }
                              }
                              break;
                    }
                    String GPA;
                    while ( true ) {
                              System.out.println("Enter the GPA : ");
                              GPA = in.next().trim().toLowerCase();
                              if ( !isNumber(GPA) ) {
                                        System.out.println("invalid number "
                                                + "\ndo you want to try again (Y/N) :");
                                        String answer = in.next().trim().toLowerCase();
                                        switch ( answer ) {
                                                  case "yes":
                                                  case "y": continue;
                                                  default: return;
                                        }
                              }
                              break;
                    }
                    students[nameRow][studentsNumber] = name;
                    students[idRow][studentsNumber] = id;
                    students[cityRow][studentsNumber] = city;
                    students[mopileNoRow][studentsNumber] = number;
                    students[emailRow][studentsNumber] = email;
                    students[ageRow][studentsNumber] = age;
                    students[GPARow][studentsNumber] = GPA;
                    System.out.println("student " + name + " (" + id + ") " + "was added successfuly");
                    studentsNumber++;
          }



          public static void avgGPA() {
                    double sum = 0;
                    for ( int i = 0 ; i < studentsNumber ; i++ ) {
                              sum += Double.parseDouble(students[GPARow][i]);
                    }
                    System.out.println("The average GPA is " + sum / studentsNumber);

          }



          public static void maxGPA() {
                    double max = Double.parseDouble(students[GPARow][0]);
                    int index = 0;
                    for ( int i = 0 ; i < studentsNumber ; i++ ) {
                              double n = Double.parseDouble(students[GPARow][i]);
                              if ( n > max ) {
                                        max = n;
                                        index = i;
                              }
                    }
                    System.out.println("The maximun GPA is " + max + " belongs to " + students[nameRow][index]);
          }



          public static void minGPA() {
                    double min = Double.parseDouble(students[GPARow][0]);
                    int index = 0;
                    for ( int i = 0 ; i < studentsNumber ; i++ ) {
                              double n = Double.parseDouble(students[GPARow][i]);
                              if ( n < min ) {
                                        min = n;
                                        index = i;
                              }
                    }
                    System.out.println("The minimun GPA is " + min + " belongs to " + students[nameRow][index]);
          }



          public static void delete() {
                    System.out.println("Enter the id : ");
                    String id = in.next();
                    int index = search(id);
                    if ( index == -1 ) {
                              System.out.println("There is no id " + id);
                    } else {
                              delete(index);
                              System.out.println("The id " + id + " was deleted successfully");
                    }
          }



          public static void printSorted() {
                    sort();
                    print(0 , studentsNumber);
          }



          public static void search() {
                    System.out.println("Enter the id : ");
                    String id = in.next().trim().toLowerCase();
                    int index = search(id);
                    if ( index == -1 ) {
                              System.out.println("There is no id " + id);
                    } else {
                              print(index , index + 1);
                    }
          }



          public static void sort() {
                    for ( int i = 0 ; i < studentsNumber ; i++ ) {
                              double ii = Double.parseDouble(students[GPARow][i]);
                              for ( int j = 0 ; j < studentsNumber ; j++ ) {
                                        double jj = Double.parseDouble(students[GPARow][j]);
                                        if ( ii < jj ) {
                                                  swap(i , j);
                                                  ii = Double.parseDouble(students[GPARow][i]);
                                        }
                              }
                    }
          }



          public static void top5() {
                    sort();
                    print(0 , 5);
          }



          public static void update() {
                    System.out.println("Enter the id : ");
                    String id = in.next().trim().toLowerCase();
                    int index = search(id);
                    if ( index == -1 ) {
                              System.out.println("There is no id " + id);
                    } else {
                              System.out.println("The old GPA is : " + students[GPARow][index]);
                              String GPA;
                              while ( true ) {
                                        System.out.println("Enter The new GPA : ");
                                        GPA = in.next().trim().toLowerCase();
                                        if ( !isNumber(GPA) ) {
                                                  System.out.println("invalid number "
                                                          + "\ndo you want to try again (Y/N) :");
                                                  String answer = in.next().trim().toLowerCase();
                                                  switch ( answer ) {
                                                            case "yes":
                                                            case "y": continue;
                                                            default: return;
                                                  }
                                        }
                                        break;
                              }
                              students[GPARow][index] = GPA;
                              System.out.println("The GPA " + GPA + " was updated successfully");
                    }
          }



          public static int search(String id) {
                    for ( int i = 0 ; i < studentsNumber ; i++ ) {
                              if ( students[idRow][i].equals(id) ) {
                                        return i;
                              }
                    }
                    return -1;
          }



          public static boolean isNumber(String str) {
                    for ( int i = 0 ; i < str.length() ; i++ ) {
                              switch ( str.charAt(i) ) {
                                        case '0':
                                        case '1':
                                        case '2':
                                        case '3':
                                        case '4':
                                        case '5':
                                        case '6':
                                        case '7':
                                        case '8':
                                        case '9':
                                        case '.': break;
                                        default: return false;
                              }
                    }
                    return true;
          }



          public static void statistics() {
                    maxGPA();
                    minGPA();
                    avgGPA();
          }



          public static void delete(int i) {
                    for ( ; i < studentsNumber - 1 ; i++ ) {
                              students[nameRow][i] = students[nameRow][i + 1];
                              students[idRow][i] = students[idRow][i + 1];
                              students[cityRow][i] = students[cityRow][i + 1];
                              students[mopileNoRow][i] = students[mopileNoRow][i + 1];
                              students[emailRow][i] = students[emailRow][i + 1];
                              students[ageRow][i] = students[ageRow][i + 1];
                              students[GPARow][i] = students[GPARow][i + 1];
                    }
                    studentsNumber--;
          }



          private static void print(int first , int last) {
                    System.out.println("===== ===== =====");
                    for ( int i = first ; i < last ; i++ ) {
                              System.out.println("name : " + students[nameRow][i]);
                              System.out.println("ID : " + students[idRow][i]);
                              System.out.println("Contact informations");
                              System.out.println("\tCity : " + students[cityRow][i]);
                              System.out.println("\tMopile number : " + students[mopileNoRow][i]);
                              System.out.println("\te-mail : " + students[emailRow][i]);
                              System.out.println("age : " + students[ageRow][i] + " years");
                              System.out.println("GPA : " + students[GPARow][i]);
                              System.out.println("===== ===== =====");
                    }
          }



          private static void swap(int i , int j) {
                    String tem = students[nameRow][i];
                    students[nameRow][i] = students[nameRow][j];
                    students[nameRow][j] = tem;

                    tem = students[idRow][i];
                    students[idRow][i] = students[idRow][j];
                    students[idRow][j] = tem;

                    tem = students[cityRow][i];
                    students[cityRow][i] = students[cityRow][j];
                    students[cityRow][j] = tem;

                    tem = students[mopileNoRow][i];
                    students[mopileNoRow][i] = students[mopileNoRow][j];
                    students[mopileNoRow][j] = tem;

                    tem = students[emailRow][i];
                    students[emailRow][i] = students[emailRow][j];
                    students[emailRow][j] = tem;

                    tem = students[ageRow][i];
                    students[ageRow][i] = students[ageRow][j];
                    students[ageRow][j] = tem;

                    tem = students[GPARow][i];
                    students[GPARow][i] = students[GPARow][j];
                    students[GPARow][j] = tem;
          }

}
