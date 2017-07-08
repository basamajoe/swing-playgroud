package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import junit.framework.Assert;

import model.Album;
import model.Gender;
import model.LegalPerson;
import model.MusicalGenre;
import model.Person;
import model.PhysicalPerson;
import model.Song;

import org.junit.Test;

import data.KnownInstances;

import utils.Utils;


public class PersistenceTests {

	private ArrayList<Person> knownPeople = new ArrayList<>();
	private ArrayList<Song> knownSongs = new ArrayList<>();
	private ArrayList<Album> knownAlbums = new ArrayList<>();
	private ArrayList<MusicalGenre> knownMusicalGenres = new ArrayList<>();
	
	public PersistenceTests() {
	}

	private void generateData() {
		knownPeople.clear();
		knownSongs.clear();
		knownAlbums.clear();
		knownMusicalGenres.clear();
		
		generateKnownPeople();
		generatePeopleRelations();
		generateMusicalGenres();
		generateKnownSongs();
		generateSongGenresRelations();
		generateKnownAlbums();
		generateSongAlbumRelations();
		generateSongPeopleRelations();
		generateAlbumPeopleRelations();
	}

	private void generateKnownPeople() {
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
	
	private void generatePeopleRelations() {
		LegalPerson gnr = (LegalPerson) knownPeople.get(knownPeople.size() - 1);
		
		for(int i = 0; i < (knownPeople.size() - 1); i++) {
			PhysicalPerson musician = (PhysicalPerson) knownPeople.get(i);
			musician.addEmployer(gnr);
		}
		
		PhysicalPerson axl = (PhysicalPerson) knownPeople.get(0);
		axl.addParticipation(gnr, 100);
	}
	
	private void generateMusicalGenres() {
		knownMusicalGenres.add(new MusicalGenre().setName("Rock"));
		knownMusicalGenres.add(new MusicalGenre().setName("Hard rock"));
	}
	
	private void generateKnownSongs() {
		knownSongs.add(new Song().setTitle("Welcome to the jungle"));
		knownSongs.add(new Song().setTitle("Nightrain"));
		knownSongs.add(new Song().setTitle("Out ta get me"));
		knownSongs.add(new Song().setTitle("Paradise city"));
		knownSongs.add(new Song().setTitle("My Michelle"));
		knownSongs.add(new Song().setTitle("Sweet child o' mine"));
		knownSongs.add(new Song().setTitle("You're crazy"));
		
		for(Song song : knownSongs) {
			song.setPublished(new Date(87, 6, 21));
		}
	}
	
	private void generateSongGenresRelations() {
		for(Song song : knownSongs) {
			song.addGenre(knownMusicalGenres.get(1));
		}
	}

	private void generateKnownAlbums() {
		knownAlbums.add(new Album().setTitle("Appetite for destruction").setPublished(new Date(87, 6, 21)));
	}
	
	private void generateSongAlbumRelations() {
		Album appetite = knownAlbums.get(0);
		for(Song song : knownSongs) {
			appetite.addSong(song);
		}
	}
	
	private void generateSongPeopleRelations() {
		LegalPerson gnr = (LegalPerson) knownPeople.get(knownPeople.size() - 1);
		for(Song song : knownSongs) {
			song.addPerformer(gnr);
		}
	}
	
	private void generateAlbumPeopleRelations() {
		LegalPerson gnr = (LegalPerson) knownPeople.get(knownPeople.size() - 1);
		Album appetite = knownAlbums.get(0);
		appetite.addMainPerformer(gnr);
	}

	@Test
	public void testSaveAndLoadInstances() throws IOException {
		generateData();
		overwriteKnownInstances();
		
		Path tempFile = Files.createTempFile(Paths.get(System.getProperty("java.io.tmpdir")), "discography", "");
		KnownInstances.saveKnownInstances(tempFile.toString());
		KnownInstances.clear();
		KnownInstances.loadKnownInstances(tempFile.toString());
		
		HashSet<Person> knownPeopleSet = new HashSet<>();
		knownPeopleSet.addAll(knownPeople);
		Assert.assertTrue(Utils.setsAreEqual(knownPeopleSet, KnownInstances.knownPeople));
		
		HashSet<Song> knownSongsSet = new HashSet<>();
		knownSongsSet.addAll(knownSongs);
		Assert.assertTrue(Utils.setsAreEqual(knownSongsSet, KnownInstances.knownSongs));
		
		HashSet<Album> knownAlbumsSet = new HashSet<>();
		knownAlbumsSet.addAll(knownAlbums);
		Assert.assertTrue(Utils.setsAreEqual(knownAlbumsSet, KnownInstances.knownAlbums));
		
		HashSet<MusicalGenre> knownMusicalGenresSet = new HashSet<>();
		knownMusicalGenresSet.addAll(knownMusicalGenres);
		Assert.assertTrue(Utils.setsAreEqual(knownMusicalGenresSet, KnownInstances.knownMusicalGenres));
	}
	
	private void overwriteKnownInstances() {
		KnownInstances.clear();
		KnownInstances.knownPeople.addAll(knownPeople);
		KnownInstances.knownSongs.addAll(knownSongs);
		KnownInstances.knownAlbums.addAll(knownAlbums);
		KnownInstances.knownMusicalGenres.addAll(knownMusicalGenres);
	}
}
