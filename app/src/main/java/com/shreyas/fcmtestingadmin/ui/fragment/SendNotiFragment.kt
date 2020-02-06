package com.shreyas.fcmtestingadmin.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.shreyas.fcmtestingadmin.model.Model
import com.shreyas.fcmtestingadmin.Noti.NotiData
import com.shreyas.fcmtestingadmin.Noti.NotiNotifaction
import com.shreyas.fcmtestingadmin.Noti.NotifiactionModel
import com.shreyas.fcmtestingadmin.R
import com.shreyas.fcmtestingadmin.ui.activity.MainActivity
import com.shreyas.fcmtestingadmin.viewmodel.PostViewModel

/**
 * A simple [Fragment] subclass.
 */
class SendNotiFragment(
    private val frameContainer: FrameLayout,
    private val model: Model
) : Fragment() {

    var mIvClose:ImageView? = null
    var mTvName:TextView? = null
    var mBtnSend: Button? = null
    var mEtNotiTitle: EditText? = null
    var mEtNotiBody: EditText? = null
    var mEtDataCommand: EditText? = null

    lateinit var postViewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_send_noti, container, false)
        mIvClose = view.findViewById(R.id.iv_close)
        mTvName = view.findViewById(R.id.tv_frag_name)
        mBtnSend = view.findViewById(R.id.btn_send)

        postViewModel = ViewModelProvider(activity as MainActivity).get(PostViewModel::class.java)

        mEtNotiTitle = view.findViewById(R.id.et_frag_noti_tite)
        mEtNotiBody = view.findViewById(R.id.et_frag_noti_body)
        mEtDataCommand = view.findViewById(R.id.et_frag_data_command)

        mEtNotiTitle?.setText( "empty")
        mEtNotiBody?.setText( "empty")
        mEtDataCommand?.setText( "White")

        mIvClose?.setOnClickListener(View.OnClickListener {
           closeFrgmant()
        })

        mTvName?.setText(model.name)

        mBtnSend?.setOnClickListener(View.OnClickListener {
            sendNoti(model)
        })


        return view
    }

    private fun closeFrgmant() {
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        frameContainer.isClickable = false
    }

    private fun sendNoti(model: Model) {
        val postNoti = NotifiactionModel(
            model.token,
            "type-a",
            NotiNotifaction(
                mEtNotiTitle?.text.toString(),
                mEtNotiBody?.text.toString()
            ),
            NotiData(
                "hi this is data",
                mEtDataCommand?.text.toString()
            )
        )
        val result = postViewModel.postNoti(postNoti)?.success
        closeFrgmant()


    }


}
