package com.lz.framecase.activity

import android.graphics.Bitmap
import android.view.MotionEvent
import com.lz.framecase.R
import com.lz.framecase.base.NewsBaseActivity
import com.lz.framecase.bean.FaceListEntity
import kotlinx.android.synthetic.main.activity_face_detail.*


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
class FaceDetailActivity : NewsBaseActivity() {


    override fun getLayout(): Int {
        return R.layout.activity_face_detail
    }

    override fun initViewData() {
        val faceListEntity = intent.getSerializableExtra("mFacesBean") as FaceListEntity
        val bitmap = intent.extras.getParcelable("bitmap") as Bitmap
        imageView.setImageBitmap(bitmap)
        displayFaceInfo(faceListEntity)
    }


    internal fun displayFaceInfo(attributes: FaceListEntity) {

        var s = ""
        val agenum = attributes.age
        val gender = attributes.gender?.type
        val beautynum = attributes.beauty
        age.setText(String.format("年龄：%s", agenum))
        sex.setText(String.format("性别：%s", if ("Male" == gender) "男" else "女"))
        beauty.setText(String.format("%1.2f", beautynum))

        val type = attributes.expression?.type
        s = StringBuilder()
                .append(type + "(none:不笑；smile:微笑；laugh:大笑)\n")
                .append(attributes.glasses?.type + "(none:无眼镜，common:普通眼镜，sun:墨镜)")

                .toString()
        emotion.setText(s)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        onBackPressed()
        return true
    }
}
