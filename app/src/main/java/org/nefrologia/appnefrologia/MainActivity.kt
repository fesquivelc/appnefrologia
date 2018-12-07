package org.nefrologia.appnefrologia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.nefrologia.appnefrologia.client.SessionService

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MenuFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Nefrologia"


//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()



        nav_view.setNavigationItemSelectedListener(this)
        val header = nav_view.getHeaderView(0)
        val tvUsername = header.findViewById<TextView>(R.id.tv_nav_login)
        tvUsername.text = SessionService.user?.nombreUsuario
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        var fragment: Fragment? = null
        when (item.itemId) {

            R.id.nav_doctor -> {
                fragment = DoctorListFragment.newInstance("","")
            }
            R.id.nav_medical_appointment -> {

            }
            R.id.nav_tests -> {
                val intent = Intent(this, AnalisisHistoryActivity::class.java)
                startActivity(intent)
            }
            else -> {
                fragment = MenuFragment.newInstance("", "")
            }
        }

        if (fragment != null) {
            fragmentManager(fragment_container, fragment)
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun fragmentManager(view: View, fragment: Fragment) {
        try {
//            val f = Class.forName(fragmentClass).newInstance() as Fragment
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(view.id, fragment)
            ft.commit()
        } catch (e: ClassNotFoundException) {
            Log.e(TAG, "level class not found", e)
        } catch (e: IllegalAccessException) {
            Log.e(TAG, "ilegal access exception", e)
        } catch (e: InstantiationException) {
            Log.e(TAG, "instantiation exception", e)
        }

    }
}
