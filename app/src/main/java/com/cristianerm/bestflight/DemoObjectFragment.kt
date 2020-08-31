package com.cristianerm.bestflight

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home_destinations.*


class DemoObjectFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home_destinations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {

            var tab_page = getInt(ARG_OBJECT).toString()
            val textView: TextView = view.findViewById(R.id.textView_home_destinations)

            if (tab_page == "1"){
                textView.text = getInt(ARG_OBJECT).toString()

                val homeList: ArrayList<HomeItem> = ArrayList()
                homeList.add(HomeItem("FLO X RIO", "14/07/2020", "20/07/2020"))
                homeList.add(HomeItem("CUR X SP", "01/07/2020", "05/07/2020"))
                homeList.add(HomeItem("RG X RIO", "04/07/2020", "28/07/2020"))

                recyclerView_home.setHasFixedSize(true)
                mLayoutManager = new LinearLayoutManager(this);
                val mAdapter = DemoObjectAdapter(homeList);
                recyclerView_home.setLayoutManager(mLayoutManager);
                recyclerView_home.setAdapter(mAdapter);

            }else{
                textView.text = "Teste"
            }


        }
    }
}