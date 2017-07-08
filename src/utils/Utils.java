package utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public final class Utils {

	public static <E> String join(Collection<E> collection, String separator) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		Iterator<E> iterator = collection.iterator();
		while(iterator.hasNext()) {
			if(!first) sb.append(separator);
			first = false;
			E current = iterator.next();
			sb.append(current.toString());
		}
		return sb.toString();
	}
	
	public static <E> boolean setsAreEqual(Set<E> a, Set<E> b) {
		if(a == b) return true;
		if(a == null || b == null) return false;
		if(a.size() != b.size()) return false;
		Iterator<E> iterator = a.iterator();
		while(iterator.hasNext()) {
			E current = iterator.next();
			System.out.println(String.format("Hash of current: %s", current.hashCode()));
			Iterator<E> iterator2 = b.iterator();
			while(iterator2.hasNext())
			{
				E other = iterator2.next();
				System.out.println(String.format("\tHash of other: %s", other.hashCode()));
				System.out.println(String.format("\tEqual: %s", current.equals(other)));
			}
			if(!b.contains(current)) return false;
		}
		return true;
	}
	
	public static boolean safeEqual(Object a, Object b) {
		if(a == b) return true;
		else if(a == null || b == null) return false;
		else return a.equals(b);
	}
}
