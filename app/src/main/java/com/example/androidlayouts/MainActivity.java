package com.example.androidlayouts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


/*
 * ==========================================
 *        ANDROID ACTIVITY LIFECYCLE
 * ==========================================
 *
 * onCreate(Bundle savedInstanceState)
 *        |
 *        V
 * onStart() ------------------------------------+
 *        |                                      |
 *        V                                      |
 * onResume()                                    |
 *    |                                          |
 *    V                                          |
 * +--onPause()                                  |
 * |      |                                      |
 * |      V                                      |
 * |  onStop() <---------------------------+     |
 * |      |                                |     |
 * |      V                                |     |
 * |  onRestart() --> onStart() -----------+     |
 * |                                        (app goes to background)
 * +--> onDestroy() (final cleanup)
 *
 * Key:
 * -->  Indicates the next method in the lifecycle
 *
 * The activity typically moves from onCreate() to onDestroy(),
 * passing through onPause() and onStop() if interrupted or closed.
 */


public class MainActivity extends AppCompatActivity {

    Button goto2ndActButton, gotoWebPageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Intents: facilitates communication between different components of an app
        //as well as between different applications

        //Types of Intents
        //1 - Explicit Intent = use for communication between applications, or in the same application
        //use for communicating between activities startActivity(intentname)
        //use for starting services startService(intentname)
        //use for sending data between components intent.putExtra(key,value)
        //use for launching broadcast receivers sendBroadcast(intentname)
        goto2ndActButton = findViewById(R.id.goto2ndActButton);
        goto2ndActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSecondActivity();
            }
        });



        //2 - Implicit Intent
        //Use for opening webpage
        //Use for sharing content
        //Use for sending email
        //Use for dialing numbers
        //Use for opening camera to take picture
        gotoWebPageButton = findViewById(R.id.gotoWebPageButton);
        gotoWebPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage();
            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

    }


    //Explicit Intent communication with different activity
    public void goToSecondActivity(){
        Intent secondIntent = new Intent(this, SecondActivity.class);
        startActivity(secondIntent);

    }

    //Implicit Intent
    public void openWebPage(){
        Uri webpage = Uri.parse("https://google.com");
        //or intent.setData(Uri.parse("https://google.com"));
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
        startActivity(intent);
    }


    //OnRestart
    protected void onStart(){
        //called when the application is opened
        super.onStart();
        Toast.makeText(this, "Starting", Toast.LENGTH_SHORT).show();



    }


    //OnResume
    protected void onResume(){
        //called when the application is opening
        //called when the application is opened again after being closed but still
        //but still on the multi window mode
        super.onResume();
        Toast.makeText(this,
                "Resuming",
                Toast.LENGTH_SHORT).show();
    }

    //OnPause
    protected void onPause(){
        //called when the application is closed but still on the multiwindow mode
        //connected to onResume method
        super.onPause();
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    //OnStop
    protected void onStop(){
        //called when the application is not visible or the user chose different app
        //connected to onRestart method when opened again restart -- start -- resume
        super.onStop();
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

    }
    //OnRestart(){
    protected void onRestart(){
        //called when the application is reopened after choosing different app
        //connected to onStop method and OnStart Method
        super.onRestart();
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    //OnDestroy
    protected void onDestroy(){
        //called when the application is totally closed such as closing the app, from multiwindow
        // cannot start again unless changing orientation (but will restart again create - start - resume)
        super.onDestroy();
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }


}