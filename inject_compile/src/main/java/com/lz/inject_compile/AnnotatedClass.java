package com.lz.inject_compile;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.lang.reflect.Constructor;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;


public class AnnotatedClass {

    private TypeElement mTypeElement;//activity  //fragmemt
    private Elements mElements;
    private Messager mMessager;//日志打印
    private String mAnnotationPath;

    public AnnotatedClass(TypeElement typeElement, Elements elements, Messager messager, String annotationPath) {
        mTypeElement = typeElement;
        mElements = elements;
        this.mMessager = messager;
        mAnnotationPath = annotationPath;
    }


    public synchronized JavaFile generateActivityFile() throws ClassNotFoundException {

        // build inject method
        MethodSpec.Builder injectMethod = MethodSpec.methodBuilder(TypeUtil.METHOD_NAME)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(TypeName.get(mTypeElement.asType()), "obj", Modifier.FINAL);

        Constructor<?>[] constructors = mTypeElement.getClass().getConstructors();
        ClassName class1;
        if (mAnnotationPath.equals(TypeUtil.ANNOTATION_PATH_ACTIVITY)) {
            class1 = ClassName.get("com.lz.framecase.component", "DaggerActivityComponent");
        } else {
            class1 = ClassName.get("com.lz.framecase.component", "DaggerFragmentComponent");
        }
        ClassName class2 = ClassName.get("com.lz.fram.app", "FrameApplication");
        injectMethod.addStatement("$T.builder()\n" +
                "                .appComponent($T.mApplication.getAppComponent())\n" +
                "                .build()\n" +
                "                .inject(obj)", class1, class2);
        //generaClas

        String packgeName = mElements.getPackageOf(mTypeElement).getQualifiedName().toString();
        String replace1 = mTypeElement.asType().toString().replace(packgeName + ".", "");
        TypeSpec injectClass = TypeSpec.classBuilder(replace1.replace(".", "$") + "$$InjectUtils")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(injectMethod.build())
                .build();

        //   ClassName class2 = ClassName.get("com.lz.framecase.component", "DaggerActivityComponent");
        return JavaFile
                .builder(packgeName, injectClass)
                .build();
    }

}
