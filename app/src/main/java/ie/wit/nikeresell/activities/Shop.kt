package ie.wit.nikeresell.activities

import ShopModel
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import ie.wit.nikeresell.adapters.ImageAdapter
import ie.wit.nikeresell.R
import ie.wit.nikeresell.main.ShopApp
import kotlinx.android.synthetic.main.activity_shop.*


class Shop : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var loader : AlertDialog
    lateinit var app: ShopApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        var app: ShopApp = this.application as ShopApp
        setContentView(R.layout.activity_shop)
        progressBar.max = 10000
        amountPicker.minValue = 1
        amountPicker.maxValue = 1000
        amountPicker.setOnValueChangedListener { _, _, newVal ->
            paymentAmount.setText("$newVal") }
        var totalOffered = 0
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val adapter = ImageAdapter(this)
        viewPager.adapter = adapter

        shopButton.setOnClickListener {
            val amount = if (paymentAmount.text.isNotEmpty())
                paymentAmount.text.toString().toInt() else amountPicker.value
            if(totalOffered >= progressBar.max)
                Toast.makeText(this,"Bidding Closed, all tickets sold.",Toast.LENGTH_LONG).show()
            else {
                val paymentmethod = if(paymentMethod.checkedRadioButtonId == R.id.Direct) "VISA" else "Paypal"
                totalOffered += amount
                totalSoFar.text = "$$totalOffered"
                totalSoFar1.text = "$totalOffered"
                progressBar.progress = totalOffered
                app.ShopStore.create(ShopModel(paymentmethod = paymentmethod,amount = amount))
            }
        }

        shopButton2.setOnClickListener {
        //    val popup = PopupMenu(this,shopButton2)
            //    popup.inflate(R.menu.test)
            //   popup.setOnMenuItemClickListener {
            //       Toast.makeText(this, "Item: " +it.title,Toast.LENGTH_SHORT).show()
            //        true
            //      }
            //     popup.show()

            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.layout_popup,null)
            window.contentView = view
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            imageView.setOnClickListener {
                window.dismiss()
            }
            window.showAsDropDown(shopButton2)
        }

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> startActivity(Intent(this, PreviousWinners::class.java))
                R.id.item2 -> startActivity(Intent(this, Purchases::class.java))
                R.id.item3 -> startActivity(Intent(this, aboutus::class.java))
                R.id.item4 -> signOut()

            }
            true
        }

    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            true
        }

        return when (item.itemId) {
            R.id.action_purchases -> { startActivity(Intent(this, Purchases::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_purchase, menu)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }


    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        val i = Intent(applicationContext, Login::class.java)
        startActivity(i)
    }


}



