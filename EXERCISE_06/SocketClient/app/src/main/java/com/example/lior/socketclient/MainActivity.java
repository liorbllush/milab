package com.example.lior.socketclient;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            socket = IO.socket("https://secret-caverns-98892.herokuapp.com/");
        } catch (URISyntaxException e) {
            Log.e(TAG, "onCreate: " + e.getMessage());
        }
        ((EditText) findViewById(R.id.stock_name_edit)).setOnEditorActionListener(new EditorActionListener());
        socket
                .on(Socket.EVENT_CONNECT, onConnectedEvent)
                .on(Socket.EVENT_DISCONNECT, onDisconnectedEvent)
                .on(Socket.EVENT_CONNECT_ERROR, onConnectionErrorEvent)
                .on(Socket.EVENT_CONNECT_TIMEOUT, onConnectionTimedOutEvent);
    }

    @NonNull
    private Emitter.Listener onPostStockPriceEvent = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];
            String price;
            try {
                price = data.getString("price");
                showToast(String.format("Updated price: %s", price));
            } catch (JSONException e) {
                showToast("Failed to read server update");
            }

        }
    };

    @NonNull
    private Emitter.Listener onConnectionTimedOutEvent = new Emitter.Listener() {

        @Override
        public void call(Object... args) {
            showToast("Connection timed out");
        }

    };

    @NonNull
    private Emitter.Listener onConnectionErrorEvent = new Emitter.Listener() {

        @Override
        public void call(Object... args) {
            showToast("Connection error");
        }

    };

    @NonNull
    private Emitter.Listener onDisconnectedEvent = new Emitter.Listener() {

        @Override
        public void call(Object... args) {
            showToast("Disconnected");
        }

    };

    @NonNull
    private Emitter.Listener onConnectedEvent = new Emitter.Listener() {

        @Override
        public void call(Object... args) {
            showToast("Connected");
        }

    };

    class EditorActionListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    actionId == EditorInfo.IME_ACTION_DONE ||
                    event != null &&
                            event.getAction() == KeyEvent.ACTION_DOWN &&
                            event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                if (event == null || !event.isShiftPressed()) {
                    if (socket.connected()) {
                        socket.off("postStockPrice", onPostStockPriceEvent);
                        socket.disconnect();
                    }
                    socket.on("postStockPrice", onPostStockPriceEvent);
                    socket.connect();
                    socket.emit("sendStockName", v.getText().toString().toUpperCase());
                    return true;
                }
            }
            return false;
        }
    }

    private void showToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "showToast: " + message);
            }
        });
    }
}
