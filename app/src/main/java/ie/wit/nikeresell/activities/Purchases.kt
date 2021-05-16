package ie.wit.nikeresell.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import ie.wit.adapters.PurchasesAdapter
import ie.wit.nikeresell.R
import ie.wit.nikeresell.main.ShopApp
import kotlinx.android.synthetic.main.activity_purchases.*

class Purchases : AppCompatActivity() {
    lateinit var app: ShopApp
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchases)
        app = this.application as ShopApp
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PurchasesAdapter(app.ShopStore.findAll())


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_shop, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_shop -> {
                startActivity(Intent(this, Shop::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        val i = Intent(applicationContext, Login::class.java)
        startActivity(i)
    }
}