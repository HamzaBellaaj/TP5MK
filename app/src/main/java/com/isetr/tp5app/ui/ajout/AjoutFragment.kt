package com.isetr.tp5app.ui.ajout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.isetr.tp5app.Model.Etudiant
import com.isetr.tp5app.R
import com.isetr.tp5app.databinding.FragmentAjoutBinding
import com.isetr.tp5app.viewModel.EtudiantViewModel

class AjoutFragment : Fragment() {

    private var _binding: FragmentAjoutBinding? = null
    private val etudiantViewModel: EtudiantViewModel by activityViewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAjoutBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.boutonAjouter.setOnClickListener {
            val id = binding.iden.text.toString()
            val email = binding.mail.text.toString()
            val classe = binding.classe.text.toString()
            val etud = Etudiant(id = id.toInt(), classe = classe, email = email, genre = "M")
            etudiantViewModel.addEtudiant(etud)
            Toast.makeText(requireContext(), "Etudiant ajouté avec succès", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_nav_ajout_to_nav_liste)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}