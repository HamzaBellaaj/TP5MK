package com.isetr.tp5app.ui.liste

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.isetr.tp5app.R
import com.isetr.tp5app.databinding.FragmentListeBinding
import com.isetr.tp5app.viewModel.EtudiantViewModel

class ListeFragment : Fragment() {

    private val etudiantViewModel: EtudiantViewModel by activityViewModels()
    private var _binding: FragmentListeBinding? = null
    private val binding get() = _binding!!

    private lateinit var etudiantAdapter: EtudAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillRecyclerView()
        etudiantViewModel.etudiants.observe(viewLifecycleOwner) { nouvelleListe ->
            etudiantAdapter.updateList(nouvelleListe)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fillRecyclerView() {
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        etudiantAdapter = EtudAdapter(
            etudiants = mutableListOf(),
            onItemClicked = { etudiant ->
                etudiantViewModel.selectEtudiant(etudiant)
                findNavController().navigate(R.id.action_nav_liste_to_nav_details)
            },
        )
        recyclerView.adapter = etudiantAdapter
    }
}
