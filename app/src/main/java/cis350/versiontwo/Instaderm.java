package cis350.versiontwo;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class Instaderm extends android.app.Application {

//    String currUser;
    /** Enable Parse on all app pages */
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(ParseUser.class);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "fviaFJ9B1jQdWCCnS419jkZ8dFVquHBd1lu0Y1jF",
                "p6dYSbB0KVF7KPvstO2ui7B32RanUEj9vmS28DLi");
    }

//    public void setCurrUser(String email) {
//        currUser = email;
//    }
//
//    public String getCurrUser() {
//        return currUser;
//    }
//
//    public void resetCurrUser() {
//        currUser = "";
//    }
}