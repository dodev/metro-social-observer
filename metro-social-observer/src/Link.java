/**
 * 
 */

/**
 * @author dodev
 *
 */
public class Link implements ISchemeObject {
	
	private int id;
	private int warningLevel;
	private int fromStId;
	private int toStId;
	private boolean isTransfer;

	/**
	 * 
	 */
	public Link(
			int id,
			int fromStId,
			int toStId,
			boolean isTransfer
			) {
		
		this.id = id;
		this.fromStId = fromStId;
		this.toStId = toStId;
		this.isTransfer = isTransfer;
		
		this.warningLevel = 0;
	}

	
	/**
	 * @return the warningLevel
	 */
	public int getWarningLevel() {
		return warningLevel;
	}

	/**
	 * @param warningLevel the warningLevel to set
	 */
	public void setWarningLevel(int warningLevel) {
		this.warningLevel = warningLevel;
	}

	public int getId() {
		return id;
	}

	/**
	 * @return the fromStId
	 */
	public int getFromStId() {
		return fromStId;
	}


	/**
	 * @return the toStId
	 */
	public int getToStId() {
		return toStId;
	}


	/**
	 * @return the isTransfer
	 */
	public boolean isTransfer() {
		return isTransfer;
	}
}
