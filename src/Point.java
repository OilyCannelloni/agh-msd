import java.util.ArrayList;

public class Point {
	private ArrayList<Point> neighbors;
	private int currentState;
	private int nextState;
	private int numStates = 6;
	private final double dropProbability = 0.05;

	
	public Point() {
		currentState = 0;
		nextState = 0;
		neighbors = new ArrayList<>();
	}

	public void clicked() {
		currentState=(++currentState)%numStates;	
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void drop() {
		if (Board.random.nextDouble() < dropProbability) {
			this.nextState = 6;
		}
	}

	public void calculateNewState() {
		//TODO: insert logic which updates according to currentState and
		if (Board.RAIN) {
			if (neighbors.isEmpty()) {
				this.nextState = 0;
				return;
			}
			int neighbourState = this.neighbors.get(0).currentState;
			if (this.currentState > 0) this.nextState = this.currentState - 1;
			else if (this.currentState == 0 && neighbourState > 0) nextState = numStates;
			else this.nextState = 0;

		} else {
			int count = this.getAliveNeighboursCount();
			if (this.currentState == 0) {
				if (count == 3) this.nextState = 1;
				else this.nextState = 0;
			} else {
				if (count == 2 || count == 3) this.nextState = 1;
				else this.nextState = 0;
			}
		}
	}

	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
	
	//TODO: write method counting all active neighbors of THIS point
	public int getAliveNeighboursCount() {
		return this.neighbors.stream().reduce(
				0, (subtotal, element) -> subtotal + element.getState(), Integer::sum
		);
	}
}
