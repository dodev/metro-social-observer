import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dodev
 *
 */
public abstract class AbstractTemplate {
	
	private Pattern regex;
	private int delta;

	/**
	 * 
	 */
	public AbstractTemplate(String regexText, int delta) {
		this.regex = Pattern.compile(regexText);
		this.delta = delta;
	}

	/**
	 * @return the delta
	 */
	public int getDelta() {
		return delta;
	}
	
	public boolean match(String text) {
		Matcher matcher = this.regex.matcher(text);
		
		return matcher.matches();
	}
	
	public abstract ISchemeObject[] extractObjects(String text, Scheme scheme);
}
