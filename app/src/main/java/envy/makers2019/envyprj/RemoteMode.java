package envy.makers2019.envyprj;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class RemoteMode extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_mode);

        findViewById(R.id.F5).setOnClickListener(btnClickListener);
        findViewById(R.id.ESC).setOnClickListener(btnClickListener);
        findViewById(R.id.back).setOnClickListener(btnClickListener);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_navigator);
        navigationView.setOnNavigationItemSelectedListener(navigationListener);

        //immersive mode
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    // Top 3 Buttons (MainMenu , F5 , ESC) Click Events
    private Button.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.F5:
                    Toast.makeText(getApplicationContext(), "F5 Button", Toast.LENGTH_LONG).show();
                    break;
                case R.id.ESC:
                    Toast.makeText(getApplicationContext(), "ESC Button", Toast.LENGTH_LONG).show();
                    break;
                case R.id.back:
                    Intent intent = new Intent();
                    intent.putExtra("name", "envy");
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
            }
        }
    };

    //Events for system(HW) buttons  : Back , Volume Up , Volume down
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                Toast.makeText(this, "Volume Down, Go to prev slide", Toast.LENGTH_LONG).show();
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Toast.makeText(this, "Volume Up, Go to NextPage", Toast.LENGTH_LONG).show();
                break;
            case KeyEvent.KEYCODE_BACK:
                 Toast.makeText(this, "You can't use Back button here. Use 'EXIT' button instead", Toast.LENGTH_LONG).show();
                break;
            default:
                return false;
        }
        return true;
    }

    //Bottom menu items click event
    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.basic:
                    Toast.makeText(getApplicationContext(), "Ctrl A Basic Mode", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.pointer:
                    Toast.makeText(getApplicationContext(), "Ctrl P to Pen Mode", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.pen:
                    Toast.makeText(getApplicationContext(), "Switched to Pointer Mode", Toast.LENGTH_LONG).show();
                    return true;
            }
            return false;
        }
    };
}
