//
// Created by le on 16/7/31.
//
#include "com_example_administrator_test_MainActivity.h"
/*
 * Class:     com_jnimobile_www_myjnidemo_MainActivity
 * Method:    getStringFromNative
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_administrator_test_MainActivity_getStringFromNative
        (JNIEnv * env, jobject obj){
    return (*env)->NewStringUTF(env,"I'm comes from to Native Function!");
}
