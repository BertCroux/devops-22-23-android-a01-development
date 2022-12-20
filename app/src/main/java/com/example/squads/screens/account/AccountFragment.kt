package com.example.squads.screens.account
import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.squads.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {
    lateinit var binding: FragmentAccountBinding
    lateinit var accountViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, com.example.squads.R.layout.fragment_account, container, false
        )

        //get the viewmodel
        val vm: AccountViewModel by lazy {
            val activity = requireNotNull(this.activity)
            ViewModelProvider(this, AccountViewModel.Factory(activity.application)).get(AccountViewModel::class.java)
        }

        //set the viewmodel
        accountViewModel = vm


        //implements the live data
        binding.lifecycleOwner = this

        //add observer to the live data
        addObservers()
        //set the click listeners for the buttons
        setupButtons()

        return binding.root
    }

    private fun addObservers() {
        //observer for the latest measurement

        Log.d("test", accountViewModel.repository.account.value.toString())
        accountViewModel.repository.account.observe(viewLifecycleOwner, Observer { acc ->
            //if the account changes, all must be updated
            Log.d("test", acc.toString())
            acc.let {
                binding.name.text = String.format("%s %s", it?.firstName, it?.lastName)
                binding.userId.text = String.format("User-ID: %d", it?.userId)
                binding.email.text = it?.email
                binding.phonenumber.text = it?.phoneNumber
                binding.address.text = String.format(
                    "%s %s %s %s",
                    it?.address?.addressLine1,
                    it?.address?.addressLine2,
                    it?.address?.zipCode,
                    it?.address?.city
                )
                binding.lengte.text = String.format("%dcm", it?.lengthInCm)
                binding.issues.text = it?.physicalIssues
                binding.drugs.text = it?.drugsUsed
                binding.drugs.text = it?.drugsUsed
            }
        })
    }

    private fun setupButtons() {
        binding.apply {
            persdtcard.setOnClickListener {
                changeVisibility(persdtcarddropdown, personalDetails)
            }
            membershipcard.setOnClickListener {
                changeVisibility(membershipcarddropdown, membership)
            }
            privacycard.setOnClickListener {
                changeVisibility(privacycarddropdown, privacyPolicy)
            }
            apptourcard.setOnClickListener {
                changeVisibility(apptourcarddropdown, appTour)
            }
            webpage.setOnClickListener {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse("https://www.squads.training/")
                startActivity(openURL)
            }
        }

    }

    private fun changeVisibility(view: View, textview: TextView) {
        if (view.isVisible) {
            view.visibility = View.GONE
            textview.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down_float, 0)
        } else {
            view.visibility = View.VISIBLE
            textview.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up_float, 0)
        }
    }



}