package org.pcc.arch_mvvm.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import org.pcc.arch_mvvm.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(this,
            findNavController(this, R.id.main_navigation_frag)
        )
    }

    override fun onSupportNavigateUp() = findNavController(this, R.id.main_navigation_frag).navigateUp()
}
