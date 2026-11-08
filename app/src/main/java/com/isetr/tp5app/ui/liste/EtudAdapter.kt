package com.isetr.tp5app.ui.liste

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.isetr.tp5app.Model.Etudiant
import com.isetr.tp5app.R
import com.isetr.tp5app.databinding.ItemEtudBinding

class EtudAdapter(
    private val etudiants: MutableList<Etudiant>,
    private val onItemClicked: (Etudiant) -> Unit,
    private val onItemlongClicked: (Etudiant) -> Boolean = { false }
) : RecyclerView.Adapter<EtudAdapter.EtudiantViewHolder>() {

    inner class EtudiantViewHolder(private val binding: ItemEtudBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(etudiant: Etudiant) {
            binding.tvId.text = "ID : ${etudiant.id}"
            binding.tvMail.text = "Mail : ${etudiant.email}"
            binding.tvClasse.text = "Classe : ${etudiant.classe}"

            // Avatar and background color based on gender
            if (etudiant.genre.equals("F", ignoreCase = true)) {
                binding.ivAvatar.setImageResource(R.drawable.ic_girl)
                binding.layoutContainer.setBackgroundColor(
                    ContextCompat.getColor(binding.root.context, R.color.girl_bg)
                )
            } else {
                binding.ivAvatar.setImageResource(R.drawable.ic_boy)
                binding.layoutContainer.setBackgroundColor(
                    ContextCompat.getColor(binding.root.context, R.color.boy_bg)
                )
            }

            binding.root.setOnClickListener {
                onItemClicked(etudiant)
            }

            binding.root.setOnLongClickListener {
                onItemlongClicked(etudiant)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EtudiantViewHolder {
        val binding = ItemEtudBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EtudiantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EtudiantViewHolder, position: Int) {
        holder.bind(etudiants[position])
    }

    override fun getItemCount() = etudiants.size

    fun trierLesDonnees(actionType: String) {
        when (actionType) {
            "SORT_NAME" -> {
                etudiants.sortBy { e -> e.id }
            }
            "SORT_CLASS" -> {
                etudiants.sortBy { e -> e.classe }
            }
        }
        notifyDataSetChanged()
    }

    fun deleteEtudiant(etudiant: Etudiant) {
        val index = etudiants.indexOf(etudiant)
        if (index != -1) {
            etudiants.removeAt(index)
            notifyItemRemoved(index)
        }
    }
    fun updateList(newList: MutableList<Etudiant>) {
        etudiants.clear()
        etudiants.addAll(newList)
        notifyDataSetChanged()
    }
}
