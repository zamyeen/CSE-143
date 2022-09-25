// The MusicNode class is used to store the information for one
// player in the music playlist.

// Do not modify this file.

public class MusicNode {
    public final String song;  // this node's song (can't modify)
    public MusicNode next;     // next node in the list

    // constructs a node with the given song and a null link
    public MusicNode(String song) {
        this(song, null);
    }

    // constructs a node with the given song and link
    public MusicNode(String song, MusicNode next) {
        this.song = song;
        this.next = next;
    }
}