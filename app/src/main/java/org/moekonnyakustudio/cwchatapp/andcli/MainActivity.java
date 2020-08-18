package org.moekonnyakustudio.cwchatapp.andcli;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
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
        Toast toast = Toast.makeText(MainActivity.this,"QAQ！连接失败了！",Toast.LENGTH_LONG);
        URI uri = URI.create(String.valueOf(iws.getText()));
        WebSocketClient wsc=new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakeData) {
                Toast toast = Toast.makeText(MainActivity.this,"连接成功！",Toast.LENGTH_LONG);

            }

            @Override
            public void onMessage(String message) {

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
            }

            @Override
            public void onError(Exception ex) {
                Toast toast = Toast.makeText(MainActivity.this,"QAQ！连接失败了！",Toast.LENGTH_LONG);
            }
        };
    }
}