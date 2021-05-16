package ie.wit.nikeresell.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import ie.wit.nikeresell.adapters.ImageAdapter
import ie.wit.nikeresell.R
import ie.wit.nikeresell.adapters.ImageAdapterPrevious
import kotlinx.android.synthetic.main.activity_previous_winners2.*
import kotlinx.android.synthetic.main.activity_shop.*

class PreviousWinners : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_winners2)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val adapter = ImageAdapterPrevious(this)
        viewPager.adapter = adapter
        val button = findViewById<Button>(R.id.pageButton3)
        infoButton.setOnClickListener {
            //    val popup = PopupMenu(this,shopButton2)
            //    popup.inflate(R.menu.test)
            //   popup.setOnMenuItemClickListener {
            //       Toast.makeText(this, "Item: " +it.title,Toast.LENGTH_SHORT).show()
            //        true
            //      }
            //     popup.show()

            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.layout_popup_info,null)
            window.contentView = view
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            imageView.setOnClickListener {
                window.dismiss()
            }
            window.showAsDropDown(infoButton)
        }
        button.setOnClickListener {
            val intent = Intent(this, PreviousWinnersPage2::class.java)
            startActivity(intent)
        }
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

    }
