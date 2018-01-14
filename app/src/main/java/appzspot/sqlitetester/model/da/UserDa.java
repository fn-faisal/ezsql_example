package appzspot.sqlitetester.model.da;

import android.content.Context;

import com.appzspot.easysql.EzQuery;

import java.util.ArrayList;
import java.util.HashMap;

import appzspot.sqlitetester.model.dto.User;

/**
 * Created by faisa on 1/14/2018.
 */

public class UserDa {

    ///////////////////////////////////////////////////////////////////////////
    // Properties.
    ///////////////////////////////////////////////////////////////////////////
    private static final String TAG = "UserDa";

    private static final String TBL_USER_COL_NAME = "name";
    private static final String TBL_USER_COL_EMAIL = "email";

    private EzQuery ezQuery;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors.
    ///////////////////////////////////////////////////////////////////////////
    public UserDa ( Context context ) {
        this.ezQuery = EzQuery.getQuery(context);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Create Methods.
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Add a new user to the database.
     * @param userName the user name.
     * @param email the email.
     * @return true if user created successfully.
     */
    public boolean addUser ( String userName, String email )
            throws Exception {
        HashMap< String, Object > user = new HashMap< String, Object >();
        user.put(TBL_USER_COL_NAME, userName );
        user.put( TBL_USER_COL_EMAIL, email );

        return (long)(ezQuery
                .insert(User.class, user )
                .go()) > 0;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Read Methods.
    ///////////////////////////////////////////////////////////////////////////

    public ArrayList<User> getUser ( String userName, String email )
            throws Exception {
        if ( userName != null && email != null )
            return ezQuery
                .find( User.class )
                .colEq(TBL_USER_COL_NAME, userName ).and()
                .colEq( TBL_USER_COL_EMAIL , '\''+email+'\'' )
                .go();
        if ( email != null )
            return ezQuery
                .find( User.class )
                .colEq( TBL_USER_COL_EMAIL, '\''+email+'\'')
                .go();
        if ( userName != null )
            return ezQuery.find( User.class )
                    .colEq(TBL_USER_COL_NAME, userName )
                    .go();
        return ezQuery.find( User.class )
                    .go();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Remove Methods.
    ///////////////////////////////////////////////////////////////////////////

    public boolean removeUser ( String userName )
        throws Exception {
        return (long)
                ( ezQuery.remove(User.class).colEq(TBL_USER_COL_NAME, '\''+userName+'\'' ).go()) > 0;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Update Methods.
    ///////////////////////////////////////////////////////////////////////////

    public boolean updateUser ( String userName, String emailNew )
        throws Exception {
        HashMap< String, Object > update = new HashMap< String, Object >();
        update.put( TBL_USER_COL_EMAIL , '\''+emailNew+'\'' );
        return (long)
                (ezQuery.update( User.class, update ).colEq( TBL_USER_COL_NAME, '\''+userName+'\'' ).go()) > 0;
    }

}
