package com.lz.inject_compile;


import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;


/**
 * Created by spc on 17/6/6.
 */
public class AnnotatedClass {

    private TypeElement mTypeElement;//activity  //fragmemt
    private Elements mElements;
    private Messager mMessager;//日志打印

    public AnnotatedClass(TypeElement typeElement, Elements elements, Messager messager) {
        mTypeElement = typeElement;
        mElements = elements;
        this.mMessager = messager;
    }


    public JavaFile generateActivityFile() {
        // build inject method
        MethodSpec.Builder injectMethod = MethodSpec.methodBuilder(TypeUtil.METHOD_NAME)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(TypeName.get(mTypeElement.asType()), "activity", Modifier.FINAL);

        injectMethod.addStatement("activity.getActivityComponent().inject(activity);");
        //generaClass
        TypeSpec injectClass = TypeSpec.classBuilder(mTypeElement.getSimpleName() + "$$InjectUtils")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(injectMethod.build())
                .build();
        String packgeName = mElements.getPackageOf(mTypeElement).getQualifiedName().toString();
        return JavaFile.builder(packgeName, injectClass).build();
    }

}
