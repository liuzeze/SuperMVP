package com.lz.framecase.activity.adapter


import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lz.framecase.R
import com.lz.framecase.bean.FaceListEntity

/**
 * -------- ���� ---------- ά���� ------------ ������� --------
 */
class FaceAdapter(faces: List<FaceListEntity>?)
    : BaseQuickAdapter<FaceListEntity, BaseViewHolder>(R.layout.item_face, faces) {
    var bitmap: Bitmap? = null


    override fun convert(helper: BaseViewHolder, item: FaceListEntity) {

        var ivFace = helper.getView<ImageView>(R.id.face)
        var tvSex = helper.getView<TextView>(R.id.sex)
        var tvAge = helper.getView<TextView>(R.id.age)
        var tvBeauty = helper.getView<TextView>(R.id.beauty)
        var tvBeautyTip = helper.getView<TextView>(R.id.tv_beauty)

        if (item == null || item.gender == null) {
            ivFace.setImageBitmap(null)
            tvSex.setText("")
            tvAge.setText("")
            tvBeauty.setText("")
            tvBeautyTip.setText("")
            return
        }
        val faceRectangle = item.location
        //将人脸从照片中抠出，并加上一定的 padding
        val left = faceRectangle?.left?.toInt()!!
        val top = faceRectangle?.top?.toInt()!!
        val width = faceRectangle?.width?.toInt()!!
        val height = faceRectangle?.height?.toInt()!!
        val widthPadding = width / 5
        val heightPadding = height / 5
        val x = if (left - widthPadding < 0) 0 else (left - widthPadding)
        val y = if (top - heightPadding < 0) 0 else (top - heightPadding)
        //防止增加 padding 后，抠图范围超过原来的照片
        try {
            val _width = if (x + width + widthPadding > bitmap?.getWidth()!!) bitmap?.getWidth()!! - x else width + widthPadding
            val _height = if (y + height + heightPadding > bitmap?.getHeight()!!) bitmap?.getHeight()!! - y else height + heightPadding
            val bitmap = Bitmap.createBitmap(bitmap, x, y, _width, _height)
            Glide.with(mContext).load(bitmap).into(ivFace)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val sex = item?.gender?.type
        tvSex.setText(String.format("性别：%s", if ("male" == sex) "男" else "女"))
        tvAge.setText(String.format("年龄：%s", item?.age))
        tvBeautyTip?.setText("颜值")
        val maleScore = item?.beauty
        tvBeauty.setText(String.format("%1.2f", maleScore))

    }
}
