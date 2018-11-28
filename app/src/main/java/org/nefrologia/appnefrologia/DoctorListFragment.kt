package org.nefrologia.appnefrologia


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_doctor_list.view.*
import org.nefrologia.appnefrologia.client.ApiService
import org.nefrologia.appnefrologia.client.Model
import org.nefrologia.appnefrologia.client.SessionService
import org.nefrologia.appnefrologia.recyclerview.DoctorViewAdapter
import org.nefrologia.appnefrologia.tools.ModelAdapter
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG_INFO = "DoctorListFragment:INFO"
private const val TAG_ERROR = "DoctorListFragment:ERR"

/**
 * A simple [Fragment] subclass.
 * Use the [DoctorListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DoctorListFragment : ModelAdapter.ClickListener<Model.Medico>,Fragment() {
    override fun onClick(item: Model.Medico, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChangeSelection(haveSelected: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCountSelection(count: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var disposable: Disposable? = null
    private var doctorList: MutableList<Model.Medico> = mutableListOf()
    private val doctorViewAdapter = DoctorViewAdapter(doctorList,this)
    private val apiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rv_medic.adapter = doctorViewAdapter
        view.rv_medic.layoutManager = LinearLayoutManager(context)
        view.rv_medic.itemAnimator = DefaultItemAnimator()
        view.rv_medic.setHasFixedSize(true)
        loadDoctorList(view)
    }

    private fun loadDoctorList(view: View) {
        disposable = apiService.medicoList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    result.datos!!
                    Log.d(TAG_INFO, "Nro de datos obtenidos: ${result.datos.size}")
                    doctorList.addAll(result.datos)
                    doctorViewAdapter.notifyDataSetChanged()
                },
                { error ->
                    Log.d(TAG_ERROR, "No se pudieron obtener los datos",error)
                }
            )
    }

    private fun reloadRecyclerView() {

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DoctorListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DoctorListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
