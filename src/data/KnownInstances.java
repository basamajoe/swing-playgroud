package data;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import model.*;

public final class KnownInstances {

	public static HashSet<Person> knownPeople = new HashSet<>();
	public static HashSet<Song> knownSongs = new HashSet<>();
	public static HashSet<Album> knownAlbums = new HashSet<>();
	public static HashSet<MusicalGenre> knownMusicalGenres = new HashSet<>();
	
	public static void clear() {
		knownPeople.clear();
		knownSongs.clear();
		knownAlbums.clear();
		knownMusicalGenres.clear();
	}
	
	public static void saveKnownInstances(String path) {
		
		// Comprovacio de parametres
		if (path.equals(null)) {
			throw new IllegalArgumentException();
		}
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toString()))) {
			
			// knownPeople
			Iterator<Person> itKnownPeople = knownPeople.iterator();
			while (itKnownPeople.hasNext()) {
				Person currentElement = itKnownPeople.next();
				oos.writeObject(currentElement);
			}

			// knownSongs
			Iterator<Song> itKnownSongs = knownSongs.iterator();
			while (itKnownSongs.hasNext()) {
				Song currentElement = itKnownSongs.next();
				oos.writeObject(currentElement);

			}
				
			// knownAlbums
			Iterator<Album> itKnownAlbums = knownAlbums.iterator();
			while (itKnownAlbums.hasNext()) {
				Album currentElement = itKnownAlbums.next();
				oos.writeObject(currentElement);
			}
		
			// knownMusicalGenres
			Iterator<MusicalGenre> itKnownMusicalGenres = knownMusicalGenres.iterator();
			while (itKnownMusicalGenres.hasNext()) {
				MusicalGenre currentElement = itKnownMusicalGenres.next();
				oos.writeObject(currentElement);
			}
		
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	public static void loadKnownInstances(String path) {

		// Comprovacio de parametres
		if (path.equals(null)) {
			throw new IllegalArgumentException();
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toString()))) {

			while (true) {
				Object inObj = ois.readObject();
				// knownPeople
				if (inObj instanceof Person) {
					Person person = (Person) inObj;
					knownPeople.add(person);
				}
				// knownSongs
				if (inObj instanceof Song) {
					Song song = (Song) inObj;
					knownSongs.add(song);
				}
				// knownAlbums
				if (inObj instanceof Album) {
					Album album = (Album) inObj;
					knownAlbums.add(album);
				}
				// knownMusicalGenres
				if (inObj instanceof MusicalGenre) {
					MusicalGenre musical = (MusicalGenre) inObj;
					knownMusicalGenres.add(musical);
				}
			}

		} catch (EOFException e) {
			System.out.println("Finished");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public static void generateKnownPeople() {
		for(int i=0;i<20;i++) {
            PhysicalPerson tapes1 = new PhysicalPerson()
                    .setName("Love"+i)
                    .setMiddleName("Tapes")
                    .addSurname("Binissalem")
                    .setArtisticName("Like tapitas")
                    .setBorn(new Date(22, 1, 6))
                    .setGender(Gender.MALE);
            PhysicalPerson tapes2 = new PhysicalPerson()
                    .setName("Like"+i)
                    .setMiddleName("Tapes")
                    .addSurname("Gratis")
                    .setArtisticName("Like tapas")
                    .setBorn(new Date(28, 1, 6))
                    .setGender(Gender.MALE);
            knownPeople.add(tapes1);
            knownPeople.add(tapes2);
        }
		knownPeople.add(
                new PhysicalPerson()
                        .setName("William")
                        .setMiddleName("Bruce")
                        .addSurname("Bailey")
                        .setArtisticName("Axl Rose")
                        .setBorn(new Date(62, 1, 6))
                        .setGender(Gender.MALE)
        );
        knownPeople.add(
                new PhysicalPerson()
                        .setName("Jeffrey")
                        .setMiddleName("Dean")
                        .addSurname("Isbell")
                        .setArtisticName("Izzy Stradlin")
                        .setBorn(new Date(62, 3, 8))
                        .setGender(Gender.MALE)
        );
        knownPeople.add(
                new PhysicalPerson()
                        .setName("Saul")
                        .addSurname("Hudson")
                        .setArtisticName("Slash")
                        .setBorn(new Date(65, 6, 23))
                        .setGender(Gender.MALE)
        );
        knownPeople.add(
                new PhysicalPerson()
                        .setName("Michael")
                        .setMiddleName("Andrew")
                        .addSurname("McKagan")
                        .setArtisticName("Duff")
                        .setBorn(new Date(64, 1, 5))
                        .setGender(Gender.MALE)
        );
        knownPeople.add(
                new PhysicalPerson()
                        .setName("Matthew")
                        .addSurname("Sorum")
                        .setBorn(new Date(60, 10, 19))
                        .setGender(Gender.MALE)
        );
        knownPeople.add(
                new PhysicalPerson()
                        .setName("Gilby")
                        .addSurname("Clarke")
                        .setBorn(new Date(60, 7, 17))
                        .setGender(Gender.MALE)
        );
        knownPeople.add(
                new LegalPerson()
                        .setName("Guns n' Roses")
                        .setFounded(new Date(85, 2, 26))
        );
    }
}
