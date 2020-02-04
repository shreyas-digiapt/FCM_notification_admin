package com.shreyas.fcmtestingadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickItem {

    lateinit var adapter: AdminAdpater
    lateinit var viewmodel:FireViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel = ViewModelProvider(this).get(FireViewmodel::class.java)
        adapter = AdminAdpater(this)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter


    }

    override fun onResume() {
        super.onResume()

        observeData()
    }

    private fun observeData() {
        viewmodel.fetchUsers()
        Log.d("test5", "sads: ")
        viewmodel._users.observe(this, Observer { model->
//            Log.d("test5", "sads: ${model.get(0).name}")
            Log.d("oioi","sda: ${model.size}")
            if (model.size < 1) {
                tv_empty.visibility = View.VISIBLE
            }else {
                tv_empty.visibility = View.GONE
            }
            adapter.setData(model.toMutableList())
            adapter.notifyDataSetChanged()
        })
    }

    override fun onclick(model: Model) {
        Toast.makeText(this, model.name, Toast.LENGTH_SHORT).show()
        frame_container.isClickable = true
        val fragTran = supportFragmentManager.beginTransaction()
        fragTran.add(R.id.frame_container, SendNotiFragment(frame_container, model))
        fragTran.commit()
    }

//    private fun observeData() {
//        viewmodel.fetchUsers().observe(this, Observer { it->
//            adapter.setData(it)
//            adapter.notifyDataSetChanged()
//        })
//    }
}
