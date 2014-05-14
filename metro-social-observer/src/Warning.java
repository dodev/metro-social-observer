/**
 * 
 */
import org.json.simple.*;

/**
 * @author dodev
 *
 */
public class Warning {
	
	private String type;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the warningLevel
	 */
	public int getWarningLevel() {
		return warningLevel;
	}

	/**
	 * @return the referencedId
	 */
	public int getReferencedId() {
		return referencedId;
	}

	private int warningLevel;
	private int referencedId;
	
	public Warning(String _type, int _referencedId, int _warningLvel) {
		this.type = _type;
		this.referencedId = _referencedId;
		this.warningLevel = _warningLvel;		
	}
	
	public JSONObject getJsonObject() {
		JSONObject obj = new JSONObject();
		obj.put("type", this.type);
		obj.put("referencedId", this.referencedId);
		obj.put("warningLevel", this.warningLevel);
		
		return obj;
	}
}
