package com.lz.framecase.bean

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-25       创建class
 */
class FaceListEntity {
    /**
     * face_token : 35235asfas21421fakghktyfdgh68bio
     * location : {"left":117,"top":131,"width":172,"height":170,"rotation":4}
     * face_probability : 1
     * angle : {"yaw":-0.34859421849251,"pitch":1.9135693311691,"roll":2.3033397197723}
     * landmark : [{"x":161.74819946289,"y":163.30244445801}]
     * landmark72 : [{"x":115.86531066895,"y":170.0546875}]
     * age : 29.298097610474
     * beauty : 55.128883361816
     * expression : {"type":"smile","probability":0.5543018579483}
     * gender : {"type":"male","probability":0.99979132413864}
     * glasses : {"type":"sun","probability":0.99999964237213}
     * race : {"type":"yellow","probability":0.99999976158142}
     * face_shape : {"type":"triangle","probability":0.5543018579483}
     * quality : {"occlusion":{"left_eye":0,"right_eye":0,"nose":0,"mouth":0,"left_cheek":0.0064102564938366,"right_cheek":0.0057411273010075,"chin":0},"blur":1.1886881756684E-10,"illumination":141,"completeness":1}
     */

    var face_token: String? = null
    var location: LocationEntity? = null
    var face_probability: Double =0.toDouble()
    var angle: AngleEntity? = null
    var age: Double = 0.toDouble()
    var beauty: Double = 0.toDouble()
    var expression: ExpressionEntity? = null
    var gender: GenderEntity? = null
    var glasses: GlassesEntity? = null
    var race: RaceEntity? = null
    var face_shape: FaceShapeEntity? = null
    var quality: QualityEntity? = null
    var landmark: List<LandmarkEntity>? = null
    var landmark72: List<Landmark72Entity>? = null
}
