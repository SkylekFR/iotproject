package com.example.iotproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iotproject.model.Wine
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * A fragment representing a list of Items.
 */
class WineFragment : Fragment() {

    lateinit var addBottleButton: Button
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: MyWineRecyclerViewAdapter
    lateinit var layoutManager: LinearLayoutManager
    var wineList = arrayListOf<Wine>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wine, container, false)
    }

    companion object {

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() =
            WineFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reference = FirebaseDatabase.getInstance().reference

        reference.child("/cave/wines/").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapsho: DataSnapshot) {
                wineList.clear()
                snapsho.children.forEach { snapshot ->
                    wineList.add(snapshot.getValue(Wine::class.java) as Wine)
                }
                recyclerViewAdapter.setData(wineList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        addBottleButton = view.findViewById(R.id.wine_add_bottle_button)
        layoutManager = LinearLayoutManager(context)
        recyclerViewAdapter = MyWineRecyclerViewAdapter()
        recyclerView = view.findViewById(R.id.wine_recyclerview)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerViewAdapter


        addBottleButton.setOnClickListener {
            WineAddDialogFragment.newInstance().show(parentFragmentManager, "wine_add_dialog")
        }

        // Set the adapter

    }
}