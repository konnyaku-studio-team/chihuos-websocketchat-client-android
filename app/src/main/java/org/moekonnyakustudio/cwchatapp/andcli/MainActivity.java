package org.moekonnyakustudio.cwchatapp.andcli;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Looper;
import android.util.Base64;
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

        final WebSocketClient wsc=new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakeData) {
                Looper.prepare();
                Toast.makeText(MainActivity.this,"恭喜！服务器连接成功！",Toast.LENGTH_LONG).show();
                Looper.loop();
                String HttpStatus=String.valueOf(handshakeData.getHttpStatus());
                Log.i("WSConnect", "onOpen!");
            }
            @Override
            public void onMessage(String message) {
                TextView showMessage=findViewById(R.id.textView);
                showMessage.setText("收到信息："+message);
                Log.i("WSConnect", "onMessage!");
            }
            @Override
            public void onClose(int code, String reason, boolean remote) {
                Looper.prepare();
                Toast.makeText(MainActivity.this,"草，服务器关闭了！",Toast.LENGTH_LONG).show();
                Log.i("WSConnect", "onClose!");
                Looper.loop();
            }
            @Override
            public void onError(Exception ex) {
                Looper.prepare();
                Toast.makeText(MainActivity.this,"呜呜呜！连接失败了！QAQ！",Toast.LENGTH_LONG).show();
                Looper.loop();
                Log.i("WSConnect", "onError!");
            }
        };
        wsc.connect();
        Button sendbut=findViewById(R.id.CommitMessage);
        sendbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText insText=findViewById(R.id.wsServerMessage);
                String SM=insText.getText().toString();
                String encodeStr = Base64.encodeToString(SM.getBytes(), Base64.DEFAULT);
                wsc.send(String.valueOf(encodeStr));
                Toast.makeText(MainActivity.this,"恭喜！信息发送成功！",Toast.LENGTH_LONG).show();
            }
        });
    }
}