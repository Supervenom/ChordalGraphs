package vanoni.chordalGraphs;

public class Vertex {

	private int alpha;
	private int size;
	private int id;
	private int index;
	private Vertex follower;
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

	/**
	 * @return the follower
	 */
	public Vertex getFollower() {
		return follower;
	}

	/**
	 * @param follower the follower to set
	 */
	public void setFollower(Vertex follower) {
		this.follower = follower;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "Vertex [alpha=" + alpha + ", id=" + id + "]";
	}

}
