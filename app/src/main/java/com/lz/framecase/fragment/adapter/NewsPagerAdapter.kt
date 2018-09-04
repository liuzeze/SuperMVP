package com.lz.framecase.fragment.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 *-----------作者----------日期----------变更内容-----
 *-          刘泽      2018-08-29       创建class
 */
class NewsPagerAdapter(fm: FragmentManager?, var arrayList: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

    private var titleArray: ArrayList<String>? = null;
    override fun getItem(position: Int): Fragment = arrayList.get(position)

    override fun getCount(): Int = arrayList.size


    override fun getPageTitle(position: Int): CharSequence? {
        return titleArray?.get(position)
    }

    fun setTitleList(string: ArrayList<String>?) {
        titleArray = string;
    }


}