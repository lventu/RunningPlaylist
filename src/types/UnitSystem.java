package types;

public enum UnitSystem {
	KM("kilometers"),
	MI("miles");
	
	private final String text;
	private UnitSystem(final String text) {
        this.text = text;
    }

	@Override
	public String toString() {
		return text;
	}
}
