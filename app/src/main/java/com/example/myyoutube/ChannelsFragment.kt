package com.example.myyoutube

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myyoutube.adapters.ChannelsAdapter
import com.example.myyoutube.adapters.VideosAdapter
import com.example.myyoutube.databinding.FragmentChannelsBinding
import com.example.myyoutube.viewModels.YoutubeAPIviewModel
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject


class ChannelsFragment : Fragment() {
    private var binding: FragmentChannelsBinding? = null
    private lateinit var mainViewModel: YoutubeAPIviewModel
    private lateinit var mAdapter : ChannelsAdapter
    private var myBundle: Parcelable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel = ViewModelProvider(requireActivity()).get(YoutubeAPIviewModel::class.java)
        binding= FragmentChannelsBinding.inflate(inflater, container, false)


        mAdapter= ChannelsAdapter{openChannelDetails(it)}
        setupRecyclerView()
        requestApiData()
        return binding?.root
    }

    private fun setupRecyclerView() {
        binding?.recyclerview?.adapter = mAdapter
        binding?.recyclerview?.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun requestApiData() {

        mainViewModel._data.observe(viewLifecycleOwner, { response->
            mainViewModel.getChannelsOnQsearch(response)
            showShimmerEffect()
            Log.i("ChannelFragmentreqAPIDa" , response )

        })

        mainViewModel._checkdata.observe(viewLifecycleOwner, { response->


            if(mainViewModel._checkdata.value==0){
                Toast.makeText(requireContext(), "NO INTERNET CONNECTON", Toast.LENGTH_SHORT).show()
            }

            if(mainViewModel._checkdata.value==2){
                Toast.makeText(requireContext(), "API RESPONSE ERROR", Toast.LENGTH_SHORT).show()
            }

            if(mainViewModel._checkdata.value==3){
                showErrorSnackBar("Click On Search Widget to Search Items")
            }

            hideShimmerEffect()

        })


        mainViewModel.channelsResponse.observe(viewLifecycleOwner, { response ->

            if(response == JSONObject.NULL) {

                hideShimmerEffect()
                Toast.makeText(
                    requireContext(),
                    " ERROR ",
                    Toast.LENGTH_LONG
                ).show()

            }else{
                hideShimmerEffect()
                mAdapter.submitList(response.body()?.items)

            }


        })
    }

    fun openChannelDetails(videoId: String) {

        val intent = Intent(getActivity(), ChannelDetailsActivity::class.java)
        intent.putExtra("CHANNELID", videoId)
        getActivity()?.startActivity(intent)

    }

    fun showErrorSnackBar(message:String){
        val snackBar : Snackbar = Snackbar.make(requireActivity().findViewById(android.R.id.content),message,
            Snackbar.LENGTH_SHORT)
        snackBar.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bg_color))
        snackBar.show()
    }

    private fun showShimmerEffect(){
        binding?.recyclerview?.showShimmer()
    }

    private fun hideShimmerEffect(){
        binding?.recyclerview?.hideShimmer()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}