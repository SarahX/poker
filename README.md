# poker
A collaboration with Olivia and Sarah O'Connor

Problem 1:

The test method for this problem is in test.java

In the file Card.java, we declared a public class called Card, along with two protected variables, an int rank and a String suit, then created two arrays suits and ranks in order to model the characteristics of a card. After this, we made a constrcutor for the card class, a toString function, and getter methods for the rank and suit. 

In the file Deck.java, we declared a public class called Deck, where we create a new array cardDeck from the Card array, which has 52 cards, generate a new card through iteration with a for loop and then shuffle these cards in the new deck through the use of a shuffle function that also utilizes a for loop to randomly select from the deck, remove the card from the next round of shuffling, and return the cardDeck. 


Problem 2: 

The test method for this problem is in test.java

We declare a public class Hand, within it generating four new arrays-hand1 and hand2 use the Card[] to generate two new hands, while val1 and val2 are two arrays used to store the value of the cards in the prior hands (high vs low card), to make later classification more efficient. From there, we utilize Deck d to fill up both hand arrays. Due to the fact that the Deck class fills new arrays through loops that randomly select a card from the deck, and then making sure the array has one less card than previously, then returning this new cardDeck, this prevents the same card from being picked twice (without replacement). 

Problem 3:

The test method for this problem is located at the end of hand.java, to make the flow of code from methods to output more readable and clear

Problem 3 involves a lot of classification, and therefore, lots of conditional statements and loops. We can utilize the rank arrays to check for the 2, 3...of a kind. In order to improve understandability, the ace is not classified as the zeroth index of array, instead making the zeroth index null. We then check for a straight, flush, (with the flush and straight being set as boolean values), and then move to rank the hands based off of how many pairs they have (0, 1, 2). A switch statement with cases then checks for the following cases: high card, pairs, three/four of a kind, straight, flush, and a full house. After this, it will output the hand in the console, use a compareTo function to for loop through all of the values of the cards in each hand, and determine which value is greater, or return zero if both hands are the same. The ranking system enables a faster way of being able to classify hands, as you can use iteration to quickly determine the rankings of each hand, and then from there apply conditional statements according to the classifications already established from the ranking system. 

Problem 4:

Problem 4 is in the test.java file.

In Problem 4, we create a scanner scan that takes user input for the rank and suit of five cards (a hand) two times each, in a loop, which stores the input into two arrays of the Card array (test1 and test2), and then we use the same method as we used to compare the two hands in a previous question to once again compare the values of the two hands, and print the result (who wins the round). This is using methods called from the hand class as used prior. When the user is prompted, there is information about what kind of data should be inputted. 

   
   
   
 
