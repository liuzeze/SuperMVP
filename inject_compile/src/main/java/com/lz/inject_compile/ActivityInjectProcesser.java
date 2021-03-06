package com.lz.inject_compile;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;


@AutoService(Processor.class)
public class ActivityInjectProcesser extends AbstractProcessor {
    //文件相关的辅助类
    private Filer mFiler;
    //元素相关的辅助类  许多元素
    private Elements mElementUtils;
    //日志相关的辅助类
    private Messager mMessager;

    private Map<String, AnnotatedClass> mAnnotatedClassMap;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        mAnnotatedClassMap = new TreeMap<>();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mAnnotatedClassMap.clear();

        try {
            processActivityCheck(roundEnv);
        } catch (Exception e) {
            e.printStackTrace();
            error(e.getMessage());
        }
        TypeSpec.Builder builder = TypeSpec.classBuilder(TypeUtil.CLASSNAME)
                .addModifiers(Modifier.PUBLIC);
        for (AnnotatedClass annotatedClass : mAnnotatedClassMap.values()) {
            try {
                builder.addMethod(annotatedClass.generateMethod());
            } catch (Exception e) {
                error("Generate file failed, reason: %s", e.getMessage());
            }
        }

        JavaFile javaFile = JavaFile
                .builder(TypeUtil.PACKAGEPATH, builder.build())
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    private void processActivityCheck(RoundEnvironment roundEnv) throws IllegalArgumentException, ClassNotFoundException {
        //check ruleslass forName(String className
        for (Element element : roundEnv.getElementsAnnotatedWith((Class<? extends Annotation>) Class.forName(TypeUtil.ANNOTATION_PATH_ACTIVITY))) {
            if (element.getKind() == ElementKind.CLASS) {
                getAnnotatedClass(element, TypeUtil.ANNOTATION_PATH_ACTIVITY);
            } else
                error("ActivityInject only can use  in ElementKind.CLASS");
        }
        for (Element element : roundEnv.getElementsAnnotatedWith((Class<? extends Annotation>) Class.forName(TypeUtil.ANNOTATION_PATH_FRAGMENT))) {
            if (element.getKind() == ElementKind.CLASS) {
                getAnnotatedClass(element, TypeUtil.ANNOTATION_PATH_FRAGMENT);
            } else
                error("ActivityInject only can use  in ElementKind.CLASS");
        }
    }

    private AnnotatedClass getAnnotatedClass(Element element, String annotationPath) {
        // tipe . can not use chines  so  ....
        // get TypeElement  element is class's --->class  TypeElement typeElement = (TypeElement) element
        //  get TypeElement  element is method's ---> TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        TypeElement typeElement = (TypeElement) element;
        String fullName = typeElement.getQualifiedName().toString();
        AnnotatedClass annotatedClass = mAnnotatedClassMap.get(fullName);
        if (annotatedClass == null) {
            annotatedClass = new AnnotatedClass(typeElement, mElementUtils, mMessager, annotationPath);
            mAnnotatedClassMap.put(fullName, annotatedClass);
        }
        return annotatedClass;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(TypeUtil.ANNOTATION_PATH_ACTIVITY);
        types.add(TypeUtil.ANNOTATION_PATH_FRAGMENT);
        return types;
    }

    private void error(String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args));
    }

    private void log(String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));
    }
}
