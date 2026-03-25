package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;

public class GestionCartes {
	private static Random random = new Random();
	
	public static <T> T extraire (List<T> liste) {
		return liste.remove(random.nextInt(liste.size()));
	}
	
	public static <T> T extraireIterator (List<T> liste) {
		ListIterator<T> iterator = liste.listIterator(random.nextInt(liste.size()));
		
		T element = iterator.next();
		iterator.remove();
		
		return element;
	}
	
	public static <T> List<T> melanger (List<T> liste){
		List<T> shuffledList = new ArrayList<>();
		
		while(!liste.isEmpty()) {
			shuffledList.add(extraire(liste));
		}
		
		return shuffledList;
	}
	
	public static <T> boolean verifierMelange (List<T> list1, List<T> list2) {
		if(list1.size() != list2.size()) return false; 
		
		Set<T> elementsUniques = new HashSet<>(list1);
		
		for(T element : elementsUniques) {
			if(Collections.frequency(list1, element) != Collections.frequency(list2, element)) return false;
		}
		
		return true;
	}
	
	public static <T> List<T> rassembler (List<T> liste){
		List<T> listeRassemblee = new ArrayList<>();
		Set<T> elementsUniques = new HashSet<>(liste);
		
		for(T element : elementsUniques) {
			for(int i = 0; i < Collections.frequency(liste, element); i++) {
				listeRassemblee.add(element);
			}
		}
		
		return listeRassemblee;
	}
	
	public static <T> boolean verifierRassemblement (List<T> liste) {
		for(ListIterator<T> iterateur = liste.listIterator(); iterateur.hasNext();) {
			T element = iterateur.next();
			
			while(iterateur.hasNext() && iterateur.next().equals(element));
			
			ListIterator<T> itOccurrence = liste.listIterator(iterateur.nextIndex());
			
			while(itOccurrence.hasNext()) {
				if(itOccurrence.next().equals(element)) return false;
			}
		}
		return true;
	}
	
	
}
