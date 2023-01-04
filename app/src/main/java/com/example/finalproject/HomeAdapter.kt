package com.example.finalproject


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ContactComponentLayoutBinding


class HomeAdapter(private val contacts : ObservableArrayList<Contact>, val onContactClicked: (Contact) -> Unit): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(val binding: ContactComponentLayoutBinding): RecyclerView.ViewHolder(binding.root)
    private var context: Context? = null

    init{
        contacts.addOnListChangedCallback(object: ObservableList.OnListChangedCallback<ObservableArrayList<Contact>>(){
            override fun onChanged(sender: ObservableArrayList<Contact>?) {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(
                sender: ObservableArrayList<Contact>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemChanged(positionStart)
            }

            override fun onItemRangeInserted(
                sender: ObservableArrayList<Contact>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemInserted(positionStart)
            }

            override fun onItemRangeMoved(
                sender: ObservableArrayList<Contact>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                notifyItemMoved(fromPosition, toPosition)
            }

            override fun onItemRangeRemoved(
                sender: ObservableArrayList<Contact>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRemoved(positionStart)
            }

        })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curContact = contacts[position]
        val viewModel = ContactsViewModel()
        holder.binding.recyclerName.text = curContact.firstName + " " + curContact.lastName

        holder.binding.recyclerItem.setOnClickListener{
            onContactClicked(curContact)
        }

        holder.binding.deleteButton.setOnClickListener{
            val confirm = AlertDialog.Builder(context)
            confirm.setMessage("Are you sure you want to delete?")
            confirm.setCancelable(true)

            confirm.setPositiveButton("Yes") {
                dialog, id -> viewModel.deleteContact(curContact)
                notifyItemRemoved(position)
            }
            confirm.setNegativeButton("No") {
                dialog, id -> dialog.cancel()
            }

            val showConfirm = confirm.create()
            showConfirm.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContactComponentLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}