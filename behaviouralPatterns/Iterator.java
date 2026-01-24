
import java.util.ArrayList; 


interface PlaylistIterator { 
    boolean hasNext(); 
    String next();
}

class FavouritePlaylistIterator implements PlaylistIterator {

    private Playlist playlist;
    private int index;

    public FavouritePlaylistIterator(Playlist playlist) {
        this.playlist = playlist;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        while (index < playlist.getSongs().size()) {
            if (playlist.getSongs().get(index).contains("fav")) {
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public String next() {
        return playlist.getSongs().get(index++);
    }
}

class SimplePlaylistIterator implements PlaylistIterator {

    private Playlist playlist;
    private int index;

    public SimplePlaylistIterator(Playlist playlist) {
        this.playlist = playlist;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < playlist.getSongs().size();
    }

    @Override
    public String next() {
        return playlist.getSongs().get(index++);
    }
}

class ShufflePlaylistIterator implements PlaylistIterator {

    private Playlist playlist;
    private ArrayList<Integer> indices;
    private int current;

    public ShufflePlaylistIterator(Playlist playlist) {
        this.playlist = playlist;
        this.indices = new ArrayList<>();
        for (int i = 0; i < playlist.getSongs().size(); i++) {
            indices.add(i);
        }
        java.util.Collections.shuffle(indices);
        this.current = 0;
    }

    @Override
    public boolean hasNext() {
        return current < indices.size();
    }

    @Override
    public String next() {
        return playlist.getSongs().get(indices.get(current++));
    }
}

class Playlist {

    private ArrayList<String> songs;

    public Playlist() {
        songs = new ArrayList<>();
    }

    public void addSong(String song) {
        songs.add(song);
    }

    public ArrayList<String> getSongs() {
        return songs;
    }

    public PlaylistIterator iterator(String type) {
        switch (type) {
            case "favourite":
                return new FavouritePlaylistIterator(this);
            case "simple":
                return new SimplePlaylistIterator(this);
            case "shuffle":
                return new ShufflePlaylistIterator(this);
            default:
                throw new AssertionError();
        }
    }
}

public class Iterator {

    public static void main(String[] args) {
        // Create a playlist
        Playlist playlist = new Playlist();
        playlist.addSong("Song 1");
        playlist.addSong("Song 2 fav");
        playlist.addSong("Song 3");
        playlist.addSong("Song 4 fav");
        playlist.addSong("Song 5");

        // Simple Playlist Iterator
        System.out.println("Simple Playlist:");
        PlaylistIterator simpleIterator = playlist.iterator("simple");
        while (simpleIterator.hasNext()) {
            System.out.println("Playing: " + simpleIterator.next());
        }

        // Shuffled Playlist Iterator
        System.out.println("nShuffled Playlist:");
        PlaylistIterator shuffledIterator = playlist.iterator("shuffle");
        while (shuffledIterator.hasNext()) {
            System.out.println("Playing: " + shuffledIterator.next());
        }

        // Favorites Playlist Iterator
        System.out.println("nFavorites Playlist:");
        PlaylistIterator favoritesIterator = playlist.iterator("favourite");
        while (favoritesIterator.hasNext()) {
            System.out.println("Playing: " + favoritesIterator.next());
        }

    }
}