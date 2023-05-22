package se.anna.business;

import java.util.ArrayList;
import java.util.List;

public class Player {

	int id;
	String name;

	List<Result> results;
	
	public void addResult(int nGuesses) {
		results.add(new Result(nGuesses));
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Player(String name) {
		super();
		this.name = name;
		results = new ArrayList<Result>();
	}

	public Player(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Player() {
		super();
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "Player{" +
				"id=" + id +
				", name='" + name + '\'' +
				", results=" + results +
				'}';
	}
}
