package httpserverexample.stackoverflow.ase.com.httpserverexample;

import android.util.Log;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by Afh on 03.10.2015.
 */
public class Server extends NanoHTTPD {
    private static final String LOG_TAG = "MyServer";
    private String parameter;

    public Server(int port) throws IOException {
        super(port);
        start();
        Log.i(LOG_TAG, "Server started");
    }

    @Override
    public Response serve(IHTTPSession session) {
        Log.i(LOG_TAG, "Receive query");
        String msg = "<html><body><h1>Hello server</h1>\n";
        msg += "<p>Hello, " + parameter + "!</p>";
        return new Response( msg + "</body></html>\n" );
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
