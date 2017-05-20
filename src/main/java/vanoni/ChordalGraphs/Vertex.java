package vanoni.ChordalGraphs;

public class Vertex {

	private int alpha;
	private int size;
	private int id;
	private static int count = 0;

	public Vertex() {
		id = count;
		count++;
	}
	
	/**
	 * @return the position
	 */
	public int getAlpha() {
		return alpha;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int alpha) {
		this.alpha = alpha;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Vertex [alpha=" + alpha + ", id=" + id + "]";
	}

}
