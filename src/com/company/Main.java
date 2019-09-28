package com.company;

import java.util.*;

public class Main {

    private static List<Album> albums = new ArrayList<Album>();

    public static void main(String[] args) {
        Album album = new Album("World of Sleepers", "Carbon Based Lifeforms");
        album.addSong("Abiogenesis", 6.45);
        album.addSong("Vortex", 6.13);
        album.addSong("Photosynthesis", 6.48);
        album.addSong("Set Theory", 7.06);
        album.addSong("Gryning", 7.20);
        album.addSong("Transmission", 4.54);
        album.addSong("World of Sleepers", 5.20);
        album.addSong("Proton", 6.51);
        album.addSong("Erratic Patterns", 7.27);
        album.addSong("Flytta Dig", 6.24);
        album.addSong("Betula Pendula", 1.52);
        albums.add(album);

        album = new Album("Solar Sampler", "Maceo Plex");//creating new album
        album.addSong("Was The Tears Away", 6.56);
        album.addSong("Solar Detroit", 8.57);
        albums.add(album);

        List<Song> playList = new ArrayList<>();
        albums.get(0).addToPlaylist("Abiogenesis", playList);
        albums.get(0).addToPlaylist("World of Sleepers", playList);
        albums.get(0).addToPlaylist("Photosynthesis", playList);
        albums.get(0).addToPlaylist("Speed King", playList);//does not exist
        albums.get(0).addToPlaylist(4, playList);
        albums.get(1).addToPlaylist(1, playList);
        albums.get(1).addToPlaylist(1, playList);
        albums.get(0).addToPlaylist(1, playList);
        albums.get(0).addToPlaylist(24, playList);//does not exist

        play(playList);
    }

    private static void play(List<Song> playList) {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;

        ListIterator<Song> listIterator = playList.listIterator();
        if (playList.size() == 0) {
            System.out.println("No song in the playlist");
            return;
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }

        while (!quit) {
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Playlist completed");
                    quit = true;
                    break;
                case 1:
                    if (!forward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next().toString());
                    } else {
                        System.out.println("We have reached the end of the playlist");
                        forward = false;
                    }
                    break;
                case 2:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous().toString());
                    } else {
                        System.out.println("We are at the start of the playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Now replaying " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the list");
                        }
                    } else {
                        if (listIterator.hasNext()) {
                            System.out.println("Now replaying " + listIterator.next().toString());
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the list");
                        }
                    }
                    break;
                case 4:
                    printList(playList);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if (playList.size() > 0) {
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next());
                        } else if (listIterator.hasPrevious()){
                            System.out.println("Now playing " + listIterator.previous());
                        }
                    }
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Please select option:\npress: ");
        System.out.println("0 - quit\n" +
                "1 - play the next song\n" +
                "2 - play the previous song\n" +
                "3 - replay the current song\n" +
                "4 - list songs in the playlist\n" +
                "5 - print available actions\n" +
                "6 - remove the song");
    }

    private static void printList(List<Song> playList) {
        Iterator<Song> iterator = playList.iterator();
        System.out.println("=========================");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("=========================");
    }
}