package org.moekonnyakustudio.cwchatapp.andcli;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.os.Bundle;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ClickOn(View v) {
        EditText iws = findViewById(R.id.wsServerInsert);
        URI uri = URI.create(iws.getText().toString());
        WebSocketClient wsc=new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakeData) {
                Looper.prepare();
                Toast.makeText(MainActivity.this,"恭喜！服务器连接成功！",Toast.LENGTH_LONG).show();
                Looper.loop();
                String HttpStatus=String.valueOf(handshakeData.getHttpStatus());
                Log.i("WSConnect", HttpStatus);
            }

            @Override
            public void onMessage(String message) {

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
            }

            @Override
            public void onError(Exception ex) {
                Looper.prepare();
                Toast.makeText(MainActivity.this,"呜呜呜！连接失败了！QAQ！",Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        };
        wsc.connect();
    }
}