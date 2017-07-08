package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import utils.Utils;

public class Album implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String title;
	private Date published;
	private HashSet<Song> songs;
	private HashSet<Person> producers;
	private HashSet<Person> mainPerformers;
	
	public Album() {
		songs = new HashSet<Song>();
		producers = new HashSet<Person>();
		mainPerformers = new HashSet<Person>();
	}
	
	public String getTitle() {
		return title;
	}
	
	public Album setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public Date getPublished() {
		return (Date) published.clone();
	}
	
	public Album setPublished(Date date) {
		this.published = date;
		return this;
	}
	
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	
	public Album addSong(Song song) {
		songs.add(song);
		song.setAlbum(this);
		return this;
	}
	
	public Album removeSong(Song song) {
		songs.remove(song);
		song.setAlbum(null);
		return this;
	}
	
	public Person[] getProducers() {
		return producers.toArray(new Person[0]);
	}

	public Album addProducer(Person producer) {
		producers.add(producer);
		return this;
	}
	
	public Album removeProducer(Person producer) {
		producers.remove(producer);
		return this;
	}
	
	public Person[] getMainPerformers() {
		return mainPerformers.toArray(new Person[0]);
	}
	
	public Album addMainPerformer(Person performer) {
		mainPerformers.add(performer);
		return this;
	}
	
	public Album removeMainPerformer(Person performer) {
		mainPerformers.remove(performer);
		return this;
	}

	@Override
	public String toString() {
		return String.format("%s - %s (%d)", getPerformersString(), title, published.getYear());
	}
	
	private String getPerformersString() {
		StringBuilder sb = new StringBuilder();
		int size = mainPerformers.size();
		int current = 1;
		Iterator<Person> iterator = mainPerformers.iterator();
		while(iterator.hasNext()) {
			boolean last = (current == size);
			if(last && size > 1) {
				sb.append(" and ");
			} else if(current > 1) {
				sb.append(", ");
			}
			sb.append(iterator.next().getArtisticName());
			current++;
		}
		return sb.toString();		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj == this) return true;
		if(obj instanceof Album) {
			Album other = (Album) obj;
			return Utils.safeEqual(title, other.title) &&
					Utils.safeEqual(published, other.published) &&
					Utils.setsAreEqual(producers, other.producers);
		}
		return false;
	}

	@Override
	public int hashCode() {
		if(title == null) return super.hashCode();
		return title.hashCode();
	}
	
}
