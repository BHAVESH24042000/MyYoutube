package com.example.myyoutube.authScreens

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.myyoutube.R
import com.example.myyoutube.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class LoginFragment : Fragment() {
    private val mFireStore = FirebaseFirestore.getInstance()
    private lateinit var  mProgressDialog : Dialog
    private var mBinding: FragmentLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProgressDialog = Dialog(requireContext(), R.style.myDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentLoginBinding.inflate(inflater, container, false)


        mBinding?.btnLogin?.setOnClickListener {
            loginRegisteredUser()
        }
        return mBinding?.root


    }


    private fun loginRegisteredUser() {
        if(validateUser()){
            showProgressDialog()
            val email=mBinding?.etEmail?.text.toString().trim {it<=' ' }
            val password=mBinding?.etPassword?.text.toString().trim {it<=' ' }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{task->
                    if(task.isSuccessful){

                        findNavController().navigate(R.id.action_loginFragment_to_resultActivity)

                        Toast.makeText(requireContext(), "LOGIN SUCESSFULL ", Toast.LENGTH_SHORT).show()
                        hideProgressBar()

                    }else{
                        hideProgressBar()
                        showErrorSnackBar(task.exception!!.message.toString())
                    }
                }
        }
    }

    private fun validateUser(): Boolean {
        return when{
            TextUtils.isEmpty(mBinding?.etEmail?.text.toString().trim {it<=' ' }) ->{
                showErrorSnackBar("Please Enter Your Email ID")
                false
            }
            TextUtils.isEmpty(mBinding?.etPassword?.text.toString().trim {it<=' ' }) ->{
                showErrorSnackBar("Please Enter Your Password")
                false
            }
            else ->{
                true
            }
        }
    }


    fun showProgressDialog(){

        mProgressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        mProgressDialog.setContentView(R.layout.dialog_progress)
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)
        mProgressDialog.show()
    }

    fun hideProgressBar(){
        mProgressDialog.dismiss()
    }

    fun showErrorSnackBar(message:String){
        val snackBar : Snackbar = Snackbar.make(requireActivity().findViewById(android.R.id.content),message,
            Snackbar.LENGTH_SHORT)
        snackBar.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bg_color))
        snackBar.show()
    }

}