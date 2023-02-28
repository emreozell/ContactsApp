package com.emreozel.contactbookapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.emreozel.contactbookapp.R
import com.emreozel.contactbookapp.data.entity.Kisiler
import com.emreozel.contactbookapp.databinding.FragmentAnasayfaBinding
import com.emreozel.contactbookapp.ui.adapter.KisilerAdapter
import com.emreozel.contactbookapp.ui.viewmodel.AnasayfaViewModel
import com.emreozel.contactbookapp.ui.viewmodel.KisiKayitViewModel
import com.emreozel.contactbookapp.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() ,SearchView.OnQueryTextListener{

    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: AnasayfaViewModel by viewModels()
        viewModel=tempViewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)
        binding.anaSayfaFragment=this
        binding.anasayfaToolBarBaslik="Kişiler"


        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarAnasayfa)

        viewModel.kisilerListesi.observe(viewLifecycleOwner){
            val adapter=KisilerAdapter(requireContext(),it,viewModel)
            binding.kisilerAdapter=adapter
        }


    requireActivity().addMenuProvider(object :MenuProvider{
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.toolbar_menu,menu)

            val item=menu.findItem(R.id.action_ara)
            val searchView=item.actionView as SearchView
            searchView.setOnQueryTextListener(this@AnasayfaFragment)

        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return  false
        }
    },viewLifecycleOwner,Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onQueryTextSubmit(query: String): Boolean {
       viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(query: String): Boolean {
        viewModel.ara(query)
        return true
    }

    fun fubTikla(it:View){
        Navigation.gecisYap(R.id.kisiKayitGecis,it)
    }

    override fun onResume() {
        super.onResume()
        viewModel.kisileriYukle()
    }

}