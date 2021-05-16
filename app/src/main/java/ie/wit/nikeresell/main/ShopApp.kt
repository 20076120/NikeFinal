package ie.wit.nikeresell.main

import ShopMemStore
import ShopStore
import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import ie.wit.nikeresell.R

class ShopApp : Application() {

    lateinit var ShopStore: ShopStore
    lateinit var app: ShopApp
    lateinit var auth: FirebaseAuth
    lateinit var gso:GoogleSignInOptions
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate() {
        app = this.applicationContext as ShopApp
        super.onCreate()
        ShopStore = ShopMemStore()


        Log.v("Donate","Donation App started")
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    googleSignInClient = GoogleSignIn.getClient(this, gso)
    }



    }