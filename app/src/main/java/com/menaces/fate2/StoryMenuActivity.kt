package com.menaces.fate2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.menaces.fate2.adapter.ItemAdapter
import com.menaces.fate2.data.StoryList

class StoryMenuActivity : AppCompatActivity() {
    private lateinit var storyIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_menu)


        val myDataset = StoryList.stories

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = ItemAdapter(this, myDataset, onClick = ::adapterOnClick)

        // Specify fixed size to improve performance
        recyclerView.setHasFixedSize(true)

        // Enable up button for backward navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun adapterOnClick() {
        storyIntent = Intent(this, MainActivity::class.java)
        startActivity(storyIntent)
    }
}