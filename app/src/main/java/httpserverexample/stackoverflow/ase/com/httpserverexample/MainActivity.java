package httpserverexample.stackoverflow.ase.com.httpserverexample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    Server mServer;
    private static final String LOG_TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button update = (Button) findViewById(R.id.updateButton);
        update.setOnClickListener(this);

        TextView ip = (TextView) findViewById(R.id.ipTextView);
        ip.setText(getLocalIpAddress());



        try {
            mServer = new Server(8080);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Can't start server", e);
        }

    }

    @Override
    public void onClick(View v) {
        TextView param = (TextView) findViewById(R.id.someData);
        mServer.setParameter(param.getText().toString());
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e(LOG_TAG, ex.toString());
        }
        return null;
    }
}
