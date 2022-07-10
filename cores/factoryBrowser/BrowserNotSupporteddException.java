package factoryBrowser;

public class BrowserNotSupporteddException extends IllegalStateException {
	private static final long serialVersionUID = 1L;

	public BrowserNotSupporteddException(String browser) {
		super(String.format("Browser not supported: %s", browser));
	}
}
