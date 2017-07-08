package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import utils.Utils;

public class Song implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private Date published;
	private float duration;
	private Song versionOf;
	private HashSet<MusicalGenre> genres;
	private HashSet<Person> writers;
	private HashSet<Person> composers;
	private HashSet<Person> performers;
	private Album album;
	
	public Song() {
		writers = new HashSet<>();
		composers = new HashSet<>();
		performers = new HashSet<>();
		genres = new HashSet<>();
	}

	public String getTitle() {
		return title;
	}

	public Song setTitle(String title) {
		this.title = title;
		return this;
	}

	public Date getPublished() {
		return (Date) published.clone();
	}

	public Song setPublished(Date published) {
		this.published = published;
		return this;
	}

	public float getDuration() {
		return duration;
	}

	public Song setDuration(float duration) {
		this.duration = duration;
		return this;
	}

	public Song getVersionOf() {
		return versionOf;
	}

	public Song setVersionOf(Song versionOf) {
		this.versionOf = versionOf;
		return this;
	}

	public MusicalGenre[] getGenres() {
		return genres.toArray(new MusicalGenre[0]);
	}

	public Song addGenre(MusicalGenre genre) {
		genres.add(genre);
		return this;
	}
	
	public Song removeGenre(MusicalGenre genre) {
		genres.remove(genre);
		return this;
	}

	public Person[] getWriters() {
		return writers.toArray(new Person[0]);
	}

	public Song addWriter(Person writer) {
		writers.add(writer);
		return this;
	}
	
	public Song removeWriter(Person writer) {
		writers.remove(writer);
		return this;
	}

	public Person[] getComposers() {
		return composers.toArray(new Person[0]);
	}

	public Song addComposer(Person composer) {
		composers.add(composer);
		return this;
	}
	
	public Song removeComposer(Person composer) {
		composers.remove(composer);
		return this;
	}

	public Person[] getPerformers() {
		return performers.toArray(new Person[0]);
	}

	public Song addPerformer(Person performer) {
		performers.add(performer);
		return this;
	}
	
	public Song removePerformer(Person performer) {
		performers.remove(performer);
		return this;
	}
	
	public Album getAlbum() {
		return album;
	}
	
	public Song setAlbum(Album album) {
		this.album = album;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj == this) return true;
		if(obj instanceof Song) {
			Song other = (Song) obj;
			return Utils.safeEqual(title, other.title) &&
					Utils.safeEqual(published, other.published) &&
					Utils.safeEqual(album, other.album);
		}
		return false;
	}

	@Override
	public int hashCode() {
		if(title == null) return super.hashCode();
		return title.hashCode();
	}
}
