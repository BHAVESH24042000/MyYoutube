package com.example.myyoutube

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myyoutube.adapters.PagerAdapter
import com.example.myyoutube.authScreens.CheckUserActivity
import com.example.myyoutube.databinding.ActivityResultBinding
import com.example.myyoutube.viewModels.YoutubeAPIviewModel
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth


class ResultActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityResultBinding
    var resultBundle = Bundle()
    val fragments = ArrayList<Fragment>()
    val titles = ArrayList<String>()
    private lateinit var mainViewModel: YoutubeAPIviewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSupportActionBar()?.setTitle("My Youtube Application")
        mainViewModel = ViewModelProvider(this).get(YoutubeAPIviewModel::class.java)

        mainViewModel._checkdata.postValue( 3)
        fragments.add(VideosFragment())
        fragments.add(ChannelsFragment())
        titles.add("Videos")
        titles.add("Channels")

        setupViewPager(fragments, titles)
    }

    private fun setupViewPager(fragments : ArrayList<Fragment>, titles : ArrayList<String> ){

        var pagerAdapter = PagerAdapter(
            resultBundle,
            fragments,
            this
        )

        binding.viewPager2.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.youtubesearch_menu, menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_logout->{
                FirebaseAuth.getInstance().signOut()
                val intent= Intent(this, CheckUserActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                this.finish()
                Toast.makeText(this, "Logged Out Successfully", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query!=null){
            mainViewModel._data.value = query
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}