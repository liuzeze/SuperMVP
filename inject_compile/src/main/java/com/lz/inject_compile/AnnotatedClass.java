package com.lz.inject_compile;


import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.lang.reflect.Constructor;
import java.util.List;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;


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


    public JavaFile generateActivityFile() throws ClassNotFoundException {
        // build inject method
        MethodSpec.Builder injectMethod = MethodSpec.methodBuilder(TypeUtil.METHOD_NAME)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(TypeName.get(mTypeElement.asType()), "obj", Modifier.FINAL);

        injectMethod.addStatement("obj.getObjectComponent().inject(obj);");
        //generaClas

        String packgeName = mElements.getPackageOf(mTypeElement).getQualifiedName().toString();
        String replace1 = mTypeElement.asType().toString().replace(packgeName + ".", "");
        TypeSpec injectClass = TypeSpec.classBuilder(replace1.replace(".","$") + "$$InjectUtils")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(injectMethod.build())
                .build();
        return JavaFile.builder(packgeName, injectClass).build();
    }

}
