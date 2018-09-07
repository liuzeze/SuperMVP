package com.lz.framecase.presenter


import com.lz.fram.base.BasePresenter
import com.lz.fram.base.BaseView

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 */
interface EmptyContract {
    interface View : BaseView
    interface Presenter : BasePresenter<View>
}
