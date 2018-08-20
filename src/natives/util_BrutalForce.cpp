#include "util_BrutalForce.h"
#include <iostream>
using namespace std;

JNIEXPORT void JNICALL Java_util_BrutalForce_unhook
  (JNIEnv* env, jclass tclass) {
    cout << "unhook" << endl;
}

JNIEXPORT void JNICALL Java_util_BrutalForce_hook
  (JNIEnv* env, jclass tclass) {
    cout << "hook" << endl;
}