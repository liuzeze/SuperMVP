package com.lz.framecase.bean

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-25       创建class
 */
class QualityEntity {
    /**
     * occlusion : {"left_eye":0,"right_eye":0,"nose":0,"mouth":0,"left_cheek":0.0064102564938366,"right_cheek":0.0057411273010075,"chin":0}
     * blur : 1.1886881756684E-10
     * illumination : 141
     * completeness : 1
     */

    var occlusion: OcclusionEntity? = null
    var blur: Double = 0.toDouble()
    var illumination: Int = 0
    var completeness: Int = 0
}
