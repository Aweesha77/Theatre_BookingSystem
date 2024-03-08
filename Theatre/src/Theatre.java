import java.io.*;
import java.util.*;
class Theatre {
    private static final int ROW1 = 12;                  //create the variables only once by using private static final.
    private static final int ROW2 = 16;                  //https://stackoverflow.com/questions/1415955/private-final-static-attribute-vs-private-final-attribute
    private static final int ROW3 = 20;
    private static final int[] row_1 = new int[ROW1];    //create arrays called row_1,row_2,row_3 and make them final because seats are not changing in the program.
    private static final int[] row_2 = new int[ROW2];
    private static final int[] row_3 = new int[ROW3];


 