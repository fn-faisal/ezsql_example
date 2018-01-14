package appzspot.sqlitetester;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;

import appzspot.sqlitetester.dbdebug.DBHomeActivity;
import appzspot.sqlitetester.model.da.UserDa;
import appzspot.sqlitetester.model.dto.Item;
import appzspot.sqlitetester.model.dto.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////

    // static finals
    private static final String TAG = "MainActivity";

    // widgets
    // new user widgets.
    private EditText newUserNameEt,
                     newEmailEt;
    private Button newQueryInsertBtn;

    // find user widgets.
    private EditText findEmailEt;
    private Button findUserBtn;

    // remove user widgets.
    private EditText removeUserNameEt;
    private Button removeUserBtn;

    // update user widgets.
    private EditText updateUserNameEt,
                     updateEmailNewEt;
    private Button updateUserBtn;

    ///////////////////////////////////////////////////////////////////////////
    // Activity Methods.
    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        initListeners();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                return true;
            case R.id.action_db_debug:
                startActivity(new Intent(this, DBHomeActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch ( view.getId() ) {
            // insert btn pressed.
            case R.id.main_new_query_insert_btn: {
                // check if the username and email field are not empty.
                if ( !newUserNameEt.getText().toString().equals("") && !newEmailEt.getText().toString().equals("") ) {
                    try {
                        if (new UserDa(this).addUser(newUserNameEt.getText().toString(), newEmailEt.getText().toString()))
                            Toast.makeText( this, "User added successfully!", Toast.LENGTH_SHORT ).show();
                        else
                            Toast.makeText( this, "User could not be added!", Toast.LENGTH_SHORT ).show();
                    }
                    catch ( Exception ex ) {
                        Toast.makeText( this, " There was an exception ", Toast.LENGTH_SHORT ).show();
                        Log.e(TAG, "onClick: Add User Exception : "+ex.getMessage() );
                    }
                }
                else
                    Toast.makeText( this, "Username or password left empty", Toast.LENGTH_SHORT ).show();

            } break;
            // find btn pressed.
            case R.id.main_query_find_btn: {
                // if email is given.
                if ( !findEmailEt.getText().toString().equals("") ) {
                    try {
                        ArrayList<User> users = new UserDa(this).getUser(null , findEmailEt.getText().toString());
                        if ( users.size() > 0 ) {
                            String toastMsg = "";
                            for ( User user : users ) {
                                toastMsg += " [ id : "+user.getId()+" , name : "+user.getName()+" , email : "+user.getEmail()+" ]\n ";
                            }
                            Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(this, "User list empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch ( Exception ex ) {
                        Toast.makeText(this, "An exception has occurred", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onClick: Find query Exception : "+ex.getMessage() );
                    }
                }
                // get all users.
                else {
                    try {
                        ArrayList<User> users = new UserDa(this).getUser(null , null);
                        if ( users.size() > 0 ) {
                            String toastMsg = "";
                            for ( User user : users ) {
                                toastMsg += " [ id : "+user.getId()+" , name : "+user.getName()+" , email : "+user.getEmail()+" ]\n ";
                            }
                            Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(this, "User list empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch ( Exception ex ) {
                        Toast.makeText(this, "An exception has occurred", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onClick: Find query Exception : "+ex.getMessage() );
                    }
                }
            } break;
            // remove btn pressed.
            case R.id.main_rm_query_remove_btn: {
                // check if username is given
                if ( !removeUserNameEt.getText().toString().equals("") ) {
                    try {
                        if ( new UserDa(this).removeUser( removeUserNameEt.getText().toString() ) )
                            Toast.makeText( this, "User removed", Toast.LENGTH_SHORT ).show();
                        else
                            Toast.makeText( this, "User could not be removed", Toast.LENGTH_SHORT ).show();
                    }
                    catch ( Exception ex ){
                        Toast.makeText( this, "An exception has occured", Toast.LENGTH_SHORT ).show();
                        Log.e(TAG, "onClick: Remove User Exception : "+ex.getMessage() );
                    }

                } else
                    Toast.makeText( this, "Please enter a user name", Toast.LENGTH_SHORT ).show();

            } break;
            // update btn pressed.
            case R.id.main_upd_query_update_btn: {
                // check for empty values.
                if ( !updateUserNameEt.getText().toString().equals("") && !updateEmailNewEt.getText().toString().equals("") ) {
                    try {
                        if ( new UserDa(this).updateUser( updateUserNameEt.getText().toString(), updateEmailNewEt.getText().toString() ) ) {
                            Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(this, "Could not update user", Toast.LENGTH_SHORT).show();

                    } catch ( Exception ex ) {
                        Toast.makeText(this, "An exception occurred!", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onClick: Update user error : "+ex.getMessage() );
                    }
                }
                else
                    Toast.makeText(this, "Username or new email left empty", Toast.LENGTH_SHORT).show();
            } break;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Helper Methods
    ///////////////////////////////////////////////////////////////////////////

    private void initWidgets () {
        // new user widgets.
        newUserNameEt = (EditText) findViewById( R.id.main_new_uname_et );
        newEmailEt = (EditText) findViewById( R.id.main_new_email_et );
        newQueryInsertBtn = (Button) findViewById( R.id.main_new_query_insert_btn );

        // remove user widgets.
        removeUserNameEt = (EditText) findViewById( R.id.main_rm_uname_et );
        removeUserBtn = (Button) findViewById( R.id.main_rm_query_remove_btn );

        // find user widgets.
        findEmailEt = (EditText) findViewById( R.id.main_find_email_et);
        findUserBtn = (Button) findViewById( R.id.main_query_find_btn );

        // update user widgets.
        updateUserNameEt = (EditText) findViewById( R.id.main_upd_uname_et );
        updateEmailNewEt = (EditText) findViewById( R.id.main_upd_email_new_et );
        updateUserBtn = (Button) findViewById( R.id.main_upd_query_update_btn );

    }

    private void initListeners () {
        newQueryInsertBtn.setOnClickListener( this );
        findUserBtn.setOnClickListener( this );
        removeUserBtn.setOnClickListener( this );
        updateUserBtn.setOnClickListener( this );
    }


}
