package se.anna.business;




public class Result {

	int id;
	int score;


	public Result(int result) {
		super();
		this.score = result;
	}

	public Result() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Result{" +
				"id=" + id +
				", score=" + score +
				'}';
	}
}
