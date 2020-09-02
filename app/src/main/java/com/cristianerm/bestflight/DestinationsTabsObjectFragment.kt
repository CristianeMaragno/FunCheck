package com.cristianerm.bestflight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_destinations.*

class DestinationsTabsObjectFragment : Fragment() {

    private lateinit var destinationsTabsRecyclerViewAdapter: DestinationsTabsRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_destinations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {

            var tab_page = getInt(ARG_OBJECT).toString()

            if (tab_page == "1"){

                initRecyclerView()
                addDataSet()

            }else{

                initRecyclerView()
                addDataSet()
            }


        }
    }

    private fun addDataSet(){
        val data = DestinationsDataSource.createDataSet()
        destinationsTabsRecyclerViewAdapter.submitList(data)
    }

    private fun initRecyclerView(){

        recyclerView_home.apply {
            layoutManager = LinearLayoutManager(context)
            destinationsTabsRecyclerViewAdapter = DestinationsTabsRecyclerViewAdapter()
            adapter = destinationsTabsRecyclerViewAdapter
        }
    }
}