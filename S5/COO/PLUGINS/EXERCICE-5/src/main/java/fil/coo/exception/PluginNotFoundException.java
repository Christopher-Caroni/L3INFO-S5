package fil.coo.exception;

public class PluginNotFoundException extends Exception {
    public PluginNotFoundException(String s) {
        super(s);
    }

    public PluginNotFoundException(Throwable e) {
        super(e);
    }
}
