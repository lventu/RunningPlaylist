package utility;

public abstract class NObject {
	
	protected static final String INIZIO = "--> inizio <--";
	protected static final String FINE = "--> fine <--";
	
	protected void fpLog(String methodName, String string) {
		System.out.println(methodName + string);
	}
	
}
