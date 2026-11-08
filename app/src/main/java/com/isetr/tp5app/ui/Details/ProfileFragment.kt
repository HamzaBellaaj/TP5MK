package com.isetr.tp5app.ui.Details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.isetr.tp5app.R
import com.isetr.tp5app.databinding.FragmentProfileBinding
import com.isetr.tp5app.viewModel.EtudiantViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val etudiantViewModel: EtudiantViewModel by activityViewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etudiantViewModel.setudiants.observe(viewLifecycleOwner) { etudiant ->
            if (etudiant != null) {
                binding.tvId.text = etudiant.id.toString()
                binding.tvEmail.text = etudiant.email
                binding.tvClasse.text = etudiant.classe

                if (etudiant.genre.equals("F", ignoreCase = true)) {
                    binding.ivProfileAvatar.setImageResource(R.drawable.ic_girl)
                } else {
                    binding.ivProfileAvatar.setImageResource(R.drawable.ic_boy)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
