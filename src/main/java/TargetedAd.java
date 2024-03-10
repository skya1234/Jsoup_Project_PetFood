/*
 * Problem 2 Sell My Pet Food
 */
import java.util.*;
import java.io.*;
public class TargetedAd  {

  public static void main(String[] args) throws FileNotFoundException
  {
    /*  
     * TODO:
     * PREPARATION WORK
     * (1) Create a file called targetWords.txt. Populate this file with words on each line that
     *     you think would determine if a user is a dog or cat owner.
     * 
     * PROGRAMMING
     * (2) Create a new DataCollector object and set the data to "socialMediaPostsSmall.txt" and "targetWords.txt"
     *     Important: Use the socialMedialPostsSmall to create your algorithm. Using a small file will help you 
     *     generate your solution quicker and give you the ability to double check your work.
     * (3) Create a String variable to hold the names of all the user. (The first word of every post is 
     *     a person's username)
     * (4) Compare each user's post to each target word. If a user mentions a target word, add their username to 
     *     the String of users. Separate usernames with a space. 
     *         Hint: You can use loops to look through each word. 
     *         Hint2: You can use indexOf to check if a word is in a user post. 
     * (5) Once you have all the users, use your DataCollector's prepareAdvertisement method to prepare a file 
     *     with all users and the advertisement you will send them.
     *         Additional Info: The prepareAdvertisement creates a new file on your computer. Check the posts of
     *         some of the usernames to make sure your algorithm worked.
     * 
     * THE FINAL SOLUTION
     * (6) Your solution should work with the socialMedialPostsSmall.txt. Modify your DataCollector initialization
     *    so you use the socialMediaPosts.txt. You should now have a larger file of users to target.
     */


    DataCollector collector = new DataCollector();
    collector.setData("reviews.csv", "targetWords.txt");
    String users = "";
   
    ArrayList<String> targetWordsList = new ArrayList<String>();
    File targetWordsFile = new File("targetWords.txt");
    Scanner sc = new Scanner(targetWordsFile);

    while (sc.hasNextLine()) {
      targetWordsList.add(sc.nextLine());
    }
   
    boolean hasNextPost = true;
    collector.getNextPost();
    while (hasNextPost == true) {     
      String nextReview = collector.getNextPost();
      String targetUserName = "";
      
      if (nextReview != "NONE") {
        
        for (String word : targetWordsList) {
          
          if (nextReview.indexOf(word) != -1) {         
            Scanner reviewScanner = new Scanner(nextReview);
            reviewScanner.useDelimiter(",");
            targetUserName = reviewScanner.next();
             if (users.indexOf(targetUserName) == -1) {
              users += targetUserName + " ";
              reviewScanner.close();    
             }
               
          }
        }
          
      } else 
      {
        hasNextPost = false;
      }      
    }
    System.out.println(users);
    collector.prepareAdvertisement("advertisements", users, "We bet your furry friend would love to smell our pet food!");
     
  }

}
