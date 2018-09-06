package com.lz.framecase.utils

import com.lz.framecase.logic.Constans
import com.vondear.rxtool.RxSPTool
import com.vondear.rxtool.RxTool

/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2018-09-06       创建class
 */
class SettingUtils {
    companion object {

        fun saveNightMode(isNight: Boolean) {
            RxSPTool.putBoolean(RxTool.getContext(), Constans.ISNIGHTMODE, isNight)
        }

        fun getNightMode(): Boolean {
            return RxSPTool.getBoolean(RxTool.getContext(), Constans.ISNIGHTMODE)
        }

        fun saveSlideBackMode(isNight: Boolean) {
            RxSPTool.putBoolean(RxTool.getContext(), Constans.SLIDBACKENABLE, isNight)
        }

        fun getSlideBackMode(): Boolean {
            return RxSPTool.getBoolean(RxTool.getContext(), Constans.SLIDBACKENABLE)
        }
    }
}