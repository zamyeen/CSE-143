// Zareef Amyeen
// 4.13.2022
// CSE 143 Section BR
// TA: Jin Terada White
// Assessment 2: Music Playlist
// Music playlist is a class that stores a list of songs in a playlist
// and also a list of songs that are removed from the playlist
// When creating a music playlist a list of songs is given and a list is created in 
// the given order. You can print the playlist, or the list of removed songs,
// check if a song is in the playlist, or if it has been removed

import java.util.*;

public class MusicManager{
   private MusicNode front;
   private MusicNode removed;

   /**
   * Constructs a Music Playlist from a list of given songs
   * @param List<String> songs - List of songs that the playlist is created out of
   * throws IllegalArgumentException if list is empty
   */
   public MusicManager(List<String> songs){
      if (songs.size() == 0){
         throw new IllegalArgumentException("Empty List");
      }
      front = new MusicNode(songs.get(0));
      //System.out.println(front.song);
      MusicNode current = front;
      for (int i = 1; i < songs.size(); i++){
         MusicNode newNode = new MusicNode(songs.get(i));
         //System.out.println("Adding " + songs.get(i));
         current.next = newNode;
         current = newNode;
      }   
   }

   /*
   * Prints the playlist of songs
   */
   public void printCurrent(){
      //System.out.println("Printing current");
      print(front);
   }

   /*
   * Prints the songs that have been removed from the playlist
   */
   public void printRemoved(){
      //System.out.println("Printing removed");
      print(removed);
   }

   /*
   * Checks if the playlist contains a specified song (case insensitive)
   * @param String song - specified song
   * return boolean of if the song is contained in the playlist
   */
   public boolean currentContains(String song){
      return contains(front,song);
   }

   /*
   * Checks if the removed songs contains a specified song (case insensitive)
   * @param String song - specified song
   * @return boolean of if the song is contained in the removed songs
   */
   public boolean removedContains(String song){
      return contains(removed,song);
   }

   /*
   *  Checks if the playlist contains any songs
   *  @return boolean - if the playlist contains any songs
   */
   public boolean hasSongs(){
      return front != null;
   }

   /*
   *  Checks if the playlist contains any songs
   *  @return String - name of the song at the top of the playlist
   *  returns null if there are no songs in the playlist
   */
   public String nextSong(){
       if (front == null){
           return null;
       }
       return front.song;
   }
   
   /*
   *  Removes specified song (case insensitive) from the playlist and places it at the top 
   *  of the removed song list
   *  @param String song - name of specified song that is being removed
   *  throws IllegalStateException if there are no songs left to remove
   *  throws IllegalArgumentException if the specified song is not in the playlist
   */
   public void remove(String song){
      if (!this.hasSongs()){
         throw new IllegalStateException("No songs left to remove");
      } else if (!this.currentContains(song)){
         throw new IllegalArgumentException("Song is not in the playlist");
      }
      //System.out.println("Removing " + song);
      MusicNode remove;
      if (front.song.equalsIgnoreCase(song)){
         remove = front;
         front = front.next;
      } 
      else {
         //System.out.println("Printing");
          MusicNode current = front.next;
          MusicNode previous = front;
          while (current != null && !current.song.equalsIgnoreCase(song)){
            //System.out.println(previous.song);
            //System.out.println(current.song);
             previous = current;
             current = current.next;
          }
          remove = current;
          previous.next = current.next;
       }
      remove.next = removed;
      removed = remove;
   }
   
   //prints either the playlist or removed songs depending on what is passed in
   //@param MusicNode node - node that refers to either "head" of playlist/removed list
   private void print(MusicNode node){
      //System.out.println(front.song);
      while (node != null){
         //System.out.println("Entered print current");
         System.out.println("    " + node.song);
         node = node.next;
      }
   }

   //returns boolean of whether the playlist or removed songs contains specified song
   //@param String song - specified songs
   //@param MusicNode node - node that refers to either "head" of playlist/removed list
   private boolean contains(MusicNode node, String song){
      while (node != null){
         if (node.song.equalsIgnoreCase(song)){
            return true;
         }
         node = node.next;
      }
      return false;
   }

   
   /*public static void main(String[] args){
      List<String> songs = new ArrayList<String>();
      songs.add("Headshot");
      songs.add("Goat");
      songs.add("Me and my Guitar");
      //songs.add("21");
      //songs.add("Headshot");
      MusicManager playlist = new MusicManager(songs);
      playlist.printCurrent();
      
      playlist.remove("Goat");
      playlist.printCurrent();
      playlist.printRemoved();
      
      playlist.remove("Me and my Guitar");
      playlist.printCurrent();
      playlist.printRemoved();
   }*/
}